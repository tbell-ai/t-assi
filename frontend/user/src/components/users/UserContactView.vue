<template>
  <main id="main" class="mypage-view">
    <section class="section1">
      <div class="subpage-title">
        <h2 class="font1">My Page</h2>
        <p>의뢰 상세</p>
      </div>
    </section>
    <section class="section2">
      <h3>내 의뢰 내용 상세</h3>
      <table class="table2">
        <tr>
          <th>의뢰 서비스 명칭</th>
          <td>{{ contactData.contactServiceName }}</td>
        </tr>
        <tr>
          <th>대상 SW 종류</th>
          <td>{{ contactData.contactServiceType }}</td>
        </tr>
        <tr>
          <th>서비스</th>
          <td>{{ contactData.contactTestType }}</td>
        </tr>
        <tr>
          <th>검증 기간</th>
          <td>{{ contactData.contactStart }} ~ {{ contactData.contactEnd }}</td>
        </tr>
        <tr>
          <th>의뢰내용</th>
          <td>{{ contactData.contactContent }}</td>
        </tr>
        <tr>
          <th>처리상태</th>
          <td>{{ contactData.contactState }}</td>
        </tr>
      </table>

      <div class="button-box">
        <a
          href="javascript:;"
          class="request-btn button1"
          @click="routeContactEdit"
        >
          수정
        </a>
        <a href="javascript:;" class="button2" @click="routeContactList"
          >목록</a
        >
      </div>
    </section>
    <section class="section2 answer-box">
      <h3>답변내용</h3>
      <div class="comment-wrap">
        <div class="comment-input-wrap">
          <textarea
            class="comment-input"
            type="text"
            placeholder="텍스트를 입력해 주세요."
            v-model="comment"
          />
        </div>
        <button type="button" class="comment-btn" @click="createComment">
          작성
        </button>
      </div>
      <ul class="comment">
        <li v-for="(item, index) in commentList" :key="index">
          <div class="comment-profile">
            <img src="../../assets/front_image/icon-profile.svg" alt="" />
          </div>
          <div>
            <b class="comment-name">{{ item.writer }}</b>
            <span class="comment-date"
              ><img src="../../assets/front_image/icon-date.svg" alt="" />{{
                item.createDate
              }}</span
            >
            <p>
              {{ item.comment }}
            </p>
          </div>
        </li>
      </ul>
    </section>
  </main>
</template>

<script>
import {
  fetchComments,
  fetchRequest,
  registerComment,
} from '../../api/contact.js';

export default {
  created() {
    this.fetchData();
    this.fetchComment();
  },
  data() {
    return {
      contactData: {},
      commentList: [],
      comment: '',
    };
  },
  methods: {
    async fetchData() {
      const id = this.$route.params.id;
      const { data } = await fetchRequest(id);
      this.contactData = data.data;
    },
    async fetchComment() {
      const id = this.$route.params.id;
      const { data } = await fetchComments(id);
      // console.log(data);
      this.commentList = data.data;
    },
    routeContactEdit() {
      this.$router.push(`/mypage/contact/edit/${this.contactData.contactId}`);
    },
    routeContactList() {
      this.$router.push('/mypage/contact/list');
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
.mypage-view {
  padding-top: 80px;
  width: 1300px;
  margin: 0 auto;
}
.section1 {
  padding-top: 40px;
  height: auto;
}
.section2 {
  height: auto;
  background: white;
  margin-bottom: 80px;
}
.quest-modify {
  width: 100%;
}
.quest-modify li {
  display: flex;
  justify-content: start;
  min-height: 50px;
  padding: 10px 0;
  align-items: center;
}
.quest-modify li:nth-child(2n-1) {
  background: #eee;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}
.quest-left {
  width: 200px;
  font-weight: 700;
}
.quest-right {
  width: 100%;
  display: flex;
  align-items: center;
}
.quest-right p {
  margin: 0 20px;
}
.quest-right input {
  border: 1px solid #000;
  width: 500px;
  height: 36px;
}
.quest-right input[type='date'] {
  width: 225px;
}
.quest-right select {
  width: 500px;
  height: 36px;
  border: 1px solid #000;
}
.comment-wrap {
  overflow: hidden;
}

.comment-input-wrap {
  float: left;
  margin-top: 30px;
  display: block;
  padding: 10px 0px 30px 10px;
  box-sizing: border-box;
  border: 1px solid #000;
  width: 85%;
  height: 60px;
  box-shadow: 0 3px 3px rgba(0, 0, 0, 0.2);
}
.comment-input {
  width: 100%;
  border: none;
}

.comment-input::-webkit-scrollbar {
  width: 6px;
}
.comment-input::-webkit-scrollbar-thumb {
  height: 17%;
  background-color: #a0a0a0;
  border-radius: 10px;
}
.comment-input::-webkit-scrollbar-track {
  background-color: rgba(0, 0, 0, 0);
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
