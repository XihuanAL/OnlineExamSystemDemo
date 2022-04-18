<template>
  <el-card>
    <div class="add-form">
      <el-form ref="dataAddForm" :model="formData" :rules="rules" label-position="right"
               label-width="70px">
        <el-row>
          <el-col :span="7">
            <el-form-item label="年级" prop="gradeName">
              <el-select v-model="formData.gradeName" placeholder="请选择" @change="handleFoundOptions4Subject()">
                <el-option
                    v-for="item in optionsGrade"
                    :key="item.gradeName"
                    :label="item.gradeName"
                    :value="item.gradeName">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="学科" prop="subjectName">
              <el-select v-model="formData.subjectName" placeholder="请选择" @change="GetTableData()">
                <el-option
                    v-for="item in optionsSubject"
                    :key="item.subjectName"
                    :label="item.subjectName"
                    :value="item.subjectName">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="15">
            <el-form-item label="试卷名" prop="examName">
              <el-input v-model="formData.quesContent"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-card v-for="(item,index) in questionList">
        <div class="question-title">
          <el-row>
            <el-col :span="2">
              <span style="size: B4">题目{{ index + 1 }}:</span>
            </el-col>
            <span style="size: B4">{{ item.quesContent }}</span>
          </el-row>
        </div>
      </el-card>
      <div slot="footer" class="dialog-footer">
        <el-button @click="back()">返回</el-button>
        <el-button type="primary" @click="handleAddExam()">确定</el-button>
        <el-button type="warning" @click="addQuestionIsClick()">添加题目</el-button>
      </div>
    </div>
    <el-dialog title="编辑题目" :close-on-click-modal="false" :destroy-on-close="true"
               :show-close="false" v-model="dialogFormVisible">
      <el-table
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange">
        <el-table-column
            type="selection"
            width="75">
        </el-table-column>
        <el-table-column
            prop="quesContent"
            label="题目"
            width="360">
        </el-table-column>
        <el-table-column
            prop="type"
            label="题型"
            width="120">
        </el-table-column>
      </el-table>
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
      <el-button @click="cancel()">取消</el-button>
      <el-button type="primary" @click="handleAddQuestion()">确定</el-button>
    </el-dialog>
  </el-card>

</template>

<script>
import axios from "axios";

export default {
  name: "ExamPaperAdd",
  data() {
    return {
      dialogFormVisible: false,
      formData: {},
      rules: {},
      optionsGrade: [],
      optionsSubject: [],
      questionList: [
        {quesContent: '请选择题目'}
      ],
      tableData: [],
      multipleSelection: [],
      pagination: {
        currentPage: 1,
        pageSize: 7,
        total: 0,
      },
    }
  },
  created() {
    this.handleFoundOptions4Grade();
  },
  methods: {
    //取消添加题目
    cancel() {
      this.dialogFormVisible = false;
    },
    back() {
    },
    handleAddExam() {
    },
    handleFoundOptions4Subject() {
      axios.get("http://localhost:80/subjects?gradeName=" + this.formData.gradeName).then((res) => {
        this.optionsSubject = res.data.data;
      });
    },
    handleFoundOptions4Grade() {
      axios.get("http://localhost:80/subjects/grade").then((res) => {
        this.optionsGrade = res.data.data;
      });
    },
    addQuestionIsClick() {
      this.dialogFormVisible = true;
    },
    handleAddQuestion() {
      console.log(this.multipleSelection);
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    GetTableData() {
      let param = "?subjectName=" + this.pagination.subjectName + "&gradeName=" + this.pagination.gradeName;
      axios.get("http://localhost:80/questions/" + this.pagination.currentPage + "/" + this.pagination.pageSize + param).then((res) => {
        this.tableData = res.data.data.records;
        this.pagination.total = res.data.data.total;
        this.pagination.currentPage = res.data.data.current;
        this.pagination.pageSize = res.data.data.size;
      })
    },
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage; //更新当前页码
      this.GetTableData(); //重新查询
    },
  }
}
</script>

<style scoped>

</style>