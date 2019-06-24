import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state : {
    categories: [],
  },
  plugins: [createPersistedState()],
  getters: {
    getCategories: (state) => {
      return state.categories;
    }
  },

  mutations: {
    changeCategories: (state,payload) => {

    }
  },
  actions: {

  }
});
