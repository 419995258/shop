package com.pb.xc.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pb.xc.controller.vo.CardVo;
import com.pb.xc.controller.vo.GoodsVo;
import com.pb.xc.controller.vo.Message;
import com.pb.xc.controller.vo.OrderVo;
import com.pb.xc.controller.vo.ResultVo;
import com.pb.xc.dao.AddMapper;
import com.pb.xc.dao.BuyMapper;
import com.pb.xc.dao.CardMapper;
import com.pb.xc.dao.GoodsMapper;
import com.pb.xc.dao.OrderMapper;
import com.pb.xc.entity.Add;
import com.pb.xc.entity.AddExample;
import com.pb.xc.entity.Buy;
import com.pb.xc.entity.Card;
import com.pb.xc.entity.CardExample;
import com.pb.xc.entity.Goods;
import com.pb.xc.entity.Order;
import com.pb.xc.service.ICardService;
import com.pb.xc.util.DateUtil;
import com.pb.xc.util.FengYeBasic;
import com.pb.xc.util.ObjectUtil;
import com.pb.xc.util.StringContentUtil;
import com.sun.mail.handlers.message_rfc822;

@Repository("cardService")
public class CardServiceImpl extends FengYeBasic implements ICardService {

	@Autowired
	private CardMapper cardMapper;

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private BuyMapper buyMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private AddMapper addMapper;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.xc.service.ICardService#insertCard(com.pb.xc.entity.Goods)
	 */
	/**
	 * 添加商品至购物车
	 */
	public Message insertCard(GoodsVo goodsVo) throws Exception {
		Message message = new Message();
		// 创建购物车对象
		Card card = new Card();
		// 用户id
		int userId = goodsVo.getUserId();
		// 商品id
		int goodsId = goodsVo.getId();
		// 判断购物车是否已经存在此商品
		// 查询
		CardExample cardExample = new CardExample();
		// 查询用户id与商品id相关联的商品
		cardExample.createCriteria().andUserIdEqualTo(userId)
				.andGoodsIdEqualTo(goodsId);
		List<Card> cardList = cardMapper.selectByExample(cardExample);
		if (!ObjectUtil.collectionIsEmpty(cardList)) {
			// 如果不为空
			card = cardList.get(0);
			// 已经加入购物车的数量
			int cardnumber = card.getCardnumber();
			// 数量+1
			card.setCardnumber(cardnumber + 1);
			int record = cardMapper.updateByPrimaryKeySelective(card);
			if (record > 0) {
				message.setSuccess(true);
			}
		} else {
			// 添加商品到购物车
			card.setUserId(userId);// 购物车用户id
			card.setGoodsId(goodsId);// 购物车商品id
			card.setGoodsName(goodsVo.getName());
			card.setGoodsPrice(goodsVo.getPrice());
			card.setCardnumber(1);
			card.setState(1);
			card.setGoodsUrl(goodsVo.getUrl());
			int record = cardMapper.insertSelective(card);
			if (record > 0) {
				message.setSuccess(true);
			}
		}
//
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pb.xc.service.ICardService#queryCard(com.pb.xc.controller.vo.ResultVo
	 * )
	 */
	/**
	 * 查询所有购物车信息
	 */
	public ResultVo queryCard(ResultVo param) throws Exception {
		ResultVo resultVo = new ResultVo();
		List<CardVo> cardVos = new ArrayList<CardVo>();

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

		// 查询购物车信息
		CardExample cardExample = new CardExample();
		List<Card> cardList = cardMapper.selectByExample(cardExample);
		if (!ObjectUtil.collectionIsEmpty(cardList)) {
			
			for (Iterator iterator = cardList.iterator(); iterator.hasNext();) {
				Card card = (Card) iterator.next();
				CardVo cardVo = new CardVo();
				BeanUtils.copyProperties(cardVo, card);
				cardVos.add(cardVo);
			}
		}

		this.setReturnPageInfo(psize, pageNum, cardList, resultVo);
		resultVo.setRows(cardVos);

		return resultVo;
	}

	/*
	 * (non-Javadoc)
	 * @see com.pb.xc.service.ICardService#updateCard(com.pb.xc.entity.Card)
	 */
	/**
	 * 更新购物车数量
	 */
	public Message updateCard(Card card) throws Exception {
		Message message = new Message();
		int record = cardMapper.updateByPrimaryKeySelective(card);
		if(record > 0){
			message.setSuccess(true);
		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * @see com.pb.xc.service.ICardService#deleteCard(com.pb.xc.entity.Card)
	 */
	/**
	 * 删除购物车商品
	 */
	public Message deleteCard(Card card) throws Exception {
		Message message = new Message();
		int record = cardMapper.deleteByPrimaryKey(card.getId());
		if(record > 0){
			message.setSuccess(true);
		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * @see com.pb.xc.service.ICardService#insertOrder(java.util.List)
	 */
	/**
	 * 立即下单
	 */
	public Message insertOrder(OrderVo orderVo) throws Exception {
		Message message = new Message();
		//获取购物车集合
		List<Card> cardList = orderVo.getCardList();
		//购买总价格
		double money = 0;
		Buy buy = new Buy();
		//添加订单	
		for (Iterator iterator = cardList.iterator(); iterator.hasNext();) {
			Card card = (Card) iterator.next();
			//商品数
			int number = card.getCardnumber();
			//购买总价格增加
			money =money +  card.getCardnumber() * card.getGoodsPrice();
		}
		//获取用户id
		int userId = orderVo.getUserId();
		buy.setUserId(userId);
		//订单状态
		buy.setState(1);
		//订单生成时间
		buy.setTime(DateUtil.getCurrentDate(DateUtil.DATE_STYLE5));
		//订单金额
		buy.setMoney(money);
		//订单备注
		buy.setNote(orderVo.getNote());
		buyMapper.insertSelective(buy);
		
		//TODO 添加商品
		//循环添加订单
		for (Iterator iterator = cardList.iterator(); iterator.hasNext();) {
			Card card = (Card) iterator.next();
			Order order = new Order();
			//商品 id
			int goodsId = card.getGoodsId();
			//商品数
			int number = card.getCardnumber();
			order.setBuyId(buy.getId());
			order.setGoodsId(goodsId);
			order.setNumber(number);
			order.setState(1);
			order.setTime(DateUtil.getCurrentDate(DateUtil.DATE_STYLE5));
			orderMapper.insertSelective(order);
			//添加商品订单次数
			//1.查询商品
			Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
			//2.增加订单数
			goods.setNum(goods.getNum() + number);
			//3.更新
			goodsMapper.updateByPrimaryKeySelective(goods);
		}
		//删除购物车数据
		CardExample cardExample = new CardExample();
		cardExample.createCriteria().andUserIdEqualTo(userId);
		cardMapper.deleteByExample(cardExample);
		return message;
	}
}
