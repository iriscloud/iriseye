import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/datasource',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/datasource',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/datasource',
    method: 'put',
    data
  })
}

export function testDbConnection(data) {
  return request({
    url: 'api/datasource/testConnect',
    method: 'post',
    data
  })
}

export function getAllSourceNames() {
  const params = {
    page: 0,
    size: 9999,
    enabled: true
  }
  return request({
    url: 'api/datasource/names',
    method: 'get',
    params
  })
}

export default { add, edit, del, testDbConnection }
