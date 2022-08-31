<template>
  <main id="main" class="contact-list">
    <section class="section1">
      <div class="subpage-title">
        <h2 class="font1">My Page</h2>
        <p>의뢰내역</p>
      </div>
    </section>
    <!-- <section class="section2">
        <ul class="filter">
          <li class="filter-title">상태</li>
          <li class="state">
            <select name="" id="">
              <option value="전체">전체</option>
              <option value="답변대기">답변대기</option>
              <option value="상담진행">상담진행</option>
              <option value="상담완료">상담완료</option>
            </select>
          </li>
          <li class="filter-title">검색어</li>
          <input type="search" />
          <button class="search">검색</button>
        </ul>
      </section> -->
    <section class="section3">
      <table class="table1 sub1-table">
        <thead>
          <tr class="t1-title">
            <th>NO</th>
            <th>서비스명</th>
            <th>테스트유형</th>
            <th>등록일</th>
            <th>처리상태</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in listItem" :key="index">
            <td scope="row">{{ index + 1 }}</td>
            <td>
              <a
                href="javascript:;"
                @click="routeContactView(item.contactId)"
                >{{ item.contactServiceName }}</a
              >
            </td>
            <td>{{ item.contactTestType }}</td>
            <td>{{ item.createDate }}</td>
            <td>{{ item.contactState }}</td>
          </tr>
        </tbody>
      </table>
      <div class="button-box">
        <router-link to="/request/request"
          ><button class="request-btn button1">
            새 의뢰 작성
          </button></router-link
        >
      </div>
      <div class="pager">
        <button class="prev">
          <img src="../../assets/front_image/icon-prev.svg" alt="" />
        </button>
        <div class="page-num">
          <button>1</button>
        </div>
        <button class="next">
          <img src="../../assets/front_image/icon-next.svg" alt="" />
        </button>
      </div>
    </section>
  </main>
</template>

<script>
import { fetchRequests } from '../../api/contact.js';

export default {
  created() {
    this.fetchData();
  },
  data() {
    return {
      listItem: [],
    };
  },
  methods: {
    async fetchData() {
      const id = this.$store.state.id;
      const { data } = await fetchRequests(id);
      if (data.result === 'success') {
        this.listItem = data.data;
      } else {
        alert('페이지 비정상 접속');
      }
    },
    routeContactView(id) {
      this.$router.push(`/mypage/contact/view/${id}`);
    },
  },
};
</script>

<style scoped>
section {
  background: white;
}

.contact-list {
  padding-top: 80px;
  width: 1300px;
  margin: 0 auto;
}

.section1 {
  padding-top: 40px;
  height: auto;
}

.section2 {
  padding-top: 20px;
  height: auto;
}
.section3 {
  padding-top: 20px;
  height: auto;
  margin-bottom: 140px;
}

.filter {
  margin-left: 0;
  margin-right: 0;
}
.filter-title {
  width: auto;
}

.button1 {
  width: 150px;
}

.t1-title > th:nth-child(1) {
  width: 10%;
}

.t1-title > th:nth-child(2) {
  width: 35%;
}

.t1-title > th:nth-child(3) {
  width: 20%;
}

.t1-title > th:nth-child(4) {
  width: 20%;
}

.t1-title > th:nth-child(5) {
  width: 15%;
}
</style>
