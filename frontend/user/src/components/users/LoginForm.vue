<template>
  <!-- 메인 -->
  <main id="main">
    <div class="wrap-type-1">
      <section class="login">
        <form class="login-content" @submit.prevent="submitForm">
          <div class="title-1">
            <img src="../../assets/front_image/t-assistant.svg" alt="" />
          </div>
          <div class="login-content">
            <article class="content-guide">
              <span>로그인 정보를 입력하세요.</span>
            </article>
            <article class="input1">
              <div class="input-area">
                <input
                  type="text"
                  id="email"
                  v-model="email"
                  placeholder="아이디를 입력해주세요."
                />
              </div>
              <!-- <div class="warning-area">
              <img src="../../assets/front_image/icon-error.svg" alt="" />
              <span>아이디를 입력해주세요.</span>
            </div> -->
              <div class="warning-area" v-if="!isEmailCheck">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>올바르지 않은 이메일 형식입니다.</span>
              </div>
            </article>
            <article class="input1">
              <div class="input-area">
                <input
                  type="password"
                  id="password"
                  v-model="password"
                  placeholder="비밀번호를 입력해주세요."
                />
              </div>
              <div class="warning-area" v-if="!isPasswordCheck">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>비밀번호를 입력해주세요.</span>
              </div>
            </article>

            <article class="login-option-area">
              <div class="input-area-3">
                <div class="input-wrap">
                  <label
                    ><input type="checkbox" name="id-save" value="y" />아이디
                    저장</label
                  >
                  <label
                    ><input type="checkbox" name="id-save" value="y" />로그인
                    상태 유지</label
                  >
                </div>
              </div>
            </article>
            <article class="btn-area">
              <button :disabled="!isEmailCheck" id="btn-signup" type="submit">
                로그인
              </button>
            </article>
            <div class="move-wrap">
              <article class="move">
                <span>계정을 만들어 사용해보세요!</span>
                <span><router-link to="/signup"> 회원가입</router-link></span>
              </article>
              <article>
                <span>|</span>
              </article>
              <article class="find-password">
                <span><a href="#">비밀번호 찾기</a></span>
              </article>
            </div>
          </div>
        </form>
      </section>
    </div>
  </main>
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
    isIdValid() {
      return this.email.length > 0;
    },
    isPasswordValid() {
      return this.password.length < 21 && this.password.length > 8;
    },
    isEmailCheck() {
      const re = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$/;
      return re.test(String(this.email).toLowerCase());
    },
    isPasswordCheck() {
      const re =
        /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\d~!@#$%^&*()+|=]{8,16}$/;
      return re.test(String(this.password));
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
          this.$router.push('/main');
        } else {
          alert('로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요!');
        }
      } catch (error) {
        console.log(error);
        if (error.response.status === 403) {
          alert('로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요!');
        }
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

<style>
@import '../../css/login_signup.css';
</style>
