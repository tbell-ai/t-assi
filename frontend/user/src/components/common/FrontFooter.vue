<template>
  <footer id="footer">
    <ul>
      <li class="f-left">
        <img src="../../assets/front_image/t-assistant-w.svg" alt="" />
        <div>
          <router-link to="/termsofuseagree"> 서비스 이용약관</router-link>
          <span></span>
          <router-link to="/privacypolicyagree">개인정보처리방침</router-link>
          <!-- <span></span> -->
          <!-- <router-link to="/privacyuseagree">개인정보 수집 및 이용</router-link> -->
        </div>
      </li>
      <li class="f-right">
        <p>
          (주)티벨 | 서울특별시 강남구 강남대로 354, 9층 (역삼동, 혜천빌딩)
          대표이사 : 김종균 | 사업자등록번호 : 376-81-00089
        </p>
        <p>
          Tel : 070.7777.9113 / 070.7777.6368 | Fax : 070.7610.7540 | E-mail :
          staff@tbell.co.kr
        </p>
        <p>Copyright ⓒ (주)티벨. All rights reserved.</p>
      </li>
    </ul>
  </footer>
</template>

<script>
export default {};
</script>

<style scoped>
@import '../../css/footer.css';
</style>
