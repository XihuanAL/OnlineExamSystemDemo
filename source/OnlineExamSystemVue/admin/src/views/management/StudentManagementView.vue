<template>
  <div id="examManager">
    <el-row>
      <el-col :span="24">
        <el-card>
          <div slot="header" class="clearfix">
            <div>
              <el-button @click="getStudentList()" class="dalfBut">查询</el-button>
              <el-button type="primary" @click="this.dialogFormVisible = true; this.formData= {}">新建</el-button>
            </div>
          </div>
          <el-table :data="studentList" style="width: 100%">
            <el-table-column label="姓名" prop="realName">
            </el-table-column>
            <el-table-column label="操作" width="180">
              <template v-slot="scope">
                <el-button type="primary" size="small">编辑</el-button>
                <el-button type="danger" size="small" @click="deleteStudent(scope.row)">删除</el-button>
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
          <div>
            <el-dialog title="新增" v-model="dialogFormVisible">
              <el-form ref="dataEditForm" :model="formData" :rules="rules" label-position="right"
                       label-width="100px">
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="用户名" prop="username">
                      <el-input v-model="formData.username"/>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="真实姓名" prop="realName">
                      <el-input v-model="formData.realName"/>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-form>
              <div>
                <el-button @click="cancel()">取消</el-button>
                <el-button type="primary" @click="createStudent()">确定</el-button>
              </div>
            </el-dialog>
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
      formData:{
        username: '',
        realName: '',
      },
      dialogFormVisible: false,
      studentList: [],
      pagination: {
        currentPage: 1,
        pageSize: 7,
        total: 0,
      },
      rules: {
        username: [{required: true, message: '年级为必填项', trigger: 'blur'}],
        realName: [{required: true, message: '学科为必填项', trigger: 'blur'}],
      }
    }
  },
  created() {
    this.getStudentList();
  },
  methods: {
    cancel(){
      this.dialogFormVisible = false;
    },
    getStudentList(){
      this.$axios.get("/users/" + this.pagination.currentPage + "/" + this.pagination.pageSize)
          .then((res)=>{
            this.studentList = res.data.data.records;
            this.pagination.total = res.data.data.total;
            this.pagination.currentPage = res.data.data.current;
            this.pagination.pageSize = res.data.data.size;
      })
    },
    deleteStudent(row){

      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.delete("/users/" + row.id)
            .then((res)=>{
              this.getStudentList();
            })
      }).catch(() => {
      });

    },
    createStudent(){
      this.$axios.post("/users", this.formData)
          .then((res)=>{
            this.dialogFormVisible = false;
            this.getStudentList();
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