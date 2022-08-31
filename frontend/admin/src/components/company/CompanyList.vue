<template>
  <div class="main-right">
    <div class="title">
      <h2>업체 목록 조회</h2>
    </div>
    <section class="section1">
      <ul class="search">
        <li class="filter-title">검색 필터</li>
        <li>
          <select v-model="searchFilter">
            <option value="전체">전체</option>
            <option value="업체명">업체명</option>
            <option value="대표자명">대표자명</option>
            <option value="담당자명">담당자명</option>
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
          <th>No</th>
          <th>업체명</th>
          <th>대표자명</th>
          <th>담당자명</th>
          <th>등록일</th>
          <th>등록자</th>
        </tr>
        <tr v-for="(item, index) in listItem" :key="index">
          <td scope="row">{{ item.companyId }}</td>
          <td>
            <a href="javascript:;" @click="routeContactView(item.companyId)">{{
              item.companyName
            }}</a>
          </td>
          <td>{{ item.companyCeoName }}</td>
          <td>{{ item.companyManagerName }}</td>
          <td>{{ item.createDate }}</td>
          <td>{{ item.userName }}</td>
        </tr>
      </table>
      <div class="button-box2">
        <router-link to="/admin/company/create" class="manager"
          >업체생성</router-link
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
import { fetchRequests } from '../../api/company.js';

export default {
  created() {
    this.fetchData(0);
  },
  data() {
    return {
      listItem: [],
      page: {},
      companyName: '',
      companyCeoName: '',
      companyManagerName: '',
      searchFilter: '전체',
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
      // console.log(data);
      if (data.result === 'success') {
        this.listItem = data.data.companyData;
        this.page = data.data.paging;
      } else {
        alert('페이지 비정상 접속');
      }
    },
    routeContactView(id) {
      this.$router.push(`/admin/company/view/${id}`);
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
        companyName: '',
        companyCeoName: '',
        companyManagerName: '',
        page: pageNum,
      };

      if (this.searchFilter === '업체명') {
        param.companyName = this.searchKeyword;
      }

      if (this.searchFilter === '대표자명') {
        param.companyCeoName = this.searchKeyword;
      }

      if (this.searchFilter === '담당자명') {
        param.companyManagerName = this.searchKeyword;
      }

      if (this.searchFilter === '전체') {
        param.companyName = this.searchKeyword;
        param.companyCeoName = this.searchKeyword;
        param.companyManagerName = this.searchKeyword;
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
.manager {
  float: right;
  display: block;
  padding: 10px;
  box-sizing: border-box;
  width: 100px;
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
