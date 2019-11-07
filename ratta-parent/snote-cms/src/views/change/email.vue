<template>
    <div class="email">
        <div class="head">
            <el-button type="primary" @click="add">新增</el-button>
            <el-button type="primary" @click="dele">删除</el-button>
        </div>
         <el-table :data="tableData" style="width: 100%;margin-top:30px;" border>
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column align="center" label="姓名"  prop="date"></el-table-column>
            <el-table-column align="center" label="邮箱" prop="date"></el-table-column>
            <el-table-column align="center" label="操作">
                <template slot-scope="scope">
                    <div class="function-icon">
                        <el-tooltip class="item" effect="dark" content="修改" placement="bottom">
                            <img @click="change(scope.row)" src="../../assets/iconfont/amend.png" alt>
                        </el-tooltip>
                    </div>
                </template>
            </el-table-column>
        </el-table>
    <el-dialog
      title="添加"
      :visible.sync="dialogFormVisibles"
      @close="addClose"
      :close-on-click-modal="false"
    >
      <el-form
        :model="add"
        :rules="addRules"
        ref="add"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
      <el-form-item label="姓名" :label-width="formLabelWidth">
              <el-input type="text" v-model="value"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth">
           <el-input type="text" v-model="value"></el-input>
        </el-form-item>
       
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addClose">取 消</el-button>
        <el-button type="primary" @click="keep">保存</el-button>
      </div>
    </el-dialog>


    <el-dialog
      title="修改"
      :visible.sync="dialogFormVisible"
      @close="changeClose"
      :close-on-click-modal="false"
    >
      <el-form
        :model="change"
        :rules="changeRules"
        ref="change"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="姓名" :label-width="formLabelWidth">
           <el-input type="text" v-model="value"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth">
           <el-input type="text" v-model="value"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="changeClose">取 消</el-button>
        <el-button type="primary" @click="keep">保存</el-button>
      </div>
    </el-dialog>
    </div>
</template>

<script>
export default {
    data() {
        return {
            value:'',
            tableData:[{
                date:'123'
            }],
            dialogFormVisible:false,
            formLabelWidth:'100px',
            changeRules:[],

            dialogFormVisibles:false,
            addRules:[]
        }
    },
    created(){

    },
    mounted(){

    },
    methods:{
        //新增
        add(){
            this.dialogFormVisibles = true
        },

        //新增关闭
        addClose(){
            this.dialogFormVisibles = false
        },
        //修改
        change(){
            this.dialogFormVisible = true
        },
        //修改关闭
        changeClose(){
             this.dialogFormVisible = false
        },

        //删除
        dele(){

        }
    }
}
</script>

<style scoped>

</style>
