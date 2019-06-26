<template>
  <div class="container">
    <v-container grid-list-md>
      <v-layout row wrap>
        <v-flex xs12 v-for="item in this.$store.getters.getTasks" :key="tasks.id">
          <v-card color="primary">
            <v-card-text class="px-0">
              {{ item.title }}
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
  export default {
    name: 'HelloWorld',
    data() {
      return {
        tasks : [],
      }
    },
    methods: {

    },

    computed: {

    },

    mounted() {
      this.$http.get('/tasks/all')
        .then((result) => {
          this.$store.commit('changeTasks', {
            arr: result
          });
      })
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .card--flex-toolbar {
    margin-top: -64px;
  }
</style>
