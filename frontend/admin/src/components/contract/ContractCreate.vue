<template>
  <div class="main-right">
    <!-- 타이틀 -->
    <div class="title">
      <h2>계약 생성</h2>
    </div>
    <section class="section1">
      <div class="s-top">
        <b>계약 정보 입력</b>
      </div>
      <form @submit.prevent="submitForm">
        <table class="table3">
          <tr>
            <th>계약 업체 정보</th>
            <td>
              <input type="text" disabled v-model="companyName" />
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
                              @click="
                                setCompanyInfo(item.companyId, item.companyName)
                              "
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
            <th>계약자 정보</th>
            <td>
              <input type="text" disabled v-model="userName" />
              <button
                type="button"
                class="call-contract-btn"
                @click="showUserModal = true"
              >
                계약자 정보 불러오기
              </button>
              <Teleport to="body">
                <AdminModal
                  :show="showUserModal"
                  @close="showUserModal = false"
                >
                  <template #header>
                    <h3>계약자 정보 검색</h3>
                  </template>
                  <template #body>
                    <div class="modal-body-wrap">
                      <input
                        class="modal-input"
                        type="text"
                        v-model="searchUser"
                        placeholder="계약자 성함을 입력하세요."
                      />
                      <button class="modal-btn" @click="fetchUser">검색</button>
                    </div>
                    <table class="table1">
                      <thead>
                        <tr class="t1-title">
                          <th>NO</th>
                          <th>이름</th>
                          <th>이메일</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(item, index) in userList" :key="index">
                          <td>{{ item.id }}</td>
                          <td>
                            <a
                              href="javascript:;"
                              @click="setUserInfo(item.id, item.name)"
                              >{{ item.name }}</a
                            >
                          </td>
                          <td>{{ item.email }}</td>
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
              <input
                type="text"
                placeholder="계약명을 입력하세요."
                v-model="contractName"
              />
            </td>
          </tr>
          <tr>
            <th>계약금액</th>
            <td>
              <input
                type="number"
                placeholder="계약금액을 입력하세요."
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
              <input
                type="text"
                placeholder="프로젝트 수행 시 필요한 산출물을 입력하세요."
                v-model="resultDocument"
              />
            </td>
          </tr>
          <tr>
            <th>산출물 전달 방법</th>
            <td>
              <input
                type="text"
                placeholder="산출물 전달 방법을 입력하세요."
                v-model="resultDelivery"
              />
            </td>
          </tr>
        </table>
        <div class="button-box">
          <router-link to="/admin/contract/list" class="cancel"
            >취소</router-link
          >
          <button
            :disabled="!isFieldInputCheck"
            type="submit"
            class="registration"
          >
            생성
          </button>
        </div>
      </form>
    </section>
  </div>
</template>

<script>
import AdminModal from '../common/AdminModal.vue';
import { registerRequest } from '../../api/contract.js';
import { fetchRequests } from '../../api/company.js';
import { fetchUsers } from '../../api/users.js';
// import {
//   isNullOrWhiteSpaceCheck,
//   isLengthCheck,
//   isTextCheck,
//   isSpecialTextCheck,
// } from '../../utils/validate.js';

export default {
  components: {
    AdminModal,
  },
  data() {
    return {
      companyList: [],
      userList: [],
      showCompanyModal: false,
      showUserModal: false,
      companyId: 0,
      companyName: '',
      userId: 0,
      userName: '',
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
      searchCompany: '',
      searchUser: '',
    };
  },
  computed: {
    isFieldInputCheck() {
      return (
        this.contractName !== '' &&
        this.contractCharge !== '' &&
        this.contractDay !== '' &&
        this.contractStart !== '' &&
        this.contractEnd !== '' &&
        this.contractContent !== '' &&
        this.resultDocument !== '' &&
        this.resultDelivery !== ''
      );
    },
  },
  methods: {
    setCompanyInfo(id, name) {
      this.companyId = id;
      this.companyName = name;
      this.showCompanyModal = false;
    },
    setUserInfo(id, name) {
      this.userId = id;
      this.userName = name;
      this.showUserModal = false;
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
    async fetchUser() {
      const param = {
        name: this.searchUser,
        email: '',
        state: '',
        page: 0,
      };
      const { data } = await fetchUsers(param);
      if (data.result === 'success') {
        this.userList = data.data.userData;
        this.page = data.data.paging;
      } else {
        alert('페이지 비정상 접속');
      }
    },
    async submitForm() {
      if (this.companyId === 0 || this.userId === 0) {
        alert('업체 또는 계약자 선택이 올바르지 않습니다. 확인 해주세요.');
      } else {
        const param = {
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
          companyId: this.companyId,
          userId: this.userId,
        };

        const { data } = await registerRequest(param);

        if (data.result === 'fail') {
          alert('계약 등록에 실패했습니다. 입력값을 확인해주세요');
          this.initForm();
        } else {
          alert('신규 계약이 등록되었습니다.');
          this.$router.push('/admin/contract/list');
        }
      }
    },
    initForm() {
      this.contractName = '';
      this.contractCharge = '';
      this.contractDay = '';
      this.contractStart = '';
      this.contractEnd = '';
      this.contractContent = '';
      this.lowLevel = 0;
      this.middleLevel = 0;
      this.highLevel = 0;
      this.specialLevel = 0;
      this.resultDocument = '';
      this.resultDelivery = '';
    },
  },
};
</script>

<style scoped>
.call-contract-btn {
  padding-top: 10px;
  width: 180px;
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
