/*
 * Auth API
 * 회원가입: /signup - Post
 * 로그인: /login - Post
 */

// 로그인, 회원가입 관련 API
import { instance } from './index.js';

// 회원가입 API (2022. 02. 06 현재 관리자 생성 기능 사용 안하고 있음)
function registerUser(userData) {
  return instance.post('/signup', userData);
}

// 로그인 API
function loginUser(loginData) {
  return instance.post('/login', loginData);
}

export { registerUser, loginUser };
