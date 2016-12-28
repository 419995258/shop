package com.pb.xc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pb.xc.controller.vo.Message;
import com.pb.xc.controller.vo.ResultVo;
import com.pb.xc.entity.Goods;
import com.pb.xc.service.IGoodsService;

@RestController
@RequestMapping("/goodsC")
@Scope("prototype")
public class GoodsController {
	@Autowired
	private IGoodsService goodsService;
	
	
	/**
	 * 管理员查询所有的商品
	 * 
	 */
	@RequestMapping(value="/showGoods",method = RequestMethod.POST)
	public ResultVo showGoods(@RequestBody ResultVo param) throws Exception{
		return goodsService.queryGoods(param);
	}
	
	/**
	 * 首页查询所有的商品
	 * 
	 */
	@RequestMapping(value="/showIndexGoods",method = RequestMethod.POST)
	public ResultVo showIndexGoods(@RequestBody ResultVo param) throws Exception{
		return goodsService.queryIndexGoods(param);
	}
	
	
}
