var LoadData = require('../../utils/loadData.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    dealers: [
    // {
    //   // 经销商名称：
    //   dealerCode: "经销商1 code",
    //   dealerName: "杭州天鑫五金建材有限公司",
    //   director: "客户名称",
    //   phone: "13645527410",
    //   sampleDate: "上样时间",
    //   sampleType: '样品类型',
    //   dealerAddress: '上天 入海',
    //   // 安装时间
    //   sampleRemake: "及时送货， 安装",
    // }
    ],
  },
  dealerDetail: function(e) {
    var index = e.currentTarget.dataset.index;
    var item = this.data.dealers[index];
    var modelString = JSON.stringify(item);
    try {
      wx.setStorageSync('dealerDetail', item);
      debugger
      wx.navigateTo({
        url: '../dealer/dealer',
      })
    } catch (e) {

    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showLoading({
      title: '努力加载中...',
    })
    // 页面初始化时，请求服务器，获取 swiperData ，用于渲染轮播图
    // 为了避免 this 指向错误，截取this，赋值给 that
    var that = this;
    this.getHttpData();
  },

  getHttpData: function () {
    
    var that = this;
    var parameters = {
      'username': '123',
      'userpwd': '123',
      'pageIndex': 10,
      'pageSize': 100,
    }
    debugger
    LoadData.requestData('POST', 'https://net.rojo.vip:8443/rojo/query.DealerServlet', parameters, function (response) {
      debugger
      that.setData({ dealers: response });
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

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  },

  addDealer: function() {
    wx.navigateTo({
      url: './addDealer',
    })
  },

  upper: function (e) {
    console.log(e)
  },
  lower: function (e) {
    console.log(e)
  },
  scroll: function (e) {
    console.log(e)
  },
  scrollToTop: function (e) {
    this.setAction({
      scrollTop: 0
    })
  },
  taptosenddata: function (e) {
    this.setData({
      xxx: "ddddd"
    })
  },
  tap: function (e) {
    // for (var i = 0; i < order.length; ++i) {
    //   if (order[i] === this.data.toView) {
    //     this.setData({
    //       toView: order[i + 1],
    //       scrollTop: (i + 1) * 200
    //     })
    //     break
    //   }
    // }
  },
  tapMove: function (e) {
    this.setData({
      scrollTop: this.data.scrollTop + 10
    })
  },
})