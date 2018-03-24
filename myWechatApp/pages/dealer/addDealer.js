var util = require('../../utils/util.js');
var LoadData = require('../../utils/loadData.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    dealerCode: "",
    dealerName: "",
    dealerPerson: "",
    currentDate: util.shortFormatTime(new Date()),
    sampleRemake: "",
    dealerAddress: "",
    sampleType: "",
    iphone: "",
  },


  formSubmit: function (e) {
    console.log('form发生了submit事件，携带数据为：', e.detail.value)
  },
  formReset: function () {
    console.log('form发生了reset事件')
  },


 
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },

  dealerCodeEvent: function (e) {
    this.setData({
      dealerCode: e.detail.value
    });
  },

  dealerNameEvent: function (e) {
    this.setData({
      dealerName: e.detail.value
    });
  },

  dealerPersonEvent: function (e) {
    this.setData({
      dealerPerson: e.detail.value
    });
  },

  iphoneEvent: function (e) {
    this.setData ({
      iphone: e.detail.value
    }); 
  },

  //  点击日期组件确定事件  
  bindDateChangeEvent: function (e) {
    this.setData({
      currentDate: e.detail.value
    })
  },

  sampleTypeEvent: function (e) {
    this.setData({
      sampleType: e.detail.value
    });
  },

  dealerAddressEvent: function(e) {
    this.setData({
      dealerAddress: e.detail.value
    });
  },

  sampleRemakeEvent: function (e) {
    this.setData({
      sampleRemake: e.detail.value
    });
  },

  addDealerEvent: function (e) {
    debugger
    var parameter = { 
      "dealerCode": this.data.dealerCode, 
      "dealerName": this.data.dealerName, 
      "director": this.data.dealerPerson, 
      "phone": this.data.iphone,
      "sampleType": this.data.sampleType, 
      "dealerAddress": this.data.dealerAddress, 
      "sampleRemake": this.data.sampleRemake, 
    };

    LoadData.requestData('POST', 'https://net.rojo.vip:8443/rojo/add.DealerServlet', parameter, function (data) {
      debugger
      console.log(data)
      debugger
    });

    console.log(this.data)
    debugger
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