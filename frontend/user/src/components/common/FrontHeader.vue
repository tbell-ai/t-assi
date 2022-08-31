<template>
  <header id="header" :class="{ sub: !isSubPageCheck }">
    <Teleport to="body">
      <FrontModal :show="showModalLink" @close="showModalLink = false">
        <template #header>
          <h3 class="modal-h3">프로젝트 대시보드 선택</h3>
        </template>
        <template #body>
          <table class="table1">
            <thead>
              <tr class="t1-title">
                <th>NO</th>
                <th>계약명</th>
                <th>담당자</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in projectList" :key="index">
                <td>{{ index + 1 }}</td>
                <td>
                  <a
                    href="javascript:;"
                    @click="routeDashboard(item.projectId)"
                    >{{ item.contractName }}</a
                  >
                </td>
                <td>{{ item.projectLeader }}</td>
              </tr>
            </tbody>
          </table>
        </template>
      </FrontModal>
    </Teleport>
    <nav>
      <div class="hleft">
        <h1 id="logo">
          <router-link to="/main">
            <img
              v-if="isSubPageCheck"
              src="../../assets/front_image/t-assistant.svg"
              alt=""
            />
            <img
              v-else
              src="../../assets/front_image/t-assistant-w.svg"
              alt=""
            />
          </router-link>
        </h1>
      </div>
      <ul class="menu-list">
        <!-- <li :class="{ sub: !isSubPageCheck }">
          <a href="#section2">서비스</a>
        </li> -->
        <li v-if="isSubPageCheck">
          <a href="#section2">서비스</a>
        </li>
        <!-- <li :class="{ sub: !isSubPageCheck }">
          <a href="#section3">컨설팅</a>
        </li> -->

        <li v-if="isSubPageCheck">
          <a href="#section3">컨설팅</a>
        </li>
        <li :class="{ sub: !isSubPageCheck }">
          <router-link to="/request/form">의뢰하기</router-link>
        </li>
        <li v-if="isUserLogin" :class="{ sub: !isSubPageCheck }">
          <a href="javascript:;" @click="clickedDashboard">대시보드</a>
        </li>
      </ul>
      <ul class="hright">
        <template v-if="isUserLogin">
          <span class="h-username" :class="{ sub: !isSubPageCheck }"
            >{{ $store.state.name }} 님</span
          >
          <div class="profile" @click="show = !show">
            <img
              src="../../assets/front_image/account_circle_white_24dp.svg"
              alt=""
            />
          </div>
          <transition name="fade" v-click-outside="onClickOutside">
            <ul v-if="show" class="mypage">
              <li>
                <router-link to="/mypage/contact/list">의뢰내역</router-link>
              </li>
              <li>
                <button @click="routeUserInfo">개인정보</button>
              </li>
              <li><button @click="logoutUser">로그아웃</button></li>
            </ul>
          </transition>
        </template>

        <template v-else>
          <li><router-link to="/login">로그인</router-link></li>
          <li><router-link to="/signup">회원가입</router-link></li>
        </template>
      </ul>
    </nav>
  </header>
</template>

<script>
import FrontModal from '../common/FrontModal.vue';
import { deleteCookie } from '../../utils/cookies.js';
import { getProjectIdList } from '../../api/project.js';
import vClickOutside from 'click-outside-vue3';
import { saveProjectIdToCookie } from '../../utils/cookies.js';

export default {
  directives: {
    clickOutside: vClickOutside.directive,
  },
  components: {
    FrontModal,
  },
  data() {
    return {
      show: false,
      sub: false,
      showModalLink: false,
      projectList: [],
    };
  },
  computed: {
    isUserLogin() {
      return this.$store.getters.isLogin;
    },
    isSubPageCheck() {
      return this.$store.state.subPage;
    },
  },
  methods: {
    logoutUser() {
      this.$store.commit('clearUser');
      this.$store.commit('clearToken');
      deleteCookie('t_customer_auth');
      deleteCookie('t_customer_user_id');
      deleteCookie('t_customer_user_name');
      deleteCookie('t_customer_user_email');
      deleteCookie('t_customer_user_company');
      this.$router.push('/login');
    },
    routeUserInfo() {
      this.$router.push(`/mypage/view/${this.$store.state.id}`);
    },
    async clickedDashboard() {
      const id = this.$store.state.id;
      const { data } = await getProjectIdList(id);

      this.projectList = data.data;

      if (this.projectList.length == 0) {
        alert('대시보드는 프로젝트 진행중인 사용자에 한해 제공됩니다.');
        this.showModalLink = false;
      } else {
        this.showModalLink = true;
      }
      // console.log(data);
      // alert('프로젝트 진행 상태가 아닐 경우, 대시보드를 제공하지 않습니다.');
      // alert('테스트를 위해 강제로 이동하겠습니다.');
      // this.$router.push(`/project/dashboard/1`);
    },
    routeDashboard(id) {
      saveProjectIdToCookie(id);
      this.$router.push(`/project/dashboard/${id}`);
    },
    onClickOutside() {
      this.show = false;
      // console.log('Clicked outside. Event: ', event);
    },
  },
  watch: {
    $route(to, from) {
      if (to.path !== from.path) {
        this.show = false;
      }
    },
  },
};
</script>

<style scoped>
@import '../../css/header.css';

.table1 {
  width: 100%;
}

.table1 thead tr th:nth-child(1) {
  width: 15%;
}

.table1 thead tr th:nth-child(2) {
  width: 50%;
}

.table1 thead tr th:nth-child(3) {
  width: 35%;
}

.table1 td {
  font-size: 13px;
}

.table1 tbody tr td:nth-child(2) {
  color: #ff4343;
}
.modal-h3 {
  font-size: 18px;
}
</style>
