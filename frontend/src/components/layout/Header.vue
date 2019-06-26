<template>
  <div>
    <v-toolbar color="#00bfa5">
      <!--<v-toolbar-side-icon></v-toolbar-side-icon>-->
      <v-toolbar-title class="white--text">Todo</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
  </div>
</template>

<script>
  export default {
    name: "Header",
    data() {
      return {

      }
    },

    methods: {

    },
    // TODO 화면이 전환될때 메서드를 실행시켜서 새 메세지를 확인하고 싶은데
    // 지금은 홈 화면으로 돌아가야만 실행되는듯 하다
    mounted() {

    }
  }
</script>

<style scoped>
  .navbar-brand > a {
    color: #00bfa5;
    font-weight: 700;
    text-decoration: none;
  }
</style>
