<template>
  <div class="main-right">
    <div class="title">
      <h2>프로젝트 질의 수정</h2>
    </div>
    <section class="section1">
      <div class="s-top">
        <b>프로젝트 질의 수정</b>
      </div>
      <form @submit.prevent="submitForm">
        <table class="table3">
          <tr>
            <th>질의 유형</th>
            <td>
              <select v-model="boardType">
                <option value="테스트">테스트</option>
                <option value="일정">일정</option>
                <option value="인력">인력</option>
                <option value="기타">기타</option>
              </select>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td>
              <input type="text" v-model="boardSubject" />
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <input type="text" v-model="boardContent" />
            </td>
          </tr>
          <tr>
            <th>처리상태</th>
            <td>
              <select v-model="boardState">
                <option value="답변대기">답변대기</option>
                <option value="처리진행">처리진행</option>
                <option value="처리완료">처리완료</option>
                <option value="처리불가">처리불가</option>
              </select>
            </td>
          </tr>
        </table>
        <div class="button-box">
          <router-link to="/admin/project/qna/list" class="cancel"
            >취소</router-link
          >
          <button type="submit" class="registration">수정</button>
        </div>
      </form>
    </section>
  </div>
</template>

<script>
import { fetchRequest, editRequest } from '../../api/board.js';

export default {
  created() {
    this.fetchContact();
  },
  data() {
    return {
      showModal: false,
      boardId: '',
      projectId: '',
      userId: '',
      boardType: '',
      boardWriter: '',
      boardSubject: '',
      boardContent: '',
      boardState: '',
    };
  },
  methods: {
    async fetchContact() {
      const id = this.$route.params.id;
      const { data } = await fetchRequest(id);

      console.log(data);

      this.boardId = this.$route.params.id;
      this.projectId = data.data.projectId;
      this.userId = data.data.userId;
      this.boardType = data.data.type;
      this.boardWriter = data.data.writer;
      this.boardSubject = data.data.subject;
      this.boardContent = data.data.content;
      this.boardState = data.data.state;
    },
    async submitForm() {
      const boardData = {
        id: this.$route.params.id,
        userId: this.userId,
        projectId: this.projectId,
        type: this.boardType,
        subject: this.boardSubject,
        content: this.boardContent,
        state: this.boardState,
      };

      const { data } = await editRequest(boardData);
      console.log(data);

      if (data.result === 'success') {
        alert(data.message);
        this.$router.push(`/admin/project/qna/view/${this.$route.params.id}`);
      } else if (data.result === 'fail') {
        alert(data.message);
      }
    },
  },
};
</script>

<style scoped>
.call-contract-btn {
  padding-top: 10px;
  width: 150px;
  padding: 10px;
  box-sizing: border-box;
  text-align: center;
  border: 1px solid #000;
  background: #000;
  color: #fff;
  font-weight: 700;
  margin-left: 20px;
}
.table3 th {
  width: 20%;
  background: #ddd;
  padding: 8px;
}
.table3 input {
  border: 1px solid #747474;
  border-radius: 3px;
  color: #747474;
  box-sizing: border-box;
  padding: 7px;
  width: 400px;
}

.table3 select,
.table3 textarea {
  border: 1px solid #747474;
  border-radius: 3px;
  font-size: 14px;
  font-weight: 600;
  color: #808080;
  padding: 7px 7px;
  box-sizing: border-box;
}
.table3 select {
  width: 200px;
}
.tester input {
  width: 100px;
  margin-left: 15px;
  margin-right: 35px;
}
</style>
