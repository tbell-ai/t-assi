<template>
  <div>
    <template v-if="isUserLogin">
      <AdminHeader></AdminHeader>
      <main id="main">
        <AdminMenu></AdminMenu>
        <router-view />
      </main>
    </template>
    <template v-else>
      <router-view />
    </template>
  </div>
</template>

<script>
import AdminHeader from './components/common/AdminHeader.vue';
import AdminMenu from './components/common/AdminMenu.vue';

export default {
  components: {
    AdminHeader,
    AdminMenu,
  },
  computed: {
    isUserLogin() {
      return this.$store.getters.isLogin;
    },
  },
};
</script>

<style>
@import './css/reset.css';
@import './css/common.css';
</style>
