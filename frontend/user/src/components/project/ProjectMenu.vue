<template>
  <nav class="snb">
    <div class="snb-top">
      <h1 class="logo">
        <router-link to="/main">
          <img src="../../assets/front_image/t-assistant.svg" alt="" />
        </router-link>
      </h1>
      <div class="user-info">
        <p id="user-name">{{ this.$store.state.name }}</p>
        <p>{{ this.$store.state.company }}</p>
      </div>
      <div class="nav">
        <ul>
          <li>
            <div class="main-menu">
              <a href="javascript:;" @click="routeProjectDashboard"
                >프로젝트 대시보드</a
              >
            </div>
          </li>
          <li>
            <div class="main-menu">
              <a href="javascript:;" @click="routeProjectIntro"
                >프로젝트 개요</a
              >
            </div>
          </li>
          <li>
            <div class="main-menu">
              <a href="javascript:;" @click="routeProjectQna"
                >프로젝트 질의응답</a
              >
            </div>
          </li>
          <!-- <li>
            <div class="main-menu">
              <img src="" alt="" />
              <a href="#">프로젝트 관리</a>
            </div>
            <div class="sub-menu">
              <ul>
                <li>
                  <img src="" alt="" />
                  <router-link to="/project/contract-modify">
                    계약 수정 요청
                  </router-link>
                </li>
                <li>
                  <img src="" alt="" />
                  <router-link to="/project/account-added">
                    계정 추가 요청
                  </router-link>
                </li>
              </ul>
            </div>
          </li> -->
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import { getProjectIdFromCookie } from '../../utils/cookies.js';

export default {
  created() {
    this.setCurId();
  },
  data() {
    return {
      projectId: '',
    };
  },
  methods: {
    setCurId() {
      this.projectId = getProjectIdFromCookie();
    },
    routeProjectIntro() {
      this.$router.push(`/project/intro/${this.projectId}`);
    },
    routeProjectDashboard() {
      this.$router.push(`/project/dashboard/${this.projectId}`);
    },
    routeProjectQna() {
      this.$router.push(`/project/qna-list/${this.projectId}`);
    },
  },
};
</script>

<style scoped>
@import '../../css/pj_sidebar.css';
</style>
