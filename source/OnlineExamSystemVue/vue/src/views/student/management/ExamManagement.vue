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
<!--              <el-button @click="getExamList()" class="dalfBut">查询</el-button>-->
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
                <el-button v-if="!this.doneExam.get(scope.row.id)" type="primary" size="small" @click="doExam(scope.row)">考试</el-button>
                <el-button v-if="this.doneExam.get(scope.row.id)" type="success" size="small" @click="checkExam(scope.row)">查看</el-button>
<!--                <el-button v-if="doneExam(scope.row)" type="success" size="small" >查看</el-button>-->
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
  name: "ExamManagement.vue",
  data() {
    return {
      doneExam : {},
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
    this.doneExam = new Map();
    this.getExamList();
  },
  methods: {
    getExamList() {
      //console.log("getExamList");
      this.$axios.get("/student/exams/" + this.pagination.currentPage + "/" + this.pagination.pageSize).then((res) => {
        this.examList = res.data.data.records;
        for(let i = 0; i < this.examList.length; i++){
          this.$axios.get("/student/exams/done/" + this.examList[i].id).then((res) => {
            this.doneExam.set(this.examList[i].id, res.data.data === true);
          })
        }
        this.pagination.total = res.data.data.total;
        this.pagination.currentPage = res.data.data.current;
        this.pagination.pageSize = res.data.data.size;
      })

    },
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage; //更新当前页码
      this.getExamList(); //重新查询
    },
    doExam(row){
      this.$router.push({
        name:"examPaper",
        params: {
          formData: JSON.stringify(row)
        }
      });
    },
    checkExam(row){
      this.$router.push({
        name:"examPaperDone",
        params: {
          formData: JSON.stringify(row),
        }
      });
    }
  }
}
</script>

<style scoped>

</style>