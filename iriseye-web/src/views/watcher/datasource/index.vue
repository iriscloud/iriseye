<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <el-input v-model="query.blurry" clearable placeholder="模糊搜索" style="width: 200px" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <date-range-picker v-model="query.createTime" class="date-item" />
        <rrOperation />
      </div>
      <crudOperation :permission="permission" />
    </div>
    <!--表单组件-->
    <eForm ref="execute" :datasource-info="currentRow" />
    <el-dialog append-to-body :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="530px">
      <el-form ref="form" :model="form" :rules="rules" size="small" label-width="100px">
        <el-form-item label="连接名称" prop="name">
          <el-input v-model="form.name" style="width: 370px" />
        </el-form-item>
        <el-form-item label="数据源类型" prop="sourceTypeName">
          <el-select
            v-model="form.type"
            style="width: 200px"
            placeholder="请选择"
            @change="changeSourceType"
          >
            <el-option
              v-for="item in sourceTypeNames"
              :key="item.name"
              :label="item.name"
              :value="item.name"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="数据源" prop="url">
          <el-input v-model="form.url" style="width: 300px" />
          <el-button :loading="loading" type="success" @click="testConnectDataSource">测试</el-button>
        </el-form-item>
        <el-form-item label="用户" prop="userName">
          <el-input v-model="form.userName" style="width: 370px" />
        </el-form-item>
        <el-form-item label="密码" prop="pwd">
          <el-input v-model="form.pwd" type="password" style="width: 370px" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="crud.cancelCU">取消</el-button>
        <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
      </div>
    </el-dialog>
    <!--表格渲染-->
    <el-table ref="table" v-loading="crud.loading" :data="crud.data" highlight-current-row stripe style="width: 100%" @selection-change="handleCurrentChange">
      <el-table-column type="selection" width="55" />
      <el-table-column prop="name" width="130px" label="数据源名称" />
      <el-table-column prop="url" label="连接地址" />
      <el-table-column prop="userName" width="200px" label="用户名" />
      <el-table-column prop="createTime" width="200px" label="创建日期" />
      <el-table-column v-if="checkPer(['admin','datasource:edit','datasource:del'])" label="操作" width="150px" align="center">
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
</template>

<script>
import { getAllSourceTypeNames } from '@/api/watcher/datasource'
import crudDataSource from '@/api/watcher/datasource'
import { testDbConnect } from '@/api/watcher/connect'
import eForm from './execute'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'
import DateRangePicker from '@/components/DateRangePicker'

const defaultForm = { id: null, name: null, type: null, url: 'jdbc:mysql://', userName: null, pwd: null }
export default {
  name: 'DataSource',
  components: { eForm, pagination, crudOperation, rrOperation, udOperation, DateRangePicker },
  cruds() {
    return CRUD({ title: '数据库', url: 'api/datasource', crudMethod: { ...crudDataSource }})
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  data() {
    return {
      currentRow: {},
      selectIndex: '',
      datasourceInfo: '',
      sourceTypeNames: [],
      loading: false,
      permission: {
        add: ['admin', 'datasource:add'],
        edit: ['admin', 'datasource:edit'],
        del: ['admin', 'datasource:del']
      },
      rules: {
        name: [
          { required: true, message: '请输入数据库名称', trigger: 'blur' }
        ],
        url: [
          { required: true, message: '请输入数据库连接地址', trigger: 'blur' }
        ],
        userName: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        pwd: [
          { required: true, message: '请输入数据库密码', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getSourceTypeNames()
  },
  methods: {
    testConnectDataSource() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.loading = true
          testDbConnect(this.form).then((res) => {
            this.loading = false
            this.crud.notify(res ? '连接成功' : '连接失败', res ? 'success' : 'error')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    },
    execute() {
      this.$refs.execute.dialog = true
    },
    getSourceTypeNames() {
      getAllSourceTypeNames().then(res => {
        this.sourceTypeNames = res.content
      }).catch(() => { })
    },
    changeSourceType(value) {
      for (const item in this.sourceTypeNames) {
        console.log(this.sourceTypeNames[item].name)
        console.log(value)
        if (value === this.sourceTypeNames[item].name) {
          defaultForm.url = this.sourceTypeNames[item].urlDesc
          this.form.url = this.sourceTypeNames[item].urlDesc
          console.log(defaultForm.url)
        }
      }
    },
    handleCurrentChange(selection) {
      this.crud.selections = selection
      if (selection.length === 1) {
        const row = selection[0]
        this.selectIndex = row.id
        this.currentRow = row
      } else {
        this.currentRow = {}
        this.selectIndex = ''
      }
    }
  }
}
</script>

<style scoped>
</style>
