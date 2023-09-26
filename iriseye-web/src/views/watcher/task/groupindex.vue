<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--侧边部门数据-->
      <el-col :xs="9" :sm="6" :md="5" :lg="4" :xl="4">
        <div class="head-container">
          <el-input
            v-model="deptName"
            clearable
            size="small"
            placeholder="输入部门名称搜索"
            prefix-icon="el-icon-search"
            class="filter-item"
            @input="getDeptDatas"
          />
        </div>
        <el-tree
          :data="deptDatas"
          :load="getDeptDatas"
          :props="defaultProps"
          :expand-on-click-node="true"
          lazy
          @node-click="handleNodeClick"
        />
      </el-col>
      <!--用户数据-->
      <el-col :xs="15" :sm="18" :md="19" :lg="20" :xl="20">
        <!--工具栏-->
        <div class="head-container">
          <div v-if="crud.props.searchToggle">
            <!-- 搜索 -->
            <el-input v-model="query.taskName" clearable size="small" placeholder="输入名称搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="toQuery" />
            <date-range-picker v-model="query.createTime" class="date-item" />
            <rrOperation />
          </div>
          <crudOperation :permission="permission">
            <!-- 批量更新 -->
            <el-button
              slot="right"
              class="filter-item"
              size="mini"
              type="warning"
              @click="doBatchUpdate"
            >批量修改</el-button>
            <el-button
              slot="right"
              class="filter-item"
              size="mini"
              type="warning"
              @click="doExportTask"
            >批量导出</el-button>
            <el-button
              slot="right"
              class="filter-item"
              size="mini"
              type="warning"
              @click="doImportTask"
            >批量导入</el-button>
          </crudOperation>
          <BatchUpdate ref="batchUpdate" />
          <ImportTask ref="importTask" />
          <ExportTask ref="exportTask" />
        </div>
        <!--Form表单-->
        <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" append-to-body width="730px">
          <el-form ref="form" :inline="true" :model="form" :rules="rules" size="small" label-width="100px">
            <el-form-item label="名称" prop="taskName">
              <el-input v-model="form.taskName" style="width: 500px;" />
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
            <el-form-item label="执行频率(秒)" prop="checkTime">
              <el-input v-model="form.checkTime" style="width: 200px;" />
            </el-form-item>

            <el-form-item label="持续时长(秒)" prop="durationTime">
              <el-input v-model="form.durationTime" style="width: 200px;" />
            </el-form-item>

            <el-form-item label="corn(可选)" prop="cronExpression">
              <el-input v-model="form.cronExpression" style="width: 500px;" />
            </el-form-item>
            <el-form-item label="执行内容" prop="params">
              <el-input v-model="form.params" style="width: 500px;" rows="3" type="textarea" />
            </el-form-item>

            <el-form-item label="描述" prop="description">
              <el-input v-model="form.description" style="width: 500px;" rows="2" type="textarea" />
            </el-form-item>

            <el-form-item label="飞书回调" prop="feiShu">
              <el-input v-model="form.feiShu" style="width: 300px;" />
            </el-form-item>
            <el-form-item label="回调地址" prop="callBack">
              <el-input v-model="form.callBack" style="width: 300px;" />
            </el-form-item>
            <el-form-item label="失败后暂停">
              <el-radio-group v-model="form.pauseAfterFailure" style="width: 200px">
                <el-radio :label="false">否</el-radio>
                <el-radio :label="true">是</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="任务状态">
              <el-radio-group v-model="form.isPause" style="width: 200px">
                <el-radio :label="false">启用</el-radio>
                <el-radio :label="true">暂停</el-radio>
              </el-radio-group>
            </el-form-item>

          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="text" @click="crud.cancelCU">取消</el-button>
            <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
          </div>
        </el-dialog>
        <!--表格渲染-->
        <el-table ref="table" v-loading="crud.loading" :data="crud.data" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
          <el-table-column :selectable="checkboxT" type="selection" />
          <el-table-column :show-overflow-tooltip="true" prop="id" label="编号" />
          <el-table-column :show-overflow-tooltip="true" prop="taskName" label="名称" />
          <el-table-column :show-overflow-tooltip="true" prop="beanName" label="执行器" />
          <el-table-column :show-overflow-tooltip="true" prop="params" label="执行语句" />
          <el-table-column :show-overflow-tooltip="true" prop="isPause" width="100px" label="状态">
            <template slot-scope="scope">
              <el-tag :type="scope.row.isPause ? 'warning' : 'success'">{{ scope.row.isPause ? '已暂停' : '运行中' }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column :show-overflow-tooltip="true" prop="createTime" width="150px" label="创建日期" />
          <el-table-column v-if="checkPer(['admin','timing:edit','timing:del'])" label="操作" width="300px" align="center" fixed="right">
            <template slot-scope="scope">
              <el-button v-permission="['admin','task:edit']" size="mini" style="margin-right: 3px;" type="text" @click="crud.toEdit(scope.row)">编辑</el-button>
              <el-button v-permission="['admin','task:edit']" size="mini" style="margin-right: 3px;" type="text" @click="crud.toClone(scope.row)">克隆</el-button>
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
      </el-col>
    </el-row>
  </div>
</template>

<script>

import { getAllSourceNames } from '@/api/watcher/datasource'
import crudTask, { getAllTaskNames } from '@/api/watcher/task'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import BatchUpdate from './batchUpdate'
import ExportTask from './exportTask'
import ImportTask from './importTask'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import pagination from '@crud/Pagination'
import DateRangePicker from '@/components/DateRangePicker'

import { getDepts, getDeptSuperior } from '@/api/system/dept'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import { LOAD_CHILDREN_OPTIONS } from '@riophae/vue-treeselect'

const defaultForm = { id: null, groupId: 0, taskName: null, sourceName: null, beanName: null, methodName: null, params: null, checkTime: 0, durationTime: 0, startTime: 0, endTime: 0, level: null, feiShu: null, cronExpression: null, pauseAfterFailure: false, isPause: false, callBack: null, description: null }
export default {
  name: 'GroupTasks',
  components: { ExportTask, ImportTask, BatchUpdate, pagination, crudOperation, rrOperation, DateRangePicker },
  cruds() {
    return CRUD({ title: '定时任务', url: 'api/tasks', crudMethod: { ...crudTask },
      optShow: {
        add: true,
        edit: false,
        del: false,
        reset: false
      }})
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  data() {
    return {
      delLoading: false,
      selectSize: 0,
      deptName: '', depts: [], deptDatas: [], jobs: [], level: 3, roles: [],
      defaultProps: { children: 'children', label: 'name', isLeaf: 'leaf' },
      taskNames: [], sourceNames: [],
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
    handleCurrentChange(selection) {
      this.crud.selections = selection
      this.selectSize = selection.length
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
    // 显示批量修改
    doBatchUpdate() {
      this.$refs.batchUpdate.dialog = true
    },
    // 显示批量修改
    doExportTask() {
      this.$refs.exportTask.dialog = true
    },
    // 显示批量修改
    doImportTask() {
      this.$refs.importTask.dialog = true
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
    },
    // 获取左侧部门数据
    getDeptDatas(node, resolve) {
      const sort = 'id,desc'
      const params = { sort: sort }
      if (typeof node !== 'object') {
        if (node) {
          params['name'] = node
        }
      } else if (node.level !== 0) {
        params['pid'] = node.data.id
      }
      setTimeout(() => {
        getDepts(params).then(res => {
          if (resolve) {
            resolve(res.content)
          } else {
            this.deptDatas = res.content
          }
        })
      }, 100)
    },
    getDepts() {
      getDepts({ enabled: true }).then(res => {
        this.depts = res.content.map(function(obj) {
          if (obj.hasChildren) {
            obj.children = null
          }
          return obj
        })
      })
    },
    getSupDepts(deptId) {
      getDeptSuperior(deptId).then(res => {
        const date = res.content
        this.buildDepts(date)
        this.depts = date
      })
    },
    buildDepts(depts) {
      depts.forEach(data => {
        if (data.children) {
          this.buildDepts(data.children)
        }
        if (data.hasChildren && !data.children) {
          data.children = null
        }
      })
    },
    // 获取弹窗内部门数据
    loadDepts({ action, parentNode, callback }) {
      if (action === LOAD_CHILDREN_OPTIONS) {
        getDepts({ enabled: true, pid: parentNode.id }).then(res => {
          parentNode.children = res.content.map(function(obj) {
            if (obj.hasChildren) {
              obj.children = null
            }
            return obj
          })
          setTimeout(() => {
            callback()
          }, 200)
        })
      }
    },
    // 切换部门
    handleNodeClick(data) {
      if (data.pid === 0) {
        this.query.groupId = null
      } else {
        this.query.groupId = data.id
      }
      this.crud.data['groupId'] = data.id
      this.crud.toQuery()
    }
  }
}
</script>
