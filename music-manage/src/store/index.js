import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

// 存放缓存数据
const store = new Vuex.Store({
  state: {
    HOST: 'http://127.0.0.1:8000',
    isPlay: false,
    url: '',
    id: ''
  },
  getters: {
    isPlay: state => state.isPlay,
    url: state => state.url,
    id: state => state.id
  },
  mutations: {
    setIsPlay: (state, isPlay) => { state.isPlay = isPlay },
    setUrl: (state, url) => { state.url = url },
    setId: (state, id) => { state.id = id }
  }
})

// 导出
export default store
