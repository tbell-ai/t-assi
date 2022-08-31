import { instance } from './index.js';

// 문의 생성 API
function registerRequest(requestData) {
  return instance.post('boards', requestData);
}

// 문의 내역 API
function fetchRequests(param) {
  const url =
    'boards/project?id=' +
    param.id +
    '&boardState=' +
    param.boardState +
    '&boardType=' +
    param.boardType +
    '&boardSubject=' +
    param.boardSubject +
    '&boardContent=' +
    param.boardContent +
    '&page=' +
    param.page;
  return instance.get(url);
}

// 문의 상세 API
function fetchRequest(boardId) {
  return instance.get(`boards/${boardId}`);
}

// 문의 수정 API
function editRequest(requestData) {
  return instance.patch('boards', requestData);
}

// 문의 댓글 작성 API
function registerComment(commentData) {
  return instance.post('boards/comment', commentData);
}

// 문의 댓글 목록 API
function fetchComments(boardId) {
  return instance.get(`boards/comment/${boardId}`);
}

export {
  registerRequest,
  fetchRequests,
  fetchRequest,
  editRequest,
  registerComment,
  fetchComments,
};
