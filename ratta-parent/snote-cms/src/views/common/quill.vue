<template>
  <div class="common">
    <quill-editor
      v-model="value"
      ref="updateData"
      :options="editorOption"
      @blur="onEditorBlur($event)"
      @focus="onEditorFocus($event)"
      @change="onEditorChange($event)"
      show-word-limit
    ></quill-editor>
  </div>
</template>

<script>
import { quillEditor } from "vue-quill-editor"; //调用编辑器
import "quill/dist/quill.snow.css";
export default {
  name:'common',
  components: { quillEditor },
  data() {
    return {
      value: "",
      editorOption: {}
    };
  },
  created(){

  },
  mounted(){

  },
  methods:{
        onEditorBlur(){

        },
        onEditorFocus(){

        },
        onEditorChange(){

        }
  }
};
</script>

<style>
</style>
