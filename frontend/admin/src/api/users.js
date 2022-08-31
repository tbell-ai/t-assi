/*
 * 사용자 API
 * 사용자 정보 조회: /user - Get
 * 사용자 정보 수정: /user/{userId} - Put
 * 사용자 정보 삭제(회원탈퇴): /user/{userId} - Delete
 */

// 사용자 정보 조회, 수정, 삭제 API
import { instance } from './index.js';

// 사용자 목록 조회 API
function fetchUsers(param) {
  const url =
    'users?name=' +
    param.name +
    '&email=' +
    param.email +
    '&state=' +
    param.state +
    '&page=' +
    param.page;
  return instance.get(url);
}

// 사용자 정보 조회 API
function fetchUser(userId) {
  return instance.get(`users/${userId}`);
}

// 사용자 정보 수정 API
function editUser(userData) {
  return instance.patch('users', userData);
}

// 사용자 정보 삭제 API
function deleteUser(userId) {
  return instance.delete(`users/${userId}`);
}

export { fetchUsers, fetchUser, editUser, deleteUser };
