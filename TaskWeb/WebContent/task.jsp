<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.AddTaskVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="script/twitter-signup.css" type="text/css">
<link rel="stylesheet" href="script/main.css" type="text/css">
<%
	AddTaskVO addTaskVO = (AddTaskVO) request.getAttribute("addTaskVO");
%>
</head>
<body>
	<div id="container">
		<br>
		<div class="label">Task</div>
		<br> <br>
		<div id="twitter-outer">
			<div id="twitter-logo">
				<img src="images/task_logo_header.png" alt="Task" height="36"
					width="155">
				<div class="content-bubble-arrow"></div>
			</div>

			<div id="twitter">
				<form action="addtask" method="post">
					<div id='title' class='outerDiv'>
						<label for="title">任务标题:</label> <input type="text" name="title" value="<%=addTaskVO.getTask().getTitle() %>" />
					</div>
					<div class='clearfix'></div>
					<div id='description' class='outerDiv'>
						<label for="description">任务描述:</label>
						<!-- <input type="text" name="description" required  />  -->
						<textarea name="description" rows="3" cols="20"> <%=addTaskVO.getTask().getDescription() %></textarea>
					</div>
					<div class='clearfix'></div>

					<div id='executor' class='outerDiv'>
						<label for="executor">执行人员:</label>
						<!-- 	<input type="text" name="executor" required />  -->
						<select
							style="position: relative; left: -2px; top: -2px; font-size: 12px; width: 183px; line-height: 14px; bo rder: 0px; color: #909993; padding: 8px;"
							name="executor">
							<%
								for (int i = 0; i < addTaskVO.getUsers().size(); i++) {
							%>
							<option value="<%=addTaskVO.getUsers().get(i).getUserCode()%>"><%=addTaskVO.getUsers().get(i).getUserCode()%>_<%=addTaskVO.getUsers().get(i).getUserName()%>
							</option>
							<%
								}
							%>
						</select>
					</div>
					<div class='clearfix'></div>
					<div id='executendtime' class='outerDiv'>
						<label for="enddate">任务到期日:</label> <input type="text"
							name="executendtime" value="<%=addTaskVO.getTask().getExecutendtime() %>" />
					</div>
					<div class='clearfix'></div>
					<div class='clearfix'></div>
				</form>
				<div class="clearfix"></div>
			</div>
		</div>
		<br>
		<!--  <div id="warning" style="margin-top: 20px; color: rgb(85, 85, 85); text-align: center; font-size: 14px;">适用浏览器：IE8、360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗.</div>
			<div style="margin-top: 15px; color: rgb(85, 85, 85); text-align: center;">来源：<a href="http://sc.chinaz.com/" target="_blank">站长素材</a></div>
-->
	</div>

</body>
</html>