<template>
  <el-dialog append-to-body :close-on-click-modal="false" :visible.sync="dialog" title="导入规则" width="550px">
    <el-form ref="form" :inline="true" :model="form" :rules="rules" size="small" label-width="100px">

      <el-input v-model="form.params" style="width: 500px;" rows="3" type="textarea" />

    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel()">取消</el-button>
      <el-button :loading="crud.status.cu === 2" type="primary" @click="submit">确认</el-button>
    </div>
  </el-dialog>
</template>

<script>
const defaultForm = { id: null, groupId: 0, taskName: null, sourceName: null, beanName: null, methodName: null, params: null, checkTime: 0, durationTime: 0, startTime: 0, endTime: 0, level: null, feiShu: null, cronExpression: null, pauseAfterFailure: false, isPause: false, callBack: null, description: null }

import { form, crud } from '@crud/crud'
export default {
  mixins: [form(defaultForm), crud()],
  data() {
    return {
      loading: false,
      dialog: false,
      rules: {}
    }
  },
  methods: {
    cancel() {
      this.dialog = false
    },
    submit() {
      this.dialog = false
    }
  }
}
</script>

<style scoped>
</style>
