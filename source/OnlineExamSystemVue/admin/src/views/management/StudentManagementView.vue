<template>
  <div id="examManager">
    <el-row>
      <el-col :span="24">
        <el-card>
          <div slot="header" class="clearfix">
            <div>
              <el-button @click="getStudentList()" class="dalfBut">查询</el-button>
              <el-button type="primary">新建</el-button>
            </div>
          </div>
          <el-table :data="studentList" style="width: 100%">
            <el-table-column label="姓名" prop="realName">
            </el-table-column>
            <el-table-column label="操作" width="180">
              <template v-slot="scope">
                <el-button type="primary" size="small">编辑</el-button>
                <el-button type="danger" size="small">删除</el-button>
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
  name: "StudentManagementView.vue",
  data() {
    return {
      studentList: [],
      pagination: {
        currentPage: 1,
        pageSize: 7,
        total: 0,
      },
    }
  },
  created() {
    this.getStudentList();
  },
  methods: {
    getStudentList(){
      this.$axios.get("http://localhost:80/students/" + this.pagination.currentPage + "/" + this.pagination.pageSize)
          .then((res)=>{
            this.studentList = res.data.data.records;
            this.pagination.total = res.data.data.total;
            this.pagination.currentPage = res.data.data.current;
            this.pagination.pageSize = res.data.data.size;
      })
    },
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage; //更新当前页码
      this.getStudentList(); //重新查询
    }
  }
}
</script>

<style scoped>

</style>