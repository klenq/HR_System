import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
  {
    path: "/a",
    name: "A",
    component: () =>
        import("../components/A.vue")
  },

  {
    path: "/viewp",
    name: "ViewP",
    component: () =>
        import("../views/ViewP"),
    children:[
      {
        path: "/viewa",
        name: "viewA",
        component: () =>
            import("../views/ViewA.vue"),
      },
      {
        path: "/viewb",
        name: "ViewB",
        component: () =>
            import("../views/ViewB")
      },
    ]
  },
];

const router = new VueRouter({
  routes,
});

export default router;
