<template>
  <main id="main">
    <form @submit.prevent="submitForm" class="wrap-type-1">
      <section>
        <div class="title-1">
          <img src="../../assets/front_image/t-assistant.svg" alt="" />
        </div>
        <div class="signup-content">
          <article class="content-guide">
            <span>회원 정보를 입력하세요.</span>
          </article>
          <div class="content-wrap-width">
            <article class="input1">
              <div class="input-area">
                <input
                  type="text"
                  id="email"
                  v-model="email"
                  v-bind:class="{
                    style1: !isEmailCheck,
                    style2: isEmailCheck,
                  }"
                />
                <label for="email"
                  ><span><ins>아이디(이메일)&ensp;*&ensp;</ins></span></label
                >
              </div>

              <!-- <div class="warning-area" v-if="!isEmailCheck">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>아이디를 입력해주세요.</span>
              </div> -->
              <div class="warning-area" v-if="!isEmailCheck">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>올바르지 않은 이메일 주소 형식입니다.</span>
              </div>
              <!-- <div class="warning-area" v-if="!isEmailCheck">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>사용중인 아이디입니다.</span>
              </div> -->

              <!-- 통과 -->
            </article>
            <article class="input1">
              <div class="input-area">
                <input
                  type="text"
                  id="name"
                  v-model="name"
                  v-bind:class="{
                    style1: !isnameValid,
                    style2: isnameValid,
                  }"
                />
                <label for="name"
                  ><span><ins>이름&ensp;*&ensp;</ins></span></label
                >
              </div>
              <div class="warning-area" v-if="!isnameValid">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>이름을 입력해주세요.</span>
              </div>
            </article>
          </div>
          <div class="content-wrap-width">
            <article class="input1">
              <div class="input-area">
                <img
                  class="checked"
                  src="../../assets/front_image/icon-checked.svg"
                  alt=""
                />
                <input
                  type="password"
                  v-model="password"
                  v-bind:class="{
                    style1: !isPasswordCheck,
                    style2: isPasswordCheck,
                  }"
                />
                <label for="password"
                  ><span>
                    <ins>비밀번호(8~20자, 영문/숫자/특수문자)&ensp;*&ensp;</ins>
                  </span></label
                >
              </div>

              <div class="warning-area" v-if="!isPasswordCheck">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>비밀번호 형식에 맞춰 입력해주세요.</span>
              </div>
            </article>
            <article class="input1">
              <div class="input-area">
                <img
                  class="checked"
                  src="../../assets/front_image/icon-checked.svg"
                  alt=""
                />
                <input
                  type="password"
                  v-model="passwordConfirm"
                  v-bind:class="{
                    style1: !isPasswordConfirm,
                    style2: isPasswordConfirm,
                  }"
                />
                <label for="password"
                  ><span> <ins>비밀번호 확인&ensp;*&ensp;</ins> </span></label
                >
              </div>
              <div class="warning-area" v-if="!isPasswordConfirm">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>비밀번호가 일치하지 않습니다.</span>
              </div>
            </article>
          </div>
          <div class="content-wrap-width">
            <article class="input1">
              <div class="input-area">
                <input
                  type="text"
                  id="department"
                  v-model="company"
                  v-bind:class="{
                    style1: !iscompanyValid,
                    style2: iscompanyValid,
                  }"
                />
                <label for="department"
                  ><span> <ins>소속&ensp;*&ensp;</ins> </span></label
                >
              </div>
              <div class="warning-area" v-if="!iscompanyValid">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>소속을 입력해주세요.</span>
              </div>
            </article>
            <article class="input1">
              <div class="input-area">
                <input
                  type="text"
                  id="tel"
                  v-model="phone"
                  v-bind:class="{
                    style1: !isphoneValid,
                    style2: isphoneValid,
                  }"
                />
                <label for="tel"
                  ><span><ins>연락처&ensp;*&ensp;</ins></span></label
                >
              </div>
              <div class="warning-area" v-if="!isphoneValid">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>숫자만 입력해주세요.( ' - ' 제외하고 입력)</span>
              </div>
            </article>
          </div>
          <article class="input5">
            <div class="title-area">
              <span>개인정보 처리 방침&ensp;*</span>
            </div>

            <div class="input-area-3">
              <div class="textarea-wrap">
                <p>
                  주식회사 티벨(이하 '티벨')은 「개인정보 보호법」 제30조에 따라
                  정보주체의 개인정보를 보호하고 이와 관련한 고 충을 신속하고
                  원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보
                  처리방침을 수립·공개합니다.
                </p>
                <br />
                <p>○ 이 개인정보처리방침은 2022년 1월 1부터 적용됩니다.</p>
                <br />
                <p>제1조(개인정보의 처리 목적)</p>
                <br />

                <p>
                  주식회사 티벨(이하 '티벨')은 「개인정보 보호법」 제30조에 따라
                  정보주체의 개인정보를 보호하고 이와 관련한 고 충을 신속하고
                  원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보
                  처리방침을 수립·공개합니다.
                </p>
                <br />
                <p>
                  주식회사 티벨(이하 '티벨')은 「개인정보 보호법」 제30조에 따라
                  정보주체의 개인정보를 보호하고 이와 관련한 고 충을 신속하고
                  원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보
                  처리방침을 수립·공개합니다.
                </p>
                <br />

                <p>
                  주식회사 티벨(이하 '티벨')은 「개인정보 보호법」 제30조에 따라
                  정보주체의 개인정보를 보호하고 이와 관련한 고 충을 신속하고
                  원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보
                  처리방침을 수립·공개합니다.
                </p>
              </div>
              <div class="input-wrap">
                <label for=""
                  ><input
                    id="personal-yn"
                    type="checkbox"
                    name="agree"
                    value="1"
                    v-model="personalDataYn"
                  />개인정보 수집 및 이용에 동의합니다.</label
                >
              </div>
            </div>
            <div class="warning-area" v-if="!isPersonalFieldCheck">
              <img src="../../assets/front_image/icon-error.svg" alt="" />
              <span>
                개인정보 수집 및 이용에 동의해야 회원가입이 가능합니다.</span
              >
            </div>
          </article>
          <article class="input6">
            <div class="title-area">
              <span>서비스 이용약관&ensp;*</span>
            </div>
            <div class="input-area-3">
              <div class="textarea-wrap">
                <p>
                  주식회사 티벨(이하 '티벨')은 「개인정보 보호법」 제30조에 따라
                  정보주체의 개인정보를 보호하고 이와 관련한 고 충을 신속하고
                  원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보
                  처리방침을 수립·공개합니다.
                </p>
                <br />
                <p>○ 이 개인정보처리방침은 2022년 1월 1부터 적용됩니다.</p>
                <br />
                <p>제1조(개인정보의 처리 목적)</p>
                <br />

                <p>
                  주식회사 티벨(이하 '티벨')은 「개인정보 보호법」 제30조에 따라
                  정보주체의 개인정보를 보호하고 이와 관련한 고 충을 신속하고
                  원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보
                  처리방침을 수립·공개합니다.
                </p>
                <br />
                <p>
                  주식회사 티벨(이하 '티벨')은 「개인정보 보호법」 제30조에 따라
                  정보주체의 개인정보를 보호하고 이와 관련한 고 충을 신속하고
                  원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보
                  처리방침을 수립·공개합니다.
                </p>
                <br />

                <p>
                  주식회사 티벨(이하 '티벨')은 「개인정보 보호법」 제30조에 따라
                  정보주체의 개인정보를 보호하고 이와 관련한 고 충을 신속하고
                  원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보
                  처리방침을 수립·공개합니다.
                </p>
              </div>
              <div class="input-wrap">
                <label for=""
                  ><input
                    id="term-yn"
                    type="checkbox"
                    name="agree"
                    value="2"
                    v-model="termYn"
                  />이용약관을 확인하였으며, 이용에 동의합니다.</label
                >
              </div>
            </div>
            <div class="warning-area" v-if="!isTermFieldCheck">
              <img src="../../assets/front_image/icon-error.svg" alt="" />
              <span> 서비스 이용약관에 동의해야 회원가입이 가능합니다.</span>
            </div>
          </article>
          <article class="btn-area">
            <button
              :disabled="
                !isPasswordConfirm ||
                !isPersonalFieldCheck ||
                !isTermFieldCheck ||
                !isEmailCheck ||
                !isPasswordCheck ||
                !isnameValid ||
                !iscompanyValid ||
                !isphoneValid
              "
              id="btn-signup"
              type="submit"
            >
              회원가입
            </button>
          </article>
          <div class="move-wrap">
            <article class="move">
              <span>이미 계정이 있으세요?</span>
              <router-link to="/login">로그인</router-link>
            </article>
          </div>
        </div>
      </section>
    </form>
  </main>
