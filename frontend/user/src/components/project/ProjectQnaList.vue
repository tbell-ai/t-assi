<template>
  <div id="wrap">
    <ProjectMenu></ProjectMenu>
    <div id="container">
      <main id="pj-main">
        <div class="pj-wrap">
          <div class="pj-title-area">
            <img
              src="../../assets/front_image/question_answer_black_24_dp_1.svg"
              alt=""
            />
            <span>프로젝트 질의응답</span>
          </div>
          <div class="pj-content-area">
            <div class="pj-qna-list">
              <section class="section-top">
                <article>
                  <div class="filter-title">
                    <span>상태</span>
                  </div>
                  <div class="filter-content">
                    <ul class="search">
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
                          <option value="답변대기">답변대기</option>
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
                      <li class="btn-area btn-area-right">
                        <button id="btn-search" @click="fetchData(0)">
                          검색
                        </button>
                      </li>
                      <li class="btn-area btn-area-right">
                        <button id="btn-reset" @click="searchAreaInit">
                          초기화
                        </button>
                      </li>
                    </ul>
                  </div>
                </article>
              </section>
              <section class="section-bottom">
                <article class="table-type2 table1">
                  <table>
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
                        <a
                          href="javascript:;"
                          @click="routeBoardView(item.id)"
                          >{{ item.subject }}</a
                        >
                      </td>
                      <td>{{ item.state }}</td>
                      <td>{{ item.createDate }}</td>
                    </tr>
                  </table>
                </article>
                <div class="btn-area btn-area-right">
                  <button type="button" @click="routeBoardCreate">등록</button>
                </div>
                <div class="page">
                  <button
                    class="left"
                    :disabled="!prevButtonCheck"
                    @click="fetchData(page.pageNumber - 1)"
                  >
                    <img src="../../assets/front_image/page-left.svg" alt="" />
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
                    <img src="../../assets/front_image/page-right.svg" alt="" />
                  </button>
                </div>
              </section>
            </div>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script>
import ProjectMenu from '../project/ProjectMenu.vue';
import { fetchRequests } from '../../api/board.js';

export default {
  created() {
    this.fetchData(0);
  },
  components: {
    ProjectMenu,
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
    routeBoardCreate() {
      const id = this.$route.params.id;
      this.$router.push(`/project/qna-create/${id}`);
    },
    routeBoardView(id) {
      this.$router.push(`/project/qna-view/${id}`);
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
        id: this.$route.params.id,
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
@import '../../css/pj_qnalist.css';

.table1 tr th:nth-child(1) {
  width: 10%;
}

.table1 tr th:nth-child(2) {
  width: 10%;
}

.table1 tr th:nth-child(3) {
  width: 60%;
}

.table1 tr th:nth-child(4) {
  width: 10%;
}

.table1 tr th:nth-child(5) {
  width: 10%;
}
</style>
