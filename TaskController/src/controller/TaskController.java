package controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import controller.cenum.ExcuteStatusEnum;
import controller.tools.StringTool;
import model.AddTaskVO;
import model.LoginVO;
import model.Task;
import model.TasksVO;
import model.User;
import service.TaskService;

public class TaskController {

	/**
	 * 新增任务
	 * 
	 * @param request
	 * @param response
	 */
	public void AddTask(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title").toString();
		String description = request.getParameter("description").toString();
		String executor = request.getParameter("executor").toString();
		String executendtime = request.getParameter("executendtime").toString();
		Task task = new Task();
		task.setTitle(StringTool.ToUTF(title));
		task.setDescription(StringTool.ToUTF(description));
		task.setExecutor(StringTool.ToUTF(executor));
		task.setExecutestatus("0");
		Date date = new Date();
		task.setStarttime(date);
		task.setEndtime(date);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		executendtime = StringTool.ToUTF(executendtime);
		Date executendDate = null;
		try {
			executendDate = sdf.parse(executendtime.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		task.setExecutendtime(executendDate);
		TaskService taskService = new TaskService();
		taskService.AddTaks(task);
	}

	/**
	 * 单个任务详细信息展示
	 * 
	 * @param request
	 * @param response
	 */
	public void DetailTask(HttpServletRequest request, HttpServletResponse response) {
		TaskService taskService = new TaskService();
		String taskid = request.getParameter("id");
		AddTaskVO addTaskVO = taskService.DetailTask(taskid);
		request.setAttribute("addTaskVO", addTaskVO);
	}

	/**
	 * 展示所有任务
	 * 
	 * @param request
	 * @param response
	 */
	public void ShowTasks(HttpServletRequest request, HttpServletResponse response) {
		TaskService taskService = new TaskService();
		List<TasksVO> tasksVOs = null;
		try {
			tasksVOs = taskService.GetTasks(null);
			for (TasksVO tasksVO : tasksVOs) {
				Task task = tasksVO.getTask();
				task.setExecutestatus(ExcuteStatusEnum.resStatus(Integer.parseInt(task.getExecutestatus())));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("tasksVOs", tasksVOs);
	}

	/**
	 * login
	 * 
	 * @param request
	 * @param response
	 */
	public boolean Login(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User loginuser = (User) session.getAttribute("taskuser");
		if (loginuser != null) {
			return true;
		}
		TaskService taskService = new TaskService();
		String usercode = request.getParameter("usercode");
		String password = request.getParameter("password");
		if (StringUtils.isBlank(usercode) || StringUtils.isBlank(password)) {
			return false;
		}
		User user = taskService.Login(usercode);
		if (user.getUserCode() != null && user.getPassword().equals(password)) {
			session.setAttribute("taskuser", user);
			return true;
		} else {
			LoginVO loginVO = new LoginVO();
			loginVO.setError("用户名或密码错误！");
			request.setAttribute("loginVO", loginVO);
			return false;
		}
	}
}
