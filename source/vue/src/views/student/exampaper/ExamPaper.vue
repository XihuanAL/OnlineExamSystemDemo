<template>
  <div v-show="formData.questionList.length !== 0">
    <div class="question-title" v-for="(item,index) in formData.questionList">
      <el-row style="margin-bottom: 20px">
        <el-col :span="2">
          <span style="size: B4">题目{{ index + 1 }}:</span>
        </el-col>
        <span style="size: B4">{{ item.quesContent }}</span>
        <el-input v-model="this.formData.answerList[index]"></el-input>
      </el-row>
    </div>
  </div>
  <el-button type="primary" @click="submit">提交</el-button>
</template>

<script>
export default {
  name: "ExamPaper",
  mounted() {
    this.formData = JSON.parse(this.$route.params.formData);
    this.formData.answerList = [];
  },
  data () {
    return {
      formData: {
        questionList: [],
        answerList: []
      },

    }
  },
  methods: {
    submit() {
      //console.log(this.formData);
      this.$axios.post("/student/exampapers/submit", this.formData).then(res => {
        this.$message.success("提交成功," + res.data.data);
        this.$router.push("/student/exam");
      });
    }
  }
}
</script>

<style scoped>

</style>