/*
 * Auth API
 * 회원가입: /signup - Post
 * 로그인: /login - Post
 */

// 로그인, 회원가입 관련 API
import { instance } from './index.js';

// 회원가입 API
function registerUser(userData) {
  return instance.post('/signup', userData);
}

// 로그인 API
function loginUser(loginData) {
  return instance.post('/login', loginData);
}

export { registerUser, loginUser };
