import axios from 'axios';
import { setInterceptors } from './common/interceptors.js';

function createInstance() {
  return axios.create({
    baseURL: process.env.VUE_APP_API_URL,
  });
}

// Axios 초기화 함수
function createInstanceWithModule(url) {
  // 데이터를 요청할때마다 고정적으로 정의되는 속성을 지정
  const instance = axios.create({
    baseURL: `${process.env.VUE_APP_API_URL}${url}`,
  });

  return setInterceptors(instance);
}

export const instance = createInstance();
export const request = createInstanceWithModule('request');
export const users = createInstanceWithModule('users');
export const project = createInstanceWithModule('project');
