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

    },
    updateTask: (state,payload) => {
      let tasks = state.tasks;
      for(let i=0; i<tasks.length; i++){
        if(tasks[i].id === payload.id){
          tasks[i].title = payload.title;
          tasks[i].endDate = payload.endDate;
          tasks[i].priorityOrderType = payload.priorityOrderType;
        }
      }
    }
  },
  actions: {

  }
});
