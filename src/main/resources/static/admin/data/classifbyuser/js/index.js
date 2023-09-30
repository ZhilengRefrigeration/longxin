var page = 0;
var pageSize = 50;
var jsq=0;
$(document).ready(function() {
jsq=0;
function getData() {
    $.ajax({
        url: "http://localhost/getClassifyByUser",
        type: "POST",
        headers: {
            'Content-Type': 'application/json',
            'satoken': localStorage.getItem("token")
        },
        data: JSON.stringify({
            "page": page,
            "number": pageSize
        }),
        success: function(response) {
            if (response.code === 0) {
                var data = response.data;
                displayData(data);
                displayPagination(response.totalPages);
            } else {
                console.error(response.msg);
                window.location.href = "../../../";
            }
        },
        error: function() {
            alert("请求数据失败");
        }
    });
}

function searchData(searchTerm) {
    jsq=1;
    $.ajax({
        url: "http://localhost/SearchClassify",
        type: "POST",
        headers: {
            'Content-Type': 'application/json',
            'satoken': localStorage.getItem("token")
        },
        data: JSON.stringify({
            "page": page,
            "number": pageSize,
            "classify": searchTerm
        }),
        success: function(response) {
            if (response.code === 0) {
                var data = response.data;
                displayData(data);
                displayPagination(response.totalPages);
            } else {
                console.error(response.msg);
                window.location.href = "../../../";
            }
        },
        error: function() {
            alert("请求数据失败");
        }
    });
}

function getClassifiedData(classify) {
    jsq=2;
    $.ajax({
        url: "http://localhost/getUserClassifyByClassify",
        type: "POST",
        headers: {
            'Content-Type': 'application/json',
            'satoken': localStorage.getItem("token")
        },
        data: JSON.stringify({
            "page": page,
            "number": pageSize,
            "classify": classify
        }),
        success: function(response) {
            if (response.code === 0) {
                var data = response.data;
                displayData(data);
                displayPagination(response.totalPages);
            } else {
                console.error(response.msg);
                window.location.href = "../../../";
            }
        },
        error: function() {
            alert("请求数据失败");
        }
    });
}

function displayData(data) {
    var table = document.getElementById("data-table");
    table.innerHTML = "";

    var tableHTML = '<tr>' +
        '<th>客户ID</th>' +
        '<th>用户地址</th>' +
        '<th>年龄</th>' +
        '<th>性别</th>' +
        '<th>城市</th>' +
        '<th>客户类型</th>' +
        '</tr>';

    data.forEach(function(item) {
        tableHTML += '<tr>' +
            '<td>' + item.userId + '</td>' +
            '<td>' + item.userAddress + '</td>' +
            '<td>' + item.userBirthday + '</td>' +
            '<td>' + item.userGender + '</td>' +
            '<td>' + item.city + '</td>' +
            '<td>' + item.classify + '</td>' +
            '</tr>';
    });

    table.innerHTML = tableHTML;
}

function displayPagination(totalPages) {
    var pagination = document.getElementById("pagination");
    pagination.innerHTML = "";

    for (var i = 1; i <= totalPages; i++) {
        var link = document.createElement("a");
        link.href = "#";
        link.textContent = i;

        if (i - 1 === page) {
            link.className = "active";
        }

        link.addEventListener("click", function() {
            page = parseInt(this.textContent) - 1;
            getData();
        });

        pagination.appendChild(link);
    }
}

getData();

document.getElementById("search-btn").addEventListener("click", function() {
    var searchTerm = document.getElementById("search-input").value;
    searchData(searchTerm);
});

document.getElementById("classify-select").addEventListener("change", function() {
    var classify = document.getElementById("classify-select").value;
    getClassifiedData(classify);
});
});

// 定义一个数组，保存当前所有页面的class name
var page_index = ["page-1", "page-2", "page-3"];

// 输入pagename，打开指定的div，隐藏其他的div
function page_option(pagename){
var tar_index = page_index.indexOf(pagename);
page_index.slice(tar_index, 1);
for (var j = 0; j < page_index.length ; j++){
var close_div = document.getElementsByClassName(page_index[j]);
for (var i = 0; i<close_div.length;i++) {
   close_div[i].style.display="none";
};
}

var opendiv = document.getElementsByClassName(pagename);
for (var i = 0; i<opendiv.length;i++) {
opendiv[i].style.display="block";
};
}

var changeEvent = new Event("change");
// 点击 上一页按钮 执行的操作
function prev_click(){
if(page >0){
page--;
}
if(jsq==0){
document.getElementById("search-btn").click();
}else if(jsq==1){
document.getElementById("search-btn").click();
}else {
document.getElementById("classify-select").dispatchEvent(changeEvent);
}
document.getElementById('currentPage').value=page+1;

}

// 点击 下一页按钮 执行的操作
function next_click(){
page++;
if(jsq==0){
document.getElementById("search-btn").click();
}else if(jsq==1){
document.getElementById("search-btn").click();
}else {
document.getElementById("classify-select").dispatchEvent(changeEvent);
}
document.getElementById('currentPage').value=page+1;
}
function choose_page(){
page = document.getElementById('currentPage').value -1;
if(jsq==0){
document.getElementById("search-btn").click();
}else if(jsq==1){
document.getElementById("search-btn").click();
}else {
document.getElementById("classify-select").dispatchEvent(changeEvent);
}
}


// 鼠标事件，经过每一条资讯时改变标题的颜色
function light(obj){
obj.firstElementChild.style.color="#FF9900";
}

function normal(obj){
obj.firstElementChild.style.color="#000000";
}
