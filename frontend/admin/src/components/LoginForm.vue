<template>
  <div id="wrap">
    <div id="container">
      <div class="top">
        <div id="logo">
          <img src="../assets/admin_image/header-logo.svg" alt="" />
        </div>
      </div>
      <div class="bottom">
        <form class="login-content" @submit.prevent="submitForm">
          <ul>
            <li>
              <b
                ><img
                  src="../assets/admin_image/header-logo.svg"
                  alt=""
                />관리자 페이지 입니다.</b
              >
            </li>
            <li>
              <p>이메일</p>
              <input type="text" v-model="email" />
              <p class="validation-text">
                <span class="warning" v-if="!isEmailCheck">
                  이메일 형식에 맞지 않습니다.
                </span>
              </p>
            </li>
            <li>
              <p>비밀번호</p>
              <input type="password" v-model="password" />
            </li>
            <li class="box1">
              <div>
                <input type="checkbox" id="check1" />
                <label for="check1"></label>
                <label for="check1" class="label-text">아이디저장</label>
              </div>
              <div>
                <input type="checkbox" id="check2" />
                <label for="check2"></label>
                <label for="check2" class="label-text">로그인 상태 유지</label>
              </div>
            </li>
            <li class="box2">
              <button :disabled="!isEmailCheck" type="submit" class="login-btn">
                로그인
              </button>
            </li>
          </ul>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      password: '',
    };
  },
  computed: {
    isEmailCheck() {
      const re = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$/;
      return re.test(String(this.email).toLowerCase());
    },
  },
  methods: {
    async submitForm() {
      try {
        const loginData = {
          email: this.email,
          password: this.password,
        };

        const result = await this.$store.dispatch('LOGIN', loginData);

        if (result === 200) {
          this.$router.push('/admin/main');
        } else {
          alert('로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요!');
        }
      } catch (error) {
        console.log(error);
        if (error.response.status === 403) {
          alert('로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요!');
        }
        // console.log(error.response.data);
        // console.log(error.response.headers);
      } finally {
        this.initForm();
      }
    },
    initForm() {
      this.email = '';
      this.password = '';
    },
  },
};
</script>

<style scoped>
@import '../css/login.css';
</style>
