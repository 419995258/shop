package com.pb.xc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.pb.xc.controller.vo.AddVo;
import com.pb.xc.controller.vo.GoodsVo;
import com.pb.xc.controller.vo.LOGVo;
import com.pb.xc.controller.vo.Message;
import com.pb.xc.controller.vo.NewsVo;
import com.pb.xc.controller.vo.ResultVo;
import com.pb.xc.controller.vo.UserVo;
import com.pb.xc.dao.AboutMapper;
import com.pb.xc.dao.AddMapper;
import com.pb.xc.dao.LOGMapper;
import com.pb.xc.dao.NewsMapper;
import com.pb.xc.dao.UserMapper;
import com.pb.xc.entity.About;
import com.pb.xc.entity.AboutExample;
import com.pb.xc.entity.Add;
import com.pb.xc.entity.AddExample;
import com.pb.xc.entity.Goods;
import com.pb.xc.entity.GoodsExample;
import com.pb.xc.entity.LOG;
import com.pb.xc.entity.LOGExample;
import com.pb.xc.entity.News;
import com.pb.xc.entity.NewsExample;
import com.pb.xc.entity.User;
import com.pb.xc.entity.UserExample;
import com.pb.xc.entity.UserExample.Criteria;
import com.pb.xc.service.IUserService;
import com.pb.xc.util.DateUtil;
import com.pb.xc.util.FengYeBasic;
import com.pb.xc.util.ObjectUtil;
import com.pb.xc.util.StringContentUtil;

