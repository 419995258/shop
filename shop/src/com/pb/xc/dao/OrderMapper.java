package com.pb.xc.dao;

import com.pb.xc.controller.vo.OrderVo;
import com.pb.xc.entity.Order;
import com.pb.xc.entity.OrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
	int deleteByExample(OrderExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Order record);

	int insertSelective(Order record);

	List<Order> selectByExample(OrderExample example);

	Order selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Order record,
			@Param("example") OrderExample example);

	int updateByExample(@Param("record") Order record,
			@Param("example") OrderExample example);

	int updateByPrimaryKeySelective(Order record);

	int updateByPrimaryKey(Order record);

	/**
	 * 查询未处理订单
	 * 
	 * @return
	 */
	List<OrderVo> queryWaitOrder();

	/**
	 * 查询未处理订单By name
	 * 
	 * @return
	 */
	List<OrderVo> queryWaitOrderByName(String name);

	/**
	 * 查询未处理订单By tel
	 * 
	 * @return
	 */
	List<OrderVo> queryWaitOrderByTel(String tel);

	/**
	 * 查询全部订单
	 * 
	 * @return
	 */
	List<OrderVo> queryAllOrder();

	/**
	 * 查询订单详情
	 * 
	 * @param userId
	 * @return
	 */
	List<OrderVo> queryWaitOrderData(int userId);

	/**
	 * 查询已经处理订单详情
	 * 
	 * @param userId
	 * @return
	 */
	List<OrderVo> queryAllOrderData(int userId);

	/**
	 * 查询用户所有购买的商品
	 * @param userId
	 * @return
	 * @author PB
	 */
	List<OrderVo> queryAllGoodsBuyByUserId(int userId);
}