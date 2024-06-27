import { createRouter, createWebHashHistory } from "vue-router";
import Shop from "../views/Shop.vue";
export default createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: "/:pathMatch(.*)*",
      component: () => import("../views/NotFound.vue"),
    },
    {
      path: "/shop",
      name: "home",
      component: Shop,
    },
    {
      path: "/profile",
      component: () => import("../views/Profile.vue"),
      children: [
        {
          path: "a_page",
          component: () => import("../views/ComponentA.vue"),
        },
        {
          path: "b_page",
          component: () => import("../views/ComponentB.vue"),
        },
      ],
    },
    {
      path: "/cart",
      component: () => import("../views/Cart.vue"),
    },
  ],
});
