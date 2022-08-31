import { instance } from './index.js';

function fetchRequest(id) {
  return instance.get(`project/${id}`);
}

function fetchDashboard(id) {
  return instance.get(`project/dashboard/${id}`);
}

function getProjectIdList(userId) {
  return instance.get(`project/list/${userId}`);
}

export { fetchRequest, fetchDashboard, getProjectIdList };
