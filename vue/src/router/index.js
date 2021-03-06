import Vue from 'vue'
import Router from 'vue-router'
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading
/* layout */
import Layout from '../views/layout/Layout'

const _import = require('./_import_' + process.env.NODE_ENV)
Vue.use(Router)
export const constantRouterMap = [
  {path: '/login', component: _import('login/index'), hidden: true},
  {path: '/404', component: _import('404'), hidden: true},
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '首页',
    hidden: true,
    children: [{
      path: 'dashboard', component: _import('dashboard/index')
    }]
  }
]
export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
export const asyncRouterMap = [
  {
    path: '/system',
    component: Layout,
    redirect: '/system/device',
    name: '功能模块',
    meta: {title: '功能模块', icon: 'tree'},
    children: [
      {
        path: 'device', name: '设备管理', component: _import('device/device'), meta: {title: '设备管理', icon: 'example'}, menu: 'device'
      },
    ]
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/user/',
    name: '',
    meta: {title: '用户权限管理', icon: 'table'},
    children: [
      {
        path: '', name: '用户管理', component: _import('user/user'), meta: {title: '用户管理', icon: 'user'}, menu: 'user'
      },
      {
        path: 'role', name: '角色管理', component: _import('user/role'), meta: {title: '角色管理', icon: 'user'}, menu: 'role'
      },
      {
        path: 'permission', name: '权限管理', component: _import('user/permission'), meta: {title: '权限管理', icon: 'password'}, menu: 'permission'
      },
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]
