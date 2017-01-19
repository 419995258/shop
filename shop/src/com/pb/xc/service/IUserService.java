package com.pb.xc.service;

import com.pb.xc.controller.vo.AddVo;
import com.pb.xc.controller.vo.Message;
import com.pb.xc.controller.vo.ResultVo;
import com.pb.xc.entity.About;
import com.pb.xc.entity.Add;
import com.pb.xc.entity.News;
import com.pb.xc.entity.User;

public interface IUserService {
	/**
	 * 添加用户注册
	 * @param user
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/08
	 */
	public Message insertUserserRegister(User user) throws Exception;
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/08
	 */
	public Message userLogin(User user) throws Exception;
	
	/**
	 * 管理员登录
	 * @param user
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/08
	 */
	public Message adminLogin(User user) throws Exception;
	
	/**
	 * 验证用户是否已经存在
	 * @param user
	 * @throws Exception
	 * pb
	 * 16/09/08
	 */
	public Message checkUsername (User user) throws Exception;
	
	/**
	 * 修改用户信息
	 * @param user
	 * @throws Exception
	 * pb
	 * 16/09/08
	 */
	public Message updateUserInfo (User user) throws Exception;
	
	
	/**
	 * 查询密码
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public Message queryPassword(String username) throws Exception;
	
	/**
	 * 查询用户
	 * @param user
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/08
	 */
	public ResultVo queryUser(ResultVo param) throws Exception;
	
	/**
	 * 查询add的访问人数，总交易金额
	 * @param addVo
	 * @return
	 * @throws Exception
	 *  pb
	 * 16/09/08
	 */
	public Message queryAdd() throws Exception;
	
	/**
	 * 查询管理员登录日志
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/11
	 */
	public ResultVo queryLog(ResultVo param) throws Exception;
	
	/**
	 * 查询用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 * @author PB
	 */
	//public Message queryUserInfo (User user) throws Exception;
	
	/**
	 * 找回密码
	 * @param username
	 * @return
	 * @throws Exception
	 * @author PB
	 */
	public Message selectPassword(String username)throws Exception;
	
	
	/**
	 * 
	 * 查询关于我们
	 * @param abount
	 * @return
	 * @throws Exception
	 */
	public About query(About about) throws Exception;
	
	/**
	 * 更新关于我们
	 */
	public Message update(About about) throws Exception;
	
	
	 /**
     * 
     * 添加news
     */
    public Message addNews(News news )throws Exception;
    
    /**
     * 查询news详细信息
     * 
     */
    
    public News showNewsData(News news)throws Exception;
    
    /**
     * 更新news详细信息
     * 
     */
    
    public Message updateNews(News newsVo)throws Exception;
    
    /**
     * 删除news信息
     */
    public Message deleteNews(News newsVo) throws Exception;
    
    
    /**
     * 查询news信息
     * 
     */
    public ResultVo selectNews(ResultVo param) throws Exception;
    
    /**
	 * 按id查询新闻
	 * @param id
	 * @throws Exception
	 */
	public News queryById(Integer id) throws Exception;
}
