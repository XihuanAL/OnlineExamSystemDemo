import { createStore } from 'vuex'

export default createStore({
  state: {
    token: '',
    role: '',
  },
  getters: {
  },
  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
      localStorage.setItem('token', token)
    },
    SET_ROLE: (state, role) => {
      state.role = role
      localStorage.setItem('role', role)
    },
    resetState: (state) => {
      state.token = ''
      state.role = ''
    },
  },
  actions: {
  },
  modules: {
  }
})
