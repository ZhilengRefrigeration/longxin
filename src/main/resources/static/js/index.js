  var imageUrls = [
    "https://www.loongson.cn/uploads/images/2022101215210381807.05012c09b95bd9685e35df37de663f68.jpg",
    "https://www.loongson.cn/uploads/images/2022101215201310016.7c8793f4c615943a0c9e69d5dee35521.jpg",
    "https://www.loongson.cn/uploads/images/2022082310431879583.49ebee810af9efa089d46a2c33c4a28d.jpg"
];

var currentIndex = 0;
var backgroundDiv = document.querySelector('.background'); 
backgroundDiv.style.backgroundImage = "url('" + imageUrls[currentIndex] + "')";

function changeBackgroundImage() {
    currentIndex = (currentIndex + 1) % imageUrls.length;
    backgroundDiv.style.backgroundImage = "url('" + imageUrls[currentIndex] + "')";
    backgroundDiv.style.backgroundPosition = "right"; 
    setTimeout(function() {
        backgroundDiv.style.backgroundPosition = "left"; 
    }, 0);
}

setInterval(changeBackgroundImage, 4000);

$(".input-btn").click(function () {
    var data = {
        "username": $("#username").val(),
        "password": $("#password").val()
    };

    $.ajax({
        url: "http://localhost/login",
        type: "POST",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(data),
        success: function (data, textStatus, jqXHR) {
            if (data.code === 0) {
                localStorage.setItem('token',data.data.tokenValue)
                window.location.href = "admin/index.html";
            } else {
                alert(data.msg);
            }
        },
        error: function () {
            alert("请求失败，服务器错误！");
        }
    });

    return false;
});


$(document).keyup(function(event){
    if(event.keyCode ==13){
      $(".input-btn").trigger("click");
    }
  });