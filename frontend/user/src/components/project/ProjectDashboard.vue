<template>
  <div id="wrap">
    <ProjectMenu></ProjectMenu>
    <div id="container">
      <main>
        <h2 class="dashboard-h2">프로젝트 대시보드</h2>
        <section class="dashboard">
          <article class="dashboard-type1 dashboard1">
            <div class="dashboard-content">
              <div class="content-left">
                <div class="dashboard-content-element">
                  <span id="count">{{ dashboard.projectPercent }}%</span>
                  <span>테스트 진행률</span>
                </div>
              </div>
              <div class="content-right">
                <div class="dashboard-content-element">
                  <span id="count">{{ dashboard.projectTotalDefect }}개</span>
                  <span>전체 결함</span>
                </div>
                <div class="dashboard-content-element">
                  <span id="count">{{ dashboard.projectRemainDefect }}개</span>
                  <span>잔여 결함</span>
                </div>
                <div class="dashboard-content-element">
                  <span id="count">{{ dashboard.projectSolveDefect }}개</span>
                  <span>해결 결함</span>
                </div>
                <div class="dashboard-content-element">
                  <span id="count">{{ dashboard.projectNeglectDefect }}개</span>
                  <span>방치 결함</span>
                </div>
              </div>
            </div>
          </article>
          <article class="dashboard-type2 dashboard2">
            <div class="dashboard-content">
              <button id="bts-account" @click="showModalAccount = true">
                BTS 계정 보기
              </button>
              <Teleport to="body">
                <!-- use the modal component, pass in the prop -->
                <FrontModal
                  :show="showModalAccount"
                  @close="showModalAccount = false"
                >
                  <template #header>
                    <h3>BTS 계정 정보</h3>
                  </template>
                  <template #body>
                    <p>아이디 : {{ dashboard.projectBtsAccount }}</p>
                    <p>비밀번호 : {{ dashboard.projectBtsPassword }}</p>
                  </template>
                </FrontModal>
              </Teleport>

              <button id="bts-detatil" @click="showModalLink = true">
                BTS 링크 보기
              </button>
              <Teleport to="body">
                <!-- use the modal component, pass in the prop -->
                <FrontModal
                  :show="showModalLink"
                  @close="showModalLink = false"
                >
                  <template #header>
                    <h3>접속 링크 정보</h3>
                  </template>
                  <template #body>
                    <p>{{ dashboard.projectBtsLink }}</p>
                  </template>
                </FrontModal>
              </Teleport>
              <button id="bts-detatil" @click="exportProject">
                프로젝트 내보내기
              </button>
            </div>
          </article>
          <article class="dashboard-type3 dashboard3">
            <div class="dashboard-title">
              <div class="dashboard-title-left">
                <img
                  src="../../assets/front_image/analytics_black_24_dp.svg"
                  alt=""
                />
                <span id="title">금일 테스트 현황</span>
              </div>
            </div>
            <div class="dashboard-content">
              <div class="chart-container">
                <canvas id="myChart"></canvas>
              </div>
            </div>
          </article>
          <article class="dashboard-type3 dashboard3">
            <div class="dashboard-title">
              <div class="dashboard-title-left">
                <img
                  src="../../assets/front_image/question_answer_black_24_dp_1.svg"
                  alt=""
                />
                <span id="title">프로젝트 질의 목록</span>
              </div>
              <div class="dashboard-title-right">
                <span
                  ><a href="javascript:;" @click="routeQnaView">더보기</a></span
                >
              </div>
            </div>
            <div class="dashboard-content">
              <div
                class="dashboard-content-element"
                v-for="(item, index) in boardList"
                :key="index"
              >
                <p id="cate">{{ item.type }}</p>
                <p id="content" @click="routeBoardView(item.id)">
                  {{ item.subject }}
                </p>
                <p id="date">{{ item.createDate }}</p>
              </div>
            </div>
          </article>
        </section>
      </main>
    </div>
  </div>
</template>

<script>
import FrontModal from '../common/FrontModal.vue';
import ProjectMenu from '../project/ProjectMenu.vue';
import { fetchDashboard } from '../../api/project.js';
import { Chart } from 'chart.js';

export default {
  created() {
    this.fetchData();
  },
  components: {
    ProjectMenu,
    FrontModal,
  },
  data() {
    return {
      dashboard: {},
      boardList: [],
      showModalAccount: false,
      showModalLink: false,
      myChart: null,
    };
  },
  methods: {
    async fetchData() {
      const id = this.$route.params.id;
      const { data } = await fetchDashboard(id);

      this.dashboard = data.data.dashboard;
      this.boardList = data.data.boardList;
      this.fillChart();
    },
    fillChart() {
      const context = document.getElementById('myChart').getContext('2d');
      this.myChart = new Chart(context, {
        type: 'bar',
        data: {
          labels: ['전체 결함', '잔여 결함', '해결 결함', '방치 결함'],
          datasets: [
            {
              label: '테스트 현황 정보',
              borderWidth: 1,
              backgroundColor: [
                'rgba(255, 67, 67, 0.2)',
                'rgba(255, 67, 67, 0.2)',
                'rgba(255, 67, 67, 0.2)',
                'rgba(255, 67, 67, 0.2)',
              ],
              borderColor: [
                'rgba(255, 67, 67, 1)',
                'rgba(255, 67, 67, 1)',
                'rgba(255, 67, 67, 1)',
                'rgba(255, 67, 67, 1)',
              ],
              data: [
                this.dashboard.projectTotalDefect,
                this.dashboard.projectRemainDefect,
                this.dashboard.projectSolveDefect,
                this.dashboard.projectNeglectDefect,
              ],
            },
          ],
        },
        options: {
          scales: {
            yAxes: [
              {
                ticks: {
                  beginAtZero: true,
                  fontSize: 14,
                },
              },
            ],
          },
        },
      });
    },
    exportProject() {
      alert('현재 지원하지 않는 기능입니다.');
      // confirm('프로젝트 내보내기 신청을 진행하시겠습니까?');
    },
    routeBoardView(id) {
      this.$router.push(`/project/qna-view/${id}`);
    },
    routeQnaView() {
      const id = this.$route.params.id;
      this.$router.push(`/project/qna-list/${id}`);
    },
  },
};
</script>

<style scoped>
@import '../../css/pj_dashboard.css';

canvas#myChart {
  width: 100%;
  height: 500px !important;
}

.dashboard-content-element:hover p:nth-child(2) {
  cursor: pointer;
}
</style>
