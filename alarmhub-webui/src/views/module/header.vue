<template>
  <div
    v-if="crud.props.searchToggle"
  >
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
    <el-input v-model="query.name" clearable size="small" placeholder="输入执行内容" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
    <date-range-picker v-model="query.createTime" class="date-item" />

    <rrOperation />
  </div>
</template>

<script>
import { header } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import DateRangePicker from '@/components/DateRangePicker'
export default {
  components: { rrOperation, DateRangePicker },
  mixins: [header()],
  props: {
    dict: {
      type: Object,
      required: true
    },
    permission: {
      type: Object,
      required: true
    }
  }
}
</script>
