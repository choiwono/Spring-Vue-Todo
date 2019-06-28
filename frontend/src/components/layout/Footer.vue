<template>
  <div>
    <v-bottom-nav
    :value="true"
    fixed
    >
    <v-btn
      color="teal"
      flat
      value="nearby"
      v-b-modal.add-modal
      height="75"
    >
      <icon name="plus"></icon>
    </v-btn>
    </v-bottom-nav>
    <b-modal ref="add-modal" id="add-modal" title="todo를 추가해주세요" @ok="handleOk" ok-only centered>
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
  </div>
</template>

<script>
    export default {
      name: "Footer",
      data() {
        return {
          bottomNav:'',
          title: '',
          content: '',
          priority: ['High','Mid','Low'],
          selected: '',
          endDate: '',
        }
      },
      methods : {
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
          this.$nextTick(() =>{
            this.title = '';
            this.content = '';
            this.endDate = '';
            this.selected = '';
          })
        }
      }
    }
</script>

<style scoped>
footer {
  height:56px;
}
.bottom-nav {
  transform:none;
}
</style>
