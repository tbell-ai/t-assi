/*
 * 의뢰 API
 * 의뢰 생성: /request - Post
 * 의뢰 내역: /request/{userId} - Get
 * 의뢰 상세: /request/{requestId} - Get
 * 의뢰 수정: /request - Put
 * 의뢰 댓글 생성: /request/comment - Post
 * 의뢰 댓글 목록: /request/comment/{requestId} - Get
 * 의뢰 댓글 수정: /request/comment - Put
 */

// 의뢰 신청, 의뢰 내역, 의뢰 수정, 의뢰 댓글 API 관리
import { instance } from './index.js';

// 의뢰 생성 API
function registerRequest(requestData) {
  return instance.post('contact', requestData);
}

// 의뢰 내역 API
function fetchRequests(param) {
  const url =
    'contact/list?serviceName=' +
    param.serviceName +
    '&testType=' +
    param.testType +
    '&serviceType=' +
    param.serviceType +
    '&state=' +
    param.state +
    '&page=' +
    param.page;
  return instance.get(url);
}

// 의뢰 상세 API
function fetchRequest(contactId) {
  return instance.get(`contact?contactId=${contactId}`);
}

// 의뢰 수정 API
function editRequest(requestData) {
  return instance.patch('contact', requestData);
}

function editState(contactId, contactState) {
  const url = 'contact/state?id=' + contactId + '&state=' + contactState;
  return instance.patch(url);
}

function fetchState(contactId) {
  return instance.get(`contact/state/${contactId}`);
}

// 의뢰 댓글 작성 API
function registerComment(commentData) {
  return instance.post('contact/comment', commentData);
}

// 의뢰 댓글 목록 API
function fetchComments(contactId) {
  return instance.get(`contact/comment/${contactId}`);
}

// 의뢰 댓글 수정 API
function editComment(commentData) {
  return instance.put('contact/comment', commentData);
}

export {
  registerRequest,
  fetchRequests,
  fetchRequest,
  editRequest,
  editState,
  fetchState,
  registerComment,
  fetchComments,
  editComment,
};
