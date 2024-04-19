import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import './plugins/element.js'

axios.defaults.baseURL = 'http://localhost:8010/'

Vue.config.productionTip = false
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
