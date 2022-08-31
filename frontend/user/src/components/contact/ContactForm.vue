<template>
  <!-- 메인 -->
  <main id="main">
    <form @submit.prevent="submitForm">
      <div class="request-wrap-1">
        <section>
          <div class="title-1">
            <div class="title-main">
              <span>품질검증 서비스 의뢰하기</span>
            </div>
            <div class="title-sub">
              <span
                >의뢰내용 제출하시면 담당자가 빠른 시간 내에 답변
                드리겠습니다.</span
              >
            </div>
          </div>
          <div class="request-content">
            <article class="input1">
              <div class="title-area">
                <span>1. 의뢰 서비스 명칭</span>
              </div>
              <div class="input-area-1">
                <input
                  type="text"
                  id="service-name"
                  name="serviceName"
                  placeholder="서비스명을 입력해주세요."
                  v-model.trim="serviceName"
                />
              </div>
              <div class="warning-area" v-if="!isNameValid">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>1~50자로 입력해주세요.</span>
              </div>
            </article>
            <article class="input2">
              <div class="title-area">
                <span>2.대상 SW 종류 선택</span>
              </div>
              <div class="input-area-1">
                <select
                  name="serviceType"
                  id="service-type"
                  v-model="serviceType"
                >
                  <option value="모바일 애플리케이션">
                    모바일 애플리케이션
                  </option>
                  <option value="웹 서비스">웹 서비스</option>
                  <option value="기타">기타</option>
                </select>
              </div>
              <div class="warning-area" v-if="!isServiceTypeValid">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>선택해주세요.</span>
              </div>
            </article>
            <article class="input3">
              <div class="title-area">
                <span>3. 서비스 선택</span>
              </div>
              <div class="input-area-1">
                <select name="testType" id="test-type" v-model="testType">
                  <option value="기본 사용성 검증">기본 사용성 검증</option>
                  <option value="심층 검증">심층 검증</option>
                  <option value="개발 검증">개발 검증</option>
                  <option value="미선택(상담 후 결정)">
                    미선택(상담 후 결정)
                  </option>
                </select>
              </div>
              <div class="warning-area" v-if="!isTestTypeValid">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>선택해주세요.</span>
              </div>
            </article>
            <article class="input3">
              <div class="title-area">
                <span>4.검증 기간</span>
              </div>
              <div class="input-area-2">
                <input type="date" v-model="startDate" :max="endDate" />
                <p>~</p>
                <input
                  type="date"
                  v-model="endDate"
                  max="9999-12-31"
                  :min="startDate"
                />
              </div>
              <div class="warning-area" v-if="!isDateValid">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>기간을 선택해주세요.</span>
              </div>
            </article>
            <article class="input5">
              <div class="title-area">
                <span>5. 의뢰내용</span>
              </div>
              <div class="input-area-4">
                <textarea
                  name="testContents"
                  id="test-contents"
                  cols="30"
                  rows="10"
                  v-model.trim="testCotents"
                ></textarea>
              </div>
              <div class="warning-area" v-if="!isContentsValid">
                <img src="../../assets/front_image/icon-error.svg" alt="" />
                <span>1~500자로 입력해주세요.</span>
              </div>
            </article>

            <article class="btn-area">
              <button
                :disabled="!isNameValid || !isContentsValid || !isDateValid"
                id="btn-request"
                type="submit"
              >
                서비스 의뢰하기
              </button>
            </article>
          </div>
        </section>
      </div>
    </form>
  </main>
</template>

<script>
import { registerRequest } from '../../api/contact.js';

export default {
  data() {
    return {
      serviceName: '',
      serviceType: '',
      testType: '',
      startDate: '',
      endDate: '',
      testCotents: '',
      writer: '',
    };
  },
  created() {
    this.serviceType = '모바일 애플리케이션';
    this.testType = '기본 사용성 검증';
    this.startDate = '';
  },
  computed: {
    isNameValid() {
      return this.serviceName.length < 50 && this.serviceName.length > 0;
    },
    isContentsValid() {
      return this.testCotents.length < 501 && this.testCotents.length > 0;
    },
    isServiceTypeValid() {
      console.log('tt' + this.serviceType.selected);
      return this.serviceType.selected !== null;
    },
    isTestTypeValid() {
      return this.testType !== '';
    },
    isDateValid() {
      const re = /^[0-9]{4}-[0-9]{2}-[0-9]{2}/;
      return re.test(this.startDate) && re.test(this.endDate);
    },
  },
  methods: {
    async submitForm() {
      try {
        const { data } = await registerRequest({
          contactServiceName: this.serviceName,
          contactServiceType: this.serviceType,
          contactTestType: this.testType,
          contactStart: this.startDate,
          contactEnd: this.endDate,
          contactContent: this.testCotents,
          userId: this.$store.state.id,
        });

        if (data.result === 'fail') {
          console.log('실패');
          alert('의뢰 문의에 실패하였습니다. 입력값을 확인해주세요.');
        } else {
          this.$router.push('/request/requestcomplete');
        }
      } catch (error) {
        this.logMessage = error.response.data.message;
      }
    },
  },
};
</script>

<style>
@import '../../css/contactform.css';
</style>
