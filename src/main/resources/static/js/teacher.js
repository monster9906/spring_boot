// 自调的方式，防止与其他库的代码冲突
(
    function () {
        // 表格刷新操作
        var refresh = function (){
            $("#teacherTable").bootstrapTable("refresh");
        }

        var soul = {

            init:function () {
                this.initEvent();
                this.initTeacherTable();
                this.initCharts();
            },
            // 初始化事件
            initEvent:function () {
                var datas = {};

                // 文件上传
                $("#lifephoto").fileupload({
                    dataType:"json",
                    done: function (e,data) {
                        var url = data.result.url;
                        datas.lifePhoto = url;
                        $(".photo").append("<img src='"+url+"' width='300px' height='300px'/>");
                    }
                });

                // 确定添加讲师
                $(".btn-addTeacher").on("click",function () {
                    var arrs = $("#teacherForm").serializeArray();
                    for (var i =0 ;i<arrs.length;i++){
                        datas[arrs[i].name] = arrs[i].value;
                    }
                    // 发送请求
                   $.ajax({
                       type:"post",
                       url:"/api/v1/teachers",
                       data:datas,
                       success:function (data) {
                           if ( data.code == 200){
                                $(".btn-close").click();
                                refresh();
                           }
                       }
                   })
                });
            },
            initTeacherTable:function () {

                $("#teacherTable").bootstrapTable({
                    url:'api/v1/teachers',
                    method : 'get', // 请求方式（*）
                    toolbar : '#toolbar', // 工具按钮用哪个容器
                    striped : true, // 是否显示行间隔色
                    cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                    sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
                    pagination : true, // 是否显示分页（*）
                    pageNumber: 1,    //如果设置了分页，首页页码
                    pageSize: 3,                       //每页的记录行数（*）
                    pageList: [3,6,9],        //可供选择的每页的行数（*）
                    //	onlyInfoPagination:false, //设置为 true 只显示总数据数，而不显示分页
                    showRefresh : true, // 是否显示刷新按钮
                    showToggle : false, // 是否显示详细视图和列表视图的切换按钮
                    search:true,   //是否启用搜索框
                    paginationFirstText: "首页",
                    paginationPreText: "上一页",
                    paginationNextText: "下一页",
                    paginationLastText: "末页",
                    rowStyle:function(row , index){
                        //ow.lifePhoto = "<img src=' "+row.lifePhoto +" ' width='100' height='100'>";
                        row.operation = "<a  class='btn btn-danger del_tea_btn' data-tid='"+row.id+"'>删除</a>"
                        return row;
                    },
                    // 数据加载完成
                    onLoadSuccess:function(){
                            // 事件代理
                        $("#teacherTable").on("click",".del_tea_btn",function(){
                            var id = this.dataset["tid"];
                            $.ajax({
                               url:"api/v1/teachers/"+id,
                               type:"delete",
                                dataType:"json",
                                success:function (data) {
                                    if(data.statu == 200){
                                        refresh();
                                    }
                                }
                            });
                        });
                    },
                    columns: [
                        {
                            field: 'id',
                            title: '编号',
                            align: 'center',//对其方式
                            valign: 'middle',
                            },
                        {
                            field: 'username',
                            title: '讲师姓名',
                            align: 'center',//对其方式
                            valign: 'middle',
                        },
                        {
                            field: 'age',
                            title: '讲师年龄',
                            align: 'center',//对其方式
                            valign: 'middle',
                        },
                        {
                            field: 'lifePhoto',
                            title: '照片',
                            align: 'center',//对其方式
                            valign: 'middle',
                            formatter:function (value) {
                                return "<img src=' "+value +" ' width='100' height='100'>";
                            }

                        },
                        {
                            field: 'lesson',
                            title: '所属课程',
                            align: 'center',//对其方式
                            valign: 'middle',
                        },
                        {
                            field: 'description',
                            title: '描述',
                            align: 'center',//对其方式
                            valign: 'middle',
                        },
                        {
                            field: 'operation',
                            title: '操作',
                            align: 'center',//对其方式
                            valign: 'middle',
                        }
                    ],
                    silent : true, // 刷新事件必须设置

                });
            },
            initCharts:function () {
                $.ajax({
                    url:"api/v1/teachers",
                    type:"get",
                    dataType:"json",
                    success:function (data) {

                        var myChart = echarts.init(document.getElementById('main'));
                        var ageData = [];
                        var nameData = [];
                        for (var i = 0; i <data.length ; i++) {
                            nameData.push(data[i].username);
                            ageData.push(data[i].age);
                            console.log(data[i].age +"==="+data[i].username);
                        }

                        // 指定图表的配置项和数据
                        var option = {
                            title: {
                                text: '讲师概况'
                            },
                            tooltip: {},
                            legend: {
                                data:['讲师年龄']
                            },
                            xAxis: {
                                data: nameData
                            },
                            yAxis: {},
                            series: [{
                                name: '年龄',
                                type: 'bar',
                                data: ageData
                            }]
                        };
                        // 使用刚指定的配置项和数据显示图表。
                        myChart.setOption(option);
                    }
                });



            }
        }

        soul.init();
    }
)(window);