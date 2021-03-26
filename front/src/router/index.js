import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  //  新增地址
  {
    path: '/addressNew',
    component: function () {
        return import('../views/AddressNew')
    }
  },
  //  地址列表
  {
    path: '/addressList',
    component: function () {
        return import('../views/AddressList')
    }
  },
  // 编辑地址
  {
    path: '/addressEdit',
    component: function () {
      return import('../views/AddressEdit')
    }
  },
  // 填写订单详情
  {
    path: '/orderDetail',
    component: function () {
      return import('../views/OrderDetail')
    }
  },
  // 支付成功
  {
    path: '/paySuccess',
    component: function () {
      return import('../views/PaySuccess')
    }
  },
  // 订单信息
  {
    path: '/orderInfo',
    component: function () {
      return import('../views/OrderInfo')
    }
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
