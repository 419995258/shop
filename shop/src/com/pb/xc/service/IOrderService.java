package com.pb.xc.service;

import java.util.List;

import com.pb.xc.controller.vo.BuyVo;
import com.pb.xc.controller.vo.Message;
import com.pb.xc.controller.vo.OrderVo;
import com.pb.xc.controller.vo.ResultVo;
import com.pb.xc.controller.vo.UserVo;
import com.pb.xc.entity.Goods;

public interface IOrderService {
	/**
	 * 查询所有订单
	 * @param order
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/08
	 */
	public ResultVo queryAllOrder(ResultVo param) throws Exception;
	
	/**
	 * 查询未处理订单
	 * @param order
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/08
	 */
	public ResultVo queryWaitOrder(ResultVo param) throws Exception;
	
	/**
	 * 取消订单
	 * @param orderVo
	 * @throws Exception
	 * pb
	 * 16/09/10
	 */
	public Message deleteOrder (BuyVo buyVo) throws Exception;
	
	/**
	 * 完成订单
	 * @param orderVo
	 * @throws Exception
	 * pb
	 * 16/09/10
	 */
	public Message sureOrder (BuyVo buyVo) throws Exception;
	
	/**
	 * 查询未处理订单具体信息
	 * @param orderVo
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/10
	 */
	public Message queryOrderData(BuyVo buyVo) throws Exception;
	
	/**
	 * 查询已处理处理订单具体信息
	 * @param userVo
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/11
	 */
	public Message queryAllOrderData(BuyVo buyVo) throws Exception;
	
	
	/**
	 * 查询所有购买商品信息
	 * @param param
	 * @return
	 * @throws Exception
	 * @author PB
	 * 16/09/12
	 */
	public ResultVo queryAllGoodsBuyByUserId(ResultVo param) throws Exception;
}
