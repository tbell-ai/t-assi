import { createStore } from 'vuex';
import {
  getAuthFromCookie,
  getUserNameFromCookie,
  getUserEmailFromCookie,
  getUserIdFromCookie,
  saveAuthToCookie,
  saveUserToCookie,
  getUserCompanyFromCookie,
} from '../utils/cookies.js';
import { loginUser } from '../api/auth.js';

export default createStore({
  state: {
    name: getUserNameFromCookie() || '',
    email: getUserEmailFromCookie() || '',
    id: getUserIdFromCookie() || '',
    company: getUserCompanyFromCookie() || '',
    token: getAuthFromCookie() || '',
    subPage: false,
  },
  getters: {
    isLogin(state) {
      return state.name !== '';
    },
  },
  mutations: {
    setUserName(state, name) {
      state.name = name;
    },
    setUserEmail(state, email) {
      state.email = email;
    },
    setUserId(state, id) {
      state.id = id;
    },
    setUserCompany(state, company) {
      state.company = company;
    },
    clearUser(state) {
      state.name = '';
      state.email = '';
      state.id = '';
      state.company = '';
    },
    setToken(state, token) {
      state.token = token;
    },
    clearToken(state) {
      state.token = '';
    },
  },
  actions: {
    async LOGIN({ commit }, loginData) {
      const { data } = await loginUser(loginData);

      if (data.result === 'success') {
        // Vuex로 상태관리, token 값 저장
        commit('setToken', data.data.accessToken);
        commit('setUserName', data.data.name);
        commit('setUserEmail', data.data.email);
        commit('setUserId', data.data.id);
        commit('setUserCompany', data.data.company);
        saveAuthToCookie(data.data.accessToken);
        saveUserToCookie(data.data);
      } else {
        return 400;
      }

      return data.state;
    },
  },
});
