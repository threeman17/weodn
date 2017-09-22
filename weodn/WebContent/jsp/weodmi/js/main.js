$(function(){
	var bgColorArr=["#1596f5","#88b447","#1bb27f","#eb626f","#f99c3e","#a387fa","#3e4a52"];
	var btnColorArr=["#97d800","#d4d802","#ffc12b","#ffc12b","#ffe25b","#7d56f8","#1596f5"];
	var random=Math.floor(Math.random()*bgColorArr.length);
	$("#bgColor").css("background",bgColorArr[random]);
	$("#searchBtn").css("background",btnColorArr[random]);
	
	var enArr=["Dare and the world always yields. If it beats you sometimes, dare it again and again and it will succumb.",
	"Yesterday is history. Tomorrow is a mystery. But today is a gift. All you can do is to grasp it. ",
	"It is one of the most beautiful compensations of life that no man can sincerely try to help another without helping himself. ",
	"All grown-ups were once children... but only few of them remember it. ",
	"To exist is to change, to change is to mature, to mature is to go on creating oneself endlessly.",
	"Bran: Can a man still be brave if he's afraid? Ned: That is the only time a man can be brave. ",
	"Hard work beats a talent when the talent fails to work hard. - Kevin Durant "
	]
	var cnArr=["你勇敢，世界就会让步。如果有时它战胜你，你要不断地勇敢再勇敢，它就会屈服。",
	"昨天已成历史，明天仍是未知，但今天是上天赐予我们的礼物，你能做的就是把握今天！",
	"任何一个人，如果真诚地试着帮助他人，必然也同时帮助了自己，这乃是人生中最优美的补偿之一。",
	"所有大人都曾是小孩, 可惜只有少数人记得这件事。",
	"要生存就要变化，要变化就要成长，要成长就要不断地自我创新。——亨利·柏格森",
	"布兰：人在恐惧的时候还能勇敢吗? 奈德：人唯有恐惧的时候方能勇敢。—— 《权力的游戏》",
	"当天才不努力时，努力可以击倒天才。——凯文•杜兰特"
	]
	
	$("#enText").text(enArr[random]);
	$("#cnText").text(cnArr[random]);
	
	
	$("#searchBtn").click(function (e) {
				youdao($("#searchBox").val());
				anime();
		});
	
	$("#searchBox").focus(function(){
		if($("#searchBox").val().length>0){
			
			$(".associate").css("display","block");
			$.ajax({
				type:"get",
				url:'http://suggestion.baidu.com/su?wd='+$(this).val()+'&cb=miaov',
				async:true,
				dataType: "jsonp"
			});
		}
	})
	$("#searchBox").click(function(e){
		e.stopPropagation();
		
	})
	$(document).click(function(){
		$(".associate").css("display","none");
	})
	//初始化翻译显示框
	$(".showtrans").hide();
	//添加输入框输入事件
	$("#searchBox").keyup(function(){
		$.ajax({
				type:"get",
				url:'http://suggestion.baidu.com/su?wd='+$(this).val()+'&cb=miaov',
				async:true,
				dataType: "jsonp",
//				success: function(data){
//					
//				}
			});
	})
	
	//热门词语添加点击事件
	var aItem=document.getElementsByClassName("item");
	for (var i=0;i<aItem.length;i++) {
		aItem[i].onclick=function(){
			$("#searchBox").val($(this).text())
			youdao(this.innerHTML);
			anime();
		}
	}
	
})

//动画
function anime(){
	 $("#bgColor").animate({ "height": "100px" },1000);
	 $(".search_box").css({ "marginTop": "-35px" },1000);
	 $(".search_logo").hide(1000);
	 $(".showtrans").show(1000);
	 $(".associate").hide();
}

//翻译
function youdao(txt){
	var oScript = document.createElement('script');
	oScript.src ="http://fanyi.youdao.com/openapi.do?keyfrom=cewinstudy&key=1245653553&type=data&doctype=jsonp&callback=show&version=1.1&q="+txt;
	document.body.appendChild(oScript);
	
}

