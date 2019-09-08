$.fn.waterFall= function () {
    // 计算每个item的值（left,top）
    var $this = $(this);

    // 页面总长度
    var zongWidth = $this.width();

    // 拿到每一个元素的集合
    var $children = $this.children(".item");

    // 假如网页展示5列数据
    var colunm = 5;

    // 每个子元素展示的宽度
    var childWidth = $children.width();

    // 每一个子元素之间的间距
    var jianju  = (zongWidth - childWidth*colunm) / (colunm-1);

    var arr = [];
    // 拿到每个子元素进行操作
    $children.each(function (index,dom) {
        var item  = $(dom);
        if (index < colunm){
                item.css({
                    top:0,
                    left:index*(childWidth+jianju)
                });
            arr.push(item.height());
        }else {
            var minIndex = 0;
            var minHeight = arr[minIndex];
            for (var i = 0; i <arr.length ; i++) {
                if(minHeight > arr[i]){
                    minIndex = i ;
                    minHeight = arr[i];
                }
            }
            item.css({
                left:minIndex*(jianju+childWidth),
                top:minHeight + jianju
            });
            arr[minIndex] = minHeight +jianju + item.height();
        }
        }

    );
}