// 从 wxappclub 的 api 中心获取数据的方法
// key 表示数据名称，_type 数据类型，callback 表示请求成功的回调函数
// 回调函数的的参数，用于携带请求得到的数据
function loadScrollImages (key, _type, callback) {
  wx.request({
    url: 'https://api.wxappclub.com/get',
    data: {
      // 笔者的API中心，提供给各位使用
      "appkey": "eaa7gcwem04j8sak7hm8g88mkftgb26h",
      "key": key,
      "type": _type
    },
    method: 'GET',
    header: {
      'content-type': 'application/json'
    },
    success: function (res) {
      if (res.data.success) {
        // console.log(res.data.result.value);
        callback(res.data.result.value);
      }
    }
  });
}