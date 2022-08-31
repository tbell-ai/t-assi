import { createStore } from 'vuex';
import {
  getAuthFromCookie,
  getUserNameFromCookie,
  getUserEmailFromCookie,
  getUserIdFromCookie,
  saveAuthToCookie,
  saveUserToCookie,
} from '../utils/cookies.js';
import { loginUser } from '../api/auth.js';

export default createStore({
  state: {
    name: getUserNameFromCookie() || '',
    email: getUserEmailFromCookie() || '',
    id: getUserIdFromCookie() || '',
    token: getAuthFromCookie() || '',
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
    clearUser(state) {
      state.name = '';
      state.email = '';
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

      if (data.data.role === 'ROLE_ADMIN') {
        // Vuex로 상태관리
        // token 값 저장
        commit('setToken', data.data.accessToken);
        commit('setUserName', data.data.name);
        commit('setUserEmail', data.data.email);
        commit('setUserId', data.data.id);
        saveAuthToCookie(data.data.accessToken);
        saveUserToCookie(data.data);

        return data.state;
      } else {
        return 400;
      }

      // admin 계정인지 확인
    },
  },
});
