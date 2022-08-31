<template>
  <div class="main-right">
    <!-- 타이틀 -->
    <div class="title">
      <h2>업체 수정</h2>
    </div>
    <section class="section1">
      <div class="s-top">
        <b>업체정보수정</b>
      </div>
      <form @submit.prevent="submitForm">
        <table class="table3">
          <tr>
            <th>업체명</th>
            <td><input type="text" v-model="companyName" /></td>
          </tr>
          <tr>
            <th>업체주소</th>
            <td>
              <input type="text" v-model="companyAddress" />
            </td>
          </tr>
          <tr>
            <th>업체 대표자</th>
            <td><input type="text" v-model="companyCeoName" /></td>
          </tr>
          <tr>
            <th>업체 담당자</th>
            <td><input type="text" v-model="companyManagerName" /></td>
          </tr>
          <tr>
            <th>업체 대표번호</th>
            <td>
              <input type="text" v-model="companyPhone" />
            </td>
          </tr>
          <tr>
            <th>업체 대표메일</th>
            <td>
              <input type="email" v-model="companyEmail" />
            </td>
          </tr>
          <tr>
            <th>업체 담당자 연락처</th>
            <td>
              <input type="text" v-model="companyManagerPhone" />
            </td>
          </tr>
          <tr>
            <th>업체 담당자 메일</th>
            <td>
              <input type="email" v-model="companyManagerEmail" />
            </td>
          </tr>
        </table>
        <div class="button-box">
          <a href="javascript:;" class="cancel" @click="routeCompanyList"
            >취소</a
          >
          <button type="submit" class="registration">수정</button>
        </div>
      </form>
    </section>
  </div>
</template>

<script>
import { fetchRequest, editRequest } from '../../api/company.js';

export default {
  created() {
    this.fetchCompany();
  },
  data() {
    return {
      companyId: '',
      companyName: '',
      companyAddress: '',
      companyCeoName: '',
      companyManagerName: '',
      companyPhone: '',
      companyEmail: '',
      companyManagerPhone: '',
      companyManagerEmail: '',
      companyState: '',
      userId: '',
    };
  },
  methods: {
    async fetchCompany() {
      const id = this.$route.params.id;
      const { data } = await fetchRequest(id);
      this.companyId = data.data.id;
      this.companyName = data.data.name;
      this.companyAddress = data.data.address;
      this.companyCeoName = data.data.ceoName;
      this.companyManagerName = data.data.managerName;
      this.companyPhone = data.data.phone;
      this.companyEmail = data.data.email;
      this.companyManagerPhone = data.data.managerPhone;
      this.companyManagerEmail = data.data.managerEmail;
      this.companyState = data.data.state;
      this.userId = data.data.userId;
    },
    async submitForm() {
      const contactData = {
        id: this.companyId,
        name: this.companyName,
        address: this.companyAddress,
        ceoName: this.companyCeoName,
        managerName: this.companyManagerName,
        phone: this.companyPhone,
        email: this.companyEmail,
        managerPhone: this.companyManagerPhone,
        managerEmail: this.companyManagerEmail,
        state: this.companyState,
        userId: this.userId,
      };

      const { data } = await editRequest(contactData);
      console.log(data);

      if (data.result === 'success') {
        alert(data.message);
        this.$router.push(`/admin/company/view/${this.companyId}`);
      } else if (data.result === 'fail') {
        alert(data.message);
      }
    },
    routeCompanyList() {
      this.$router.push('/admin/company/list');
    },
  },
};
</script>

<style scoped>
/* 표3 */
.table3 th {
  width: 20%;
  background: #ddd;
  padding: 8px;
}
.table3 div {
  margin: 0 20px;
}
.table3 input {
  border: 1px solid #747474;
  border-radius: 3px;
  color: #747474;
  box-sizing: border-box;
  padding: 0 10px;
  width: 200px;
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
.table3 textarea {
  width: 450px;
}
.cancel {
  margin-left: 20px;
}
</style>
