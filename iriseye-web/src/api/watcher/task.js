import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/tasks',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/tasks',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/tasks',
    method: 'put',
    data
  })
}

export function updateIsPause(id) {
  return request({
    url: 'api/tasks/' + id,
    method: 'put'
  })
}

export function execution(id) {
  return request({
    url: 'api/tasks/exec/' + id,
    method: 'put'
  })
}

export function getAllTaskNames() {
  const params = {
    page: 0,
    size: 9999,
    enabled: true
  }
  return request({
    url: 'api/tasks/names',
    method: 'get',
    params
  })
}

export default { del, updateIsPause, execution, add, edit }
