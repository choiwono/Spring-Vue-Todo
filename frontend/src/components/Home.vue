<template>
  <div class="container">
    <v-container grid-list-md>
      <v-layout row wrap>
        <v-flex xs12 v-for="item in this.$store.getters.getTasks" :key="tasks.id">
          <v-card>
            <v-card-text class="d-flex">
              <div class="text-left">
                <v-btn icon class="modify-icon m-0 cursor-pointer" @click="modifyModal(item.id)">
                  <icon name="pen"></icon>
                </v-btn>
                <v-btn icon class="remove-icon m-0 cursor-pointer" @click="removeTodo(item.id)">
                  <icon name="minus-circle"></icon>
                </v-btn>
              </div>
              <div class="text-right mt-1">
                <span class="date-time">{{ item.endDate.substr(0,10) }}</span>
                  <b-badge pill v-bind:class="[ item.priorityOrderType, priorityFont ]">
                    <span >
                      {{ item.priorityOrderType }}
                    </span>
                  </b-badge>
              </div>
            </v-card-text>
            <v-card-actions>
              <v-card-text class="px-0 title text-left pt-0 pl-3">
                {{ item.title }}
              </v-card-text>
            </v-card-actions>
          </v-card>
          <b-modal :ref="'modify-modal'+item.id" title="todo 수정" @ok="handleOk" ok-only centered>
            <form @submit.stop.prevent="handleSubmit">
              <input :value="id" name ="id" type="hidden" />
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
        selected : '',
        options : [],
        endDate : '',
        title : '',
        content : '',
        id: '',
        priority: ['High','Mid','Low'],
        priorityFont : 'priorityFont'
      }
    },
    methods: {
      removeTodo(id){
        this.$http.delete('/tasks/'+id)
          .then((result) => {
              this.fetchDate();
          });
          this.$nextTick(() => {
            this.$notify({
              group:'notify', title:'삭제완료',
              text: '데이터 삭제가 성공했습니다',type:'success'
            });
          })
      },
      modifyModal(id){
        this.$http.get('/tasks/'+id).then((result) => {
          this.id = result.id;
          this.endDate = result.endDate.substr(0,10);
          this.title = result.title;
          this.content = result.content;
          this.selected = result.priorityOrderType;
        });
        this.$nextTick(() =>{
          this.$refs['modify-modal'+id][0].show();
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
        data.append('id',this.id);
        data.append('title',this.title);
        data.append('content',this.content);
        data.append('endDate',this.endDate+" 23:59:59");
        data.append('priorityOrderType',this.selected);

        this.$http.put('/tasks/'+this.id,data).then((result) => {
          this.$store.commit('updateTask', {
             id:this.id,title:this.title,endDate:this.endDate,
             priorityOrderType:this.selected
          });
          this.$nextTick(() =>{
            this.$notify({ group:'notify', title:'TODO가 수정되었습니다', text:'성공했습니다', type:'success'});
          })
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
  body {
    font-size:100%;
  }
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
    font-size:1.1rem;
  }
  .priorityFont {
    color:#ffffff;
    font-weight:800;
    padding:7.5px;
    font-size:0.75rem;
  }
  .Mid {
    background-color:#ff9900;
  }
  .High {
    background-color:#ff3300;
  }
  .title {
    font-size:1.2em !important;
  }
</style>
