package com.pb.xc.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pb.xc.controller.vo.BuyVo;
import com.pb.xc.controller.vo.Message;
import com.pb.xc.controller.vo.OrderVo;
import com.pb.xc.controller.vo.ResultVo;
import com.pb.xc.controller.vo.UserVo;
import com.pb.xc.service.IOrderService;

@RestController
@RequestMapping("/ordermanageC")
@Scope("prototype")
public class OrderManageController {
	@Resource(name = "orderService")
	private IOrderService orderService;

	/**
	 * 查询未处理订单
	 * 
	 */
	@RequestMapping(value = "/queryWaitOrder", method = RequestMethod.POST)
	public ResultVo queryWaitOrder(@RequestBody ResultVo param)
			throws Exception {
		return orderService.queryWaitOrder(param);
	}

	/**
	 * 取消订单
	 * 
	 */
	@RequestMapping(value = "/deleteOrder", method = RequestMethod.PUT)
	public Message deleteOrder(@RequestBody BuyVo buyVo) throws Exception {
		return orderService.deleteOrder(buyVo);
	}

	/**
	 * 取消订单
	 * 
	 */
	@RequestMapping(value = "/sureOrder", method = RequestMethod.PUT)
	public Message sureOrder(@RequestBody BuyVo buyVo) throws Exception {
		return orderService.sureOrder(buyVo);
	}

	/**
	 * 查看未处理订单详情
	 * 
	 */
	@RequestMapping(value = "/queryOrderData", method = RequestMethod.POST)
	public Message queryOrderData(@RequestBody BuyVo buyVo) throws Exception {
		return orderService.queryOrderData(buyVo);
	}

	/**
	 * 查询全部订单
	 * 
	 */
	@RequestMapping(value = "/queryAllOrder", method = RequestMethod.POST)
	public ResultVo queryAllOrder(@RequestBody ResultVo param) throws Exception {
		return orderService.queryAllOrder(param);
	}
	
	/**
	 * 查看已经处理订单详情
	 * 
	 */
	@RequestMapping(value = "/queryAllOrderData", method = RequestMethod.POST)
	public Message queryAllOrderData(@RequestBody BuyVo buyVo) throws Exception {
		return orderService.queryAllOrderData(buyVo);
	}

}
