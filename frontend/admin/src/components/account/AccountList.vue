<template>
  <div class="main-right">
    <!-- 타이틀 -->
    <div class="title">
      <h2>회원 목록 조회</h2>
    </div>
    <section class="section1">
      <ul class="search">
        <li class="filter-title">상태</li>
        <li>
          <select v-model="searchState">
            <option
              v-for="(item, index) in stateOptions"
              :key="index"
              :value="item.value"
            >
              {{ item.text }}
            </option>
          </select>
        </li>
        <li class="filter-title filter-title2">검색어 필터</li>
        <li>
          <select v-model="searchFilter">
            <option
              v-for="(item, index) in filterOptions"
              :key="index"
              :value="item.value"
            >
              {{ item.text }}
            </option>
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
          <th>회원명</th>
          <th>가입일</th>
          <th>회원상태</th>
        </tr>
        <tr v-for="(item, index) in listItem" :key="index">
          <td class="table-no">{{ item.id }}</td>
          <td>
            <a href="javascript:;" @click="routeUserView(item.id)">{{
              item.name
            }}</a>
          </td>
          <td>{{ item.createDate }}</td>
          <td>{{ item.state }}</td>
        </tr>
      </table>
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
import { fetchUsers } from '../../api/users.js';

export default {
  created() {
    this.fetchData(0);
  },
  data() {
    return {
      listItem: [],
      page: {},
      searchKeyword: '',
      searchFilter: '0',
      searchState: '0',
      stateOptions: [
        {
          value: '0',
          text: '전체',
        },
        {
          value: '1',
          text: '기본회원',
        },
        {
          value: '2',
          text: '계약회원',
        },
        {
          value: '3',
          text: '특별회원',
        },
        {
          value: '4',
          text: '휴면회원',
        },
        {
          value: '5',
          text: '탈퇴회원',
        },
      ],
      filterOptions: [
        {
          value: '0',
          text: '전체',
        },
        {
          value: '1',
          text: '이름',
        },
        {
          value: '2',
          text: '이메일',
        },
      ],
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
      const { data } = await fetchUsers(param);
      // console.log(data);
      if (data.result === 'success') {
        this.listItem = data.data.userData;
        this.page = data.data.paging;
      } else {
        alert('페이지 비정상 접속');
      }
    },
    routeUserView(id) {
      this.$router.push(`/admin/account/view/${id}`);
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
        name: '',
        email: '',
        state: '',
        page: pageNum,
      };

      if (this.searchFilter === '0') {
        param.state = '';
        param.name = this.searchKeyword;
        param.email = this.searchKeyword;
      }

      if (this.searchState === '0') {
        param.state = '';
      } else {
        param.state = this.stateOptions[parseInt(this.searchState)].text;
        console.log(param.state);
      }

      if (this.searchFilter === '1') {
        param.state = '이름';
        param.name = this.searchKeyword;
      } else if (this.searchFilter === '2') {
        param.state = '이메일';
        param.email = this.searchKeyword;
      }

      return param;
    },
    searchAreaInit() {
      this.searchKeyword = '';
      this.searchFilter = '0';
      this.searchState = '0';
    },
  },
};
</script>

<style scoped>
.table1 tr th {
  background: #ddd;
}

.table1 tr td {
  text-align: center;
}

.search select {
  text-align: center;
}
</style>
