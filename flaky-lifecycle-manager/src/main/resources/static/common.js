
var initStatus = 0,projectId = getQueryString('projectId'),curflakyId = '',currentPage = 1;
$(function() {
    $('#project').text(projectId);

    $("#historyTable table tbody").on("click","tr",function() {
        var id = $(this).attr("data-id");
        // toast(id);
        getDetail(id);
    })

    // initEchart(81,28);
    // pageList(11,32);
    getChart();
    getList(projectId,1,6);

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
        data: { flakyHistoryId:id},
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
    $('.tableGrid div#newFound,.tableGrid div#done').hide();
    initStatus = status;
    currentPage = 1;
    getList(projectId,1,6);
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
                    text: 'Bugs Processing Progress',
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
                    data:['Unhandled','Handled'],
                    textStyle:{
                        color:'#999'
                    }
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                toolbox: {
                    show : false,
                },
                visualMap: {
                    show: false,
                    min: 80,
                    max: 600,
                    inRange: {
                        colorLightness: [0, 1]
                    }
                },
                series : [
                    {
                        name:'Detected Bugs',
                        type:'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        startAngle: 40,
                        data:[
                            {value:unhandle, name:'Unhandled'},
                            {value:handle, name:'Handled'}
                        ],
                        itemStyle:{
                            normal: {
                                labelLine: {
                                    show: true,
                                    length:3,
                                    length2:3
                                }
                            }
                        }
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
    currentPage = 1;
    getHisList(id,1,6);
    window.event.stopPropagation();
}

function changeStatus(id) {
    $.ajax({
        type: 'POST',
        url: '/flaky/updateFlakyTestStatus',
        dataType: 'json',
        data: {projectId: projectId, flakyStatus: 1 - initStatus, flakyId: id},
        success: function (res) {
            if (res.status === 200) {
                getList(projectId, 1, 6);
                toast('Change status successfully!');
            } else {
                toast(res.message);
            }
        }
    });
    window.event.stopPropagation();
}

function getList(id,cur,size) {
    $.ajax({
        type: 'POST',
        url: '/flaky/getFlakyList',
        dataType: 'json',
        data: { projectId:id,flakyStatus:initStatus,currentPage:cur,pageSize:size},
        success: function(res){
            if(res.status===200){
                console.log(res.data.items);
                var trStr = '',statusStr = (initStatus===0?'Unhandled':'Handled'),markStatus =  (initStatus===0?'Handled':'Unhandled');
                for (var i = 0;i<res.data.items.length;i++){
                    trStr += '<tr data-id="'+res.data.items[i].flakyId+'"><td onmouseover="overShow('+res.data.items[i].className+')" onmouseout="outHide()">'+res.data.items[i].className+'</td>';
                    trStr += '<td>'+res.data.items[i].unitTestName+'</td>';
                    trStr += '<td>'+res.data.items[i].detectCount+'</td>';
                    trStr += '<td>'+formatUnixtimestamp(res.data.items[i].lastDetectTime)+'</td>';
                    trStr += '<td>'+res.data.items[i].flakyId+'</td>';
                    trStr += '<td>'+statusStr+'</td>';
                    trStr += '<td> <a class="statusSwitch" onclick="changeStatus('+res.data.items[i].flakyId+')">Mark as '+markStatus+'</a><a class="toHistory" onclick="history('+res.data.items[i].flakyId+')">History</a></td></tr>';
                }
                if(initStatus===0){
                    $('#newFound table tbody').html(trStr);
                }else{
                    $('#done table tbody').html(trStr);
                }
                if(cur===1){
                    console.log('current:',cur);
                    pageList(res.data.totalPage,res.data.totalNum);
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

function overShow(str) {
    $('.showClassName>span').text(str);
    $('.showClassName').css({left:event.clientX,top:event.clientY}).show();
}

function outHide() {
    $('.showClassName').hide();
}

function pageList(total,nums){
    $('#box').paging({
        initPageNo: 1, // 初始页码
        totalPages: total, //总页数
        totalCount: nums + '  Records', // 条目总数
        slideSpeed: 600, // 缓动速度。单位毫秒
        jump: true, //是否支持跳转
        callback: function(page) { // 回调函数
            if(currentPage!==page){
                getList(projectId,page,6);
                currentPage = page;
            }
            console.log(page)
        }
    })
}

function getHisList(id,cur,size) {
    $.ajax({
        type: 'POST',
        url: '/flaky/getFlakyHistoryList',
        dataType: 'json',
        data: { projectId:projectId,flakyId:id,currentPage:cur,pageSize:size},
        success: function(res){
            if(res.status===200){
                $('.content').hide();
                $('.historyContent').show();
                var trStr = '',statusStr = (initStatus===0?'Unhandled':'Handled');
                for (var i = 0;i<res.data.items.length;i++){
                    trStr += '<tr data-id="'+res.data.items[i].flakyHistoryId+'"><td>'+res.data.items[i].className+'</td>';
                    trStr += '<td>'+res.data.items[i].unitTestName+'</td>';
                    trStr += '<td>'+formatUnixtimestamp(res.data.items[i].detectTime)+'</td>';
                    trStr += '<td>'+res.data.items[i].flakyHistoryId+'</td>';
                    trStr += '<td>'+statusStr+'</td>';
                    trStr += '<td>'+res.data.items[i].sha1+'</td></tr>';
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
            console.log('Ajax error!');
        }
    })
}

function pageList1(total,nums){
    $('#box1').paging({
        initPageNo: 1, // 初始页码
        totalPages: total, //总页数
        totalCount: 'total' + nums , // 条目总数
        slideSpeed: 600, // 缓动速度。单位毫秒
        jump: true, //是否支持跳转
        callback: function(page) { // 回调函数
            if(currentPage!==page){
                getHisList(curflakyId,page,6);
                currentPage = page;
            }
            console.log(page);
        }
    })
}

function formatUnixtimestamp (inputTime){
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
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