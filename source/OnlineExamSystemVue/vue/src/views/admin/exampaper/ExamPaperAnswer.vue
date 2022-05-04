<template>
  <div v-show="formData.questionList.length !== 0">
    <div class="question-title" v-for="(item,index) in formData.questionList">
      <el-row style="margin-bottom: 20px">
        <el-col :span="2">
          <span style="size: B4">题目{{ index + 1 }}:</span>
        </el-col>
        <span style="size: B4">{{ item.quesContent }}</span>

      </el-row>
      <p>学生答案:{{ this.formData.answerList[index] }}</p>
      <p>正确答案:{{ item.correct }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "ExamPaperAnswer",
  mounted() {
    this.formData = JSON.parse(this.$route.params.formData);
    this.formData.questionList = [];
    this.formData.answerList = [];
    this.getAnswerList();
  },
  data() {
    return {
      formData: {
        questionList: [],
        answerList: []
      },
    }
  },
  methods: {
    getAnswerList() {
      this.formData.answerList = [];
      console.log(this.formData);
      this.$axios.get("/exams/questions/" + this.formData.examId).then((res) => {
        this.formData.questionList = res.data.data;
        console.log(this.formData);
        this.formData.questionList.forEach((item, index) => {
          this.$axios.get("/admin/exampapers/answers/" + this.formData.id + "/" + item.id + "/" + this.formData.createrId).then(res => {
            this.formData.answerList[index] = res.data.data;
          });
        });
      })
    },

  }
}
</script>

<style scoped>

</style>