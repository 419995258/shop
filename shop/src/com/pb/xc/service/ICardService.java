package com.pb.xc.service;

import java.util.List;


import com.pb.xc.controller.vo.GoodsVo;
import com.pb.xc.controller.vo.Message;
import com.pb.xc.controller.vo.OrderVo;
import com.pb.xc.controller.vo.ResultVo;
import com.pb.xc.entity.Card;
import com.pb.xc.entity.Goods;
import com.pb.xc.entity.User;

public interface ICardService {
	
	/**
	 * 添加商品至购物车
	 * @param goods
	 * @return
	 * @throws Exception
	 * @author PB
	 * @serialData 16/09/12
	 */
	public Message insertCard(GoodsVo goodsVo) throws Exception;
	
	/**
	 * 查询所有购物车信息
	 * @param param
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/12
	 * @author PB
	 */
	public ResultVo queryCard(ResultVo param) throws Exception;
	
	
	/**
	 * 更新购物车数量
	 * @param card
	 * @return
	 * @throws Exception
	 * @author PB 16/09/12
	 */
	public Message updateCard(Card card) throws Exception;
	
	/**
	 * 删除购物车商品
	 * @param card
	 * @return
	 * @throws Exception
	 * @author PB 16/09/12
	 */
	public Message deleteCard(Card card)throws Exception;
	
	/**
	 * 立即下单
	 * @param cardList
	 * @return
	 * @throws Exception
	 * @author PB 16/09/12
	 */
	public Message insertOrder(OrderVo orderVo)throws Exception;
	
}
