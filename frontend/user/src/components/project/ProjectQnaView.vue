<template>
  <div id="wrap">
    <ProjectMenu></ProjectMenu>
    <div id="container">
      <main>
        <div class="pj-wrap">
          <div class="pj-title-area">
            <img
              src="../../assets/front_image/question_answer_black_24_dp_1.svg"
              alt=""
            />
            <span>프로젝트 질의응답</span>
          </div>
          <div class="pj-content-area">
            <section class="pj-qna-detail-content">
              <article class="sub-title">
                <p>문의 상세</p>
              </article>
              <article class="table-type1 table1">
                <table>
                  <tr>
                    <th>문의유형</th>
                    <td>{{ boardData.type }}</td>
                  </tr>
                  <tr>
                    <th>등록자</th>
                    <td>{{ boardData.writer }}</td>
                  </tr>
                  <tr>
                    <th>등록일</th>
                    <td>{{ boardData.createDate }}</td>
                  </tr>
                  <tr>
                    <th>문의 제목</th>
                    <td>{{ boardData.subject }}</td>
                  </tr>
                  <tr>
                    <th>내용</th>
                    <td>{{ boardData.content }}</td>
                  </tr>
                  <tr>
                    <th>문의상태</th>
                    <td>{{ boardData.state }}</td>
                  </tr>
                </table>
              </article>
              <article class="btn-area btn-area-right">
                <button id="btn-save">
                  <a href="javascript:;" @click="routeBoardEdit">수정</a>
                </button>
                <button id="btn-list">
                  <a href="javascript:;" @click="routeBoardList">목록</a>
                </button>
              </article>
            </section>
            <section class="section2 answer-box">
              <h3>답변내용</h3>
              <div class="comment-wrap">
                <input
                  class="comment-input"
                  type="text"
                  placeholder="텍스트를 입력해 주세요."
                  v-model="comment"
                />
                <button
                  type="button"
                  class="comment-btn"
                  @click="createComment"
                >
                  작성
                </button>
              </div>
              <ul class="comment">
                <li v-for="(item, index) in commentList" :key="index">
                  <div class="comment-profile">
                    <img
                      src="../../assets/front_image/icon-profile.svg"
                      alt=""
                    />
                  </div>
                  <div>
                    <b class="comment-name">{{ item.writer }}</b>
                    <span class="comment-date"
                      ><img
                        src="../../assets/front_image/icon-date.svg"
                        alt=""
                      />{{ item.createDate }}</span
                    >
                    <p>
                      {{ item.comment }}
                    </p>
                  </div>
                </li>
              </ul>
            </section>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script>
import ProjectMenu from '../project/ProjectMenu.vue';
import {
  fetchComments,
  fetchRequest,
  registerComment,
} from '../../api/board.js';

export default {
  created() {
    this.fetchData();
    this.fetchComment();
  },
  components: {
    ProjectMenu,
  },
  data() {
    return {
      boardData: {},
      commentList: [],
      comment: '',
    };
  },
  methods: {
    async fetchData() {
      const id = this.$route.params.id;
      const { data } = await fetchRequest(id);
      this.boardData = data.data;
    },
    async fetchComment() {
      const id = this.$route.params.id;
      const { data } = await fetchComments(id);
      // console.log(data);
      this.commentList = data.data;
    },
    routeBoardEdit() {
      this.$router.push(`/project/qna-edit/${this.boardData.id}`);
    },
    routeBoardList() {
      this.$router.push(`/project/qna-list/${this.boardData.projectId}`);
    },
    async createComment() {
      const commentData = {
        targetId: this.$route.params.id,
        userId: this.$store.state.id,
        comment: this.comment,
      };

      const { data } = await registerComment(commentData);

      if (data.result === 'success') {
        this.comment = '';
        this.fetchComment();
      } else {
        alert('페이지 비정상 접근입니다.');
      }
    },
  },
};
</script>

<style scoped>
.table1 tr th {
  width: 20%;
  background: #ddd;
  border-bottom: 1px solid #ddd;
}
.table1 tr:last-child th {
  border-bottom: #000;
}

.table1 tr td {
  text-align: left;
}

#container .pj-wrap .pj-content-area {
  padding: 0px 30px;
}

.pj-content-area .btn-area {
  margin-top: 30px;
}
.comment-wrap {
  overflow: hidden;
}
.comment-input {
  float: left;
  margin-top: 30px;
  display: block;
  padding: 20px 10px;
  box-sizing: border-box;
  border: 1px solid #000;
  width: 85%;
  box-shadow: 0 3px 3px rgba(0, 0, 0, 0.2);
}
.comment-btn {
  cursor: pointer;
  color: #444;
  float: left;
  width: 13%;
  margin-top: 30px;
  margin-left: 2%;
  padding: 20px 10px;
  background: #fff;
  border: 1px solid #444;
  box-shadow: 0 3px 3px rgba(0, 0, 0, 0.2);
}
.answer-box {
  width: 100%;
}
.comment {
  margin: 40px 0;
  width: 100%;
  border-top: 2px solid #ddd;
}
.comment-profile {
  margin: 0 20px 0 10px;
}
.comment li {
  display: flex;
  padding: 12px 0;
  width: 100%;
  border-bottom: 1px solid #ddd;
}
.comment li b {
  font-size: 18px;
  font-weight: 800;
}
.comment li span {
  margin-top: 7px;
  display: block;
  color: #888;
}
.comment li span img {
  transform: translateY(-2px);
  margin-right: 5px;
}
.comment li p {
  margin-top: 14px;
  line-height: 1.5;
}
</style>
