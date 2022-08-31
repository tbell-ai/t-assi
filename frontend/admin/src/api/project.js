import { instance } from './index.js';

function registerRequest(requestData) {
  return instance.post('project', requestData);
}

function fetchRequests(param) {
  const url =
    'project?projectState=' + param.projectState + '&page=' + param.page;
  return instance.get(url);
}

function fetchRequest(id) {
  return instance.get(`project/${id}`);
}

function editRequest(requestData) {
  return instance.patch('project', requestData);
}

export { registerRequest, fetchRequests, fetchRequest, editRequest };
