
var initStatus = 0,projectId = getQueryString('projectId'),curflakyId = '';
$(function() {
    $('#project').text(projectId);

    $("table tbody").on("click","tr",function() {
        var id = $(this).attr("data-id");
        // toast(id);
        getDetail(id);
    })
    // initEchart(81,28);
    // pageList(11,32);
    getChart();
    getList(projectId,1,5);

});

function backtoIndex() {
    $('.historyContent').hide();
    $('.content').show();
}

function getDetail(id){
    $.ajax({
        type: 'POST',
        url: '/flaky/getFlakyEnvDetail',
        dataType: 'json',
        data: { projectId: projectId ,flakyId:id},
        success: function (res) {
            if (res.status === 200) {
                var paramStr = '';
                for(var key in res.data){
                    paramStr += '<div class="fakeTr"><label>'+key+'</label><label>'+res.data[key]+'</label></div>'
                }
                $('#fillBox').html(paramStr);
                $('.popup').show();
            } else {
                toast(res.message);
            }
        }
    });
}

function tabLabel(status,label){
    $('.tabLabel label').removeClass('active');
    $(label).addClass('active');
    $('.tableGrid div').hide();
    initStatus = status
    getList(projectId,1,5);
    if(status===0){
        $('#newFound').show();
    }else {
        $('#done').show();
    }
}

function getChart(){
    $.ajax({
        type: 'POST',
        url: '/flaky/getFlakyChart',
        dataType: 'json',
        data: { projectId: projectId },
        success: function (res) {
            if (res.status === 200) {
                initEchart(res.data.unhandleCount,res.data.handleCount);
            } else {
                toast(res.message);
            }
        }
    });
}

function closedPopup(){
    $('.popup').hide();
}
function initEchart(unhandle,handle){
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });
    require(
        [
            'echarts',
            'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('chartMain'));

            var option = {
                title : {
                    text: 'BUG处理进度',
                    padding:16,
                    textStyle:{
                        color:'#ddd',
                        fontSize: 16,
                        fontWeight:'500'
                    }
                },
                legend: {
                    orient : 'vertical',
                    x : 'right',
                    data:['未处理','已处理'],
                    textStyle:{
                        color:'#999'
                    }
                },
                toolbox: {
                    show : false,
                },
                // calculable : true,
                series : [
                    {
                        name:'发现BUG',
                        type:'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
                            {value:unhandle, name:'未处理'},
                            {value:handle, name:'已处理'}
                        ]
                    }
                ]
            };
            // 为echarts对象加载数据
            myChart.setOption(option);
        }
    );
}
function history(id) {
    curflakyId = id;
    getHisList(id,1,5);
}

function changeStatus(id) {
    $.ajax({
        type: 'POST',
        url: '/flaky/updateFlakyTestStatus',
        dataType: 'json',
        data: {projectId: projectId, flakyStatus: 1 - initStatus, flakyId: id},
        success: function (res) {
            if (res.status === 200) {
                getList(projectId, 1, 5);
                toast('状态修改成功');
            } else {
                toast(res.message);
            }
        }
    });
}

function getList(id,cur,size) {
    $.ajax({
        type: 'POST',
        url: '/flaky/getFlakyList',
        dataType: 'json',
        data: { projectId:id,flakyStatus:initStatus,currentPage:cur,pageSize:size},
        success: function(res){
            if(res.status===200){
                var trStr = '',statusStr = (initStatus===0?'未处理':'已处理');
                for (var i = 0;i<res.data.item.length;i++){
                    trStr += '<tr data-id="'+res.data.item[i].flakyId+'"><td>'+res.data.item[i].className+'</td>';
                    trStr += '<td>'+res.data.item[i].unitTestName+'</td>';
                    trStr += '<td>'+res.data.item[i].detectCount+'</td>';
                    trStr += '<td>'+formatUnixtimestamp(res.data.item[i].lastDetectTime)+'</td>';
                    trStr += '<td>'+res.data.item[i].flakyId+'</td>';
                    trStr += '<td>'+statusStr+'</td>';
                    trStr += '<td> <a class="toHistory" onclick="history('+res.data.item[i].flakyId+')">查看历史</a><a class="statusSwitch" onclick="changeStatus('+res.data.item[i].flakyId+')">标记为'+statusStr+'</a></td></tr>';
                }
                if(initStatus===0){
                    $('#newFound table tbody').html(trStr);
                }else{
                    $('#newFound table tbody').html(trStr);
                }
                if(cur===1){
                    pageList(res.data.totalPage,res.data.totalNum)
                }
            }else{
                toast(res.message);
            }
        },
        error: function(xhr, type){
            console.log('Ajax error!')
        }
    })
}

