<template>
  <header id="header">
    <nav>
      <div class="header-top">
        <router-link to="/" id="mainlogo"
          ><img src="../../assets/admin_image/header-logo.svg" alt="메인로고"
        /></router-link>
        <div class="right-box">
          <!-- <a href="" class="alarm">
            <img src="../../assets/admin_image/alarm.svg" alt="" /> -->
          <!-- 알림 갯수 -->
          <!-- <b></b>
          </a> -->
          <!-- <div class="mypage">
            <div class="name">
              <ul>
                <li><a href="#">개인 설정</a></li>
                <li><a href="#">알림 설정</a></li>
                <li class="logout"><a href="">로그아웃</a></li>
              </ul>
            </div> -->
          <!-- 닉네임 앞글자 -->
          <!-- <div class="profile"></div>
          </div> -->
          <p class="admin-text">
            {{ $store.state.name }} 관리자님, 어서오세요!
          </p>
          <a href="javascript:;" class="logout" @click="logoutUser">로그아웃</a>
        </div>
      </div>
    </nav>
  </header>
</template>

<script>
import { deleteCookie } from '../../utils/cookies.js';

export default {
  methods: {
    logoutUser() {
      this.$store.commit('clearUser');
      this.$store.commit('clearToken');
      deleteCookie('t_admin_auth');
      deleteCookie('t_admin_user_id');
      deleteCookie('t_admin_user_name');
      deleteCookie('t_admin_user_email');
      this.$router.push('/admin/login');
    },
  },
};
</script>

<style scoped>
@import '../../css/header.css';
</style>
