package com.pb.xc.dao;

import com.pb.xc.controller.vo.OrderVo;
import com.pb.xc.entity.Order;
import com.pb.xc.entity.OrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrderMapperExt {
	

	/**
	 * 查询订单详情
	 * 
	 * @param userId
	 * @return
	 */
	List<OrderVo> queryWaitOrderData(int buyId);

	/**
	 * 查询已经处理订单详情
	 * 
	 * @param userId
	 * @return
	 */
	List<OrderVo> queryAllOrderData(int buyId);

	/**
	 * 查询用户所有购买的商品
	 * @param userId
	 * @return
	 * @author PB
	 */
	List<OrderVo> queryAllGoodsBuyByUserId(int userId);
}