<template>
  <div class="all-wrap">
    <main id="main">
      <form @submit.prevent="submitForm" class="mypage-view">
        <section class="section1">
          <div class="subpage-title">
            <h2 class="font1">My Page</h2>
            <p>의뢰수정</p>
          </div>
        </section>
        <section class="section2">
          <h3>내 의뢰 내용 상세</h3>
          <ul class="quest-modify">
            <li>
              <div class="quest-left">1. 의뢰 서비스 명칭</div>
              <div class="quest-right">
                <input type="text" v-model="serviceName" />
              </div>
            </li>
            <li>
              <div class="quest-left">2. 대상 SW 종류 선택</div>
              <div class="quest-right">
                <select v-model="serviceType">
                  <option value="모바일 애플리케이션">
                    모바일 애플리케이션
                  </option>
                  <option value="웹 서비스">웹 서비스</option>
                  <option value="기타">기타</option>
                </select>
              </div>
            </li>
            <li>
              <div class="quest-left">3. 서비스 선택</div>
              <div class="quest-right">
                <select v-model="testType">
                  <option value="기본 사용성 검증">기본 사용성 검증</option>
                  <option value="심층 검증">심층 검증</option>
                  <option value="개발 검증">개발 검증</option>
                  <option value="미선택(상담 후 결정)">
                    미선택(상담 후 결정)
                  </option>
                </select>
              </div>
            </li>
            <li>
              <div class="quest-left">4. 검증기간(선택)</div>
              <div class="quest-right">
                <input type="date" v-model="contactStart" />
                <p>&#126;</p>
                <input type="date" v-model="contactEnd" />
              </div>
            </li>
            <li>
              <div class="quest-left">5. 의뢰내용</div>
              <div class="quest-right">
                <textarea
                  placeholder="의뢰 내용을 입력하세요."
                  rows="5"
                  cols="60"
                  v-model="contactContent"
                ></textarea>
              </div>
            </li>
          </ul>
          <div class="button-box">
            <a href="javascript:;" class="button2" @click="routeContactList"
              >취소</a
            >
            <button type="submit" class="request-btn button1">저장</button>
          </div>
        </section>
      </form>
    </main>
  </div>
</template>

<script>
import { fetchRequest, editRequest } from '../../api/contact.js';

export default {
  created() {
    this.fetchContact();
  },
  data() {
    return {
      contactId: '',
      serviceName: '',
      serviceType: '',
      testType: '',
      contactStart: '',
      contactEnd: '',
      contactContent: '',
      contactState: '',
      userId: '',
    };
  },
  methods: {
    async fetchContact() {
      const id = this.$route.params.id;
      const { data } = await fetchRequest(id);
      this.contactId = data.data.contactId;
      this.serviceName = data.data.contactServiceName;
      this.serviceType = data.data.contactServiceType;
      this.testType = data.data.contactTestType;
      this.contactStart = data.data.contactStart;
      this.contactEnd = data.data.contactEnd;
      this.contactContent = data.data.contactContent;
      this.contactState = data.data.contactState;
      this.userId = data.data.userId;
    },
    async submitForm() {
      const contactData = {
        contactId: this.contactId,
        contactServiceName: this.serviceName,
        contactServiceType: this.serviceType,
        contactTestType: this.testType,
        contactStart: this.contactStart,
        contactEnd: this.contactEnd,
        contactContent: this.contactContent,
        contactState: this.contactState,
        userId: this.userId,
      };

      const { data } = await editRequest(contactData);

      if (data.result === 'success') {
        alert(data.message);
        this.$router.push(`/mypage/contact/view/${this.contactId}`);
      } else if (data.result === 'fail') {
        alert(data.message);
      }
    },
    routeContactList() {
      this.$router.push('/mypage/contact/list');
    },
  },
};
</script>

<style scoped>
.mypage-view {
  padding-top: 140px;
  width: 1300px;
  margin: 0 auto;
}
.section2 {
  margin-bottom: 140px;
}
/* 섹션2 */
.quest-modify {
  width: 100%;
}
.quest-modify li {
  display: flex;
  justify-content: start;
  min-height: 50px;
  padding: 10px 0;
  align-items: center;
}
.quest-modify li:nth-child(2n-1) {
  background: #eee;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}
.quest-left {
  width: 200px;
  padding-left: 20px;
  font-weight: 700;
}
.quest-right {
  width: 100%;
  display: flex;
  align-items: center;
}
.quest-right p {
  margin: 0 20px;
}
.quest-right input {
  border: 1px solid #000;
  width: 500px;
  height: 36px;
}
.quest-right input[type='date'] {
  width: 225px;
  padding-left: 5px;
}

.quest-right textarea {
  padding: 10px;
}

.quest-right select {
  width: 500px;
  height: 36px;
  border: 1px solid #000;
}

.section1 {
  height: auto;
}
</style>
