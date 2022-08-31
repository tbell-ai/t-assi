import { createRouter, createWebHistory } from 'vue-router';
import store from '../store';

const routes = [
  {
    path: '/',
    redirect: '/admin/account',
    // redirect: '/admin/main',
  },
  {
    path: '/admin',
    redirect: '/admin/account',
    // redirect: '/admin/main',
  },
  {
    path: '/admin/main',
    redirect: '/admin/account',
    // redirect: '/admin/main',
  },
  // {
  // path: '/admin/main',
  // name: 'main',
  // component: () => import('../views/account/AccountPage.vue'),
  // component: () => import('../views/MainPage.vue'),
  // meta: { auth: true },
  // },
  {
    path: '/admin/login',
    name: 'login',
    component: () => import('../views/LoginPage.vue'),
  },
  {
    path: '/admin/account',
    name: 'account',
    component: () => import('../views/account/AccountPage.vue'),
    meta: { auth: true },
    children: [
      {
        path: '',
        redirect: '/admin/account/list',
        meta: { auth: true },
      },
      {
        path: 'view/:id',
        component: () => import('../components/account/AccountView.vue'),
        meta: { auth: true },
      },
      {
        path: 'edit/:id',
        component: () => import('../components/account/AccountEdit.vue'),
        meta: { auth: true },
      },
      {
        path: 'list',
        component: () => import('../components/account/AccountList.vue'),
        meta: { auth: true },
      },
    ],
  },
  {
    path: '/admin/contact',
    name: 'contact',
    component: () => import('../views/contact/ContactPage.vue'),
    meta: { auth: true },
    children: [
      {
        path: '',
        redirect: '/admin/contact/list',
        meta: { auth: true },
      },
      {
        path: 'view/:id',
        component: () => import('../components/contact/ContactView.vue'),
        meta: { auth: true },
      },
      {
        path: 'edit/:id',
        component: () => import('../components/contact/ContactEdit.vue'),
        meta: { auth: true },
      },
      {
        path: 'list',
        component: () => import('../components/contact/ContactList.vue'),
        meta: { auth: true },
      },
    ],
  },
  {
    path: '/admin/company',
    name: 'company',
    component: () => import('../views/company/CompanyPage.vue'),
    meta: { auth: true },
    children: [
      {
        path: '',
        redirect: '/admin/company/list',
        meta: { auth: true },
      },
      {
        path: 'create/',
        component: () => import('../components/company/CompanyCreate.vue'),
        meta: { auth: true },
      },
      {
        path: 'view/:id',
        component: () => import('../components/company/CompanyView.vue'),
        meta: { auth: true },
      },
      {
        path: 'edit/:id',
        component: () => import('../components/company/CompanyEdit.vue'),
        meta: { auth: true },
      },
      {
        path: 'list',
        component: () => import('../components/company/CompanyList.vue'),
        meta: { auth: true },
      },
    ],
  },
  {
    path: '/admin/contract',
    name: 'contract',
    component: () => import('../views/contract/ContractPage.vue'),
    meta: { auth: true },
    children: [
      {
        path: '',
        redirect: '/admin/contract/list',
        meta: { auth: true },
      },
      {
        path: 'create/',
        component: () => import('../components/contract/ContractCreate.vue'),
        meta: { auth: true },
      },
      {
        path: 'view/:id',
        component: () => import('../components/contract/ContractView.vue'),
        meta: { auth: true },
      },
      {
        path: 'edit/:id',
        component: () => import('../components/contract/ContractEdit.vue'),
        meta: { auth: true },
      },
      {
        path: 'list',
        component: () => import('../components/contract/ContractList.vue'),
        meta: { auth: true },
      },
    ],
  },
  {
    path: '/admin/project',
    name: 'project',
    component: () => import('../views/project/ProjectPage.vue'),
    meta: { auth: true },
    children: [
      {
        path: '',
        redirect: '/admin/project/list',
        meta: { auth: true },
      },
      {
        path: 'create/',
        component: () => import('../components/project/ProjectCreate.vue'),
        meta: { auth: true },
      },
      {
        path: 'view/:id',
        component: () => import('../components/project/ProjectView.vue'),
        meta: { auth: true },
      },
      {
        path: 'edit/:id',
        component: () => import('../components/project/ProjectEdit.vue'),
        meta: { auth: true },
      },
      {
        path: 'list',
        component: () => import('../components/project/ProjectList.vue'),
        meta: { auth: true },
      },
    ],
  },
  {
    path: '/admin/project/qna',
    name: 'project-qna',
    component: () => import('../views/project/ProjectPage.vue'),
    meta: { auth: true },
    children: [
      {
        path: '',
        redirect: '/admin/project/qna/list',
        meta: { auth: true },
      },
      {
        path: 'view/:id',
        component: () => import('../components/qna/ProjectQnaView.vue'),
        meta: { auth: true },
      },
      {
        path: 'edit/:id',
        component: () => import('../components/qna/ProjectQnaEdit.vue'),
        meta: { auth: true },
      },
      {
        path: 'list',
        component: () => import('../components/qna/ProjectQnaList.vue'),
        meta: { auth: true },
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  if (to.meta.auth && !store.getters.isLogin) {
    alert('인증이 필요한 페이지입니다.');
    next('/admin/login');
    return;
  }
  next();
});

export default router;
