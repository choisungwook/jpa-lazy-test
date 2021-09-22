import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/default/Index";

Vue.use(VueRouter);

const routes = [
  {
    path: "/lazy/",
    name: "Home",
    component: DefaultLayout,
    children: [
      {
        path: "/lazy/school",
        name: "school",
        component: () => import("@/views/school/School.vue"),
      },
      {
        path: "/lazy/classroom",
        name: "classroom",
        component: () => import("@/views/classroom/Classroom.vue"),
      },
      {
        path: "/lazy/home",
        name: "home",
        component: () => import("@/views/Home.vue"),
      },
      {
        path: "/lazy/helloworld",
        name: "helloworld",
        component: () =>
          import(/* webpackChunkName: "about" */ "../views/Helloworld.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
