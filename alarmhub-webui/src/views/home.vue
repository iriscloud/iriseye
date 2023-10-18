<template>
  <div class="app-container">
    <groupTask />
  </div>
</template>

<script>
import groupTask from './watcher/task/groupindex'

export default {
  name: 'Home',
  components: { groupTask },

  data() {
    return {
    }
  },
  methods: {
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
::v-deep .el-input-number .el-input__inner {
  text-align: left;
}
</style>
