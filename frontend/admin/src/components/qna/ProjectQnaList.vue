<template>
  <div class="main-right">
    <div class="title">
      <h2>프로젝트 질의 목록 조회</h2>
    </div>
    <section class="section1">
      <ul class="search">
        <li class="filter-title">상태</li>
        <li>
          <select v-model="boardType">
            <option value="전체">전체</option>
            <option value="테스트">테스트</option>
            <option value="일정">일정</option>
            <option value="인력">인력</option>
            <option value="기타">기타</option>
          </select>
        </li>
        <li>
          <select v-model="boardState">
            <option value="전체">전체</option>
            <option value="처리대기">처리대기</option>
            <option value="처리진행">처리진행</option>
            <option value="처리완료">처리완료</option>
            <option value="처리불가">처리불가</option>
          </select>
        </li>
        <li>
          <select v-model="searchFilter">
            <option value="전체">전체</option>
            <option value="제목">제목</option>
            <option value="내용">내용</option>
          </select>
        </li>
        <li class="growbox">
          <input
            type="text"
            placeholder="검색어를 입력하세요."
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
          <th>질문 유형</th>
          <th>제목</th>
          <th>답변상태</th>
          <th>등록일</th>
        </tr>
        <tr v-for="(item, index) in listItem" :key="index">
          <td scope="row">{{ item.id }}</td>
          <td>{{ item.type }}</td>
          <td>
            <a href="javascript:;" @click="routeBoardView(item.id)">{{
              item.subject
            }}</a>
          </td>
          <td>{{ item.state }}</td>
          <td>{{ item.createDate }}</td>
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
import { fetchRequests } from '../../api/board.js';

export default {
  created() {
    this.fetchData(0);
  },
  data() {
    return {
      listItem: [],
      page: {},
      boardType: '전체',
      boardState: '전체',
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

      if (data.result === 'success') {
        this.listItem = data.data.boardData;
        this.page = data.data.paging;
      } else {
        alert('페이지 비정상 접속');
      }
    },
    routeBoardView(id) {
      this.$router.push(`/admin/project/qna/view/${id}`);
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
        boardState: '',
        boardType: '',
        boardSubject: '',
        boardContent: '',
        page: pageNum,
      };

      if (this.boardState === '전체') {
        param.boardState = '';
      } else {
        param.boardState = this.boardState;
      }

      if (this.boardType === '전체') {
        param.boardType = '';
      } else {
        param.boardType = this.boardType;
      }

      if (this.searchFilter === '내용') {
        param.boardContent = this.searchKeyword;
      } else if (this.searchFilter === '제목') {
        param.boardSubject = this.searchKeyword;
      } else if (this.searchFilter === '전체') {
        param.boardContent = this.searchKeyword;
        param.boardSubject = this.searchKeyword;
      }

      return param;
    },
    searchAreaInit() {
      this.boardState = '전체';
      this.boardType = '전체';
      this.searchFilter = '전체';
      this.searchKeyword = '';
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
