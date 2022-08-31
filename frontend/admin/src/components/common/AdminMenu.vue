<template>
  <!-- 좌측메뉴 -->
  <div class="main-left">
    <div class="lnb">
      <!-- <div class="list-box list-box1">
        <div class="list">
          <router-link to="/admin/main"><h3>대시보드</h3></router-link>
        </div>
      </div> -->
      <div class="list-box list-box2">
        <div class="list">
          <router-link to="/admin/account/list"><h3>회원관리</h3></router-link>
        </div>
      </div>
      <div class="list-box list-box2">
        <div class="list">
          <router-link to="/admin/contact/list"><h3>의뢰관리</h3></router-link>
        </div>
      </div>
      <div class="list-box list-box3">
        <div class="list">
          <router-link to="/admin/company/list"><h3>업체관리</h3></router-link>
        </div>
      </div>
      <div class="list-box list-box3">
        <div class="list">
          <router-link to="/admin/contract/list"><h3>계약관리</h3></router-link>
        </div>
      </div>
      <div class="list-box list-box3">
        <div class="list">
          <router-link to="/admin/project/list"
            ><h3>프로젝트관리</h3></router-link
          >
        </div>
      </div>
      <div class="list-box list-box3">
        <div class="list">
          <router-link to="/admin/project/qna/list"
            ><h3>프로젝트 질의관리</h3></router-link
          >
        </div>
      </div>
      <!-- <div class="list-box list-box3">
        <div class="list">
          <a href="javascript:;" @click="routeTesterList"
            ><h3>테스터관리</h3></a
          >
        </div>
      </div> -->
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    routeTesterList() {
      alert('현재 준비중인 기능입니다.');
    },
  },
};
</script>

<style scoped>
@import '../../css/leftside.css';
</style>
