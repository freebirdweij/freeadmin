<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="ID" prop="configId">
            <el-input v-model="form.configId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="收件人">
            <el-input v-model="form.fromUser" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="邮件服务器SMTP地址">
            <el-input v-model="form.host" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.pass" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="端口">
            <el-input v-model="form.port" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="发件者用户名">
            <el-input v-model="form.user" style="width: 370px;" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="configId" label="ID" />
        <el-table-column prop="fromUser" label="收件人" />
        <el-table-column prop="host" label="邮件服务器SMTP地址" />
        <el-table-column prop="pass" label="密码" />
        <el-table-column prop="port" label="端口" />
        <el-table-column prop="user" label="发件者用户名" />
        <el-table-column v-if="checkPer(['admin','toolEmailConfig:edit','toolEmailConfig:del'])" label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import crudToolEmailConfig from '@/api/toolEmailConfig'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { configId: null, fromUser: null, host: null, pass: null, port: null, user: null }
export default {
  name: 'ToolEmailConfig',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: 'Ifemail', url: 'api/toolEmailConfig', idField: 'configId', sort: 'configId,desc', crudMethod: { ...crudToolEmailConfig }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'toolEmailConfig:add'],
        edit: ['admin', 'toolEmailConfig:edit'],
        del: ['admin', 'toolEmailConfig:del']
      },
      rules: {
        configId: [
          { required: true, message: 'ID不能为空', trigger: 'blur' }
        ]
      }    }
  },
  methods: {
    // 钩子：在获取表格数据之前执行，false 则代表不获取数据
    [CRUD.HOOK.beforeRefresh]() {
      return true
    }
  }
}
</script>

<style scoped>

</style>
