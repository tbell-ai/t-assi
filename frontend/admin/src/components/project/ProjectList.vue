<template>
  <div class="main-right">
    <div class="title">
      <h2>프로젝트 목록 조회</h2>
    </div>
    <section class="section1">
      <ul class="search">
        <li class="filter-title">상태</li>
        <li>
          <select v-model="projectState">
            <option value="전체">전체</option>
            <option value="대기">대기</option>
            <option value="진행">진행</option>
            <option value="보류">보류</option>
            <option value="종료">종료</option>
          </select>
        </li>
        <li>
          <button class="but-search" @click="fetchData(0)">검색</button>
        </li>
        <li>
          <button class="but-reset" @click="searchAreaInit">초기화</button>
        </li>
      </ul>
    </section>
    <section class="section2">
      <table class="table1">
        <tr>
          <th class="table-no">No</th>
          <th>프로젝트명</th>
          <th>프로젝트 리더</th>
          <th>계약 업체 담당자</th>
          <th>프로젝트 검증대상</th>
          <th>프로젝트 테스트 유형</th>
          <th>프로젝트 시작일</th>
        </tr>
        <tr v-for="(item, index) in listItem" :key="index">
          <td scope="row">{{ item.projectId }}</td>
          <td>
            <a href="javascript:;" @click="routeContactView(item.projectId)">{{
              item.contractName
            }}</a>
          </td>
          <td>{{ item.projectLeader }}</td>
          <td>{{ item.contractManagerName }}</td>
          <td>{{ item.projectServiceType }}</td>
          <td>{{ item.projectTestType }}</td>
          <td>{{ item.contractStart }}</td>
        </tr>
      </table>
      <div class="button-box2">
        <router-link to="/admin/project/create" class="project-manager"
          >신규 프로젝트 생성</router-link
        >
      </div>
      <div class="page">
        <button
          class="left"
          :disabled="!prevButtonCheck"
          @click="fetchData(page.pageNumber - 1)"
        >
          <img src="../../assets/admin_image/page-left.svg" alt="" />
        </button>
        <ul>
          <li v-for="paging in getListCount" :key="paging">
            <button
              :class="{
                active: paging - 1 === page.pageNumber,
              }"
              @click="fetchData(paging - 1)"
            >
              {{ paging }}
            </button>
          </li>
        </ul>
        <button
          class="right"
          :disabled="!nextButtonCheck"
          @click="fetchData(page.pageNumber + 1)"
        >
          <img src="../../assets/admin_image/page-right.svg" alt="" />
        </button>
      </div>
    </section>
  </div>
</template>

<script>
import { fetchRequests } from '../../api/project.js';

export default {
  created() {
    this.fetchData(0);
  },
  data() {
    return {
      listItem: [],
      page: {},
      projectState: '전체',
    };
  },
  computed: {
    prevButtonCheck() {
      return this.page.prev;
    },
    nextButtonCheck() {
      return this.page.next;
    },
    getListCount() {
      const count = [];
      for (let i = this.getPage; i <= this.getEndPage; i += 1) {
        count.push(i);
      }
      return count;
    },
    getPage() {
      const unit = this.getDigit(this.page.pageNumber);
      const start = this.page.pageNumber - unit;

      return start + 1;
    },
    getEndPage() {
      let lastPage = parseInt(this.page.pageNumber / 10) * 10 + 10;
      return lastPage <= this.page.totalPages ? lastPage : this.page.totalPages;
    },
  },
  methods: {
    async fetchData(page) {
      const param = this.settingParam(page);
      const { data } = await fetchRequests(param);
      // console.log(data);
      if (data.result === 'success') {
        this.listItem = data.data.projectData;
        this.page = data.data.paging;
      } else {
        alert('페이지 비정상 접속');
      }
    },
    routeContactView(id) {
      this.$router.push(`/admin/project/view/${id}`);
    },
    getDigit(num) {
      let result = 0;

      if (num > 0) {
        result = num % 10;
      } else {
        result = 0;
      }

      return result;
    },
    settingParam(pageNum) {
      const param = {
        projectState: '',
        page: pageNum,
      };

      if (this.projectState === '전체') {
        param.projectState = '';
      } else {
        param.projectState = this.projectState;
      }

      return param;
    },
    searchAreaInit() {
      this.projectState = '전체';
    },
  },
};
</script>

<style scoped>
.project-manager {
  float: right;
  display: block;
  padding: 10px;
  box-sizing: border-box;
  width: 150px;
  background: #000;
  color: #fff;
  font-weight: 700;
  text-align: center;
  margin-top: 10px;
}
.table1 tr th {
  background: #ddd;
}

.table1 tr td {
  text-align: center;
}
</style>
