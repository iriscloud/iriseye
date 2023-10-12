import request from '@/utils/request'

export function testDbConnect(data) {
  return request({
    url: 'api/datasource/testConnect',
    method: 'post',
    data
  })
}

