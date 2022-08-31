<template>
  <div class="main-right">
    <!-- 타이틀 -->
    <div class="title">
      <h2>계약 수정</h2>
    </div>
    <section class="section1">
      <div class="s-top">
        <b>업체 정보 변경</b>
      </div>
      <form @submit.prevent="submitForm">
        <table class="table3">
          <tr>
            <th>계약 업체 정보</th>
            <td>
              <input type="text" disabled v-model="companyId" />
              <button
                type="button"
                class="call-contract-btn"
                @click="showCompanyModal = true"
              >
                업체 정보 불러오기
              </button>
              <Teleport to="body">
                <!-- use the modal component, pass in the prop -->
                <AdminModal
                  :show="showCompanyModal"
                  @close="showCompanyModal = false"
                >
                  <template #header>
                    <h3>업체 정보 검색</h3>
                  </template>

                  <template #body>
                    <div class="modal-body-wrap">
                      <input
                        class="modal-input"
                        type="text"
                        v-model="searchCompany"
                        placeholder="업체명을 입력하세요."
                      />
                      <button class="modal-btn" @click="fetchComapny">
                        검색
                      </button>
                    </div>
                    <table class="table1">
                      <thead>
                        <tr class="t1-title">
                          <th>NO</th>
                          <th>업체명</th>
                          <th>대표자</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(item, index) in companyList" :key="index">
                          <td>{{ item.companyId }}</td>
                          <td>
                            <a
                              href="javascript:;"
                              @click="setId(item.companyId, item.userId)"
                              >{{ item.companyName }}</a
                            >
                          </td>
                          <td>{{ item.companyCeoName }}</td>
                        </tr>
                      </tbody>
                    </table>
                  </template>
                </AdminModal>
              </Teleport>
            </td>
          </tr>
          <tr>
            <th>계약명</th>
            <td>
              <input type="text" v-model="contractName" />
            </td>
          </tr>
          <tr>
            <th>계약금액</th>
            <td>
              <input
                type="number"
                min="0"
                max="1000000000"
                v-model="contractCharge"
              />
            </td>
          </tr>
          <tr>
            <th>계약일자</th>
            <td>
              <input type="date" v-model="contractDay" />
            </td>
          </tr>
          <tr>
            <th>계약기간</th>
            <td>
              <input type="date" v-model="contractStart" />
              <span>~</span>
              <input type="date" v-model="contractEnd" />
            </td>
          </tr>
          <tr class="tel">
            <th>계약내용</th>
            <td>
              <textarea
                cols="50"
                rows="10"
                v-model="contractContent"
              ></textarea>
            </td>
          </tr>
          <tr>
            <th>투입인원</th>
            <td class="tester">
              <span>초급</span
              ><input type="number" min="0" max="10" v-model="lowLevel" />
              <span>중급</span
              ><input type="number" min="0" max="10" v-model="middleLevel" />
              <span>고급</span
              ><input type="number" min="0" max="10" v-model="highLevel" />
              <span>특급</span
              ><input type="number" min="0" max="10" v-model="specialLevel" />
            </td>
          </tr>
          <tr>
            <th>산출물 종류</th>
            <td>
              <input type="text" v-model="resultDocument" />
            </td>
          </tr>
          <tr>
            <th>산출물 전달 방법</th>
            <td>
              <input type="text" v-model="resultDelivery" />
            </td>
          </tr>
          <tr>
            <th>계약 진행 상태</th>
            <td>
              <select v-model="contractState">
                <option value="계약대기">계약대기</option>
                <option value="계약완료">계약완료</option>
                <option value="계약진행">계약진행</option>
                <option value="계약종료">계약종료</option>
              </select>
            </td>
          </tr>
        </table>
        <div class="button-box">
          <router-link to="/admin/contract/list" class="cancel"
            >취소</router-link
          >
          <button type="submit" class="registration">수정</button>
        </div>
      </form>
    </section>
  </div>
</template>

<script>
import AdminModal from '../common/AdminModal.vue';
import { fetchRequest, editRequest } from '../../api/contract.js';
import { fetchRequests } from '../../api/company.js';

