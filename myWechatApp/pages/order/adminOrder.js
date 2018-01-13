// var util = require('../../utils/util.js');
// var ListCompment = require('../../lib/ListCompment.js');
// var listCompment = new ListCompment({
//   name: 'adminOrders',
//   api: '/partner-userMforlist',
//   ajax: util.ajax //这是我自己封装的ajax
// });

Page({
  /**
   * 页面的初始数据
   */
  data: {
    adminOrders: [{
      // 经销商名称：
      distributorCode: "经销商1 code",
      distributorCompanyName: "经销商1 公司",
      distributorName: "经销商1",
      distributorPhoneNumber: "13645527410",
      productName: "产品1",
      productCount: '5',
      productTotalPrice: '1000',
      // 安装时间
      installTime: Date.toString,
      // 下单时间
      orderTime: Date.toString,
      doorType: '门类型',
      // 衬板宽、高
      LinerWidth: "50",
      LinerHeight: "50",
      customerAddress: "顾客地址",
      // 备注：
      remarks: "及时送货， 安装",
    },
    {
      // 经销商名称：
      distributorCode: "经销商1 code",
      distributorCompanyName: "经销商1 公司",
      distributorName: "经销商1",
      distributorPhoneNumber: "13645527410",
      productName: "产品1",
      productCount: '5',
      productTotalPrice: '1000',
      // 安装时间
      installTime: Date.toString,
      // 下单时间
      orderTime: Date.toString,
      doorType: '门类型',
      // 衬板宽、高
      LinerWidth: "50",
      LinerHeight: "50",
      customerAddress: "顾客地址",
      // 备注：
      remarks: "及时送货， 安装",
    },
    {
      // 经销商名称：
      distributorCode: "经销商1 code",
      distributorCompanyName: "经销商1 公司",
      distributorName: "经销商1",
      distributorPhoneNumber: "13645527410",
      productName: "产品1",
      productCount: '5',
      productTotalPrice: '1000',
      // 安装时间
      installTime: Date.toString,
      // 下单时间
      orderTime: Date.toString,
      doorType: '门类型',
      // 衬板宽、高
      LinerWidth: "50",
      LinerHeight: "50",
      customerAddress: "顾客地址",
      // 备注：
      remarks: "及时送货， 安装",
    }],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // listCompment.setParams('cate_id', 1).setParams('page_size', 10); //设置额外传参
    // listCompment.init(this); //初始化，传入this
  },


  // /**
  //  * 页面相关事件处理函数--监听用户下拉动作
  //  */
  // onPullDownRefresh: function () {
  //   listCompment.refresh(); //下拉刷新
  // },

  // /**
  //  * 页面上拉触底事件的处理函数
  //  */
  // onReachBottom: function () {
  //   listCompment.next(); //加载下一页
  // },
})