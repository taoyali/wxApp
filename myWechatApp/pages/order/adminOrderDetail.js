var loadUtil = require('../../utils/loadData.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    // titleName: "",
    subName: "hello",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var name = options.name;
    this.setData({
      titleName: name,
    });

    console.log(name);

    // 页面初始化时，请求服务器，获取 swiperData ，用于渲染轮播图
    // 为了避免 this 指向错误，截取this，赋值给 that
    var that = this;
    this.getHttpData("swiperData", "json");
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