import { createRouter, createWebHistory } from 'vue-router';
import store from '../store';

const routes = [
  {
    path: '',
    redirect: '/main',
  },
  {
    path: '/',
    redirect: '/main',
  },
  {
    path: '/main',
    name: 'main',
    component: () => import('../views/MainPage.vue'),
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/LoginPage.vue'),
  },
  {
    path: '/signup',
    name: 'signup',
    component: () => import('../views/SignupPage.vue'),
  },
  {
    path: '/privacypolicyagree',
    name: 'privacypolicyagree',
    component: () => import('../components/agree/PrivacyPolicyAgree.vue'),
  },
  {
    path: '/privacyuseagree',
    name: 'privacyuseagree',
    component: () => import('../components/agree/PrivacyUseAgree.vue'),
  },
  {
    path: '/termsofuseagree',
    name: 'termsofuseagree',
    component: () => import('../components/agree/TermsOfUseAgree.vue'),
  },
  {
    path: '/request',
    name: 'request',
    component: () => import('../views/contact/ContactPage.vue'),
    meta: { auth: true },
    children: [
      {
        path: '',
        redirect: '/request/form',
        meta: { auth: true },
      },
      {
        path: 'form',
        component: () => import('../components/contact/ContactForm.vue'),
        meta: { auth: true },
      },
      {
        path: 'requestcomplete',
        component: () => import('../components/contact/ContactCompletion.vue'),
        meta: { auth: true },
      },
    ],
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: () => import('../views/usesr/Mypage.vue'),
    meta: { auth: true },
    children: [
      {
        path: 'view/:id',
        component: () => import('../components/users/UserInfoView.vue'),
        meta: { auth: true },
      },
      {
        path: 'edit/:id',
        component: () => import('../components/users/UserInfoEdit.vue'),
        meta: { auth: true },
      },
      {
        path: 'contact/list',
        component: () => import('../components/users/UserContactList.vue'),
        meta: { auth: true },
      },
      {
        path: 'contact/view/:id',
        component: () => import('../components/users/UserContactView.vue'),
        meta: { auth: true },
      },
      {
        path: 'contact/edit/:id',
        component: () => import('../components/users/UserContactEdit.vue'),
        meta: { auth: true },
      },
    ],
  },
  {
    path: '/project',
    name: 'project',
    component: () => import('../views/project/ProjectPage.vue'),
    meta: { auth: true },
    children: [
      {
        path: 'intro/:id',
        component: () => import('../components/project/ProjectIntro.vue'),
        meta: { auth: true },
      },
      {
        path: 'dashboard/:id',
        component: () => import('../components/project/ProjectDashboard.vue'),
        meta: { auth: true },
      },
      {
        path: 'contract-modify',
        component: () =>
          import('../components/project/ProjectContractModify.vue'),
        meta: { auth: true },
      },
      {
        path: 'account-added',
        component: () =>
          import('../components/project/ProjectAccountAdded.vue'),
        meta: { auth: true },
      },
      {
        path: 'qna-list/:id',
        component: () => import('../components/project/ProjectQnaList.vue'),
        meta: { auth: true },
      },
      {
        path: 'qna-create/:id',
        component: () => import('../components/project/ProjectQnaForm.vue'),
        meta: { auth: true },
      },
      {
        path: 'qna-view/:id',
        component: () => import('../components/project/ProjectQnaView.vue'),
        meta: { auth: true },
      },
      {
        path: 'qna-edit/:id',
        component: () => import('../components/project/ProjectQnaEdit.vue'),
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
    next('/login');
    return;
  }

  if (to.path === '/login') {
    if (store.getters.isLogin) {
      alert('로그인 상태에서 로그인 페이지로 진입할 수 없습니다.');
      next('/main');
    }
  }

  if (to.path !== '/main') {
    store.state.subPage = false;
  } else {
    store.state.subPage = true;
  }

  next();
});

export default router;
