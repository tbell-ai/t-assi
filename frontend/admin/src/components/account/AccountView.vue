<template>
  <div class="main-right">
    <div class="title">
      <h2>회원 상세 조회</h2>
    </div>
    <section class="section1">
      <div class="s-top">
        <b>회원정보</b>
        <a href="javascript:;" class="admin-button" @click="routeUserList"
          >목록</a
        >
      </div>
      <div class="before">
        <table class="table3">
          <tr>
            <th>회원명</th>
            <td>{{ this.userData.name }}</td>
          </tr>
          <tr>
            <th>이메일</th>
            <td>{{ this.userData.email }}</td>
          </tr>
          <tr>
            <th>전화번호</th>
            <td>{{ this.userData.phone }}</td>
          </tr>
          <tr>
            <th>소속</th>
            <td>{{ this.userData.company }}</td>
          </tr>
          <tr>
            <th>권한</th>
            <td>
              {{ this.userData.role === 'ROLE_ADMIN' ? '관리자' : '일반회원' }}
            </td>
          </tr>
          <tr>
            <th>개인정보처리방침 동의여부</th>
            <td>
              {{ this.userData.personDataYn === true ? '동의' : '비동의' }}
            </td>
          </tr>
          <tr>
            <th>서비스 이용약관 동의여부</th>
            <td>{{ this.userData.termYn === true ? '동의' : '비동의' }}</td>
          </tr>
          <tr>
            <th>상태</th>
            <td>{{ this.userData.state }}</td>
          </tr>
        </table>
      </div>
      <div>
        <button class="admin-button" @click="routeUserEdit">수정</button>
        <button class="admin-button" @click="deleteUser">삭제</button>
      </div>
    </section>
  </div>
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
    routeUserList() {
      this.$router.push(`/admin/account/list`);
    },
    routeUserEdit() {
      this.$router.push(`/admin/account/edit/${this.userData.id}`);
    },
    deleteUser() {
      alert('현재 지원하지 않는 기능입니다.');
    },
  },
};
</script>

<style scoped>
.admin-button {
  float: right;
  padding: 7px 10px;
  width: 100px;
  box-sizing: border-box;
  text-align: center;
  border: none;
  background: #000;
  color: #fff;
  font-weight: 700;
  margin-top: 10px;
  margin-right: 10px;
}
.table3 th {
  width: 30%;
  background: #ddd;
  padding: 8px;
}
</style>
