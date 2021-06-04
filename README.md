# 简单查题网站

#### 介绍

写此项目是的原因是为了解决创新创业考试，方便大家共享答案，避免不必要的时间浪费。话说这学期课也太多了！！！

#### 软件架构
SpringBoot构建的SSM项目+Druid数据库连接池


#### 安装教程

打包成jar运行即可


#### 使用说明

1. 将application.yml中的数据库账号和密码改成自己的
2. 在MySQL中创建test数据库
3. 在test数据库中创建Innovation表，其中包含两个字段question和answer，其中question为主键
4. 数据库详情请看SQL目录(提供了部分测试数据)
5. 如果要安装Https证书，可以参考网上的教程，网上这方面的教程很多

#### 前端脚本
##### 1. 安徽省创业服务云平台批量导题脚本
```javascript
var getQuestionAndAnswer = function() {
    var question = document.querySelectorAll('p.fl.problemLeft')
    var option = document.querySelectorAll('div.solutionBox')
    var answer = document.querySelectorAll('span.fl.f_green')
    var questionAndAnswerContent = "";
    for (var i = 0; i < question.length; i++) {
        questionAndAnswerContent += question[i].innerText.replace(/[0-9]+\.\s\[...\]/g,"").trim() + "&&";
        var answerOption = answer[i].innerText.replace("正确答案：","").split("")
        var optionList = option[i].innerText.replace(/\s+/g, " ").split(/\s/)
        for (var k = 0; k < answerOption.length; k++) {
            for (var j = 0; j < optionList.length; j++) {
                if(optionList[j] == answerOption[k]) questionAndAnswerContent += optionList[j+1].trim() + "#"
           }
        }
        if(option[i].innerText == "") questionAndAnswerContent += answerOption[0]
        questionAndAnswerContent += "&&"
    
    }
    $.ajax({
        type:'POST',
        async:true,
        url:'https://xxxxxxxxxxx/batch',
        contentType:'application/x-www-form-urlencoded;charset=UTF-8',
        data:{
            questionAndAnswer: encodeURI(questionAndAnswerContent)   
        }
    });
    
    console.log(questionAndAnswerContent)
    if ($("span.next").hasClass("current next")) {
        window.clearInterval(time)
        $("body").append(alert("你已成功将本次测试的所有题目导入数据库中"))
    }
    $("a.next").click();
}
var time = window.setInterval(getQuestionAndAnswer, 2000)
```

##### 2. 易班批量导题脚本
###### 2.1 单选题批量导题脚本
```javascript
var question = document.querySelectorAll("p.q-cnt");
var answerOption = document.querySelectorAll("div.the-ans")
var option = document.querySelectorAll("ul.single-ans")
var questionAndAnswerContent = "";
for (var i = 0,j = 1; i < answerOption.length; i++, j+=2) {
	questionAndAnswerContent += question[j].innerText + "&&";
	var options = option[i].innerText.split(/\s/);
	var answerOptions = answerOption[i].innerText.replace("正确答案：", "");
	var temp = answerOptions +".";
	for (var k = 0; k < options.length; k++) {
		if(options[k] == temp) questionAndAnswerContent += options[k+1];
	}
	questionAndAnswerContent += "&&";
}

$.ajax({
		type:'POST',
		async:true,
		url:'https://xxxxxx/batch',
		contentType:'application/x-www-form-urlencoded;charset=UTF-8',
		data:{
			questionAndAnswer: encodeURI(questionAndAnswerContent)	 
		}
	});
console.log(questionAndAnswerContent)
```
###### 2.2 多选题批量导题脚本
```javascript
var question = document.querySelectorAll("p.q-cnt");
var answerOption = document.querySelectorAll("div.the-ans")
var option = document.querySelectorAll("ul.srl-ans")
var questionAndAnswerContent = "";
for (var i = 0,j = 1; i < answerOption.length; i++, j+=2) {
	questionAndAnswerContent += question[j].innerText + "&&";	
	var options = option[i].innerText.split(/\s/);
	var answerOptions = answerOption[i].innerText.replace("正确答案：", "").split("、");	
	for (var k = 0; k < answerOptions.length; k++) {
		var temp = answerOptions[k] +".";
		for (var m = 0; m < options.length; m++) {
			if(options[m] == temp) questionAndAnswerContent += options[m+1] + "#";
		}
	}
	questionAndAnswerContent += "&&";
}


$.ajax({
		type:'POST',
		async:true,
		url:'https://xxxxxx/batch',
		contentType:'application/x-www-form-urlencoded;charset=UTF-8',
		data:{
			questionAndAnswer: encodeURI(questionAndAnswerContent)	 
		}
	});
console.log(questionAndAnswerContent)
```
###### 2.3 判断题

判断题的脚本懒的写了，给上面的脚本改改就能用了，就不多说了。
