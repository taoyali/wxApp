function ListCompment(settings) {
    this.name = settings.name;
    this.api = settings.api;
    this.ajax = settings.ajax;
    this.params = {
        page_no: 1,
        page_size: 10
    };
}

//初始化
ListCompment.prototype.init = function(that) {
    this.that = that;
    this.reset();
    this.getList();
    return this;
};

ListCompment.prototype.getList = function() {
    var self = this;
    var that = this.that;
    var data = that.data[self.name];

    data.loading = true;

    self.ajax(this.api, this.params, function(res) {
        res = res.data;
        if (res.success) {
            data.list = data.list.concat(res.data.list); //列表数据
            data.hasNext = res.data.has_next; //是否有下一页
            data.empty = res.data.empty; //没有数据
            data.loading = false;
            data.over = !res.data.has_next; //是否没有数据了
            that.setData({
                [self.name]: data
            });
        }
    });
    return this;
};

//重置
ListCompment.prototype.reset = function() {
    var self = this;
    var that = this.that;

    that.setData({
        [self.name]: {
            list: [],
            loading: false,
            hasNext: false,
            over:false,
            empty: false
        }
    });

    return this;
};

//刷新
ListCompment.prototype.refresh = function() {
    this.reset();
    this.setParams('page_no', 1);
    this.getList();
    return this;
};

//下一页
ListCompment.prototype.next = function() {
    var self = this;
    var that = this.that;
    var data = that.data[self.name];

    if (data.hasNext) {
        self.setParams('page_no', ++self.params.page_no);
        self.getList();
    }
    return this;
};

//设置额外的参数
ListCompment.prototype.setParams = function(key, value) {
    this.params[key] = value;
    return this;
};

module.exports = ListCompment;