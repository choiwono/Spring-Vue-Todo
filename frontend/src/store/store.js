import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state : {
    tasks: [],
  },
  plugins: [createPersistedState()],
  getters: {
    getTasks: (state) => {
      return state.tasks;
    }
  },

  mutations: {
    changeTasks: (state,payload) => {
      return state.tasks = payload.arr;
    },
    addTask: (state,payload) => {
      state.tasks.push(payload.data);
    },
    deleteTask: (state,payload) => {

    }
  },
  actions: {

  }
});
