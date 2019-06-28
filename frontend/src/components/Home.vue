<template>
  <div class="container">
    <v-container grid-list-md>
      <v-layout row wrap>
        <v-flex xs12 v-for="item in this.$store.getters.getTasks" :key="tasks.id">
          <v-card color="primary">
            <v-card-actions class="pb-0">
              <v-btn icon class="modify-icon m-0 cursor-pointer" v-b-modal.modify-modal>
                <icon name="pen"></icon>
              </v-btn>
              <v-btn icon class="remove-icon m-0 cursor-pointer" @click="removeTodo(item.id)">
                <icon name="minus-circle"></icon>
              </v-btn>
              <v-card-text>
                <span class="date-time">{{ item.endDate.substr(0,10) }}</span>
                <span class="priority-type">{{ item.priorityOrderType }}</span>
              </v-card-text>
            </v-card-actions>
            <v-card-text class="px-0 title">
              {{ item.title }}
            </v-card-text>
          </v-card>
          <b-modal ref="modify-modal" id="modify-modal" title="todo 수정" @ok="handleOk" ok-only centered>
            <form @submit.stop.prevent="handleSubmit">
              <b-form-input class="mb-3" v-model="endDate" type="date"></b-form-input>
              <b-form-input class="mb-3" v-model="title" placeholder="할일 제목"></b-form-input>
              <b-form-input class="mb-3" v-model="content" placeholder="할일 내용"></b-form-input>
              <b-form-group class="mb-3" label="일의 우선순위를 선택해주세요">
                <b-form-radio-group v-model="selected" name="priorityOrderType" :options="priority">
                </b-form-radio-group>
              </b-form-group>
            </form>
          </b-modal>
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
        selected : [],
        options : []
      }
    },
    methods: {
      removeTodo(id){
        this.$http.delete('/tasks/'+id)
          .then((result) => {
              this.fetchDate();
          })
      },
      fetchDate(){
        this.$http.get('/tasks/all')
          .then((result) => {
            this.$store.commit('changeTasks', {
              arr: result
            });
          })
      },
      handleOk(){
        this.handleSubmit();
      },
      handleSubmit(){
        let data = new FormData();
        data.append('title',this.title);
        data.append('content',this.content);
        data.append('endDate',this.endDate);
        data.append('priorityOrderType',this.selected);

        this.$http.post('/tasks/',data).then((result) =>{
          this.$store.commit('addTask', {
            data: result
          });
        })
      }
    },

    computed: {

    },

    mounted() {
      this.fetchDate();
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .card--flex-toolbar {
    margin-top: -64px;
  }
  .remove-icon {
    color: #DC143C;
  }
  .modify-icon {
    color: #17a2b8;
  }
  .cursor-pointer {
    cursor: pointer;
  }
  .date-time {
    padding-right:7.5px;
  }
  .priority-type {
    padding-left:7.5px;
    font-weight:700;
  }
  .title {
    font-size:15px !important;
  }
</style>
