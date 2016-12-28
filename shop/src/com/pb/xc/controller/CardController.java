package com.pb.xc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pb.xc.controller.vo.GoodsVo;
import com.pb.xc.controller.vo.Message;
import com.pb.xc.controller.vo.OrderVo;
import com.pb.xc.controller.vo.ResultVo;
import com.pb.xc.entity.Card;
import com.pb.xc.entity.User;
import com.pb.xc.service.ICardService;
import com.pb.xc.service.IOrderService;
import com.pb.xc.util.Const;

@RestController
@RequestMapping("/cardC")
@Scope("prototype")
public class CardController extends BasicController {

	@Resource(name = "cardService")
	private ICardService cardService;
	
	@Resource(name = "orderService")
	private IOrderService orderService;

	/**
	 * 添加商品到购物车
	 * 
	 * @param goodsVo
	 * @param session
	 * @return
	 * @throws Exception
	 *             pb 16/09/12
	 */
	@RequestMapping(value = "/insertCard", method = RequestMethod.PUT)
	public Message insertCard(@RequestBody GoodsVo goodsVo, HttpSession session)
			throws Exception {
		Message message = new Message();
		// 获取session
		User user = (User) session.getAttribute(Const.SESSION_CUSTOMER_USER);
		int userId = user.getId();
		goodsVo.setUserId(userId);
		// 添加商品
		message = cardService.insertCard(goodsVo);
		return message;
	}

	/**
	 * 查询所有购物车信息
	 * 
	 */
	@RequestMapping(value = "/queryCard", method = RequestMethod.POST)
	public ResultVo queryCard(@RequestBody ResultVo param) throws Exception {
		return cardService.queryCard(param);
	}

	/**
	 * 更新商品
	 * 
	 * @param card
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCard", method = RequestMethod.PUT)
	public Message updateCard(@RequestBody Card card) throws Exception {
		Message message = new Message();
		// 如果商品数量小于1则删除
		if (card.getCardnumber() < 1) {
			message = cardService.deleteCard(card);
		} else {
			// 更新商品
			message = cardService.updateCard(card);
		}

		return message;
	}

	/**
	 * 删除商品
	 * 
	 * @param card
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteCard", method = RequestMethod.PUT)
	public Message deleteCard(@RequestBody Card card) throws Exception {
		return cardService.deleteCard(card);
	}

	/**
	 * 立即下单
	 * 
	 * @param cardList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertOrder", method = RequestMethod.PUT)
	public Message insertOrder(@RequestBody OrderVo orderVo, HttpSession session)
			throws Exception {
		// 获取session
		User user = (User) session.getAttribute(Const.SESSION_CUSTOMER_USER);
		int userId = user.getId();
		orderVo.setUserId(userId);
		return cardService.insertOrder(orderVo);
	}
	
	/**
	 * 查询所有购买到的商品信息
	 * 
	 */
	@RequestMapping(value = "/queryAllGoodsBuyByUserId", method = RequestMethod.POST)
	public ResultVo queryAllGoodsBuyByUserId(@RequestBody ResultVo param, HttpSession session) throws Exception {
		User user = (User) session.getAttribute(Const.SESSION_CUSTOMER_USER);
		int userId = user.getId();
		param.setUserId(userId);
		return orderService.queryAllGoodsBuyByUserId(param);
	}
	
}