export default {
  components: {
    AdminModal,
  },
  created() {
    this.fetchContact();
  },
  data() {
    return {
      companyList: [],
      showCompanyModal: false,
      companyId: 0,
      userId: 0,
      contractId: 0,
      contractName: '',
      contractCharge: '',
      contractDay: '',
      contractStart: '',
      contractEnd: '',
      contractContent: '',
      lowLevel: 0,
      middleLevel: 0,
      highLevel: 0,
      specialLevel: 0,
      resultDocument: '',
      resultDelivery: '',
      contractState: '',
    };
  },
  methods: {
    setId(companyId, userId) {
      this.companyId = companyId;
      this.userId = userId;
      this.showCompanyModal = false;
    },
    async fetchComapny() {
      const param = {
        companyName: this.searchCompany,
        companyCeoName: '',
        companyManagerName: '',
        page: 0,
      };
      const { data } = await fetchRequests(param);

      if (data.result === 'success') {
        this.companyList = data.data.companyData;
        this.page = data.data.paging;
      } else {
        alert('페이지 비정상 접속');
      }
    },
    async fetchContact() {
      const id = this.$route.params.id;
      const { data } = await fetchRequest(id);

      this.contractId = data.data.contractId;
      this.companyId = data.data.companyId;
      this.userId = data.data.userId;
      this.contractName = data.data.contractName;
      this.contractCharge = data.data.contractCharge;
      this.contractDay = data.data.contractDay;
      this.contractStart = data.data.contractStart;
      this.contractEnd = data.data.contractEnd;
      this.contractContent = data.data.contractContent;
      this.lowLevel = data.data.lowLevel;
      this.middleLevel = data.data.middleLevel;
      this.highLevel = data.data.highLevel;
      this.specialLevel = data.data.specialLevel;
      this.resultDocument = data.data.resultDocument;
      this.resultDelivery = data.data.resultDelivery;
      this.contractState = data.data.contractState;
    },
    async submitForm() {
      const contractData = {
        id: this.$route.params.id,
        contractName: this.contractName,
        contractCharge: this.contractCharge,
        contractDay: this.contractDay,
        contractStart: this.contractStart,
        contractEnd: this.contractEnd,
        contractContent: this.contractContent,
        lowLevel: this.lowLevel,
        middleLevel: this.middleLevel,
        highLevel: this.highLevel,
        specialLevel: this.specialLevel,
        resultDocument: this.resultDocument,
        resultDelivery: this.resultDelivery,
        contractState: this.contractState,
        companyId: this.companyId,
        userId: this.userId,
      };

      const { data } = await editRequest(contractData);
      // console.log(data);

      if (data.result === 'success') {
        alert(data.message);
        this.$router.push(`/admin/contract/view/${this.contractId}`);
      } else if (data.result === 'fail') {
        alert(data.message);
      }
    },
  },
};
</script>

<style scoped>
.call-contract-btn {
  padding-top: 10px;
  width: 150px;
  padding: 10px;
  box-sizing: border-box;
  text-align: center;
  border: 1px solid #000;
  background: #000;
  color: #fff;
  font-weight: 700;
  margin-left: 20px;
}
.table3 th {
  width: 20%;
  background: #ddd;
  padding: 8px;
}
.table3 input {
  border: 1px solid #747474;
  border-radius: 3px;
  color: #747474;
  box-sizing: border-box;
  padding: 7px;
  width: 400px;
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
.tester input {
  width: 100px;
  margin-left: 15px;
  margin-right: 35px;
}
.modal-body-wrap {
  overflow: hidden;
}
.modal-input {
  width: 60%;
  padding: 7px;
  border: 1px solid #aaa;
  float: left;
}
.modal-btn {
  width: auto;
  margin-left: 10px;
  padding: 7px;
  background: #fff;
  border: 1px solid #333;
  float: left;
}

.table1 {
  margin-top: 25px;
  font-size: 15px;
}

.table1 thead tr th {
  background: #aaa;
}

.table1 tbody tr td:nth-child(2) {
  font-weight: 800;
  color: #ff4343;
}
</style>