function pageList(total,nums){
    $('#box').paging({
        initPageNo: 1, // 初始页码
        totalPages: total, //总页数
        totalCount: '共' + nums + '条', // 条目总数
        slideSpeed: 600, // 缓动速度。单位毫秒
        jump: true, //是否支持跳转
        callback: function(page) { // 回调函数
            getList(projectId,page,5);
            console.log(page)
        }
    })
}

function getHisList(id,cur,size) {
    $.ajax({
        type: 'POST',
        url: '/flaky/getFlakyList',
        dataType: 'json',
        data: { projectId:projectId,flakyId:id,currentPage:cur,pageSize:size},
        success: function(res){
            if(res.status===200){
                $('.content').hide();
                $('.historyContent').show();
                var trStr = '',statusStr = (initStatus===0?'未处理':'已处理');
                for (var i = 0;i<res.data.item.length;i++){
                    trStr += '<tr data-id="'+res.data.item[i].flakyId+'"><td>'+res.data.item[i].className+'</td>';
                    trStr += '<td>'+res.data.item[i].unitTestName+'</td>';
                    trStr += '<td>'+formatUnixtimestamp(res.data.item[i].lastDetectTime)+'</td>';
                    trStr += '<td>'+res.data.item[i].flakyHistoryId+'</td>';
                    trStr += '<td>'+statusStr+'</td>';
                    trStr += '<td>'+res.data.item[i].sha1+'</td></tr>';
                }
                $('#historyTable table tbody').html(trStr);

                if(cur===1){
                    pageList1(res.data.totalPage,res.data.totalNum)
                }
            }else{
                toast(res.message);
            }
        },
        error: function(xhr, type){
            console.log('Ajax error!')
        }
    })
}

function pageList1(total,nums){
    $('#box1').paging({
        initPageNo: 1, // 初始页码
        totalPages: total, //总页数
        totalCount: '共' + nums + '条', // 条目总数
        slideSpeed: 600, // 缓动速度。单位毫秒
        jump: true, //是否支持跳转
        callback: function(page) { // 回调函数
            getList1(curflakyId,page,5);
            console.log(page)
        }
    })
}


function formatUnixtimestamp (unixtimestamp){
    var unixtimestamp = new Date(unixtimestamp*1000);
    var year = 1900 + unixtimestamp.getYear();
    var month = "0" + (unixtimestamp.getMonth() + 1);
    var date = "0" + unixtimestamp.getDate();
    var hour = "0" + unixtimestamp.getHours();
    var minute = "0" + unixtimestamp.getMinutes();
    var second = "0" + unixtimestamp.getSeconds();
    return year + "-" + month.substring(month.length-2, month.length)  + "-" + date.substring(date.length-2, date.length)
        + " " + hour.substring(hour.length-2, hour.length) + ":"
        + minute.substring(minute.length-2, minute.length) + ":"
        + second.substring(second.length-2, second.length);
}

function toast(string){
    $('div.toast').remove();
    $('body').append('<div class="toast">'+string+'</div>');
    setTimeout(function(){
        $('div.toast').remove();
    },1500);
}

function getQueryString(name){
    if(unescape(window.location.href).indexOf('?')>0){
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i")
        var urlStr = unescape(window.location.href).split('?')
        var temp = urlStr[urlStr.length-1]
        var r = temp.match(reg)
        if (r != null) return unescape(r[2]); return null;
    }else{
        return ''
    }
}