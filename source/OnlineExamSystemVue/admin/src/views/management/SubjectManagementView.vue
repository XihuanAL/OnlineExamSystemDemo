<template>
  <div>
    <el-card>
      <el-input placeholder="年级" v-model="pagination.gradeName" style="width: 200px;"></el-input>
      <el-input placeholder="学科" v-model="pagination.subjectName" style="width: 200px;"></el-input>
      <el-button @click="getSubjectList()">查询</el-button>
      <el-button @click="createSubjectIsClick()" type="primary">新建</el-button>

      <el-table :data="subjectList" style="width: 100%">
        <el-table-column label="年级" width="100" prop="gradeName">
        </el-table-column>
        <el-table-column label="学科" prop="subjectName">
        </el-table-column>
        <el-table-column label="操作" width="180">
          <template v-slot="scope">
            <el-button type="primary" size="small" @click="editSubject(scope.row)">编辑</el-button>
            <el-button type="danger" size="small" @click="delSubject(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--                          分页组件-->
      <el-pagination
          background
          @current-change="handleCurrentChange"
          :current-page="pagination.currentPage"
          :page-size="pagination.pageSize"
          layout="total, prev, pager, next, jumper"
          style="float: right;"
          :total="pagination.total">
      </el-pagination>
      <!--                 新增学科弹层-->
      <div>
        <el-dialog title="新增学科" v-model="dialogFormVisible">
          <el-form ref="dataAddForm" :model="formData" :rules="rules" label-position="right"
                   label-width="100px">
            <el-row>
              <el-col :span="12">
                <el-form-item label="年级" prop="gradeName">
                  <el-input v-model="formData.gradeName"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="学科" prop="subjectName">
                  <el-input v-model="formData.subjectName"/>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <el-row slot="footer" class="dialog-footer">
            <el-button @click="cancel()">取消</el-button>
            <el-button type="primary" @click="handleAddSubject()">确定</el-button>
          </el-row>
        </el-dialog>
      </div>
      <!--                        编辑学科弹层-->
          <div>
            <el-dialog title="编辑" v-model="dialogFormVisible4Edit">
              <el-form ref="dataEditForm" :model="formData" :rules="rules" label-position="right"
                       label-width="100px">
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="年级" prop="gradeName">
                      <el-input v-model="formData.gradeName"/>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="学科" prop="subjectName">
                      <el-input v-model="formData.subjectName"/>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-form>
              <div>
                <el-button @click="cancel()">取消</el-button>
                <el-button type="primary" @click="handleEditSubject()">确定</el-button>
              </div>
            </el-dialog>
          </div>
    </el-card>
  </div>
</template>

<script>
const axios = require('axios'); // 在webpack中引入axios

export default {
  name: "SubjectManagementView.vue",
  data() {
    return {
      dialogFormVisible: false,
      dialogFormVisible4Edit: false,
      pagination: {
        currentPage: 1,
        pageSize: 7,
        total: 0,
        gradeName: '',
        subjectName: ''
      },
      subjectList: [],
      formData: {
        gradeName: '',
        subjectName: ''
      },
      rules: {
        gradeName: [{required: true, message: '年级为必填项', trigger: 'blur'}],
        subjectName: [{required: true, message: '学科为必填项', trigger: 'blur'}],
      }
    }
  },
  created() {
    this.getSubjectList();
  },
  methods: {
    getSubjectList() {
      let param = "?" + "subjectName=" + this.pagination.subjectName + "&gradeName=" + this.pagination.gradeName;
      axios.get("http://localhost:80/subjects/" + this.pagination.currentPage + "/" + this.pagination.pageSize + param).then((res) => {
        this.subjectList = res.data.data.records;
        this.pagination.total = res.data.data.total;
        this.pagination.currentPage = res.data.data.current;
        this.pagination.pageSize = res.data.data.size;
      })
    },
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage; //更新当前页码
      this.getSubjectList(); //重新查询
    },
    cancel() {
      this.dialogFormVisible = false;
      this.dialogFormVisible4Edit = false;
    },
    createSubjectIsClick() {
      this.formData = {};
      this.dialogFormVisible = true;
    },
    handleAddSubject() {
      axios.post("http://localhost:80/subjects", this.formData).then((res) => {
        this.dialogFormVisible = false;
        this.getSubjectList();
      })
    },
    handleEditSubject() {
      axios.put("http://localhost:80/subjects", this.formData).then((res) => {
        this.dialogFormVisible4Edit = false;
        this.getSubjectList();
      })
    },
    editSubject(row) {
      this.dialogFormVisible4Edit = true;
      axios.get("http://localhost:80/subjects/" + row.id).then((res) => {
        this.formData= res.data.data;
      })
    },
    delSubject(row) {
      this.$confirm('此操作将同时删除该学科下的所有题目和试卷, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete("http://localhost:80/subjects/" + row.id).then((res) => {
          this.getSubjectList();
        });
      }).catch(() => {
      });
    },
  }

}
</script>

<style scoped>

</style>