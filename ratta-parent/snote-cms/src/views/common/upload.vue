<template>
    <div class="upload">
         <el-upload
                  ref="upload"
                  action="#"
                  accept="image/png, image/gif, image/jpg, image/jpeg"
                  list-type="picture-card"
                  :limit="limitNum"
                  :auto-upload="false"
                  :on-exceed="handleExceed"
                  :before-upload="handleBeforeUpload"
                  :on-preview="handlePictureCardPreview"
                  :on-remove="handleRemove"
                >
                  <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                  <img width="100%" :src="dialogImageUrl" alt>
        </el-dialog>
    </div>
</template>

<script>
export default {
    data(){
        return {
            
        }
    },
    created(){

    },
    mounted(){

    },
    methods:{

    }
}
</script>

<style>

</style>
