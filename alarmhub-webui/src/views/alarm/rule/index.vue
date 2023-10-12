<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <el-input v-model="query.name" clearable placeholder="输入名称搜索" style="width: 200px" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <date-range-picker v-model="query.createTime" class="date-item" />
        <rrOperation />
      </div>
      <crudOperation :permission="permission">
        <el-button
          slot="left"
          v-permission="['admin','app:add']"
          :disabled="!currentRow"
          class="filter-item"
          size="mini"
          type="primary"
          icon="el-icon-plus"
          @click="copy"
        >复制</el-button>
      </crudOperation>
    </div>
    <!--表单组件-->
    <el-dialog append-to-body :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="800px">
      <el-form ref="form" :model="form" :rules="rules" size="small" label-width="100px">
        <el-form-item label="规则名称" prop="name">
          <el-input v-model="form.name" style="width: 670px" placeholder="名称" />
        </el-form-item>
        <el-form-item label="告警级别" prop="level">
          <el-input-number v-model="form.level" placeholder="例如：1,2,3" />
        </el-form-item>
        <el-form-item label="规则类型" prop="ruleType">
          <el-input v-model="form.ruleType" style="width: 670px" placeholder="http,prometheus" />
        </el-form-item>
        <el-form-item label="规则内容" prop="ruleContent">
          <el-input v-model="form.ruleContent" style="width: 670px" placeholder="PromQL" />
        </el-form-item>
        <el-form-item label="参考规则内容" prop="ruleContentRefer">
          <el-input v-model="form.ruleContentRefer" style="width: 670px" placeholder="PromQL" />
        </el-form-item>
        <el-form-item label="对比方式" prop="compare">
          <el-input v-model="form.compare" style="width: 670px" placeholder="> <" />
        </el-form-item>
        <el-form-item label="预期值" prop="expected">
          <el-input v-model="form.expected" style="width: 670px" placeholder="1000" />
        </el-form-item>
        <el-form-item label="执行周期" prop="execTime">
          <el-input v-model="form.execTime" style="width: 670px" placeholder="5s" />
        </el-form-item>
        <el-form-item label="执行次数" prop="execCount">
          <el-input v-model.number="form.execCount" style="width: 670px" placeholder="3" />
        </el-form-item>
        <el-form-item label="预案连接" prop="dealUrl">
          <el-input v-model="form.dealUrl" style="width: 670px" placeholder="http://127.0.0.1:8080" />
        </el-form-item>
        <el-form-item label="告警类型" prop="notifyType">
          <el-input v-model="form.notifyType" style="width: 670px" placeholder="邮箱" />
        </el-form-item>
        <el-form-item label="回调地址" prop="callBackUrl">
          <el-input v-model="form.callBackUrl" style="width: 670px" placeholder="http://127.0.0.1:8080" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="crud.cancelCU">取消</el-button>
        <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
      </div>
    </el-dialog>
    <!--表格渲染-->
    <el-table ref="table" v-loading="crud.loading" :data="crud.data" highlight-current-row style="width: 100%" @selection-change="crud.selectionChangeHandler" @current-change="handleCurrentChange">
      <el-table-column type="selection" width="55" />
      <el-table-column prop="name" label="规则名称" />
      <el-table-column prop="level" label="告警级别" />
      <el-table-column prop="ruleType" label="规则类型" />
      <el-table-column prop="ruleContent" label="规则内容" />
      <el-table-column prop="execTime" label="执行周期" />
      <el-table-column prop="execCount" label="执行次数" />
      <el-table-column v-if="checkPer(['admin','app:edit','app:del'])" label="操作" width="150px" align="center">
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
import crudAlertRule from '@/api/alarm/rule'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'
import DateRangePicker from '@/components/DateRangePicker'

const defaultForm = {
  id: null,
  name: 'MSG:test',
  level: 1,
  ruleType: 'prometheus',
  compare: '>',
  ruleContent: '',
  expected: '1000',
  execTime: '5',
  execCount: '3',
  notifyType: 'email'
}
export default {
  name: 'App',
  components: { pagination, crudOperation, rrOperation, udOperation, DateRangePicker },
  cruds() {
    return CRUD({ title: '应用', url: 'api/alarm/rule', crudMethod: { ...crudAlertRule }})
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  data() {
    return {
      currentRow: null,
      permission: {
        add: ['admin', 'alertRule:add'],
        edit: ['admin', 'alertRule:edit'],
        del: ['admin', 'alertRule:del']
      },
      rules: {
        name: [
          { required: true, message: '规则名称', trigger: 'blur' }
        ],
        level: [
          { required: true, message: '告警级别', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    copy() {
      for (const key in this.currentRow) {
        this.form[key] = this.currentRow[key]
      }
      this.form.id = null
      this.form.createTime = null
      this.crud.toAdd()
    },
    handleCurrentChange(row) {
      this.currentRow = JSON.parse(JSON.stringify(row))
    }
  }
}
</script>

<style scoped>
</style>
