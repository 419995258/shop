package com.pb.xc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pb.xc.controller.vo.Message;
import com.pb.xc.controller.vo.ResultVo;
import com.pb.xc.entity.Add;
import com.pb.xc.entity.User;
import com.pb.xc.service.IGoodsService;
import com.pb.xc.service.IUserService;
import com.pb.xc.service.impl.MailService;
import com.pb.xc.util.Const;

@RestController
@RequestMapping("/userC")
@Scope("prototype")
public class UserController extends BasicController {

	@Resource(name = "userService")
	private IUserService userService;
	
	
	@Resource(name = "mailService")
	private MailService mailService;

	
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Message getUsers(
			@RequestBody(required = false) HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Message message = new Message();

		return message;
	}
*/
	/**
	 * 查询所有用户
	 * 
	 */
	@RequestMapping(value = "/queryUser", method = RequestMethod.POST)
	public ResultVo queryUser(@RequestBody ResultVo param) throws Exception {
		return userService.queryUser(param);
	}

	/**
	 * 查询登录日志
	 * 
	 */
	@RequestMapping(value = "/queryLog", method = RequestMethod.POST)
	public ResultVo queryLog(@RequestBody ResultVo param) throws Exception {
		return userService.queryLog(param);
	}

	/**
	 * 管理员登录
	 */

	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	public Message adminLogin(@RequestBody User user,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {
		Message message = new Message();
		message = userService.adminLogin(user);
		if (message.getSuccess()) {
			//保存管理员登录session
			User userSession = (User) message.getResult().get("user");
			conInfoToSession(session, Const.SESSION_ADMIN_USER, userSession);
		}
		return message;
	}
	
	/**
	 * 验证管理员是否登录
	 */
	@RequestMapping(value="ckLogined", method = RequestMethod.POST)
	public Message ckLogined(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {
		Message message = new Message();
		//获取session
		Object user = session.getAttribute(Const.SESSION_ADMIN_USER);
		
		if (user != null) {
			Map<String, User> result = new HashMap<String, User>();
			result.put("user", (User) user);
			message.setResult(result);
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;
	}
	
	/**
	 * 用户登录
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Message userLogin(@RequestBody User user,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {
		Message message = new Message();
		message = userService.userLogin(user);
		if (message.getSuccess()) {
			//保存管理员登录session
			User userSession = (User) message.getResult().get("user2");
			conInfoToSession(session, Const.SESSION_CUSTOMER_USER, userSession);
		}
		return message;
	}
	
	/**
	 * 验证用户是否登录
	 */
	@RequestMapping(value="ckUserLogined", method = RequestMethod.POST)
	public Message ckUserLogined(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {
		Message message = new Message();
		//获取session
		Object user = session.getAttribute(Const.SESSION_CUSTOMER_USER);
		
		if (user != null) {
			Map<String, User> result = new HashMap<String, User>();
			result.put("user", (User) user);
			message.setResult(result);
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;
	}
	
	/**
	 * 查询用户名是否已经存在
	 * 
	 */
	/*@RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
	public Message checkUsername(@RequestBody User user) throws Exception {
		return userService.checkUsername(user);
	}*/
	
	/**
	 * 注册新用户
	 * 
	 */
	@RequestMapping(value = "/registerUser", method = RequestMethod.PUT)
	public Message registerUser(@RequestBody User user) throws Exception {
		return userService.insertUserserRegister(user);
	}
	
	/**
	 * 用户登出
	 * @param session
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public Message logOut(HttpSession session) throws Exception {
		Message message = new Message();
		clearSession(session, Const.SESSION_CUSTOMER_USER);
		message.setSuccess(true);
		return message;

	}
	
	/**
	 * 查询交易额度，访问人数
	 * @param add
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAdd", method = RequestMethod.POST)
	public Message queryAdd() throws Exception{
		return userService.queryAdd();
		
	}
	
	/**
	 * 注册新用户
	 * 
	 */
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.PUT)
	public Message updateUserInfo(@RequestBody User user) throws Exception {
		return userService.updateUserInfo(user);
	}
	
	/**
	 * 找回密码
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/recoveryPass", method = RequestMethod.PUT)
	public Message recoveryPass(@RequestBody String username) throws Exception {
		Message message = new Message();
		//查找密码
		message = userService.selectPassword(username);
		Map<String, String> result = message.getResult();
		//获取密码
		String password = result.get("password");
		mailService.sendMail(username, password);
		
		return message;
	}
}
