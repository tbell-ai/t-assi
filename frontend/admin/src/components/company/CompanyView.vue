<template>
  <div class="main-right">
    <div class="title">
      <h2>업체 상세 조회</h2>
    </div>
    <section class="section1">
      <div class="s-top">
        <b>업체정보</b>
        <a href="javascript:;" class="listbutton" @click="routeCompanyList"
          >목록</a
        >
      </div>
      <div class="before">
        <table class="table3">
          <tr>
            <th>업체명</th>
            <td>{{ companyData.name }}</td>
          </tr>
          <tr>
            <th>업체주소</th>
            <td>{{ companyData.address }}</td>
          </tr>
          <tr>
            <th>업체 대표자</th>
            <td>{{ companyData.ceoName }}</td>
          </tr>
          <tr>
            <th>업체 담당자</th>
            <td>{{ companyData.managerName }}</td>
          </tr>
          <tr>
            <th>업체 대표번호</th>
            <td>{{ companyData.phone }}</td>
          </tr>
          <tr>
            <th>업체 대표메일</th>
            <td>{{ companyData.email }}</td>
          </tr>
          <tr>
            <th>업체 담당자 연락처</th>
            <td>{{ companyData.managerPhone }}</td>
          </tr>
          <tr>
            <th>업체 담당자 메일</th>
            <td>{{ companyData.managerEmail }}</td>
          </tr>
          <tr>
            <th>등록일</th>
            <td>{{ companyData.createDate }}</td>
          </tr>
          <tr>
            <th>등록자</th>
            <td>{{ companyData.writer }}</td>
          </tr>
        </table>
      </div>
      <div>
        <button class="admin-button" @click="routeCompanyEdit">수정</button>
        <button class="admin-button" @click="deleteCompany">삭제</button>
      </div>
    </section>
  </div>
</template>

<script>
import { fetchRequest } from '../../api/company.js';

export default {
  created() {
    this.fetchData();
  },
  data() {
    return {
      companyData: {},
    };
  },
  methods: {
    async fetchData() {
      const id = this.$route.params.id;
      const { data } = await fetchRequest(id);
      console.log(data);
      this.companyData = data.data;
    },
    routeCompanyEdit() {
      this.$router.push(`/admin/company/edit/${this.companyData.id}`);
    },
    routeCompanyList() {
      this.$router.push('/admin/company/list');
    },
    deleteCompany() {
      alert('현재 지원하지 않는 기능입니다.');
    },
  },
};
</script>

<style scoped>
.admin-button {
  float: right;
  padding: 7px 10px;
  width: 100px;
  box-sizing: border-box;
  text-align: center;
  border: none;
  background: #000;
  color: #fff;
  font-weight: 700;
  margin-top: 10px;
  margin-right: 10px;
}
.table3 th {
  width: 20%;
  background: #ddd;
  padding: 8px;
}
</style>