</template>

<script>
import { registerUser } from '../../api/auth.js';

export default {
  data: function () {
    return {
      email: '',
      name: '',
      password: '',
      passwordConfirm: '',
      company: '',
      phone: '',
      personalDataYn: '',
      termYn: '',
    };
  },

  computed: {
    isEmailCheck() {
      const re =
        /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
      return re.test(String(this.email).toLowerCase());
    },
    isPasswordCheck() {
      const re =
        /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\d~!@#$%^&*()+|=]{8,16}$/;
      return re.test(String(this.password));
    },
    isnameValid() {
      return this.name.length > 0;
    },
    isPasswordConfirm() {
      return this.password === this.passwordConfirm && this.password !== '';
    },
    isPersonalFieldCheck() {
      return this.personalDataYn;
    },
    isTermFieldCheck() {
      return this.termYn;
    },
    iscompanyValid() {
      return this.company.length > 0;
    },
    isphoneValid() {
      const re = /^[0-9]+$/;
      return this.phone.length > 0 && re.test(String(this.phone));
    },
  },
  methods: {
    async submitForm() {
      const userData = {
        email: this.email,
        name: this.name,
        password: this.password,
        company: this.company,
        phone: this.phone,
        role: 'USER',
        personalDataYn: this.personalDataYn,
        termYn: this.termYn,
      };

      const { data } = await registerUser(userData);
      if (data.result === 'success') {
        this.initForm();
        alert(data.message);
        this.$router.push('/main');
      } else if (data.result === 'fail') {
        this.initForm();
        alert(data.message);
      }
    },
    initForm() {
      this.email = '';
      this.name = '';
      this.password = '';
      this.passwordConfirm = '';
      this.company = '';
      this.phone = '';
      this.personalDataYn = '';
      this.termYn = '';
    },
  },
};
</script>

<style>
@import '../../css/login_signup.css';
</style>
