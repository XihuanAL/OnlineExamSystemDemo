<template>
  <div id="examManager">
    <el-row>
      <el-col :span="24">
        <el-card>
          <div slot="header" class="clearfix">
            <div>
              <!--              条件查询-->
              <!--              <el-select v-model="pagination.gradeName" placeholder="年级">-->
              <!--                <el-option-->
              <!--                    v-for="item in optionsGrade"-->
              <!--                    :key="item.gradeName"-->
              <!--                    :label="item.gradeName"-->
              <!--                    :value="item.gradeName">-->
              <!--                </el-option>-->
              <!--              </el-select>-->
              <!--              <el-select v-model="pagination.subjectName" placeholder="学科">-->
              <!--                <el-option-->
              <!--                    v-for="item in optionsSubject"-->
              <!--                    :key="item.subjectName"-->
              <!--                    :label="item.subjectName"-->
              <!--                    :value="item.subjectName">-->
              <!--                </el-option>-->
              <!--              </el-select>-->
              <!--              <el-input placeholder="试卷名" v-model="pagination.examName"-->
              <!--                        style="width: 200px;"></el-input>-->
              <el-button @click="getExamList()" class="dalfBut">查询</el-button>
              <el-button type="primary" @click="this.$router.push('/exam/add')">新建</el-button>
            </div>
          </div>
          <el-table :data="examList" style="width: 100%">
            <el-table-column label="年级" width="100" prop="gradeName">
            </el-table-column>
            <el-table-column label="学科" width="100" prop="subjectName">
            </el-table-column>
            <el-table-column label="试卷名" prop="examName">
            </el-table-column>
            <el-table-column label="操作" width="180">
              <template v-slot="scope">
                <el-button type="primary" size="small" @click="editExam(scope.row)">编辑</el-button>
                <el-button type="danger" size="small" @click="del(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <!--                    分页组件-->
          <div class="pagination-container">
            <el-pagination
                background
                class="pagiantion"
                @current-change="handleCurrentChange"
                :current-page="pagination.currentPage"
                :page-size="pagination.pageSize"
                layout="total, prev, pager, next, jumper"
                style="float: right;"
                :total="pagination.total">
            </el-pagination>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>

export default {
  name: "ExamManagementView.vue",
  data() {
    return {
      pagination: {
        currentPage: 1,
        pageSize: 7,
        total: 0,
        gradeName: '',
        subjectName: '',
        examName: '',
      },
      optionsGrade: [],
      optionsSubject: [],
      examList: [],
    }
  },
  created() {
    this.getExamList();
  },
  methods: {
    getExamList() {
      console.log("getExamList");
      this.$axios.get("http://localhost:80/exams/" + this.pagination.currentPage + "/" + this.pagination.pageSize).then((res) => {
        this.examList = res.data.data.records;
        console.log(this.examList);
        this.pagination.total = res.data.data.total;
        this.pagination.currentPage = res.data.data.current;
        this.pagination.pageSize = res.data.data.size;
      })
    },
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage; //更新当前页码
      this.getExamList(); //重新查询
    },
    del(row) {
      this.$confirm('此操作将永久删除该试卷, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.delete("http://localhost:80/exams/" + row.id).then((res) => {
          this.getExamList();
        });
      }).catch(() => {
      });
    },
    editExam(row) {
      console.log("row");
      console.log(row);
      this.$router.push({
        name:"examEdit",
        params: {
          formData: JSON.stringify(row)
        }
      });
    },
  }
}
</script>

<style scoped>

</style>