<template>
  <div class="wrap-agree">
    <span>해당 페이지는 현재 준비중입니다.</span>
    <router-link to="/"> <button>MAIN</button></router-link>
  </div>
</template>

<script>
export default {};
</script>

<style>
.wrap-agree {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.wrap-agree span {
  font-size: 30px;
  font-weight: 800px;
}

.wrap-agree button {
  margin-top: 30px;
  padding: 11px 45px;
  box-sizing: border-box;
  background-color: #ff4343;
  color: #fff;
  border-radius: 11px;
  font-size: 15px;
  font-weight: 600;
}
</style>
