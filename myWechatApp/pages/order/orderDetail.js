var loadUtil = require('../../utils/loadData.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderDetail: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var detail = wx.getStorageSync("orderDetail");
    console.log(detail);
    this.setData({
      orderDetail: detail,
    });
  },

  getHttpData: function (key, _type) {
    loadUtil.loadScrollImages(key, _type, function (data) {
      console.log(data);
      that.setData({ swiperData: data });
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },
})