import { instance } from './index.js';

function registerRequest(requestData) {
  return instance.post('contract', requestData);
}

function fetchRequests(param) {
  const url =
    'contract?contractName=' +
    param.contractName +
    '&contractState=' +
    param.contractState +
    '&page=' +
    param.page;
  return instance.get(url);
}

function fetchRequest(id) {
  return instance.get(`contract/${id}`);
}

function editRequest(requestData) {
  return instance.patch('contract', requestData);
}

export { registerRequest, fetchRequests, fetchRequest, editRequest };
