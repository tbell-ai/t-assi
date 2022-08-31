<template>
  <div class="main-right">
    <!-- 타이틀 -->
    <div class="title">
      <h2>프로젝트 생성</h2>
    </div>
    <section class="section1">
      <div class="s-top">
        <b>프로젝트 정보 입력</b>
      </div>
      <form @submit.prevent="submitForm">
        <table class="table3">
          <tr>
            <th>계약정보</th>
            <td>
              <input type="text" disabled v-model="contractId" />
              <button
                type="button"
                class="call-contract-btn"
                @click="showContractModal = true"
              >
                계약 정보 불러오기
              </button>
              <Teleport to="body">
                <!-- use the modal component, pass in the prop -->
                <AdminModal
                  :show="showContractModal"
                  @close="showContractModal = false"
                >
                  <template #header>
                    <h3>계약 정보</h3>
                  </template>
                  <template #body>
                    <div class="modal-body-wrap">
                      <input
                        class="modal-input"
                        type="text"
                        v-model="searchContract"
                        placeholder="계약명을 입력하세요."
                      />
                      <button class="modal-btn" @click="fetchContract">
                        검색
                      </button>
                    </div>
                    <table class="table1">
                      <thead>
                        <tr class="t1-title">
                          <th>NO</th>
                          <th>계약명</th>
                          <th>업체명</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(item, index) in contractList" :key="index">
                          <td>{{ item.contactId }}</td>
                          <td>
                            <a
                              href="javascript:;"
                              @click="setId(item.contactId, item.userId)"
                              >{{ item.contractName }}</a
                            >
                          </td>
                          <td>{{ item.companyName }}</td>
                        </tr>
                      </tbody>
                    </table>
                  </template>
                </AdminModal>
              </Teleport>
            </td>
          </tr>
          <tr>
            <th>프로젝트 담당자</th>
            <td>
              <input
                type="text"
                placeholder="담당자를 입력하세요."
                v-model="projectLeader"
              />
            </td>
          </tr>
          <tr>
            <th>프로젝트 개요</th>
            <td>
              <textarea cols="50" rows="10" v-model="projectSummary"></textarea>
            </td>
          </tr>
          <tr>
            <th>수행계획</th>
            <td>
              <textarea cols="50" rows="10" v-model="projectPlan"></textarea>
            </td>
          </tr>
          <tr>
            <th>대상 SW 종류</th>
            <td>
              <select
                name="serviceType"
                id="service-type"
                v-model="projectServiceType"
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
              <select name="testType" id="test-type" v-model="projectTestType">
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
            <th>BTS 링크</th>
            <td>
              <input
                type="text"
                placeholder="BTS 링크를 입력하세요."
                v-model="projectBtsLink"
              />
            </td>
          </tr>
          <tr>
            <th>BTS 계정 아이디</th>
            <td>
              <input
                type="text"
                placeholder="BTS 계정 아이디를 입력하세요."
                v-model="projectBtsAccount"
              />
            </td>
          </tr>
          <tr>
            <th>BTS 계정 패스워드</th>
            <td>
              <input
                type="text"
                placeholder="BTS 계정 비밀번호를 입력하세요."
                v-model="projectBtsPassword"
              />
            </td>
          </tr>
        </table>
        <div class="button-box">
          <router-link to="/admin/project/list" class="cancel"
            >취소</router-link
          >
          <button :disabled="!isFieldInputCheck" class="registration">
            생성
          </button>
        </div>
      </form>
    </section>
  </div>
</template>

<script>
import AdminModal from '../common/AdminModal.vue';
import { fetchRequests } from '../../api/contract.js';
import { registerRequest } from '../../api/project.js';

export default {
  components: {
    AdminModal,
  },
  data() {
    return {
      showContractModal: false,
      contractList: [],
      contractId: 0,
      userId: 0,
      projectLeader: '',
      projectSummary: '',
      projectPlan: '',
      projectServiceType: '',
      projectTestType: '',
      projectBtsLink: '',
      projectBtsAccount: '',
      projectBtsPassword: '',
      searchContract: '',
    };
  },
  computed: {
    isFieldInputCheck() {
      return (
        this.projectLeader !== '' &&
        this.projectSummary !== '' &&
        this.projectPlan !== '' &&
        this.projectServiceType !== '' &&
        this.projectTestType !== '' &&
        this.projectBtsLink !== '' &&
        this.projectBtsAccount !== '' &&
        this.projectBtsPassword !== ''
      );
    },
  },
  methods: {
    setId(contractId, userId) {
      this.contractId = contractId;
      this.userId = userId;
      this.showContractModal = false;
    },
    async fetchContract() {
      const param = {
        contractName: this.searchContract,
        contractState: '',
        page: 0,
      };
      const { data } = await fetchRequests(param);

      if (data.result === 'success') {
        this.contractList = data.data.contractData;
        this.page = data.data.paging;
      } else {
        alert('페이지 비정상 접속');
      }
    },
    async submitForm() {
      if (this.contractId === 0 || this.userId === 0) {
        alert('계약 정보 선택이 올바르지 않습니다. 확인 해주세요.');
      } else {
        const param = {
          contractId: this.contractId,
          userId: this.userId,
          projectLeader: this.projectLeader,
          projectSummary: this.projectSummary,
          projectPlan: this.projectPlan,
          projectServiceType: this.projectServiceType,
          projectTestType: this.projectTestType,
          projectBtsLink: this.projectBtsLink,
          projectBtsAccount: this.projectBtsAccount,
          projectBtsPassword: this.projectBtsPassword,
        };

        // console.log(param);

        const { data } = await registerRequest(param);

        if (data.result === 'fail') {
          alert('프로젝트 등록에 실패했습니다. 입력값을 확인해주세요');
          this.initForm();
        } else {
          alert('신규 프로젝트가 등록되었습니다.');
          this.$router.push('/admin/project/list');
        }
      }
    },
    initForm() {
      this.projectLeader = '';
      this.projectSummary = '';
      this.projectPlan = '';
      this.projectServiceType = '';
      this.projectTestType = '';
      this.projectBtsLink = '';
      this.projectBtsAccount = '';
      this.projectBtsPassword = '';
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
