<template>
  <div class="main-right">
    <!-- 타이틀 -->
    <div class="title">
      <h2>회원 수정</h2>
    </div>
    <section class="section1">
      <div class="s-top">
        <b>회원정보수정</b>
      </div>
      <form @submit.prevent="submitForm">
        <table class="table3">
          <tr>
            <th>회원명</th>
            <td><input type="text" v-model="userData.name" /></td>
          </tr>
          <tr>
            <th>이메일</th>
            <td>
              <input type="text" v-model="userData.email" />
            </td>
          </tr>
          <tr>
            <th>현재 비밀번호</th>
            <td>
              <input
                type="password"
                placeholder="현재 비밀번호"
                v-model="oldPassword"
                autocomplete="off"
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
                autocomplete="off"
              />
              <p class="validation-text" v-if="isOldPasswordInputCheck">
                <span class="warning" v-if="!isPasswordCheck">
                  비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.
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
                autocomplete="off"
              />
              <p class="validation-text">
                <span class="warning" v-if="!isPasswordConfirm">
                  입력하신 비밀번호와 일치하지 않습니다.
                </span>
              </p>
            </td>
          </tr>
          <tr>
            <th>전화번호</th>
            <td><input type="text" v-model="userData.phone" /></td>
          </tr>
          <tr>
            <th>소속</th>
            <td><input type="text" v-model="userData.company" /></td>
          </tr>
          <tr>
            <th>개인정보처리방침</th>
            <td>
              <input
                type="radio"
                v-model="userData.personDataYn"
                value="동의"
                disabled
              />동의
              <input
                type="radio"
                v-model="userData.personDataYn"
                value="비동의"
                disabled
              />비동의
            </td>
          </tr>
          <tr>
            <th>서비스 이용약관</th>
            <td>
              <input
                type="radio"
                v-model="userData.termYn"
                value="동의"
                disabled
              />동의
              <input
                type="radio"
                v-model="userData.termYn"
                value="비동의"
                disabled
              />비동의
            </td>
          </tr>
          <tr>
            <th>회원 상태</th>
            <td>
              <select v-model="userData.state">
                <!-- 회원가입 완료 상태 -->
                <option value="기본회원">기본회원</option>
                <!-- 회원가입 완료 상태 -->
                <!-- 프로젝트 진행 상태 -->
                <option value="계약회원">계약회원</option>
                <!-- 프로젝트 진행 상태 -->
                <!-- 장기미사용자 -->
                <option value="휴면회원">휴면회원</option>
                <!-- 장기미사용자 -->
                <!-- 탈퇴상태 -->
                <option value="탈퇴회원">탈퇴회원</option>
                <!-- 탈퇴상태 -->
                <!-- 관리자 -->
                <option value="특별회원">특별회원</option>
                <!-- 관리자 -->
              </select>
            </td>
          </tr>
        </table>
        <div class="button-box">
          <a href="javascript:;" class="cancel" @click="routeUserList">취소</a>
          <button
            :disabled="!isPasswordConfirm"
            type="submit"
            class="registration"
          >
            수정
          </button>
        </div>
      </form>
    </section>
  </div>
</template>

<script>
import { fetchUser, editUser } from '../../api/users.js';

export default {
  created() {
    this.fetchUser();
  },
  data() {
    return {
      userData: {},
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
    isOldPasswordInputCheck() {
      return this.oldPassword !== '';
    },
  },
  methods: {
    async fetchUser() {
      const id = this.$route.params.id;
      const { data } = await fetchUser(id);

      if (data.result === 'success') {
        this.userData = data.data;

        if (this.userData.personDataYn === true) {
          this.userData.personDataYn = '동의';
        } else {
          this.userData.personDataYn = '비동의';
        }

        if (this.userData.termYn === true) {
          this.userData.termYn = '동의';
        } else {
          this.userData.termYn = '비동의';
        }
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
    async submitForm() {
      if (
        !this.passwordInputChecker(
          this.oldPassword,
          this.newPassword,
          this.passwordConfirm,
        )
      ) {
        return;
      }

      const editData = {
        id: this.$route.params.id,
        email: this.userData.email,
        name: this.userData.name,
        phone: this.userData.phone,
        company: this.userData.company,
        oldPassword: this.oldPassword,
        newPassword: this.newPassword,
        personalDataYn: this.converterData(this.userData.personDataYn),
        termYn: this.converterData(this.userData.termYn),
      };

      const { data } = await editUser(editData);

      if (data.result === 'success') {
        alert(data.message);
        this.$router.push(`/admin/account/view/${this.$route.params.id}`);
      } else if (data.result === 'fail') {
        this.oldPassword = '';
        this.newPassword = '';
        this.passwordConfirm = '';
        alert(data.message);
      }
    },
    passwordInputChecker(oldPw, newPw, conPw) {
      if (
        (oldPw !== '' && newPw === '') ||
        (newPw !== '' && oldPw === '') ||
        (conPw !== '' && newPw === '') ||
        (conPw !== '' && oldPw === '')
      ) {
        alert(
          '비밀번호 변경을 하시려면 현재 비밀번호와 신규 비밀번호 모두 입력 바랍니다.',
        );
        this.initPasswordForm();
        return false;
      }

      return true;
    },
    converterData(data) {
      if (data === '동의') {
        return true;
      }

      return false;
    },
    initPasswordForm() {
      this.oldPassword = '';
      this.newPassword = '';
      this.passwordConfirm = '';
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
.section1 input {
  padding: 5px;
  width: 400px;
}
.section1 input[type='radio'] {
  width: 40px;
}
</style>
