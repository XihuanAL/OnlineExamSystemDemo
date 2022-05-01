<template>
  <div v-show="formData.questionList.length !== 0">
    <div class="question-title" v-for="(item,index) in formData.questionList">
      <el-row style="margin-bottom: 20px">
        <el-col :span="2">
          <span style="size: B4">题目{{ index + 1 }}:</span>
        </el-col>
        <span style="size: B4">{{ item.quesContent }}</span>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "ExamPaper",
  mounted() {
    this.formData= JSON.parse(this.$route.params.formData);
  },
  data () {
    return {
      formData: {
        questionList: []
      }
    }
  },
}
</script>

<style scoped>

</style>