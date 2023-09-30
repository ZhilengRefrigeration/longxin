// 获取侧边栏中的所有选项
const sidebarItems = document.querySelectorAll('.navbar ul li');

// 获取内容区域元素
const content = document.getElementById('content');

// 遍历每个侧边栏选项
sidebarItems.forEach((item, index) => {
    // 添加点击事件监听器
    item.addEventListener('click', () => {
        // 移除所有选项的激活样式
        sidebarItems.forEach((item) => {
            item.classList.remove('active');
        });

        // 添加当前选项的激活样式
        item.classList.add('active');

        // 根据选项索引显示对应的内容
        switch (index) {
            case 1:
                // 第一个选项的内容
                content.innerHTML = '<div id="data-results"><iframe src="data/index/index.html" frameborder="0" style="width: 100%; height: 100%;"></iframe></div>';
                break;
            case 2:
                // 第二个选项的内容
                content.innerHTML = '<iframe src="data/classifbyuser/index.html" frameborder="0" style="width: 100%; height: 100%;"></iframe>';
                break;
            case 3:
                // 第三个选项的内容
                content.innerHTML = '<iframe src="data/up/index.html" frameborder="0" style="width: 100%; height: 100%;"></iframe>';
                break;
            default:
                // 默认情况下显示空内容
                content.innerHTML = '';
                break;
        }
    });
});