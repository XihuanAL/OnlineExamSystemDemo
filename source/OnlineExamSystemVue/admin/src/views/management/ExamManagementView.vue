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
                <el-button type="primary" size="small">编辑</el-button>
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
          <!--           新增题目弹层-->
<!--          <div class="add-form">-->
<!--            <el-dialog title="新增题目" :close-on-click-modal="false" :destroy-on-close="true"-->
<!--                       :show-close="false" v-model="dialogFormVisible">-->
<!--              <el-form ref="dataAddForm" :model="formData" :rules="rules" label-position="right"-->
<!--                       label-width="100px">-->
<!--                <el-row>-->
<!--                  <el-col :span="12">-->
<!--                    <el-form-item label="年级" prop="gradeName">-->
<!--                      <el-select v-model="formData.gradeName" placeholder="请选择" @change="handleFoundOptions4Subject()">-->
<!--                        <el-option-->
<!--                            v-for="item in optionsGrade"-->
<!--                            :key="item.gradeName"-->
<!--                            :label="item.gradeName"-->
<!--                            :value="item.gradeName">-->
<!--                        </el-option>-->
<!--                      </el-select>-->
<!--                    </el-form-item>-->
<!--                  </el-col>-->
<!--                </el-row>-->
<!--                <el-row>-->
<!--                  <el-col :span="12">-->
<!--                    <el-form-item label="学科" prop="subjectName">-->
<!--                      <el-select v-model="formData.subjectName" placeholder="请选择">-->
<!--                        <el-option-->
<!--                            v-for="item in optionsSubject"-->
<!--                            :key="item.subjectName"-->
<!--                            :label="item.subjectName"-->
<!--                            :value="item.subjectName">-->
<!--                        </el-option>-->
<!--                      </el-select>-->
<!--                    </el-form-item>-->
<!--                  </el-col>-->
<!--                </el-row>-->
<!--                <el-row>-->
<!--                  <el-col :span="12">-->
<!--                    <el-form-item label="类型" prop="type">-->
<!--                      <el-select v-model="formData.type" placeholder="请选择">-->
<!--                        <el-option-->
<!--                            v-for="item in optionsQuestionType"-->
<!--                            :key="item.type"-->
<!--                            :label="item.type"-->
<!--                            :value="item.type">-->
<!--                        </el-option>-->
<!--                      </el-select>-->
<!--                    </el-form-item>-->
<!--                  </el-col>-->
<!--                </el-row>-->
<!--                <el-row>-->
<!--                  <el-col :span="24">-->
<!--                    <el-form-item label="题目" prop="quesContent">-->
<!--                      <el-input v-model="formData.quesContent"/>-->
<!--                    </el-form-item>-->
<!--                  </el-col>-->
<!--                </el-row>-->
<!--                <el-row>-->
<!--                  <el-col :span="24">-->
<!--                    <el-form-item label="答案" prop="correct">-->
<!--                      <el-input v-model="formData.correct"/>-->
<!--                    </el-form-item>-->
<!--                  </el-col>-->
<!--                </el-row>-->
<!--                <el-row>-->
<!--                  <el-col :span="8">-->
<!--                    <el-form-item label="分值" prop="score">-->
<!--                      <el-input v-model="formData.score"/>-->
<!--                    </el-form-item>-->
<!--                  </el-col>-->
<!--                </el-row>-->
<!--              </el-form>-->
<!--              <div slot="footer" class="dialog-footer">-->
<!--                <el-button @click="cancel()">取消</el-button>-->
<!--                <el-button type="primary" @click="handleAddQuestion()">确定</el-button>-->
<!--              </div>-->
<!--            </el-dialog>-->
<!--          </div>-->
          <!--          编辑题目弹层-->
<!--          <div class="add-form">-->
<!--            <el-dialog title="编辑题目" :close-on-click-modal="false" :destroy-on-close="true"-->
<!--                       :show-close="false" v-model="dialogFormVisible4Edit">-->
<!--              <el-form ref="dataAddForm" :model="formData" :rules="rules" label-position="right"-->
<!--                       label-width="100px">-->
<!--                <el-row>-->
<!--                  <el-col :span="12">-->
<!--                    <el-form-item label="年级" prop="gradeName">-->
<!--                      <el-select v-model="formData.gradeName" placeholder="请选择" @change="handleFoundOptions4Subject()">-->
<!--                        <el-option-->
<!--                            v-for="item in optionsGrade"-->
<!--                            :key="item.gradeName"-->
<!--                            :label="item.gradeName"-->
<!--                            :value="item.gradeName">-->
<!--                        </el-option>-->
<!--                      </el-select>-->
<!--                    </el-form-item>-->
<!--                  </el-col>-->
<!--                </el-row>-->
<!--                <el-row>-->
<!--                  <el-col :span="12">-->
<!--                    <el-form-item label="学科" prop="subjectName">-->
<!--                      <el-select v-model="formData.subjectName" placeholder="请选择">-->
<!--                        <el-option-->
<!--                            v-for="item in optionsSubject"-->
<!--                            :key="item.subjectName"-->
<!--                            :label="item.subjectName"-->
<!--                            :value="item.subjectName">-->
<!--                        </el-option>-->
<!--                      </el-select>-->
<!--                    </el-form-item>-->
<!--                  </el-col>-->
<!--                </el-row>-->
<!--                <el-row>-->
<!--                  <el-col :span="12">-->
<!--                    <el-form-item label="类型" prop="type">-->
<!--                      <el-select v-model="formData.type" placeholder="请选择">-->
<!--                        <el-option-->
<!--                            v-for="item in optionsQuestionType"-->
<!--                            :key="item.type"-->
<!--                            :label="item.type"-->
<!--                            :value="item.type">-->
<!--                        </el-option>-->
<!--                      </el-select>-->
<!--                    </el-form-item>-->
<!--                  </el-col>-->
<!--                </el-row>-->
<!--                <el-row>-->
<!--                  <el-col :span="24">-->
<!--                    <el-form-item label="题目" prop="quesContent">-->
<!--                      <el-input v-model="formData.quesContent"/>-->
<!--                    </el-form-item>-->
<!--                  </el-col>-->
<!--                </el-row>-->
<!--                <el-row>-->
<!--                  <el-col :span="24">-->
<!--                    <el-form-item label="答案" prop="correct">-->
<!--                      <el-input v-model="formData.correct"/>-->
<!--                    </el-form-item>-->
<!--                  </el-col>-->
<!--                </el-row>-->
<!--                <el-row>-->
<!--                  <el-col :span="8">-->
<!--                    <el-form-item label="分值" prop="score">-->
<!--                      <el-input v-model="formData.score"/>-->
<!--                    </el-form-item>-->
<!--                  </el-col>-->
<!--                </el-row>-->
<!--              </el-form>-->
<!--              <div slot="footer" class="dialog-footer">-->
<!--                <el-button @click="cancel()">取消</el-button>-->
<!--                <el-button type="primary" @click="handleEditQuestion()">确定</el-button>-->
<!--              </div>-->
<!--            </el-dialog>-->
<!--          </div>-->
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
const axios = require('axios');

export default {
  name: "ExamManagementView.vue",
  data() {
    return{
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
      axios.get("http://localhost:80/exams/" + this.pagination.currentPage + "/" + this.pagination.pageSize).then((res) => {
        this.examList = res.data.data.records;
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
        axios.delete("http://localhost:80/exams/" + row.id).then((res) => {
          this.getExamList();
        });
      }).catch(() => {
      });
    },
  }
}
</script>

<style scoped>

</style>