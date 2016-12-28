package com.pb.xc.service;

import org.springframework.web.multipart.MultipartFile;

import com.pb.xc.controller.vo.GoodsVo;
import com.pb.xc.controller.vo.Message;
import com.pb.xc.controller.vo.ResultVo;
import com.pb.xc.entity.Goods;

public interface IGoodsService {
	/**
	 * 保存临时文件
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public Message saveTempFiles(MultipartFile[] file) throws Exception;
	
	/**
	 * 添加商品
	 * @param goods
	 * @return
	 * @throws Exception
	 * pb 
	 * 16/09/08
	 */
	public Message insertGoods(Goods goods) throws Exception; 
	
	/**
	 * 更新商品
	 * @param goods
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/08
	 */
	public Message updateGoods(Goods goods) throws Exception;
	
	/**
	 * 删除商品
	 * @param goods
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/08
	 */
	public Message deleteGoods(Goods goods) throws Exception;
	
	/**
	 * 查询商品
	 * @param goods
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/08
	 */
	public ResultVo queryGoods(ResultVo param) throws Exception;
	
	/**
	 * 查询商品
	 * @param goods
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/08
	 */
	public ResultVo queryIndexGoods(ResultVo param) throws Exception;
	
	
	/**
	 * 查询商品具体信息
	 * @param goods
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/08
	 */
	public Goods queryGoodsData(Goods goods) throws Exception;
	
	/**
	 * 修改上下架
	 * @param goods
	 * @return
	 * @throws Exception
	 * pb
	 * 16/09/08
	 */
	public Message downGoods(GoodsVo goodsVo)throws Exception;
}
