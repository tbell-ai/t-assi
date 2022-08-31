<template>
  <div class="main-right">
    <!-- 타이틀 -->
    <div class="title">
      <h2>의뢰 목록 조회</h2>
    </div>
    <section class="section1">
      <ul class="search">
        <li class="filter-title">상태</li>
        <li>
          <select v-model="contactState">
            <option value="">전체</option>
            <option value="">답변대기</option>
            <option value="">답변진행</option>
            <option value="">답변완료</option>
          </select>
        </li>
        <li class="filter-title filter-title2">서비스 유형</li>
        <li>
          <select v-model="contactServiceType">
            <option value="전체">전체</option>
            <option value="모바일 어플리케이션">모바일 어플리케이션</option>
            <option value="웹 서비스">웹 서비스</option>
            <option value="기타">기타</option>
          </select>
        </li>
        <li class="filter-title filter-title2">테스트 유형</li>
        <li>
          <select v-model="contactTestType">
            <option value="전체">전체</option>
            <option value="기본 사용성 검증">기본 사용성 검증</option>
            <option value="심층 검증">심층 검증</option>
            <option value="개발 검증">개발 검증</option>
            <option value="미선택(상담 후 결정)">미선택(상담 후 결정)</option>
          </select>
        </li>
        <li class="filter-title filter-title2">검색어</li>
        <li class="growbox">
          <input
            type="search"
            placeholder="서비스 명칭을 입력해주세요."
            v-model="contactServiceName"
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
          <th>의뢰 서비스명</th>
          <th>테스트 유형</th>
          <th>등록일</th>
          <th>등록자</th>
          <th>처리상태</th>
        </tr>
        <tr v-for="(item, index) in listItem" :key="index">
          <td scope="row">{{ item.contactId }}</td>
          <td>
            <a href="javascript:;" @click="routeContactView(item.contactId)">{{
              item.contactServiceName
            }}</a>
          </td>
          <td>{{ item.contactTestType }}</td>
          <td>{{ item.createDate }}</td>
          <td>{{ item.userName }}</td>
          <td>{{ item.contactState }}</td>
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
import { fetchRequests } from '../../api/contact.js';

export default {
  created() {
    this.fetchData(0);
  },
  data() {
    return {
      listItem: [],
      page: {},
      contactServiceName: '',
      contactServiceType: '전체',
      contactTestType: '전체',
      contactState: '전체',
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
        this.listItem = data.data.contactData;
        this.page = data.data.paging;
      } else {
        alert('페이지 비정상 접속');
      }
    },
    routeContactView(id) {
      this.$router.push(`/admin/contact/view/${id}`);
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
        serviceName: '',
        testType: '',
        serviceType: '',
        state: '',
        page: pageNum,
      };

      param.serviceName = this.contactServiceName;
      param.state = this.contactState;

      if (this.contactServiceType === '전체') {
        param.serviceType = '';
      } else {
        param.serviceType = this.contactServiceType;
      }

      if (this.contactTestType === '전체') {
        param.testType = '';
      } else {
        param.testType = this.contactTestType;
      }

      if (this.contactState === '전체') {
        param.state = '';
      } else {
        param.state = this.contactState;
      }
      // console.log(param);

      return param;
    },
    searchAreaInit() {
      this.contactServiceName = '';
      this.contactServiceType = '전체';
      this.contactTestType = '전체';
      this.contactState = '전체';
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

.table1 tr th:nth-child(1) {
  width: 10%;
}
.table1 tr td:nth-child(1) {
  width: 10%;
}

.table1 tr th:nth-child(2) {
  width: 30%;
}
.table1 tr td:nth-child(2) {
  width: 30%;
  text-align: left;
}

.table1 tr th:nth-child(3) {
  width: 20%;
}
.table1 tr td:nth-child(3) {
  width: 20%;
}

.table1 tr th:nth-child(4) {
  width: 15%;
}
.table1 tr td:nth-child(4) {
  width: 15%;
}

.table1 tr th:nth-child(5) {
  width: 15%;
}
.table1 tr td:nth-child(5) {
  width: 15%;
}

.table1 tr th:nth-child(6) {
  width: 10%;
}
.table1 tr td:nth-child(6) {
  width: 10%;
}

.section1 .search select {
  width: 80px;
  margin-right: 25px;
}
</style>
