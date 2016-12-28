package com.pb.xc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.util.Hash;
import com.pb.xc.controller.vo.BuyVo;
import com.pb.xc.controller.vo.GoodsVo;
import com.pb.xc.controller.vo.Message;
import com.pb.xc.controller.vo.OrderVo;
import com.pb.xc.controller.vo.ResultVo;
import com.pb.xc.controller.vo.UserVo;
import com.pb.xc.dao.BuyMapper;
import com.pb.xc.dao.GoodsMapper;
import com.pb.xc.dao.OrderMapper;
import com.pb.xc.dao.UserMapper;
import com.pb.xc.entity.Buy;
import com.pb.xc.entity.Order;
import com.pb.xc.entity.OrderExample;
import com.pb.xc.entity.User;
import com.pb.xc.entity.UserExample;
import com.pb.xc.entity.UserExample.Criteria;
import com.pb.xc.service.IOrderService;
import com.pb.xc.util.DateUtil;
import com.pb.xc.util.FengYeBasic;
import com.pb.xc.util.ObjectUtil;
import com.pb.xc.util.StringContentUtil;

@Service("orderService")
public class OrderServiceImpl extends FengYeBasic implements IOrderService {
	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private BuyMapper buyMapper;
	
	@Autowired
	private UserMapper userMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pb.xc.service.IOrderService#queryAllOrder(com.pb.xc.controller.vo
	 * .ResultVo)
	 */
	/**
	 * 查询所有已完成订单信息
	 */
	public ResultVo queryAllOrder(ResultVo param) throws Exception {
		ResultVo resultVo = new ResultVo();
		List<UserVo> vos = new ArrayList<>();

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

		// 查询已处理订单

		List<User> buyVoList = new ArrayList<>();
		UserExample userExample = new UserExample();
		userExample.setOrderByClause("money desc");
		Criteria cr = userExample.createCriteria();
		cr.andMoneyGreaterThan(0);
		// 搜索订单；0：全查，1：按姓名查，2：按电话查
		switch (param.getQueryType()) {
		case 0:
			
			buyVoList = userMapper.selectByExample(userExample);
			break;

		case 1:
			cr.andNameLike(param.getQueryText());
			buyVoList = userMapper.selectByExample(userExample);
			break;

		case 2:
			cr.andTelLike(param.getQueryText());
			buyVoList = userMapper.selectByExample(userExample);
			break;

		default:
			buyVoList = userMapper.selectByExample(userExample);
			break;
		}

		// 修改时间为string类型
		if (!ObjectUtil.collectionIsEmpty(buyVoList)) {
			for (Iterator iterator = buyVoList.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				UserVo userVo = new UserVo();
				Date time = user.getRegister();
				BeanUtils.copyProperties(userVo, user);
				userVo.setStrRegister(DateUtil
						.getDateStr(DateUtil.DATE_STYLE5, time));
				vos.add(userVo);
			}
		}

		this.setReturnPageInfo(psize, pageNum, buyVoList, resultVo);
		resultVo.setRows(vos);
		return resultVo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pb.xc.service.IOrderService#queryWaitOrder(com.pb.xc.controller.vo
	 * .ResultVo)
	 */
	/**
	 * 查询所有等待处理订单信息
	 */
	public ResultVo queryWaitOrder(ResultVo param) throws Exception {
		ResultVo resultVo = new ResultVo();
		List<BuyVo> vos = new ArrayList<>();

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

		// 查询未处理订单

		List<BuyVo> buyVoList = new ArrayList<>();
		// 搜索订单；0：全查，1：按姓名查，2：按电话查
		switch (param.getQueryType()) {
		case 0:
			buyVoList = buyMapper.queryWaitOrder();
			break;

		case 1:
			buyVoList = buyMapper.queryWaitOrderByName(param.getQueryText());
			break;

		case 2:
			buyVoList = buyMapper.queryWaitOrderByTel(param.getQueryText());
			break;

		default:
			buyVoList = buyMapper.queryWaitOrder();
			break;
		}

		// 修改时间为string类型
		if (!ObjectUtil.collectionIsEmpty(buyVoList)) {
			for (Iterator iterator = buyVoList.iterator(); iterator.hasNext();) {
				BuyVo buyVo = (BuyVo) iterator.next();
				Date time = buyVo.getTime();
				buyVo.setStrTime(DateUtil
						.getDateStr(DateUtil.DATE_STYLE5, time));
				vos.add(buyVo);
			}
		}

		this.setReturnPageInfo(psize, pageNum, buyVoList, resultVo);
		resultVo.setRows(vos);
		return resultVo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pb.xc.service.IOrderService#deleteOrder(com.pb.xc.controller.vo.OrderVo
	 * )
	 */
	/**
	 * 取消订单
	 */
	public Message deleteOrder(BuyVo buyVo) throws Exception {
		Message message = new Message();
		Buy buy = new Buy();

		buy.setId(buyVo.getId());
		buy.setState(0);// 更新订单为2，完成订单
		int record = buyMapper.updateByPrimaryKeySelective(buy);
		if (record > 0) {
			OrderExample orderExample = new OrderExample();
			orderExample.createCriteria().andUserIdEqualTo(buyVo.getUserId())
					.andStateEqualTo(1);
			List<Order> orderList = orderMapper.selectByExample(orderExample);
			if (!ObjectUtil.collectionIsEmpty(orderList)) {
				for (Iterator iterator = orderList.iterator(); iterator
						.hasNext();) {
					Order order = (Order) iterator.next();
					order.setState(0);
					orderMapper.updateByPrimaryKeySelective(order);

				}
			}
			message.setSuccess(true);

		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pb.xc.service.IOrderService#sureOrder(com.pb.xc.controller.vo.OrderVo
	 * )
	 */
	/**
	 * 完成订单
	 */
	public Message sureOrder(BuyVo buyVo) throws Exception {
		Message message = new Message();
		Buy buy = new Buy();
		// BeanUtils.copyProperties(order, orderVo);
		buy.setId(buyVo.getId());
		buy.setState(2);// 更新订单为2，完成订单
		int record = buyMapper.updateByPrimaryKeySelective(buy);
		if (record > 0) {
			OrderExample orderExample = new OrderExample();
			orderExample.createCriteria().andUserIdEqualTo(buyVo.getUserId())
					.andStateEqualTo(1);
			List<Order> orderList = orderMapper.selectByExample(orderExample);
			if (!ObjectUtil.collectionIsEmpty(orderList)) {
				for (Iterator iterator = orderList.iterator(); iterator
						.hasNext();) {
					Order order = (Order) iterator.next();
					order.setState(2);
					orderMapper.updateByPrimaryKeySelective(order);

				}
			}
			message.setSuccess(true);
		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pb.xc.service.IOrderService#queryOrderData(com.pb.xc.controller.vo
	 * .OrderVo)
	 */
	/**
	 * 未处理订单商品详情
	 */
	public Message queryOrderData(BuyVo buyVo) throws Exception {
		Message message = new Message();
		List<OrderVo> orderVoList = new ArrayList<>();
		orderVoList = orderMapper.queryWaitOrderData(buyVo.getUserId());
		message.setSuccess(true);
		Map<String, Object> map = new HashMap<>();
		map.put("data", orderVoList);
		message.setResult(map);
		return message;
	}
	
	/**
	 * 已经处理订单商品详情
	 */
	public Message queryAllOrderData(UserVo userVo) throws Exception {
		Message message = new Message();
		List<OrderVo> orderVoList = new ArrayList<>();
		orderVoList = orderMapper.queryAllOrderData(userVo.getId());
		if(!ObjectUtil.collectionIsEmpty(orderVoList)){
			for (Iterator iterator = orderVoList.iterator(); iterator.hasNext();) {
				OrderVo orderVo = (OrderVo) iterator.next();
				orderVo.setStrTime(DateUtil.getDateStr(DateUtil.DATE_STYLE5,orderVo.getTime()));
			}
		}
		message.setSuccess(true);
		Map<String, Object> map = new HashMap<>();
		map.put("data", orderVoList);
		message.setResult(map);
		return message;
	}

	/*
	 * (non-Javadoc)
	 * @see com.pb.xc.service.IOrderService#queryAllGoodsBuyByUserId(com.pb.xc.controller.vo.ResultVo)
	 */
	/**
	 * 查询所有用户购买商品信息
	 */
	public ResultVo queryAllGoodsBuyByUserId(ResultVo param) throws Exception {
		ResultVo resultVo = new ResultVo();
		List<OrderVo> Vos = new ArrayList<>();

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
		
		
		List<OrderVo> orderVos = orderMapper.queryAllGoodsBuyByUserId(param.getUserId());
		if(!ObjectUtil.collectionIsEmpty(orderVos)){
			for (Iterator iterator = orderVos.iterator(); iterator.hasNext();) {
				OrderVo orderVo = (OrderVo) iterator.next();
				orderVo.setStrTime(DateUtil.getDateStr(DateUtil.DATE_STYLE5,orderVo.getTime()));
			}
		}
		
		this.setReturnPageInfo(psize, pageNum, orderVos, resultVo);
		resultVo.setRows(orderVos);
		return resultVo;
	}

}
