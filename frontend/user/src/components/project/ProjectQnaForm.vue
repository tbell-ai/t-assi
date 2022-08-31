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
                  <button type="submit" id="btn-save">저장</button>
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
import { registerRequest } from '../../api/board.js';

export default {
  components: {
    ProjectMenu,
  },
  data() {
    return {
      boardType: '',
      boardSubject: '',
      boardContent: '',
    };
  },
  computed: {
    isFieldInputCheck() {
      return (
        this.boardType !== '' &&
        this.boardSubject !== '' &&
        this.boardContent !== ''
      );
    },
  },
  methods: {
    async submitForm() {
      const param = {
        type: this.boardType,
        subject: this.boardSubject,
        content: this.boardContent,
        userId: this.$store.state.id,
        projectId: this.$route.params.id,
      };

      const { data } = await registerRequest(param);

      // console.log(data);

      if (data.result === 'fail') {
        alert('문의 등록에 실패했습니다. 입력값을 확인해주세요');
        this.initForm();
      } else {
        alert('신규 문의가 등록되었습니다.');
        this.$router.push(`/project/qna-list/${this.$route.params.id}`);
      }
    },
    initForm() {
      this.boardType = '';
      this.boardSubject = '';
      this.boardContent = '';
    },
    routeBoardList() {
      this.$router.push(`/project/qna-list/${this.$route.params.id}`);
    },
  },
};
</script>

<style scoped>
@import '../../css/pj_qnaform.css';
</style>
