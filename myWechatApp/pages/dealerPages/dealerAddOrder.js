var LoadData = require('../../utils/loadData.js');
var util = require('../../utils/util.js');
var WXPay = require('../../utils/wxPay.js');

Page({
  /**
   * 页面的初始数据
   */
  data: {
    currentDate: util.shortFormatTime(new Date()),
    goodIndex: 0,
    // goodNames: [{ productName: '商品A', price: 1000 }, { productName: '商品B', price: 1001 }, { productName: '商品C', price: 1002 }],
    goodNames: [],
    goodPrices: [],
    parameters: {},
    count: 0,
    price: 0,
  },

  //  点击日期组件确定事件  
  bindDateChangeEvent: function (e) {
    this.setData({
      currentDate: e.detail.value
    })
  },

  bindPickerChange: function (e) {
    this.setData({
      goodIndex: e.detail.value
    })
    this.data.price = this.data.goodPrices[goodIndex]
  },

  bindCountChange: function (e) {
    this.data.count = e.detail.value;
    // this.setData({
    //   totalMoney: e.detail.value * this.data.goodPrices[this.data.goodIndex]
    // })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
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
    LoadData.requestData('POST', 'https://net.rojo.vip:8443/rojo/queryList.ProductServlet', parameters, function (response) {
      var goodNames = [];
      var prices = [];
      for (var index in response ) {
        goodNames[index] = response[index].productName;
        prices[index] = response[index].price;
      }
      that.setData({ goodNames: goodNames });
      that.setData({ goodPrices: prices });
    });
  },

  addDealerOrder: function (e) {
    wx.showLoading({
      title: '提交订单...',
    })
    var that = this;
    var index = this.data.goodIndex;
    this.data.parameters = {
      // 客户名称
      'customName': e.detail.value["customName"],
      // 产品名称
      'productName': this.data.goodNames[index],
      // 客户联系号码
      'phone': e.detail.value["phone"],
      // 安装时间
      'installDate': this.data.currentDate,
      // 门类型
      'doorType': e.detail.value["doorType"],
      // 客户地址
      'address': e.detail.value["address"],
      // 备注
      'remake': e.detail.value["remake"],
      // 衬板宽度
      'scaleboardWidth': e.detail.value["scaleboardWidth"],
      // 衬板高度
      'scaleboardHeight': e.detail.value["scaleboardHeight"],
      // 商品个数
      'count': e.detail.value["count"],
      // 总价
      'totalPrice': this.data.goodPrices[index],
      // 外键商品
      'dealer_id': wx.getStorageSync('userId'),
      'status': e.detail.value["status"],
    }
    LoadData.requestData('POST', 'https://net.rojo.vip:8443/rojo/add.OrderServlet', this.data.parameters, function (response) {
      debugger
      var payCode = wx.getStorageSync("payCode");
      var totalMoney = that.data.parameters.count * that.data.parameters.totalPrice;
      // var totalMoney = that.data.count * that.data.price;
      // wx.showModal({
      //   title: '付款',
      //   content: '货款:' + this.data.totalMoney,
      //   success: function (res) {
      //     if (res.confirm) {
      WXPay.requestPay(response, totalMoney, payCode, function () {
              LoadData.requestData('POST', 'https://net.rojo.vip:8443/rojo/paySucess.WXPayNotifyServlet', { id: response }, function (response) {
                wx.navigateBack({
                  delta: 1
                })
              })
            })
      //     }
      //   },
      // })
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