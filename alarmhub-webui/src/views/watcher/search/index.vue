<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle" style="width:90%">
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

        <!-- 搜索 -->
        <el-input v-model="query.blurry" clearable placeholder="模糊搜索" style="width:50%;margin-top: 8px" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <rrOperation style="width:50%;margin-top: 8px" />
      </div>
      <crudOperation :permission="permission" />
    </div>
    <!--表格渲染-->
    <el-table ref="table" v-loading="crud.loading" :data="crud.data" highlight-current-row stripe style="width: 100%" @selection-change="handleCurrentChange">
      <el-table-column type="selection" width="55" />
      <el-table-column prop="name" width="130px" label="内容" />
      <el-table-column prop="url" label="连接地址" />
      <el-table-column prop="userName" width="200px" label="用户名" />
      <el-table-column prop="createTime" width="200px" label="创建日期" />
    </el-table>
  </div>
</template>

<script>
import { getAllSourceTypeNames } from '@/api/watcher/datasource'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import crudTask from '@/api/watcher/task'

const defaultForm = { id: null, name: null, type: null, url: 'jdbc:mysql://', userName: null, pwd: null }
export default {
  name: 'DataSearch',
  components: { crudOperation, rrOperation },
  cruds() {
    return CRUD({ title: '定时任务', url: 'api/tasks', crudMethod: { ...crudTask },
      optShow: {
        add: false,
        edit: false,
        del: false,
        reset: false
      }})
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  data() {
    return {
      currentRow: {},
      selectIndex: '',
      datasourceInfo: '',
      sourceTypeNames: [],
      loading: false
    }
  },
  created() {
    this.getSourceTypeNames()
  },
  methods: {
    // searchSourceData() {
    //   this.$refs['form'].validate((valid) => {
    //     if (valid) {
    //       this.loading = true
    //       searchApiSourceData(this.form).then((res) => {
    //         this.loading = false
    //         this.crud.notify(res ? '连接成功' : '连接失败', res ? 'success' : 'error')
    //       }).catch(() => {
    //         this.loading = false
    //       })
    //     }
    //   })
    // },
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
