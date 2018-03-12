Page({

  /**
   * 页面的初始数据
   */
  data: {
    dealer: {
      // // 经销商名称：
      // dealerCode: "经销商1 code",
      // dealerName: "杭州天鑫五金建材有限公司",
      // director: "客户名称",
      // phone: "13645527410",
      // sampleDate: "上样时间",
      // sampleType: '样品类型',
      // dealerAddress: '上天 入海',
      // // 安装时间
      // sampleRemake: "及时送货， 安装",
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var item = wx.getStorageSync('dealerDetail');
    this.setData({
      dealer: item,
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
    
  }
})