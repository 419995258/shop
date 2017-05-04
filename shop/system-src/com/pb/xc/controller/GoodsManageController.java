package com.pb.xc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pb.xc.controller.vo.GoodsVo;
import com.pb.xc.controller.vo.Message;
import com.pb.xc.entity.Goods;
import com.pb.xc.service.IGoodsService;

@RestController
@RequestMapping("/goodsmanageC")
@Scope("prototype")
public class GoodsManageController {
	@Resource(name = "goodsService")
	private IGoodsService goodsService;
	
	/**
	 * 上传图片临时保存文件
	 * 
	 * @param file
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadTemp", method = RequestMethod.POST)
	public Map<String, Object> uploadTemp(@RequestParam("imgs") MultipartFile[] file) throws Exception {
		Map dataMap = new HashMap();
		// Integer orgId = (Integer) session.getAttribute("currentOrgId");
		Message m = goodsService.saveTempFiles(file);

		dataMap.put("success", m.getSuccess());
		if (m.getSuccess()) {
			dataMap.put("fileId", m.getResult().get("fileId"));
			dataMap.put("fileUrl", m.getResult().get("fileUrl"));
		}
		return dataMap;
	}
	
	/**
	 * 添加商品
	 */
	@RequestMapping(value="/addGoods",method = RequestMethod.PUT)
	public Message addGoods(@RequestBody Goods goods) throws Exception{
		return goodsService.insertGoods(goods);
	}
	
	/**
	 * 删除商品
	 */
	@RequestMapping(value="/deleteGoods",method = RequestMethod.PUT)
	public Message deleteGoods(@RequestBody Goods goods) throws Exception{
		return goodsService.deleteGoods(goods);
	}

	/**
	 * 查询商品详细信息
	 * 
	 */
	@RequestMapping(value="/queryGoodsData",method = RequestMethod.POST)
	public Goods queryGoodsData(@RequestBody Goods goods) throws Exception{
		return goodsService.queryGoodsData(goods);
	}
	
	/**
	 * 修改商品信息
	 */
	@RequestMapping(value="/updateGoods",method = RequestMethod.PUT)
	public Message updateGoods(@RequestBody Goods goods) throws Exception{
		if(null == goods.getUrl() || goods.getUrl().equals("")){
			Goods goods2 = goodsService.queryGoodsData(goods);
			goods.setUrl(goods2.getUrl());
		}
		return goodsService.updateGoods(goods);
	}
	
	/**
	 * 下架商品
	 */
	@RequestMapping(value="/downGoods",method = RequestMethod.PUT)
	public Message downGoods(@RequestBody GoodsVo goodsVo) throws Exception{
		return goodsService.downGoods(goodsVo);
	}
}
