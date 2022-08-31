<template>
  <div>
    <FrontHeader></FrontHeader>
    <LoginForm></LoginForm>
    <FrontFooter></FrontFooter>
  </div>
</template>

<script>
import FrontHeader from '../components/common/FrontHeader.vue';
import FrontFooter from '../components/common/FrontFooter.vue';
import LoginForm from '../components/users/LoginForm.vue';

export default {
  components: {
    FrontHeader,
    FrontFooter,
    LoginForm,
  },
};
</script>

<style></style>
