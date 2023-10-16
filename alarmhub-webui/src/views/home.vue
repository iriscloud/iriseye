<template>
  <div class="app-container">
    <dataSearch />
  </div>
</template>

<script>
import dataSearch from './watcher/search/index'

export default {
  name: 'Home',
  components: { dataSearch },

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
