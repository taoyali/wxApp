var loadUtil = require('../../utils/loadData.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    // titleName: "",
    subName: "hello",

    // orderDetail: {
      // // 经销商名称：
      // dealerCode: "经销商1 code",
      // dealerName: "杭州天鑫五金建材有限公司",
      // customName: "客户名称",
      // customPhoneNumber: "13645527410",
      // productName: "H103锁",
      // count: '5',
      // productTotalPrice: '1000',
      // // 安装时间
      // installTime: "time",
      // // 下单时间
      // orderTime: "time",
      // doorType: '门类型',
      // // 衬板宽、高
      // scaleboardWidth: "50",
      // scaleboardHeight: "50",
      // address: "顾客地址",
      // // 备注：
      // remake: "及时送货， 安装",
      // status: 2,
    // }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      orderDetail: options.orderDetail,
    });

    console.log(orderDetail);

    // // 页面初始化时，请求服务器，获取 swiperData ，用于渲染轮播图
    // // 为了避免 this 指向错误，截取this，赋值给 that
    // var that = this;
    // this.getHttpData("swiperData", "json");
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