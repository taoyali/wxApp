// key 表示数据名称，_type 数据类型，callback 表示请求成功的回调函数
// 回调函数的的参数，用于携带请求得到的数据
function requestData (type, url, data, callback) {
  wx.request({
    url: url,
    data: data,
    method: type,
    header: {
      'content-type': 'application/json'
    },
    success: function (res) {
      wx.hideLoading();
      if (res.statusCode == 200) {
        wx.hideLoading(),
          wx.showToast({
            title: '操作成功',
            icon: 'success',
            duration: 2000
          })
        callback(res.data);
      }
    },
    fail: function (res) {
      debugger
      wx.hideLoading();
      wx.showToast({
        title: "操作失败",
        icon: 'fail',
        duration: 2000
      })
    }
  });
};

module.exports = {
  requestData: requestData
}