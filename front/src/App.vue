<template>
  <div>
    <div class="container">
      <header class="d-flex justify-content-center py-3">
        <ul class="nav">
          <li class="nav-item">
            <button class="nav-link active">
              <router-link :to="{name: 'home'}">Shop</router-link>
            </button>
          </li>
          <li class="nav-item">
            <button class="nav-link"><router-link to="/cart"> Cart </router-link></button>
          </li>
          <li class="nav-item">
            <button class="nav-link" @click="goTOProfile"> Profile </button>
          </li>
        </ul>
      </header>
    </div>
  </div>

  <router-view></router-view>
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous"
  />
</template>
<script>
import Shop from "./views/Shop.vue";
import Cart from "./views/Cart.vue";
import Profile from "./views/Profile.vue";


export default {
  method: {
    goTOProfile(){
      console.log(this.$route);
      this.$router.push("/profile");
    }
  },
};
</script>
<style></style>
