<template>
  <div class="main-right">
    <!-- 타이틀 -->
    <div class="title">
      <h2>업체 생성</h2>
    </div>
    <section class="section1">
      <form @submit.prevent="submitForm">
        <div class="s-top">
          <b>업체정보입력</b>
        </div>
        <table class="table3">
          <tr>
            <th>업체명</th>
            <td>
              <input
                type="text"
                placeholder="업체명을 입력하세요."
                v-model="companyName"
              />
              <p class="validation-text">
                <span class="warning" v-if="!isCompanyNameCheck">
                  특수문자 및 공백 제외, 50자 이내 입력 바랍니다.
                </span>
              </p>
            </td>
          </tr>
          <tr>
            <th>업체주소</th>
            <td>
              <input
                class="address"
                type="text"
                placeholder="업체주소를 입력하세요."
                v-model="companyAddress"
              />
              <p class="validation-text">
                <span class="warning" v-if="!isCompanyAddressCheck">
                  특수문자 및 공백 제외, 50자 이내 입력 바랍니다.
                </span>
              </p>
            </td>
          </tr>
          <tr>
            <th>업체 대표자</th>
            <td>
              <input
                type="text"
                placeholder="대표명을 입력하세요."
                v-model="companyCeoName"
              />
              <p class="validation-text">
                <span class="warning" v-if="!isCompanyCeoNameCheck">
                  특수문자 및 공백 제외, 50자 이내 입력 바랍니다.
                </span>
              </p>
            </td>
          </tr>
          <tr>
            <th>업체 담당자</th>
            <td>
              <input
                type="text"
                placeholder="담당자명을 입력하세요."
                v-model="companyManagerName"
              />
              <p class="validation-text">
                <span class="warning" v-if="!isCompanyManagerNameCheck">
                  특수문자 및 공백 제외, 50자 이내 입력 바랍니다.
                </span>
              </p>
            </td>
          </tr>
          <tr class="tel">
            <th>업체 대표번호</th>
            <td>
              <input
                type="text"
                placeholder="- 없이 숫자만 입력하세요."
                v-model="companyPhone"
              />
              <p class="validation-text">
                <span class="warning" v-if="!isCompanyPhoneCheck">
                  전화번호 형식(01012345678, 021234567)에 맞춰, 숫자만 입력
                  바랍니다.
                </span>
              </p>
            </td>
          </tr>
          <tr class="mail">
            <th>업체 대표메일</th>
            <td>
              <input
                type="text"
                placeholder="이메일을 입력하세요."
                v-model="companyEmail"
              />
              <p class="validation-text">
                <span class="warning" v-if="!isCompanyEmailCheck">
                  이메일 형식(xxxx@email.com)에 맞춰 입력 바랍니다.
                </span>
              </p>
            </td>
          </tr>
          <tr class="tel">
            <th>업체 담당자 연락처</th>
            <td>
              <input
                type="text"
                placeholder="- 없이 숫자만 입력하세요."
                v-model="companyManagerPhone"
              />
              <p class="validation-text">
                <span class="warning" v-if="!isCompanyManagerPhoneCheck">
                  전화번호 형식(01012345678, 021234567)에 맞춰, 숫자만 입력
                  바랍니다.
                </span>
              </p>
            </td>
          </tr>
          <tr class="mail">
            <th>업체 담당자 메일</th>
            <td>
              <input
                type="text"
                placeholder="담당자 이메일을 입력하세요."
                v-model="companyManagerEmail"
              />
              <p class="validation-text">
                <span class="warning" v-if="!isCompanyManagerEmailCheck">
                  이메일 형식(xxxx@email.com)에 맞춰 입력 바랍니다.
                </span>
              </p>
            </td>
          </tr>
        </table>
        <div class="button-box">
          <a href="#" class="cancel">취소</a>
          <button
            :disabled="
              !isCompanyNameCheck &&
              !isCompanyAddressCheck &&
              !isCompanyCeoNameCheck &&
              !isCompanyManagerNameCheck &&
              !isCompanyPhoneCheck &&
              !isCompanyEmailCheck &&
              !isCompanyManagerPhoneCheck &&
              !isCompanyManagerEmailCheck
            "
            type="submit"
            class="registration"
          >
            생성
          </button>
        </div>
      </form>
    </section>
  </div>
