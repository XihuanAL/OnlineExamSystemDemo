<template>
  <div id="examManager">
    <el-row>
      <el-col :span="24">
        <el-card>
          <el-table :data="examList" style="width: 100%">
            <el-table-column label="年级" width="100" prop="gradeName">
            </el-table-column>
            <el-table-column label="学科" width="100" prop="subjectName">
            </el-table-column>
            <el-table-column label="试卷名" prop="examName">
            </el-table-column>
            <el-table-column label="学生名" prop="createrName">
            </el-table-column>
            <el-table-column label="提交时间" prop="createTime">
            </el-table-column>
            <el-table-column label="得分" prop="userScore">
            </el-table-column>
            <el-table-column label="满分" prop="paperScore">
            </el-table-column>
            <el-table-column label="操作" width="180">
              <template v-slot="scope">
                <el-button type="primary" size="small" @click="checkExam(scope.row)">查看</el-button>
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
  name: "ExamPaperManagementView",
  data() {
    return {
      examList: [],
      pagination: {
        currentPage: 1,
        pageSize: 7,
        total: 0,
        gradeName: '',
        subjectName: '',
        examName: '',
      },
    };
  },
  created() {
    this.getExamList();
  },
  methods: {
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage;
      this.getExamList();
    },
    getExamList() {
      this.$axios.get("/admin/exampapers/" + this.pagination.currentPage + "/" + this.pagination.pageSize).then((res) => {
        this.examList = res.data.data.records;
        console.log(this.examList);
        this.pagination.total = res.data.data.total;
        this.pagination.currentPage = res.data.data.current;
        this.pagination.pageSize = res.data.data.size;
      })
    },
    checkExam(row) {
      this.$router.push({
        name:"exampaperanswer",
        params: {
          formData: JSON.stringify(row),
        }
      })
    },
  }
}
</script>

<style scoped>

</style>