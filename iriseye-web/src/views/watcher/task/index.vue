<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <el-input v-model="query.taskName" clearable size="small" placeholder="输入名称搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="toQuery" />
        <date-range-picker v-model="query.createTime" class="date-item" />
        <rrOperation />
      </div>
      <crudOperation :permission="permission">
        <!-- 任务日志 -->
        <el-button
          slot="right"
          class="filter-item"
          size="mini"
          type="info"
          icon="el-icon-tickets"
          @click="doLog"
        >日志</el-button>
      </crudOperation>
      <Log ref="log" />
    </div>
    <!--Form表单-->
    <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" append-to-body width="730px">
      <el-form ref="form" :inline="true" :model="form" :rules="rules" size="small" label-width="100px">
        <el-form-item label="名称" prop="taskName">
          <el-input v-model="form.taskName" style="width: 220px;" />
        </el-form-item>

        <el-form-item label="数据源" prop="sourceName">
          <el-select
            v-model="form.sourceName"
            style="width: 200px"
            placeholder="请选择"
          >
            <el-option
              v-for="item in sourceNames"
              :key="item.name"
              :label="item.name"
              :value="item.name"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="执行器" prop="beanName">
          <el-select
            v-model="form.beanName"
            style="width: 200px"
            placeholder="请选择"
          >
            <el-option
              v-for="item in taskNames"
              :key="item.name"
              :label="item.name"
              :value="item.name"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="执行方法" prop="methodName">
          <el-input v-model="form.methodName" style="width: 220px;" />
        </el-form-item>
        <el-form-item label="执行规则" prop="cronExpression">
          <el-input v-model="form.cronExpression" style="width: 220px;" />
        </el-form-item>
        <el-form-item label="任务负责人" prop="personInCharge">
          <el-input v-model="form.personInCharge" style="width: 220px;" />
        </el-form-item>
        <el-form-item label="失败后暂停">
          <el-radio-group v-model="form.pauseAfterFailure" style="width: 220px">
            <el-radio :label="true">是</el-radio>
            <el-radio :label="false">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="任务状态">
          <el-radio-group v-model="form.isPause" style="width: 220px">
            <el-radio :label="false">启用</el-radio>
            <el-radio :label="true">暂停</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="参数内容">
          <el-input v-model="form.params" style="width: 556px;" rows="4" type="textarea" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" style="width: 220px;" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="crud.cancelCU">取消</el-button>
        <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
      </div>
    </el-dialog>
    <!--表格渲染-->
    <el-table ref="table" v-loading="crud.loading" :data="crud.data" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
      <el-table-column :selectable="checkboxT" type="selection" width="55" />
      <el-table-column :show-overflow-tooltip="true" prop="id" label="ID" />
      <el-table-column :show-overflow-tooltip="true" prop="taskName" label="名称" />
      <el-table-column :show-overflow-tooltip="true" prop="beanName" label="执行器" />
      <el-table-column :show-overflow-tooltip="true" prop="methodName" label="执行方法" />
      <el-table-column :show-overflow-tooltip="true" prop="params" label="参数" />
      <el-table-column :show-overflow-tooltip="true" prop="cronExpression" label="cron表达式" />
      <el-table-column :show-overflow-tooltip="true" prop="isPause" width="90px" label="状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isPause ? 'warning' : 'success'">{{ scope.row.isPause ? '已暂停' : '运行中' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" prop="description" width="150px" label="描述" />
      <el-table-column :show-overflow-tooltip="true" prop="createTime" width="136px" label="创建日期" />
      <el-table-column v-if="checkPer(['admin','timing:edit','timing:del'])" label="操作" width="170px" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button v-permission="['admin','task:edit']" size="mini" style="margin-right: 3px;" type="text" @click="crud.toEdit(scope.row)">编辑</el-button>
          <el-button v-permission="['admin','task:edit']" size="mini" style="margin-right: 3px;" type="text" @click="crud.toEdit(scope.row)">克隆</el-button>
          <el-button v-permission="['admin','task:edit']" style="margin-left: -2px" type="text" size="mini" @click="execute(scope.row.id)">执行</el-button>
          <el-button v-permission="['admin','task:edit']" style="margin-left: 3px" type="text" size="mini" @click="updateStatus(scope.row.id,scope.row.isPause ? '恢复' : '暂停')">
            {{ scope.row.isPause ? '恢复' : '暂停' }}
          </el-button>
          <el-popover
            :ref="scope.row.id"
            v-permission="['admin','timing:del']"
            placement="top"
            width="200"
          >
            <p>确定停止并删除该任务吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.row.id].doClose()">取消</el-button>
              <el-button :loading="delLoading" type="primary" size="mini" @click="delMethod(scope.row.id)">确定</el-button>
            </div>
            <el-button slot="reference" type="text" size="mini">删除</el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <!--分页组件-->
    <pagination />
  </div>
</template>

<script>
import { getAllSourceNames } from '@/api/watcher/datasource'
import crudTask, { getAllTaskNames } from '@/api/watcher/task'
import CRUD, { presenter, header, form, crud } from '@crud/crud'

import Log from './log'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import pagination from '@crud/Pagination'
import DateRangePicker from '@/components/DateRangePicker'

const defaultForm = { id: null, taskName: null, sourceName: null, beanName: null, methodName: null, params: null, cronExpression: null, pauseAfterFailure: true, isPause: false, callBack: null, description: null }
export default {
  name: 'Tasks',
  components: { Log, pagination, crudOperation, rrOperation, DateRangePicker },
  cruds() {
    return CRUD({ title: '定时任务', url: 'api/tasks', crudMethod: { ...crudTask }})
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  data() {
    return {
      delLoading: false,
      deptName: '', taskNames: [], sourceNames: [],
      permission: {
        add: ['admin', 'task:add'],
        edit: ['admin', 'task:edit'],
        del: ['admin', 'task:del']
      },
      rules: {
        taskName: [
          { required: true, message: '请输入任务名称', trigger: 'blur' }
        ],
        sourceName: [
          { required: true, message: '请输入数据源名称', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入任务描述', trigger: 'blur' }
        ],
        beanName: [
          { required: true, message: '请输入Bean名称', trigger: 'blur' }
        ],
        methodName: [
          { required: true, message: '请输入方法名称', trigger: 'blur' }
        ],
        cronExpression: [
          { required: true, message: '请输入Cron表达式', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getTaskNames()
    this.getSourceNames()
  },
  methods: {
    // 执行
    execute(id) {
      crudTask.execution(id).then(res => {
        this.crud.notify('执行成功', CRUD.NOTIFICATION_TYPE.SUCCESS)
      }).catch(err => {
        console.log(err.response.data.message)
      })
    },
    // 改变状态
    updateStatus(id, status) {
      if (status === '恢复') {
        this.updateParams(id)
      }
      crudTask.updateIsPause(id).then(res => {
        this.crud.toQuery()
        this.crud.notify(status + '成功', CRUD.NOTIFICATION_TYPE.SUCCESS)
      }).catch(err => {
        console.log(err.response.data.message)
      })
    },
    updateParams(id) {
      console.log(id)
    },
    delMethod(id) {
      this.delLoading = true
      crudTask.del([id]).then(() => {
        this.delLoading = false
        this.$refs[id].doClose()
        this.crud.dleChangePage(1)
        this.crud.delSuccessNotify()
        this.crud.toQuery()
      }).catch(() => {
        this.delLoading = false
        this.$refs[id].doClose()
      })
    },
    // 显示日志
    doLog() {
      this.$refs.log.dialog = true
      this.$refs.log.doInit()
    },
    checkboxT(row, rowIndex) {
      return row.id !== 1
    },
    getTaskNames() {
      getAllTaskNames().then(res => {
        this.taskNames = res.content
      }).catch(() => { })
    },
    getSourceNames() {
      getAllSourceNames().then(res => {
        this.sourceNames = res.content
      }).catch(() => { })
    }
  }
}
</script>