</template>

<script>
import { registerRequest } from '../../api/company.js';
import {
  isNullOrWhiteSpaceCheck,
  isLengthCheck,
  isEmailCheck,
  isPhoneCheck,
  isTextCheck,
} from '../../utils/validate.js';

export default {
  data() {
    return {
      companyName: '',
      companyAddress: '',
      companyCeoName: '',
      companyManagerName: '',
      companyPhone: '',
      companyEmail: '',
      companyManagerPhone: '',
      companyManagerEmail: '',
    };
  },
  computed: {
    isCompanyNameCheck() {
      return (
        isNullOrWhiteSpaceCheck(this.companyName) &&
        isLengthCheck(this.companyName)
      );
    },
    isCompanyAddressCheck() {
      return (
        isNullOrWhiteSpaceCheck(this.companyAddress) &&
        isLengthCheck(this.companyAddress)
      );
    },
    isCompanyCeoNameCheck() {
      return (
        isNullOrWhiteSpaceCheck(this.companyCeoName) &&
        isLengthCheck(this.companyCeoName)
      );
    },
    isCompanyManagerNameCheck() {
      return (
        isNullOrWhiteSpaceCheck(this.companyManagerName) &&
        isLengthCheck(this.companyManagerName)
      );
    },
    isCompanyPhoneCheck() {
      return (
        isNullOrWhiteSpaceCheck(this.companyPhone) &&
        isLengthCheck(this.companyPhone) &&
        isTextCheck(this.companyPhone) &&
        isPhoneCheck(this.companyPhone)
      );
    },
    isCompanyEmailCheck() {
      return (
        isNullOrWhiteSpaceCheck(this.companyEmail) &&
        isLengthCheck(this.companyEmail) &&
        isEmailCheck(this.companyEmail)
      );
    },
    isCompanyManagerPhoneCheck() {
      return (
        isNullOrWhiteSpaceCheck(this.companyManagerPhone) &&
        isLengthCheck(this.companyManagerPhone) &&
        isTextCheck(this.companyManagerPhone) &&
        isPhoneCheck(this.companyManagerPhone)
      );
    },
    isCompanyManagerEmailCheck() {
      return (
        isNullOrWhiteSpaceCheck(this.companyManagerEmail) &&
        isLengthCheck(this.companyManagerEmail) &&
        isEmailCheck(this.companyManagerEmail)
      );
    },
  },
  methods: {
    async submitForm() {
      console.log(this.isValidationCheck());
      const param = {
        name: this.companyName,
        address: this.companyAddress,
        ceoName: this.companyCeoName,
        phone: this.companyPhone,
        email: this.companyEmail,
        managerName: this.companyManagerName,
        managerPhone: this.companyManagerPhone,
        managerEmail: this.companyManagerEmail,
        userId: this.$store.state.id,
      };

      console.log(param);

      const { data } = await registerRequest(param);

      // console.log(data);

      if (data.result === 'fail') {
        alert('업체 등록에 실패했습니다. 입력값을 확인해주세요');
        // this.initForm();
      } else {
        alert('신규 업체가 등록되었습니다.');
        this.$router.push('/admin/company/list');
      }
    },
    isValidationCheck() {},
    initForm() {
      this.companyName = '';
      this.companyAddress = '';
      this.companyCeoName = '';
      this.companyPhone = '';
      this.companyEmail = '';
      this.companyManagerName = '';
      this.companyManagerPhone = '';
      this.companyManagerEmail = '';
    },
  },
};
</script>

<style scoped>
.table3 th {
  width: 20%;
  background: #ddd;
  padding: 8px;
}
.table3 input {
  border: 1px solid #747474;
  border-radius: 3px;
  color: #747474;
  box-sizing: border-box;
  padding: 7px;
  width: 400px;
}

.table3 select,
.table3 textarea {
  border: 1px solid #747474;
  border-radius: 3px;
  font-size: 14px;
  font-weight: 600;
  color: #808080;
  padding: 7px 7px;
  box-sizing: border-box;
}
.table3 select {
  width: 200px;
}
</style>
