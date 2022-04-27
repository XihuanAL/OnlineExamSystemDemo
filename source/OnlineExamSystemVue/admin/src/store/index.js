import { createStore } from 'vuex'

export default createStore({
  state: {
    token: '',
  },
  getters: {
  },
  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
      localStorage.setItem('token', token)
    },
    resetState: (state) => {
      state.token = ''
    },
  },
  actions: {
  },
  modules: {
  }
})
