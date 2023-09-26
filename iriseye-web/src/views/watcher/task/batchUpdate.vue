<template>
  <el-dialog append-to-body :close-on-click-modal="false" :visible.sync="dialog" title="执行脚本" width="500px">
    <el-form ref="form" :inline="true" :model="form" :rules="rules" size="small" label-width="100px">

      <el-form-item label="执行频率(秒)" prop="checkTime">
        <el-input v-model="form.checkTime" style="width: 300px;" />
      </el-form-item>

      <el-form-item label="持续时长(秒)" prop="durationTime">
        <el-input v-model="form.durationTime" style="width: 300px;" />
      </el-form-item>
      <el-form-item label="飞书回调" prop="feiShu">
        <el-input v-model="form.feiShu" style="width: 300px;" />
      </el-form-item>
      <el-form-item label="回调地址" prop="callBack">
        <el-input v-model="form.callBack" style="width: 300px;" />
      </el-form-item>
      <el-form-item label="失败后暂停">
        <el-radio-group v-model="form.pauseAfterFailure" style="width: 300px">
          <el-radio :label="false">否</el-radio>
          <el-radio :label="true">是</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="任务状态">
        <el-radio-group v-model="form.isPause" style="width: 300px">
          <el-radio :label="false">启用</el-radio>
          <el-radio :label="true">暂停</el-radio>
        </el-radio-group>
      </el-form-item>

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