//执行获取联想数据结果
function miaov(data){
	var oUl = document.getElementsByClassName('associate')[0];
	var html = '';
	if (data.s.length) {
		oUl.style.display = 'block';
		for (var i=0; i<data.s.length; i++) {
			html += '<li><span>'+data.s[i]+'</span><a target="_blank" href="http://s.uc.cn/?q='+data.s[i]+'">超级搜索</a></li>';
		}
		oUl.innerHTML = html;
	} else {
		oUl.style.display = 'none';
	}
	var aLi=oUl.getElementsByTagName("li");
	for (var i=0;i<aLi.length;i++) {
		aLi[i].onclick=function(){
			var str2=this.getElementsByTagName("span")[0].innerHTML;
			document.getElementById("searchBox").value=str2;
			youdao(str2);
			anime();
		}
	}
}

//执行翻译数据结果
function show(data){
		var str1='';
		$(".keyword-text").text(data.query);
		if(typeof data.basic.phonetic =="undefined"){
			$(".keyword-pinyin").text(data.basic.phonetic);
		}else{
			$(".keyword-pinyin").text("没有拼音数据");
		}
		
		for (var i=0;i<data.basic.explains.length;i++) {
			str1+=data.basic.explains[i]+",";
		}
		$(".explains-text").text(str1);
		$(".translation-text").text(data.translation[0]);
//		var Reg=/[计]/;
//		if(data.basic.explains[0]&&data.basic.explains[0].match(Reg)){
//			addBooksUrl(data.translation[0])
//		}else{
//			alert(false)
//		}
		addBooksUrl(data.translation[0]);
	}

//发送书籍请求
function addBooksUrl(book){
		/*
		var oScript = document.createElement('script');
		oScript.src ="https://api.douban.com/v2/book/search?callback=showbook&q="+book;
		document.body.appendChild(oScript);
		*/
		$.ajax({
					type:"get",
					url:"https://api.douban.com/v2/book/search?tag="+$("#searchBox").val(),
					async:true,
					dataType: "jsonp",
					success: function(data){
						showbook(data)
					}
				});
	}
	
//处理书籍书籍结果
function showbook(data){
		document.getElementsByClassName("recommend")[0].innerHTML="";
		if(data.books.length>0){
			for(var i=0;i<data.books.length;i++){
				var oDiv=document.createElement('div');
				var oImg=document.createElement('img');
				var tA=document.createElement('a');
				var aA=document.createElement('a');
				//更多信息
				var mDiv=document.createElement("div");
				var bH=document.createElement("h4");
				var bP=document.createElement("p");
				var aH=document.createElement("h4");
				var aP=document.createElement("p");
				
				//设置图片
				oDiv.className="book";
				oImg.src=data.books[i].images.large;
				
				
				//设置标题
				tA.className="book-title";
				tA.setAttribute("href","http://s.uc.cn/?q="+data.books[i].title);
				tA.setAttribute("target","_blank");
				tA.innerHTML=data.books[i].title;
				//设置作者
				aA.className="book-author";
				aA.setAttribute("href","http://s.uc.cn/?q="+data.books[i].author);
				aA.setAttribute("target","_blank");
				if(data.books[i].author.length!=0){
					aA.innerHTML=data.books[i].author;
				}else{
					aA.innerHTML="佚名";
				}
				
				
				//设置更多信息
				bH.innerHTML="《"+data.books[i].title+"》简介";
				if(data.books[i].summary.length!=0){
					bP.innerHTML=data.books[i].summary;
				}else{
					bP.innerHTML="暂无书籍介绍";
				}
				
				aH.innerHTML=data.books[i].author+" 简介";
				if(data.books[i].author_intro.length!=0){
					aP.innerHTML=data.books[i].author_intro;
				}else{
					aP.innerHTML="暂无作者介绍";
				}
				
				
				mDiv.className="book-more";
				mDiv.setAttribute("style","display: none");
				mDiv.appendChild(bH);
				mDiv.appendChild(bP);
				mDiv.appendChild(aH);
				mDiv.appendChild(aP);
				
				oDiv.appendChild(oImg);
				oDiv.appendChild(tA);
				oDiv.appendChild(aA);
				oDiv.appendChild(mDiv);
				
	
				document.getElementsByClassName("recommend")[0].appendChild(oDiv);
				console.log(data.books[i].title+" \t"+data.books[i].author_intro.length);
			}
		}
		demo();
	}
//给所有推送的书籍加上移入移出事件
function demo(){
	var book=document.getElementsByClassName("book");
	for (var i=0;i<book.length;i++) {
		book[i].onmouseover=function(){
			this.getElementsByClassName("book-more")[0].style.display="block";
		}
		book[i].onmouseout=function(){
			this.getElementsByClassName("book-more")[0].style.display="none";
		}
	}
}
