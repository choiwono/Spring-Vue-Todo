import Vue from 'vue'
import Router from 'vue-router'
import Home from "@/components/Home";
import axios from 'axios';

Vue.use(Router);

const router = new Router({
  mode:'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'main',
      component: Home
    },
    {
      path: '/home/',
      name: 'home',
      component: Home
    }
  ]
});

/*router.beforeEach((to,from,next) => {
  // 여기서 알람체크를 하면 좋을 것 같다!!
});*/

export default router


