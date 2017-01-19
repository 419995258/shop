package com.pb.xc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pb.xc.controller.vo.Message;
import com.pb.xc.controller.vo.ResultVo;
import com.pb.xc.entity.About;
import com.pb.xc.entity.News;
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
	
	/**
	 * 添加新闻
	 */
	@RequestMapping(value="/addNews",method = RequestMethod.PUT)
	public Message addNews(@RequestBody News news) throws Exception{
		return userService.addNews(news);
	}
	
	/**
	 * 查询新闻Byid
	 * 
	 */
	
	@RequestMapping(value="/showNewsData", method = RequestMethod.POST)
	public News showNewsData(@RequestBody News news) throws Exception {
		return userService.showNewsData(news);
	}
	
	/**
	 * 更新信息
	 */
	@RequestMapping(value="/updateNews",method = RequestMethod.PUT)
	public Message updateNews(@RequestBody News news) throws Exception{
		return userService.updateNews(news);
	}
	
	/**
	 * 删除信息
	 */
	@RequestMapping(value="/deleteNews",method = RequestMethod.PUT)
	public Message deleteNews(@RequestBody News news) throws Exception{
		return userService.deleteNews(news);
	}
	
	
	/**
	 * 查询所有的信息
	 * 
	 */
	@RequestMapping(value="/showNews",method = RequestMethod.POST)
	public ResultVo showNews(@RequestBody ResultVo param) throws Exception{
		return userService.selectNews(param);
	}
	
	/**
	 * 查询单条新闻
	 * @param id
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryInfo", method = RequestMethod.POST)
	public News queryInfo(@RequestBody Integer id, HttpSession session)throws Exception{
		return userService.queryById(id);
	}
}
