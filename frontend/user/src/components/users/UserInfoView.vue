<template>
  <main id="main" class="mypage-view">
    <div class="wrap-1">
      <section class="section1">
        <div class="subpage-title">
          <h2 class="font1">My Page</h2>
          <p>개인정보설정</p>
        </div>
      </section>
      <section class="section2">
        <table class="table2">
          <tr>
            <th>이메일</th>
            <td>{{ this.userData.email }}</td>
          </tr>
          <tr>
            <th>이름</th>
            <td>{{ this.userData.name }}</td>
          </tr>
          <tr>
            <th>연락처</th>
            <td>{{ this.userData.phone }}</td>
          </tr>
          <tr>
            <th>회사명</th>
            <td>{{ this.userData.company }}</td>
          </tr>
        </table>
        <div class="button-box">
          <button class="request-btn button1" @click="outMember">
            회원탈퇴
          </button>
          <button class="save button2" @click="routeUserEdit">정보수정</button>
        </div>
      </section>
    </div>
  </main>
</template>

<script>
import { fetchUser } from '../../api/users.js';

export default {
  created() {
    this.fetchUser();
  },
  data() {
    return {
      userData: {},
    };
  },
  methods: {
    async fetchUser() {
      const id = this.$route.params.id;
      const { data } = await fetchUser(id);
      if (data.result === 'success') {
        this.userData = data.data;
      } else {
        alert('페이지 비정상 접속');
      }
    },
    outMember() {
      alert('현재 회원탈퇴 기능은 지원하지 않습니다.');
    },
    routeUserEdit() {
      this.$router.push(`/mypage/edit/${this.userData.id}`);
    },
  },
};
</script>

<style scoped>
section {
  background: white;
}
.mypage-view {
  padding-top: 80px;
  width: 1300px;
  margin: 0 auto;
}

.section1 {
  width: 100% !important;
  padding-top: 40px;
  height: auto;
}

.section2 {
  width: 100% !important;
  padding-top: 20px;
  margin-bottom: 140px;
}

.wrap-1 button {
  width: 150px;
  border: 0;
  border-radius: 0;
}

.button2 {
  border: 1px solid #555 !important;
  background: white;
  color: #ff4343;
}
</style>
