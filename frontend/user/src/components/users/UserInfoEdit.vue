<template>
  <main id="main">
    <form @submit.prevent="submitForm" class="mypage-view">
      <section class="section1">
        <div class="subpage-title">
          <h2 class="font1">My Page</h2>
          <p>개인정보 수정</p>
        </div>
      </section>
      <section class="section2">
        <table class="table2">
          <tr>
            <th>이메일</th>
            <td>
              <input type="text" v-model.trim="email" />
            </td>
          </tr>
          <tr>
            <th>이름</th>
            <td>
              <input type="text" v-model.trim="name" />
            </td>
          </tr>
          <tr>
            <th>연락처</th>
            <td>
              <input type="text" v-model.trim="phone" />
            </td>
          </tr>
          <tr>
            <th>회사명</th>
            <td>
              <input type="text" v-model.trim="company" />
            </td>
          </tr>
        </table>
      </section>
      <section class="section3">
        <table class="table2">
          <tr>
            <th>현재 비밀번호</th>
            <td>
              <input
                type="password"
                placeholder="현재 비밀번호"
                v-model="oldPassword"
              />
            </td>
          </tr>
          <tr>
            <th>새 비밀번호</th>
            <td>
              <input
                type="password"
                placeholder="새 비밀번호"
                v-model="newPassword"
              />
              <p class="validation-text">
                <span class="warning" v-if="!isPasswordCheck">
                  현재 비밀번호를 확인해주세요.
                </span>
              </p>
            </td>
          </tr>
          <tr>
            <th>새 비밀번호 확인</th>
            <td>
              <input
                type="password"
                placeholder="새 비밀번호 확인"
                v-model="passwordConfirm"
              />
              <p class="validation-text">
                <span class="warning" v-if="!isPasswordConfirm">
                  입력하신 비밀번호와 일치하지 않습니다.
                </span>
              </p>
            </td>
          </tr>
        </table>
        <div class="button-box">
          <button
            class="save button1"
            :disabled="!isPasswordConfirm || !isPasswordCheck"
            type="submit"
          >
            저장
          </button>
          <button class="save button2" @click="routeUserView">취소</button>
        </div>
      </section>
    </form>
  </main>
</template>

<script>
import { fetchUser, editUser } from '../../api/users.js';

export default {
  created() {
    this.fetchUser();
  },
  data() {
    return {
      email: '',
      name: '',
      phone: '',
      company: '',
      oldPassword: '',
      newPassword: '',
      passwordConfirm: '',
    };
  },
  computed: {
    isPasswordConfirm() {
      return this.newPassword === this.passwordConfirm;
    },
    isPasswordCheck() {
      const re =
        /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\d~!@#$%^&*()+|=]{8,16}$/;
      return re.test(String(this.newPassword));
    },
  },
  methods: {
    async fetchUser() {
      const id = this.$route.params.id;
      const { data } = await fetchUser(id);
      this.email = data.data.email;
      this.name = data.data.name;
      this.phone = data.data.phone;
      this.company = data.data.company;
    },
    async submitForm() {
      const userData = {
        id: this.$route.params.id,
        email: this.email,
        name: this.name,
        phone: this.phone,
        company: this.company,
        oldPassword: this.oldPassword,
        newPassword: this.newPassword,
        personalDataYn: true,
        termYn: true,
      };

      const { data } = await editUser(userData);

      if (data.result === 'success') {
        if (this.oldPassword === this.newPassword) {
          this.oldPassword = '';
          this.newPassword = '';
          this.passwordConfirm = '';
          alert('새로운 비밀번호를 입력해주세요.');
        } else {
          alert(data.message);
          this.$router.push(`/mypage/view/${this.$route.params.id}`);
        }
      } else if (data.result === 'fail') {
        this.oldPassword = '';
        this.newPassword = '';
        this.passwordConfirm = '';
        //alert(data.message);
        alert('현재 비밀번호를 확인해주세요.');
      }
    },
    routeUserView() {
      this.$router.push(`/mypage/view/${this.$route.params.id}`);
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
  padding-top: 40px;
  height: auto;
}

.section2 {
  padding-top: 20px;
  height: auto;
}

.section3 {
  padding-top: 20px;
  margin-bottom: 140px;
  height: auto;
}

.wrap-1 button {
  width: 150px;
  border: 0;
}

.button2 {
  border: 1px solid #555 !important;
}

.table2 input {
  width: 350px;
  padding: 10px;
  border: 1px solid #747474;
}
</style>
