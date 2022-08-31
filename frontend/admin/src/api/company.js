import { instance } from './index.js';

function registerRequest(requestData) {
  return instance.post('company', requestData);
}

function fetchRequests(param) {
  const url =
    'company?companyName=' +
    param.companyName +
    '&companyCeoName=' +
    param.companyCeoName +
    '&companyManagerName=' +
    param.companyManagerName +
    '&page=' +
    param.page;
  return instance.get(url);
}

function fetchRequest(id) {
  return instance.get(`company/${id}`);
}

function editRequest(requestData) {
  return instance.patch('company', requestData);
}

export { registerRequest, fetchRequests, fetchRequest, editRequest };
