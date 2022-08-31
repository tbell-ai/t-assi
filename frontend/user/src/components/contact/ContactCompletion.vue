<template>
  <main id="main">
    <div class="request-complete-wrap-1">
      <section class="request-complete">
        <div class="title-1">
          <img src="../../assets/front_image/t-assistant.svg" alt="" />
        </div>
        <div class="request-complete-content">
          <article class="content-guide">
            <span>서비스 신청이 완료되었습니다.</span>
          </article>
          <article>
            <p>
              <span id="em">24시간 이내</span>에 담당자가 배정되어 진행되며,
            </p>
            <p>마이페이지에서 실시간 상황을 확인하실 수 있습니다.</p>
          </article>

          <article class="btn-area">
            <button id="btn-comfirm">
              <router-link to="/main"> 확인</router-link>
            </button>
          </article>
        </div>
      </section>
    </div>
  </main>
</template>

<script>
export default {};
</script>

<style>
@import '../../css/contactCompletion.css';
</style>
