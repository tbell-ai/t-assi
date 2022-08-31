<template>
  <div id="wrap">
    <FrontHeader></FrontHeader>

    <main id="main">
      <MainSlide />
      <section id="section2" class="section2">
        <div class="section-inner">
          <div class="title">
            <h2 class="font1">01 Service</h2>
            <p>
              다양한 품질 검증 서비스를 합리적인 가격에, 비대면으로 진행할 수
              있습니다.
            </p>
          </div>
          <ul
            class="content2"
            data-aos="fade-up"
            data-aos-delay="50"
            data-aos-duration="1300"
            data-aos-mirror="true"
            data-aos-once="false"
          >
            <li>
              <div class="litop">
                <img src="../assets/front_image/section2-1.svg" alt="" />
                <h3>기본 사용성 검증</h3>
                <p>사용자(End User) 관점에서 평가</p>
              </div>
              <span class="bar1"></span>
              <ul class="limid">
                <li>
                  <b>검증기간</b> <span class="bar2"></span>
                  <p>1~2주일</p>
                </li>
                <li>
                  <b>투입인원</b> <span class="bar2"></span>
                  <p>1~3명</p>
                </li>
                <li>
                  <b>제공서비스</b><span class="bar2"></span>
                  <p>
                    테스트 대시보드 <br />
                    BTS 1계정
                  </p>
                </li>
              </ul>
              <span class="bar1"></span>
              <div class="libtm">
                <p>간단한 애플리케이션 테스트에 적합합니다.</p>
              </div>
            </li>
            <li>
              <div class="litop">
                <img src="../assets/front_image/section2-2.svg" alt="" />
                <h3>심층 검증</h3>
                <p>
                  기획서 · 설계서를 기반으로 TC 작성부터 <br />
                  전문가 품질 테스트 까지 One-Stop으로
                </p>
              </div>
              <span class="bar1"></span>
              <ul class="limid">
                <li>
                  <b>검증기간</b> <span class="bar2"></span>
                  <p>1개월</p>
                </li>
                <li>
                  <b>투입인원</b> <span class="bar2"></span>
                  <p>1~5명</p>
                </li>
                <li>
                  <b>제공서비스</b><span class="bar2"></span>
                  <p>
                    테스트 대시보드 <br />
                    BTS 2계정 <br />
                    테스트케이스 등 산출물
                  </p>
                </li>
              </ul>
              <span class="bar1"></span>
              <div class="libtm">
                <p>
                  복잡도가 보통 이상 애플리케이션과 <br />
                  웹 서비스 등의 테스트에 적합합니다.
                </p>
              </div>
            </li>
            <li>
              <div class="litop">
                <img src="../assets/front_image/section2-3.svg" alt="" />
                <h3>개발 검증</h3>
                <p>
                  개발 단계부터 기획 · 개발자와 소통, TC 작성,<br />테스트 수행.
                  타 서비스 비교 및 의견 제시
                </p>
              </div>
              <span class="bar1"></span>
              <ul class="limid">
                <li>
                  <b>검증기간</b> <span class="bar2"></span>
                  <p>3개월 이상</p>
                </li>
                <li>
                  <b>투입인원</b> <span class="bar2"></span>
                  <p>3명 이상</p>
                </li>
                <li>
                  <b>제공서비스</b><span class="bar2"></span>
                  <p>
                    테스트 대시보드 <br />
                    BTS 4계정 <br />
                    테스트케이스 등 산출물
                  </p>
                </li>
              </ul>
              <span class="bar1"></span>
              <div class="libtm">
                <p>
                  규모가 큰 애플리케이션과 웹 서비스 <br />결제, 보안 등 고난도
                  서비스에 적합합니다.
                </p>
              </div>
            </li>
          </ul>
        </div>
      </section>
      <section id="section3" class="section3">
        <div class="section-inner">
          <div class="title">
            <h2 class="font1">02 Consulting</h2>
            <p>전속 담당자가 신속하게 품질 검증을 컨설팅 해드립니다.</p>
          </div>
          <div
            class="content3"
            data-aos="zoom-in"
            data-aos-delay="50"
            data-aos-duration="1300"
            data-aos-mirror="true"
            data-aos-once="false"
          >
            <img
              src="../assets/front_image/section3-1.svg"
              alt=""
              class="bg1"
            />
            <img
              src="../assets/front_image/section3-2.png"
              alt=""
              class="bg2"
            />
            <div class="maincopy">
              <span class="underline"></span>
              <h3>우리 서비스에 적합한 품질검증은 어떤 것일까?</h3>
            </div>
            <div class="subcopy">
              <p>
                궁금하시다구요? <b>경력 15년 이상의 품질 전문가</b>가 <br />
                귀하의 서비스에 적합한 <b>품질 검증 솔루션을 안내</b>해드립니다.
              </p>
              <p class="lastcopy">
                <b>가벼운 사용성 테스트</b>부터 <br />
                <b>대규모 프로젝트의 개발 검증</b>까지 <ins>TBELL</ins
                ><b>이 도와드리고 있습니다!</b>
              </p>
            </div>
          </div>
          <div class="request-box">
            <span></span>
            <router-link to="/request/form" class="request">
              서비스 의뢰
            </router-link>
            <span></span>
          </div>
        </div>
      </section>
    </main>
    <FrontFooter></FrontFooter>
  </div>
</template>

<script>
import FrontHeader from '../components/common/FrontHeader.vue';
import FrontFooter from '../components/common/FrontFooter.vue';
import MainSlide from '../components/MainSlide.vue';

import AOS from 'aos';
import 'aos/dist/aos.css';

export default {
  created() {
    AOS.init({
      offset: 0,
    });
  },
  data() {
    return { dir: 'ltr' };
  },
  components: {
    FrontHeader,
    FrontFooter,
    MainSlide,
  },
};
</script>

<style scoped>
@import '../css/index.css';
</style>
