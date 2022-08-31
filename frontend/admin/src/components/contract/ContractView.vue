<template>
  <div class="main-right">
    <div class="title">
      <h2>계약 상세 조회</h2>
    </div>
    <section class="section1">
      <div class="s-top">
        <b>계약정보</b>
        <router-link to="/admin/contract/list" class="admin-button"
          >목록</router-link
        >
      </div>
      <div class="before">
        <table class="table3">
          <tr>
            <th>계약명</th>
            <td>{{ contractData.contractName }}</td>
          </tr>
          <tr>
            <th>계약 금액</th>
            <td>{{ contractData.contractCharge }}</td>
          </tr>
          <tr>
            <th>계약 일자</th>
            <td>{{ contractData.contractDay }}</td>
          </tr>
          <tr>
            <th>계약 기간</th>
            <td>
              {{ contractData.contractStart }} ~ {{ contractData.contractEnd }}
            </td>
          </tr>
          <tr>
            <th>계약 내용</th>
            <td>
              {{ contractData.contractContent }}
            </td>
          </tr>
          <tr>
            <th>투입 인원</th>
            <td>
              <ul>
                <li>초급 : {{ contractData.lowLevel }} 명</li>
                <li>중급 : {{ contractData.middleLevel }} 명</li>
                <li>고급 : {{ contractData.highLevel }} 명</li>
                <li>특급 : {{ contractData.specialLevel }} 명</li>
              </ul>
            </td>
          </tr>
          <tr>
            <th>산출물 종류</th>
            <td>{{ contractData.resultDocument }}</td>
          </tr>
          <tr>
            <th>산출물 전달 방법</th>
            <td>{{ contractData.resultDelivery }}</td>
          </tr>
          <tr>
            <th>담당자</th>
            <td>{{ contractData.companyManagerName }}</td>
          </tr>
          <tr>
            <th>담당자 연락처</th>
            <td>{{ contractData.companyManagerPhone }}</td>
          </tr>
          <tr>
            <th>등록일</th>
            <td>{{ contractData.createDate }}</td>
          </tr>
        </table>
      </div>
      <div>
        <button class="admin-button" @click="routeContractEdit">수정</button>
        <button class="admin-button" @click="deleteCotract">삭제</button>
      </div>
    </section>
  </div>
</template>

<script>
import { fetchRequest } from '../../api/contract.js';

export default {
  created() {
    this.fetchData();
  },
  data() {
    return {
      contractData: {},
    };
  },
  methods: {
    async fetchData() {
      const id = this.$route.params.id;
      const { data } = await fetchRequest(id);
      console.log(data);
      this.contractData = data.data;
    },
    deleteCotract() {
      alert('현재 삭제 기능은 지원하지 않습니다.');
    },
    routeContractEdit() {
      this.$router.push(`/admin/contract/edit/${this.contractData.contractId}`);
    },
    routeContractList() {
      this.$router.push('/admin/contract/list');
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
