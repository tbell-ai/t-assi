<template>
  <div class="main-right">
    <div class="title">
      <h2>의뢰 수정</h2>
    </div>
    <section class="section1">
      <div class="s-top">
        <b>의뢰정보수정</b>
      </div>
      <form @submit.prevent="submitForm" class="mypage-view">
        <table class="table3">
          <tr>
            <th>의뢰 서비스 명칭</th>
            <td>
              <input
                type="text"
                placeholder="서비스명을 입력해주세요."
                v-model="serviceName"
              />
            </td>
          </tr>
          <tr>
            <th>대상 SW 종류</th>
            <td>
              <select
                name="serviceType"
                id="service-type"
                v-model="serviceType"
              >
                <option value="모바일 애플리케이션">모바일 애플리케이션</option>
                <option value="웹 서비스">웹 서비스</option>
                <option value="기타">기타</option>
              </select>
            </td>
          </tr>
          <tr>
            <th>테스팅 서비스 종류</th>
            <td>
              <select name="testType" id="test-type" v-model="testType">
                <option value="기본 사용성 검증">기본 사용성 검증</option>
                <option value="심층 검증">심층 검증</option>
                <option value="개발 검증">개발 검증</option>
                <option value="미선택(상담 후 결정)">
                  미선택(상담 후 결정)
                </option>
              </select>
            </td>
          </tr>
          <tr>
            <th>검증 기간 선택</th>
            <td>
              <input type="date" v-model="contactStart" />
              <span>~</span>
              <input type="date" v-model="contactEnd" />
            </td>
          </tr>
          <tr>
            <th>의뢰 상세 내용</th>
            <td>
              <textarea cols="50" rows="10" v-model="contactContent"></textarea>
            </td>
          </tr>
          <tr>
            <th>의뢰 상태</th>
            <td>
              <select v-model="contactState">
                <option value="답변대기">답변대기</option>
                <option value="답변진행">답변진행</option>
                <option value="답변완료">답변완료</option>
              </select>
            </td>
          </tr>
        </table>
        <div class="button-box">
          <a href="javascript:;" class="button2" @click="routeContactView"
            >취소</a
          >
          <button type="submit" class="request-btn button1">저장</button>
        </div>
      </form>
    </section>
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
      // console.log(data);

      if (data.result === 'success') {
        alert(data.message);
        this.$router.push(`/admin/contact/view/${this.contactId}`);
      } else if (data.result === 'fail') {
        alert(data.message);
      }
    },
    routeContactView() {
      this.$router.push(`/admin/contact/view/${this.contactId}`);
    },
  },
};
</script>

<style scoped>
/* 표3 */
.table3 div {
  margin: 0 20px;
}
.table3 input {
  border: 1px solid #747474;
  border-radius: 3px;
  color: #747474;
  box-sizing: border-box;
  padding: 0 10px;
  width: 200px;
}

.table3 select,
.table3 textarea {
  border: 1px solid #747474;
  border-radius: 3px;
  font-size: 14px;
  font-weight: 600;
  color: #808080;
  padding: 7px 7px;
  box-sizing: border-box;
}
.table3 select {
  width: 200px;
}
.table3 textarea {
  width: 450px;
}
.cancel {
  margin-left: 20px;
}
</style>
