package com.pb.xc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pb.xc.controller.vo.Message;
import com.pb.xc.entity.About;
import com.pb.xc.service.IUserService;

@RestController
@RequestMapping("/otherManageC")
@Scope("prototype")
public class OtherManageController {
	@Autowired
	private IUserService userService;
	/**
	 * 
	 * 查询关于我们信息
	 * @param newsVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/showAboutData", method = RequestMethod.POST)
	public About showAboutData(@RequestBody About abount) throws Exception {
		return userService.query(abount);
	}
	
	/**
	 * 更新关于我们
	 * @param abount
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateAboutData", method = RequestMethod.PUT)
	public Message updateAboutData(@RequestBody About abount) throws Exception{
		return userService.update(abount);
	}
	
}
