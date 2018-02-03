/**
 * Created by taoyali on 2018/2/3.
 */
//将动态生成列表的部分封装成函数，可以重复调用，也方便统一修改
function creatTable(parent, headers, datas) {

    var table = document.createElement("table");
    table.id = "tb";
    parent.appendChild(table);

    var thead = document.createElement("thead");
    table.appendChild(thead);

    var tr = document.createElement("tr");
    thead.appendChild(tr);

    for (var i = 0; i < headers.length; i++) {
        var th = document.createElement("th");
        th.innerHTML = headers[i];
        tr.appendChild(th);
    }

    var tbody = document.createElement("tbody");
    table.appendChild(tbody);

    for (var i = 0; i < datas.length; i++) {
        var tr = document.createElement("tr");
        tbody.appendChild(tr);

        for (var k in datas[i]) {
            var td = document.createElement("td");
            td.innerHTML = datas[i][k];
            tr.appendChild(td);
        }
        var td = document.createElement("td");
        td.innerHTML = "<a href='javascript:'>删除</a>";
        tr.appendChild(td);

        td.children[0].onclick = function () {
            var lines = tbody.children.length;
            if (lines <= 1) {
                alert("最后一条！请留一点数据吧！");
                return;
            }
            var tip = confirm("确认删除？");
            if (tip) {
                tbody.removeChild(this.parentNode.parentNode);
            }

        }
    }
}