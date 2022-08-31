<template>
  <section id="section1" class="section1">
    <ul class="mainslide">
      <Carousel
        :autoplay="5000"
        :items-to-show="1"
        :wrap-around="true"
        :transition="1200"
        :mouseDrag="false"
        :autoplayHoverPause="false"
      >
        <slide key="1">
          <li class="slide1">
            <div class="section-inner">
              <div class="logobox">
                <span class="line1"></span>
                <div class="imgbox">
                  <img src="../assets/front_image/t-assistant.svg" alt="" />
                </div>
                <span class="line2"></span>
              </div>
              <div class="maintext">
                <p>
                  우리 회사의 <b>서비스</b> <b>애플리케이션</b>에 적합한
                  <br />
                  <b>품질 검증 서비스</b>를 시작해보세요.
                </p>
                <router-link to="/request/form" class="request">
                  서비스 의뢰
                </router-link>
              </div>
            </div>
          </li>
        </slide>
        <slide key="2">
          <li class="slide2">
            <div class="section-inner">
              <div class="logobox">
                <span class="line1"></span>
                <div class="imgbox">
                  <img src="../assets/front_image/t-assistant.svg" alt="" />
                </div>
                <span class="line2"></span>
              </div>
              <div class="maintext">
                <p>
                  우리 회사의 <b>서비스</b> <b>애플리케이션</b>에 적합한
                  <br />
                  <b>품질 검증 서비스</b>를 시작해보세요.
                </p>
                <router-link to="/request/form" class="request">
                  서비스 의뢰
                </router-link>
              </div>
            </div>
          </li>
        </slide>
        <slide key="3">
          <li class="slide3">
            <div class="section-inner">
              <div class="logobox">
                <span class="line1"></span>
                <div class="imgbox">
                  <img src="../assets/front_image/t-assistant.svg" alt="" />
                </div>
                <span class="line2"></span>
              </div>
              <div class="maintext">
                <p>
                  우리 회사의 <b>서비스</b> <b>애플리케이션</b>에 적합한
                  <br />
                  <b>품질 검증 서비스</b>를 시작해보세요.
                </p>
                <router-link to="/request/form" class="request">
                  서비스 의뢰
                </router-link>
              </div>
            </div>
          </li>
        </slide>
        <template #addons>
          <Pagination />
          <div class="slide-pager"><Navigation /></div>
        </template>
      </Carousel>
    </ul>
  </section>
</template>

<script>
import 'vue3-carousel/dist/carousel.css';
import { Carousel, Slide, Navigation, Pagination } from 'vue3-carousel';

export default {
  components: {
    Carousel,
    Slide,
    Navigation,
    Pagination,
  },
};
</script>

<style>
@import '../css/mainslide.css';
</style>
