<template>
  <div id="wrap">
    <ProjectMenu></ProjectMenu>
    <div id="container">
      <main id="pj-main">
        <div class="pj-wrap">
          <div class="pj-title-area">
            <img
              src="../../assets/front_image/question_answer_black_24_dp_1.svg"
              alt=""
            />
            <span>프로젝트 질의응답</span>
          </div>
          <div class="pj-content-area">
            <section class="pj-qna-create-content">
              <form @submit.prevent="submitForm">
                <article class="sub-title">
                  <p>문의하기</p>
                </article>
                <article class="input-type-width input1">
                  <div class="title-area">
                    <span>문의 유형</span>
                  </div>
                  <div class="input-area input-area-1">
                    <select v-model="boardType">
                      <option value="테스트">테스트</option>
                      <option value="일정">일정</option>
                      <option value="인력">인력</option>
                      <option value="기타">기타</option>
                    </select>
                  </div>
                </article>
                <article class="input-type-width input2">
                  <div class="title-area">
                    <span>문의 제목</span>
                  </div>
                  <div class="input-area input-area-1">
                    <input type="text" v-model="boardSubject" />
                  </div>
                </article>
                <article class="input-type-width input3">
                  <div class="title-area">
                    <span>문의 내용</span>
                  </div>
                  <div class="input-area input-area-4">
                    <textarea
                      cols="50"
                      rows="10"
                      v-model="boardContent"
                    ></textarea>
                  </div>
                </article>
                <article class="btn-area btn-area-right">
                  <button type="submit" id="btn-save">수정</button>
                  <button type="button" id="btn-cancel" @click="routeBoardList">
                    취소
                  </button>
                </article>
              </form>
            </section>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script>
import ProjectMenu from '../project/ProjectMenu.vue';
import { fetchRequest, editRequest } from '../../api/board.js';

export default {
  created() {
    this.fetchData();
  },
  components: {
    ProjectMenu,
  },
  data() {
    return {
      boardId: '',
      projectId: '',
      userId: '',
      boardType: '',
      boardSubject: '',
      boardContent: '',
      boardState: '',
    };
  },
  methods: {
    async fetchData() {
      const id = this.$route.params.id;
      const { data } = await fetchRequest(id);

      this.boardId = data.data.id;
      this.projectId = data.data.projectId;
      this.userId = data.data.userId;
      this.boardType = data.data.type;
      this.boardSubject = data.data.subject;
      this.boardContent = data.data.content;
      this.boardState = data.data.state;
    },
    async submitForm() {
      const boardData = {
        id: this.boardId,
        projectId: this.projectId,
        userId: this.$store.state.id,
        type: this.boardType,
        subject: this.boardSubject,
        content: this.boardContent,
        state: this.boardState,
      };

      const { data } = await editRequest(boardData);

      if (data.result === 'success') {
        alert(data.message);
        this.$router.push(`/project/qna-view/${this.boardId}`);
      } else if (data.result === 'fail') {
        alert(data.message);
      }
    },
    routeBoardList() {
      this.$router.push(`/project/qna-list/${this.projectId}`);
    },
  },
};
</script>

<style scoped>
@import '../../css/pj_qnaform.css';
</style>
