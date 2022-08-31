<template>
  <div class="main-right">
    <div class="title">
      <h2>의뢰 상세 조회</h2>
    </div>
    <section class="section1">
      <div class="s-top">
        <b>의뢰정보</b>
      </div>
      <div class="before">
        <table class="table3">
          <tr>
            <th>의뢰 서비스 명칭</th>
            <td>{{ contactData.contactServiceName }}</td>
          </tr>
          <tr>
            <th>대상 SW 종류</th>
            <td>{{ contactData.contactServiceType }}</td>
          </tr>
          <tr>
            <th>테스팅 서비스 종류</th>
            <td>{{ contactData.contactTestType }}</td>
          </tr>
          <tr>
            <th>검증 기간</th>
            <td>
              {{ contactData.contactStart }} ~ {{ contactData.contactEnd }}
            </td>
          </tr>
          <tr>
            <th>의뢰 상세 내용</th>
            <td>{{ contactData.contactContent }}</td>
          </tr>
          <tr>
            <th>의뢰 작성일</th>
            <td>{{ contactData.createDate }}</td>
          </tr>
          <tr>
            <th>의뢰 작성자 이름</th>
            <td>{{ contactData.userName }}</td>
          </tr>
          <tr>
            <th>의뢰 작성자 메일</th>
            <td>{{ contactData.userEmail }}</td>
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
      </div>
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
        <button type="button" class="comment-btn" @click="createComment">
          작성
        </button>
      </div>
      <ul class="comment">
        <li v-for="(item, index) in commentList" :key="index">
          <div class="comment-profile">
            <img src="../../assets/admin_image/icon-profile.svg" alt="" />
          </div>
          <div>
            <b class="comment-name">{{ item.writer }}</b>
            <span class="comment-date"
              ><img src="../../assets/admin_image/icon-date.svg" alt="" />{{
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
  </div>
</template>

<script>
import {
  fetchComments,
  fetchRequest,
  registerComment,
  editState,
  fetchState,
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
      this.$router.push(`/admin/contact/edit/${this.contactData.contactId}`);
    },
    routeContactList() {
      this.$router.push('/admin/contact/list');
    },
    async createComment() {
      const commentData = {
        targetId: this.$route.params.id,
        userId: this.$store.state.id,
        comment: this.comment,
      };

      const { data } = await registerComment(commentData);

      // console.log(data);

      if (data.result === 'success') {
        if (
          this.contactData.contactState === '답변대기' ||
          this.contactData.contactState === '답변완료'
        ) {
          const id = this.$route.params.id;
          // 1. 수정 로직 추가

          const { data } = await editState(id, '답변진행');
          // console.log(data);

          if (data.result === 'success') {
            const { data } = await fetchState(id);
            // console.log(data);
            if (data.result === 'success') {
              this.contactData.contactState = data.data.state;
            } else {
              alert('페이지 비정상 접근입니다.');
            }
          } else {
            alert('페이지 비정상 접근입니다.');
          }
        }
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
.table3 th {
  width: 30%;
  background: #ddd;
  padding: 8px;
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