@Repository("userService")
// @Scope("prototype")
public class UserServiceImpl extends FengYeBasic implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private AddMapper addMapper;

	@Autowired
	private LOGMapper logMapper;
	
	@Autowired
	private AboutMapper aboutMapper;
	
	@Autowired
	private NewsMapper newsMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pb.xc.service.IUserService#insertUserserRegister(com.pb.xc.entity.
	 * User)
	 */
	public Message insertUserserRegister(User user) throws Exception {
		Message message = new Message();
		// 判断是否已经用户名存在
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUsernameEqualTo(user.getUsername());
		List<User> userList = userMapper.selectByExample(userExample);
		if (!ObjectUtil.collectionIsEmpty(userList)) {
			message.setMessage("该邮箱已注册,请重新注册");
			message.setSuccess(false);
		} else {

			// 添加用户
			Date register = DateUtil.getCurrentDate(DateUtil.DATE_STYLE4);// 日期处理
			user.setRegister(register);
			user.setType(1);
			int record = userMapper.insertSelective(user);
			if (record > 0) {
				message.setSuccess(true);
				message.setMessage("注册成功");
			}
		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.xc.service.IUserService#userLogin(com.pb.xc.entity.User)
	 */
	public Message userLogin(User user) throws Exception {
		Message message = new Message();
		// 用户登录判断
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUsernameEqualTo(user.getUsername())
				.andPasswordEqualTo(user.getPassword());
		List<User> userlist = userMapper.selectByExample(userExample);
		if (!ObjectUtil.collectionIsEmpty(userlist)) {
			User userSession = userlist.get(0);
			Map<String, User> map = new HashMap<>();
			map.put("user2", userSession);
			message.setResult(map);
			message.setSuccess(true);
		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.xc.service.IUserService#adminLogin(com.pb.xc.entity.User)
	 */
	public Message adminLogin(User user) throws Exception {
		Message message = new Message();
		// 用户登录判断
		UserExample userExample = new UserExample();
		// 管理员身份认证判断
		userExample.createCriteria().andUsernameEqualTo(user.getUsername())
				.andPasswordEqualTo(user.getPassword()).andTypeEqualTo(2);
		List<User> userlist = userMapper.selectByExample(userExample);
		// 验证成功
		if (!ObjectUtil.collectionIsEmpty(userlist)) {
			// 获取user
			User userSession = userlist.get(0);
			message.setSuccess(true);
			// 将user存放message用来存放session
			Map<String, Object> map = new HashMap<>();
			map.put("user", userSession);
			message.setResult(map);

			// 记录管理员登录日志
			LOG log = new LOG();
			log.setUserId(userSession.getId());
			log.setUsername(userSession.getUsername());
			log.setName(userSession.getName());
			Date time = new Date();
			log.setTime(DateUtil.getCurrentDate(DateUtil.DATE_STYLE5));
			logMapper.insertSelective(log);

		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.xc.service.IUserService#checkUsername(com.pb.xc.entity.User)
	 */
	/**
	 * 验证是否存在用户名
	 */
	public Message checkUsername(User user) throws Exception {
		Message message = new Message();
		message.setSuccess(true);
		UserExample userExample = new UserExample();
		// 查找是否有同名账号
		userExample.createCriteria().andUsernameEqualTo(user.getUsername());
		List<User> userList = userMapper.selectByExample(userExample);
		if (!ObjectUtil.collectionIsEmpty(userList)) {
			message.setSuccess(false);
			message.setMessage("账号已经存在");
		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pb.xc.service.IUserService#updateUserInfo(com.pb.xc.controller.vo.
	 * UserVo)
	 */
	/**
	 * 更新用户信息
	 */
	public Message updateUserInfo(User user) throws Exception {
		Message message = new Message();
		int record = userMapper.updateByPrimaryKeySelective(user);
		if (record > 0) {
			message.setSuccess(true);
		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.xc.service.IUserService#queryPassword(java.lang.String)
	 */
	/**
	 * 找回密码
	 */
	public Message queryPassword(String username) throws Exception {
		Message message = new Message();
		Map<String, String> result = new HashMap<>();
		// 查询密码
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUsernameEqualTo(username);
		List<User> userList = userMapper.selectByExample(userExample);
		if (!ObjectUtil.collectionIsEmpty(userList)) {
			User user = new User();
			user = userList.get(0);
			// 保存密码至map
			result.put("password", user.getPassword());
			message.setResult(result);
			message.setMessage("密码已经找到");
			message.setSuccess(true);
		} else {
			message.setMessage("账号不存在");
			message.setSuccess(false);
		}

		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pb.xc.service.IUserService#queryUser(com.pb.xc.controller.vo.ResultVo
	 * )
	 */
	/**
	 * 查询所有用户
	 */
	public ResultVo queryUser(ResultVo param) throws Exception {
		ResultVo resultVo = new ResultVo();
		List<UserVo> userVos = new ArrayList<UserVo>();

		String pagesize = param.getPageSize();
		String currentpage = param.getCurrentpage();

		int pageNum = 1;
		int psize = 10;

		if (StringContentUtil.isNoEmpty(pagesize)) {
			psize = ObjectUtil.convToInteger(pagesize);
		}

		if (StringContentUtil.isNoEmpty(currentpage)) {
			pageNum = ObjectUtil.convToInteger(currentpage);
		}
		this.setPageInfo(psize, pageNum);

		// 查找按条件,消费金额倒序
		UserExample userExample = new UserExample();
		userExample.setOrderByClause("money desc");
		Criteria cr = userExample.createCriteria();
		cr.andTypeEqualTo(1);
		List<User> userList = new ArrayList<>();
		// 搜索订单；0：全查，1：按姓名查，2：按电话查
		switch (param.getQueryType()) {
		case 0:

			userList = userMapper.selectByExample(userExample);
			break;

		case 1:
			cr.andNameLike(param.getQueryText());
			userList = userMapper.selectByExample(userExample);
			break;

		case 2:
			cr.andTelLike(param.getQueryText());
			userList = userMapper.selectByExample(userExample);
			break;

		default:
			userList = userMapper.selectByExample(userExample);
			break;
		}

		if (!ObjectUtil.collectionIsEmpty(userList)) {
			for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				UserVo userVo = new UserVo();
				BeanUtils.copyProperties(userVo, user);
				Date createTime = user.getRegister();
				userVo.setStrRegister(DateUtil.getDateStr(DateUtil.DATE_STYLE4,
						createTime));
				userVos.add(userVo);
			}
		}

		this.setReturnPageInfo(psize, pageNum, userList, resultVo);
		resultVo.setRows(userVos);
		return resultVo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pb.xc.service.IUserService#queryAdd(com.pb.xc.controller.vo.AddVo)
	 */
	/**
	 * 查询访问人数，交易额度
	 */
	public Message queryAdd() throws Exception {
		Message message = new Message();
		Add add = new Add();
		AddExample addExample = new AddExample();
		List<Add> addList = addMapper.selectByExample(addExample);
		if (ObjectUtil.collectionIsEmpty(addList)) {
			add.setMoney(0.0);
			add.setTime(1);
			addMapper.insert(add);
		} else {
			add = addList.get(0);
			add.setTime(add.getTime() + 1);// 访问人数+1
			addMapper.updateByPrimaryKey(add);
		}
		message.setSuccess(true);
		Map<String,Add> result = new HashMap<>();
		result.put("add", add);
		message.setResult(result);

		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pb.xc.service.IUserService#queryLog(com.pb.xc.controller.vo.ResultVo)
	 */
	/**
	 * 查询登录日志
	 */
	public ResultVo queryLog(ResultVo param) throws Exception {
		ResultVo resultVo = new ResultVo();
		List<LOGVo> logVos = new ArrayList<LOGVo>();

		String pagesize = param.getPageSize();
		String currentpage = param.getCurrentpage();

		int pageNum = 1;
		int psize = 10;

		if (StringContentUtil.isNoEmpty(pagesize)) {
			psize = ObjectUtil.convToInteger(pagesize);
		}

		if (StringContentUtil.isNoEmpty(currentpage)) {
			pageNum = ObjectUtil.convToInteger(currentpage);
		}
		this.setPageInfo(psize, pageNum);

		// 按id倒序查找
		LOGExample logExample = new LOGExample();
		logExample.setOrderByClause("id desc");
		List<LOG> logList = logMapper.selectByExample(logExample);

		if (!ObjectUtil.collectionIsEmpty(logList)) {
			for (Iterator iterator = logList.iterator(); iterator.hasNext();) {
				LOG log = (LOG) iterator.next();
				LOGVo logVo = new LOGVo();
				BeanUtils.copyProperties(logVo, log);
				Date time = log.getTime();
				logVo.setStrTime(DateUtil
						.getDateStr(DateUtil.DATE_STYLE5, time));
				logVos.add(logVo);
			}
		}

		this.setReturnPageInfo(psize, pageNum, logList, resultVo);
		resultVo.setRows(logVos);
		return resultVo;

	}

	/*
	 * (non-Javadoc)
	 * @see com.pb.xc.service.IUserService#selectPassword(java.lang.String)
	 */
	/**
	 * 
	 */
	public Message selectPassword(String username) throws Exception {
		Message message = new Message();
		Map<String, String> result = new HashMap<>();
		//查找密码
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUsernameEqualTo(username);
		List<User> userList = userMapper.selectByExample(userExample);
		if(!ObjectUtil.collectionIsEmpty(userList)){
			User user = new User();
			user = userList.get(0);
			//设置密码返回，保存密码至result（Map）
			result.put("password", user.getPassword());
			message.setResult(result);
			message.setMessage("密码已经找到");
			message.setSuccess(true);
		}else{
			message.setMessage("账号不存在");
			message.setSuccess(false);
		}
		return message;
	}

	@Override
	public About query(About about) throws Exception {
		// TODO 查询关于我们
				// int id = abount.getId();
				AboutExample abountExample = new AboutExample();
				// abountExample.createCriteria().andIdEqualTo(id);
				List<About> abountList = aboutMapper
						.selectByExampleWithBLOBs(abountExample);
				if (!ObjectUtil.collectionIsEmpty(abountList)) {
					about = abountList.get(0);

				}

				return about;
	}

	@Override
	public Message update(About about) throws Exception {
		// TODO Auto-generated method stub
		// TODO 更新关于我们
				Message message = new Message();

				int record = aboutMapper.updateByPrimaryKeyWithBLOBs(about);

				if (record > 0) {
					message.setSuccess(true);
				}
				return message;
	}

	@Override
	public Message addNews(News news) throws Exception {
		// TODO Auto-generated method stub
		Message message = new Message();
		Date createTime = DateUtil.getCurrentDate(DateUtil.DATE_STYLE4);
		news.setTime(createTime);
		int record = newsMapper.insertSelective(news);
		if (record > 0) {
			message.setSuccess(true);
			}
		return message;
	}

	@Override
	public News showNewsData(News news) throws Exception {
		// TODO Auto-generated method stub
		int id = news.getId();
		NewsExample newsExample = new NewsExample();
		newsExample.createCriteria().andIdEqualTo(id);
		List<News> newsList = newsMapper
				.selectByExampleWithBLOBs(newsExample);
		if (!ObjectUtil.collectionIsEmpty(newsList)) {
			news = newsList.get(0);
		}
		return news;
	}

	@Override
	public Message updateNews(News news) throws Exception {
		// TODO Auto-generated method stub
		Message message = new Message();
		int record = newsMapper.updateByPrimaryKeyWithBLOBs(news);
		if (record > 0) {
			message.setSuccess(true);
			}

		return message;
	}

	@Override
	public Message deleteNews(News newsVo) throws Exception {
		// TODO Auto-generated method stub
		Message message = new Message();
		int id = newsVo.getId();

		int record = newsMapper.deleteByPrimaryKey(id);
		if (record > 0) {
			message.setSuccess(true);
		}
		return message;
	}

	@Override
	public ResultVo selectNews(ResultVo param) throws Exception {
		// TODO Auto-generated method stub
		ResultVo resultVo = new ResultVo();
		List<NewsVo> newsVos = new ArrayList<NewsVo>();

		String pagesize = param.getPageSize();
		String currentpage = param.getCurrentpage();

		int pageNum = 1;
		int psize = 10;

		if (StringContentUtil.isNoEmpty(pagesize)) {
			psize = ObjectUtil.convToInteger(pagesize);
		}

		if (StringContentUtil.isNoEmpty(currentpage)) {
			pageNum = ObjectUtil.convToInteger(currentpage);
		}
		this.setPageInfo(psize, pageNum);
		
		NewsExample newsExample = new NewsExample();
		newsExample.setOrderByClause("id desc");
		com.pb.xc.entity.NewsExample.Criteria cr = newsExample.createCriteria();
		
		//查询不同种类
		Integer queryType = param.getQueryType();
		if(queryType == null){
			queryType = 0;
		}
		
		if (queryType == 1) {
		} else if (queryType == 2) {
			cr.andTitleLike(param.getQueryText());
		}
		
		List<News> newsList = newsMapper.selectByExample(newsExample);

		if (!ObjectUtil.collectionIsEmpty(newsList)) {

			for (Iterator iterator = newsList.iterator(); iterator.hasNext();) {
				News news= (News) iterator.next();
				NewsVo temp = new NewsVo();
				BeanUtils.copyProperties(temp, news);
				Date Time = news.getTime();
				temp.setStrTime(DateUtil.getDateStr(DateUtil.DATE_STYLE4,
						Time));
				newsVos.add(temp);
			}

		}

		this.setReturnPageInfo(psize, pageNum, newsList, resultVo);
		resultVo.setRows(newsVos);
		return resultVo;
	}

	@Override
	public News queryById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		News news = new News();
		if (id != null) {
			news = newsMapper.selectByPrimaryKey(id);
		}
		return news;
	}

	

}
