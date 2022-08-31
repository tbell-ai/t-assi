<template>
  <div class="main-right">
    <div class="title">
      <h2>프로젝트 상세 조회</h2>
    </div>
    <section class="section1">
      <div class="s-top">
        <b>계약자 정보</b>

        <a
          href="javascript:;"
          class="admin-button width-200"
          @click="routeUserView(projectData.userId)"
          >계약자 정보 상세 보기</a
        >
      </div>
      <div class="before">
        <table class="table3">
          <tr>
            <th>이름</th>
            <td>{{ projectData.projectCustomerName }}</td>
          </tr>
          <tr>
            <th>이메일</th>
            <td>{{ projectData.projectCustomerEmail }}</td>
          </tr>
          <tr>
            <th>연락처</th>
            <td>{{ projectData.projectCustomerPhone }}</td>
          </tr>
          <tr>
            <th>소속</th>
            <td>{{ projectData.projectCustomerCompany }}</td>
          </tr>
        </table>
      </div>
    </section>
    <section class="section1">
      <div class="s-top">
        <b>계약 정보</b>

        <a
          href="javascript:;"
          class="admin-button width-200"
          @click="routeContractView(projectData.contractId)"
          >계약 정보 상세 보기</a
        >
      </div>
      <div class="before">
        <table class="table3">
          <tr>
            <th>계약명</th>
            <td>{{ projectData.projectContractName }}</td>
          </tr>
          <tr>
            <th>계약일</th>
            <td>{{ projectData.projectContractDay }}</td>
          </tr>
          <tr>
            <th>투입인원</th>
            <td>
              <ul>
                <li>초급 : {{ projectData.lowLevel }} 명</li>
                <li>중급 : {{ projectData.middleLevel }} 명</li>
                <li>고급 : {{ projectData.highLevel }} 명</li>
                <li>특급 : {{ projectData.specialLevel }} 명</li>
              </ul>
            </td>
          </tr>
          <tr>
            <th>산출물</th>
            <td>{{ projectData.projectResultDocument }}</td>
          </tr>
          <tr>
            <th>산출물 전달방법</th>
            <td>{{ projectData.projectResultDelivery }}</td>
          </tr>
        </table>
      </div>
    </section>
    <section class="section1">
      <div class="s-top">
        <b>프로젝트 정보</b>
      </div>
      <div class="before">
        <table class="table3">
          <tr>
            <th>프로젝트 리더</th>
            <td>{{ projectData.projectLeader }}</td>
          </tr>
          <tr>
            <th>프로젝트 요약</th>
            <td>{{ projectData.projectSummary }}</td>
          </tr>
          <tr>
            <th>수행계획</th>
            <td>{{ projectData.projectPlan }}</td>
          </tr>
          <tr>
            <th>프로젝트 검증 대상</th>
            <td>{{ projectData.projectServiceType }}</td>
          </tr>
          <tr>
            <th>프로젝트 테스트 유형</th>
            <td>{{ projectData.projectTestType }}</td>
          </tr>
          <tr>
            <th>BTS 링크</th>
            <td>{{ projectData.projectBtsLink }}</td>
          </tr>
          <tr>
            <th>BTS 계정</th>
            <td>{{ projectData.projectBtsAccount }}</td>
          </tr>
          <tr>
            <th>BTS 계정 암호</th>
            <td>{{ projectData.projectBtsPassword }}</td>
          </tr>
          <tr>
            <th>프로젝트 진행률</th>
            <td>{{ projectData.projectPercent + ' %' }}</td>
          </tr>
          <tr>
            <th>프로젝트 발생 결함</th>
            <td>{{ projectData.projectTotalDefect + ' 개' }}</td>
          </tr>
          <tr>
            <th>프로젝트 잔여 결함</th>
            <td>{{ projectData.projectRemainDefect + ' 개' }}</td>
          </tr>
          <tr>
            <th>프로젝트 해결 결함</th>
            <td>{{ projectData.projectSolveDefect + ' 개' }}</td>
          </tr>
          <tr>
            <th>프로젝트 방치 결함</th>
            <td>{{ projectData.projectNeglectDefect + ' 개' }}</td>
          </tr>
          <tr>
            <th>등록일</th>
            <td>{{ projectData.createDate }}</td>
          </tr>
          <tr>
            <th>프로젝트 상태</th>
            <td>{{ projectData.projectState }}</td>
          </tr>
        </table>
      </div>
      <div>
        <router-link to="/admin/project/list" class="admin-button"
          >목록</router-link
        >
        <button class="admin-button" @click="routeProjectEdit">수정</button>
        <button class="admin-button" @click="deleteProject">삭제</button>
      </div>
    </section>
  </div>
</template>

<script>
import { fetchRequest } from '../../api/project.js';

export default {
  created() {
    this.fetchData();
  },
  data() {
    return {
      projectData: {},
    };
  },
  methods: {
    async fetchData() {
      const id = this.$route.params.id;
      const { data } = await fetchRequest(id);
      // console.log(data);
      this.projectData = data.data;
    },
    deleteProject() {
      alert('현재 삭제 기능은 지원하지 않습니다.');
    },
    routeProjectEdit() {
      this.$router.push(`/admin/project/edit/${this.projectData.projectId}`);
    },
    routeProjectList() {
      this.$router.push('/admin/project/list');
    },
    routeContractView(id) {
      this.$router.push(`/admin/contract/view/${id}`);
    },
    routeUserView(id) {
      this.$router.push(`/admin/account/view/${id}`);
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
.width-200 {
  width: 200px;
}
.table3 th {
  width: 20%;
  background: #ddd;
  padding: 8px;
}
</style>
