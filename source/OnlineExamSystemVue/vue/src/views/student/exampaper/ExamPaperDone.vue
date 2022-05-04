<template>
  <p>我的得分: {{userScore}}</p>
  <div v-show="formData.questionList.length !== 0">
    <div class="question-title" v-for="(item,index) in formData.questionList">
      <el-row style="margin-bottom: 20px">
        <el-col :span="2">
          <span style="size: B4">题目{{ index + 1 }}:</span>
        </el-col>
        <span style="size: B4">{{ item.quesContent }}</span>

      </el-row>
      <p>我的答案:{{this.formData.answerList[index]}}</p>
      <p>正确答案:{{item.correct}}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "ExamPaperDone",
  mounted() {
    this.formData = JSON.parse(this.$route.params.formData);
    this.formData.answerList = [];
    this.getAnswerList();
    this.getUserScore();
  },
  data () {
    return {
      formData: {
        questionList: [],
        answerList: []
      },
      userScore: 0
    }
  },
  methods: {
    getAnswerList() {
      this.formData.answerList = [];
      this.formData.questionList.forEach((item, index) => {
        this.$axios.get("/student/exampapers/answers/" + this.formData.id + "/" + item.id).then(res => {
          //console.log(res.data.data);
          this.formData.answerList[index]= res.data.data;
        });
      });
    },
    getUserScore() {
      this.$axios.get("/student/exampapers/score/" + this.formData.id).then(res => {
        this.userScore = res.data.data;
      });
    }
  }
}
</script>

<style scoped>

</style>