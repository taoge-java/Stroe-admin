/**
 * @param cont   tbody的id
 * @param totalPage  //总页数
 * @param groups  //连续显示分页数
 * @param url
 * @param content 
 */
function page(cont,totalPage,groups,url,content){
	layui.use(['laypage', 'layer'], function(){
		  var laypage = layui.laypage,
		  layer = layui.layer;
	      //调用分页
		   laypage({
			   cont: cont,
			   pages: totalPage,//总页数
			   groups: groups, //连续显示分页数
			   jump: function(obj,first){
				   if(first!=true){//是否首次进入页面
					   $.ajax({
							url:url,
							dataType:"html",
							data:{
								"pageNumber":obj.curr
							},
							type:"post",
							success:function(data){
								$("#"+content).html(data);
							}
						});
				   }
			   }
	       });
	});
}

/**
 * layer操作成功提示消息
 * @param message
 */
function alertSuccess(message){
	if(!message){
        message = "操作成功";
	}
	layer.alert(message, {
         icon: 1,
		 closeBtn: 0,
		 time: 1000,
         skin: 'layer-ext-moon',
    });
}

/**
 * layer操作失败提示
 * @param message
 */
function  alertFail(message) {
    if(!message){
        message = "操作失败";
    }
    layer.alert(message,{
        icon: 2,
        time: 2000,
        skin: 'layer-ext-moon',
    });
}


function popSuccess(message) {
	layer.msg(message, {
	    skin: 'layui-layer-green',
	    closeBtn: 0,
	    time: 2000,
	    icon: 1,
	    shift: 0 //动画类型
  	});
}

var global = {
	
	loading:function(message){
		if(!message){
			message = '正在提交，请稍等...';
		}
		var index = layer.msg(message, {
			  icon: 16
			  ,shade: 0.01,
			  time:999999999//设置超长时间
		});
		  
	    return index;
	},
	
	close: function(index){
		if(index){
			layer.close(index);
		}else{
			layer.closeAll("loading");
		}
	}
}

function popError(message) {
	layer.alert(message, {
	    skin: 'layui-layer-green',
	    closeBtn: 0,
	    time: 2000,
	    icon: 2,
	    shift: 0 //动画类型
  	});
}


function addInformation(url,data,dataType){
	
}
/***
 * 系统时间
 */
/*function showTime(){
	 var dat=new Date();
	 var year=dat.getFullYear();
	 var month=dat.getMonth()+1;
	 var day=dat.getDate();
	 var week=dat.getDay();
	   switch(week){
			case 0:
			   week="星期天";
			   break;
			case 1:
				week="星期一";
				break;
			case 2:
			   week="星期二";
			   break;
			case 3:
				week="星期三";
				break;
			case 4:
			   week="星期四";
			   break;
			case 5:
				week="星期五";
				break;
			case 6:
			   week="星期六";
			   break;
	   }
	 var hours=dat.getHours();
	 var minue=dat.getMinutes();
	 var second=dat.getSeconds();
	 if(hours>=0&&hours<10){
		 hours="0"+hours;
	 }
	 if(minue>=0&&minue<10){
		minue="0"+minue;
	 }
	 if(second>=0&&second<10){
		second="0"+second;
	 }
	 var time=year+"-"+month+"-"+day+"  "+week+" "+hours+":"+minue+":"+second;
	 document.getElementById("time").innerHTML=time;
}
  setInterval("showTime()",1000);*/