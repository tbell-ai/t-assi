<template>
  <div id="wrap">
    <FrontHeader></FrontHeader>
    <router-view></router-view>
    <FrontFooter></FrontFooter>
  </div>
</template>

<script>
import FrontHeader from '../../components/common/FrontHeader.vue';
import FrontFooter from '../../components/common/FrontFooter.vue';
// import RequestForm from '../../components/contact/ContactForm.vue';
//import RequestForm from '../../components/contact/ContactForm_2.vue';

export default {
  components: {
    FrontHeader,
    FrontFooter,
    // RequestForm,
  },
};
</script>

<style></style>
