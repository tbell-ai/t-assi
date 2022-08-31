<template>
  <div>
    <FrontHeader></FrontHeader>
    <SignupForm></SignupForm>
    <FrontFooter></FrontFooter>
  </div>
</template>

<script>
import FrontHeader from '../components/common/FrontHeader.vue';
import FrontFooter from '../components/common/FrontFooter.vue';
// import SignupFrom from '../components/users/SignupForm.vue';
import SignupForm from '../components/users/SignupForm.vue';

export default {
  components: {
    FrontHeader,
    FrontFooter,
    SignupForm,
  },
};
</script>

<style></style>
