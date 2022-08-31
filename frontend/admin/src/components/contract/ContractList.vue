<template>
  <div class="main-right">
    <!-- 타이틀 -->
    <div class="title">
      <h2>계약 목록 조회</h2>
    </div>
    <section class="section1">
      <ul class="search">
        <li class="filter-title">상태</li>
        <li>
          <select v-model="contractState">
            <option value="전체">전체</option>
            <option value="계약대기">계약대기</option>
            <option value="계약완료">계약완료</option>
            <option value="계약진행">계약진행</option>
            <option value="계약종료">계약종료</option>
          </select>
        </li>
        <li class="filter-title filter-title2">검색어</li>
        <li class="growbox">
          <input
            type="search"
            placeholder="검색어를 입력해주세요."
            v-model="searchKeyword"
          />
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
          <th>계약명</th>
          <th>계약금액</th>
          <th>업체명</th>
          <th>담당자명</th>
          <th>계약일자</th>
        </tr>
        <tr v-for="(item, index) in listItem" :key="index">
          <td scope="row">{{ item.contactId }}</td>
          <td>
            <a href="javascript:;" @click="routeContactView(item.contactId)">{{
              item.contractName
            }}</a>
          </td>
          <td>{{ item.contractCharge + ' 원' }}</td>
          <td>{{ item.companyName }}</td>
          <td>{{ item.companyManagerName }}</td>
          <td>{{ item.contractDay }}</td>
        </tr>
      </table>
      <div class="button-box2">
        <router-link to="/admin/contract/create" class="contract-manager"
          >신규 계약 생성</router-link
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
import { fetchRequests } from '../../api/contract.js';

export default {
  created() {
    this.fetchData(0);
  },
  data() {
    return {
      listItem: [],
      page: {},
      contractState: '전체',
      searchKeyword: '',
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
      if (data.result === 'success') {
        this.listItem = data.data.contractData;
        this.page = data.data.paging;
      } else {
        alert('페이지 비정상 접속');
      }
    },
    routeContactView(id) {
      this.$router.push(`/admin/contract/view/${id}`);
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
        contractName: '',
        contractState: '',
        page: pageNum,
      };

      param.contractName = this.searchKeyword;

      if (this.contractState === '전체') {
        param.contractState = '';
      } else {
        param.contractState = this.contractState;
      }

      return param;
    },
    searchAreaInit() {
      this.searchFilter = '전체';
      this.searchKeyword = '';
    },
  },
};
</script>

<style scoped>
.contract-manager {
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
