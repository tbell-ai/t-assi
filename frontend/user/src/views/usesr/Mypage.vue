<template>
  <div>
    <FrontHeader></FrontHeader>
    <router-view />
    <FrontFooter></FrontFooter>
  </div>
</template>

<script>
import FrontHeader from '../../components/common/FrontHeader.vue';
import FrontFooter from '../../components/common/FrontFooter.vue';

export default {
  components: {
    FrontHeader,
    FrontFooter,
  },
};
</script>

<style></style>
