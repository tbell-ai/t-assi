<template>
  <div id="wrap">
    <ProjectMenu></ProjectMenu>
    <div id="container">
      <main id="pj-main">
        <div class="pj-wrap">
          <div class="pj-title-area">
            <img
              src="../../assets/front_image/history_edu_black_24_dp.svg"
              alt=""
            />
            <span>프로젝트 개요</span>
          </div>
          <div class="pj-content-area">
            <section class="pj-outline">
              <article class="table-type1">
                <p>프로젝트 정보</p>
                <table class="table1">
                  <tr>
                    <th>프로젝트 명</th>
                    <td>{{ project.projectContractName }}</td>
                    <th>프로젝트 리더</th>
                    <td>{{ project.projectLeader }}</td>
                  </tr>
                  <tr>
                    <th>검증 대상</th>
                    <td>{{ project.projectServiceType }}</td>
                    <th>테스트 유형</th>
                    <td>{{ project.projectTestType }}</td>
                  </tr>
                  <tr>
                    <th>프로젝트 개요</th>
                    <td colspan="3">
                      {{ project.projectSummary }}
                    </td>
                  </tr>
                  <tr>
                    <th>프로젝트 계획</th>
                    <td colspan="3">
                      {{ project.projectPlan }}
                    </td>
                  </tr>
                </table>
              </article>
              <article class="table-type1">
                <p>계약 정보</p>
                <table class="table2">
                  <tr>
                    <th>계약명</th>
                    <td>{{ project.projectContractName }}</td>
                  </tr>
                  <tr>
                    <th>계약일</th>
                    <td>{{ project.projectContractDay }}</td>
                  </tr>
                  <tr>
                    <th>업체명</th>
                    <td>{{ project.projectCustomerCompany }}</td>
                  </tr>
                  <tr>
                    <th>업체 담당자</th>
                    <td>{{ project.projectCustomerName }}</td>
                  </tr>
                  <tr>
                    <th>업체 담당자 이메일</th>
                    <td>{{ project.projectCustomerEmail }}</td>
                  </tr>
                  <tr>
                    <th>업체 담당자 전화번호</th>
                    <td>{{ project.projectCustomerPhone }}</td>
                  </tr>
                  <tr>
                    <th>최종 산출물</th>
                    <td>{{ project.projectResultDocument }}</td>
                  </tr>
                  <tr>
                    <th>산출물 전달 방법</th>
                    <td>{{ project.projectResultDelivery }}</td>
                  </tr>
                </table>
              </article>
            </section>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script>
import ProjectMenu from '../project/ProjectMenu.vue';
import { fetchRequest } from '../../api/project.js';

export default {
  created() {
    this.fetchRequest();
  },
  components: {
    ProjectMenu,
  },
  data() {
    return {
      project: {},
    };
  },
  methods: {
    async fetchRequest() {
      const id = this.$route.params.id;
      const { data } = await fetchRequest(id);

      this.project = data.data;
    },
  },
};
</script>

<style scoped>
@import '../../css/pj_intro.css';

.table1 tr th {
  width: 20%;
  background: #ddd;
}
.table1 tr td {
  width: 40%;
  text-align: left;
}

.table2 tr th {
  width: 20%;
  background: #ddd;
}
.table2 tr td {
  width: 80%;
  text-align: left;
}
</style>
