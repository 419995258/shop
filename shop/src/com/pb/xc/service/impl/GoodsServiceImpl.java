package com.pb.xc.service.impl;

import com.pb.xc.controller.vo.GoodsVo;
import com.pb.xc.controller.vo.Message;
import com.pb.xc.controller.vo.ResultVo;
import com.pb.xc.dao.GoodsMapper;
import com.pb.xc.entity.Goods;
import com.pb.xc.entity.GoodsExample;
import com.pb.xc.entity.GoodsExample.Criteria;
import com.pb.xc.service.IGoodsService;
import com.pb.xc.util.DateUtil;
import com.pb.xc.util.FengYeBasic;
import com.pb.xc.util.FileOperationUtil;
import com.pb.xc.util.ObjectUtil;
import com.pb.xc.util.StringContentUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("goodsService")
public class GoodsServiceImpl extends FengYeBasic implements IGoodsService {
	@Autowired
	private GoodsMapper goodsMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pb.xc.service.IGoodsService#saveTempFiles(org.springframework.web.
	 * multipart.MultipartFile[])
	 */
	public Message saveTempFiles(MultipartFile[] file) throws Exception {
		// TODO Auto-generated method stub
		Message message = new Message();
		Map<String, Object> fm = new HashMap<String, Object>();

		if (file != null && file.length > 0) {
			// Date createTime = DateUtil.getCurrentDate(DateUtil.DATE_STYLE5);

			for (int i = 0; i < file.length; i++) {
				MultipartFile f = file[i];
				String fileName = f.getOriginalFilename(); // 文件名
				String diskFileName = StringContentUtil.getUuid(); // 保存后的文件名
				CommonsMultipartFile cf = (CommonsMultipartFile) f;
				DiskFileItem fi = (DiskFileItem) cf.getFileItem();
				File f1 = fi.getStoreLocation();
				Message m = FileOperationUtil.saveFileToDisk(f1, fileName,
						diskFileName, "goods");

				/*
				 * 返回文件url地址
				 */

				if (m.getSuccess()) {
					try {
						fm.put("fileUrl", ".." + (String) m.getResult().get("filePath"));
						message.setSuccess(true);
						
						// 删除文件
						// FileOperationUtil.deleteFile2(path);
					} catch (Exception e) {
						Object o = m.getResult().get("savefile");

						File ef = (File) o;
						if (ef.exists()) {
							ef.delete();
						}
						throw e;
					}
				}
			}
		}
		message.setResult(fm);

		return message;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.xc.service.IGoodsService#insertGoods(com.pb.xc.entity.Goods)
	 */
	/**
	 * 添加商品
	 */
	public Message insertGoods(Goods goods) throws Exception {
		Message message = new Message();
		// 设置商品属性
		Date createTime = DateUtil.getCurrentDate(DateUtil.DATE_STYLE4);
		goods.setNum(0);
		goods.setState(2);
		goods.setCreatetime(createTime);
		int record = goodsMapper.insertSelective(goods);
		if (record > 0) {
			message.setSuccess(true);
			message.setMessage("添加成功");
		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.xc.service.IGoodsService#updateGoods(com.pb.xc.entity.Goods)
	 */
	/**
	 * 更新商品
	 */
	public Message updateGoods(Goods goods) throws Exception {
		Message message = new Message();
		// 添更新商品
		int record = goodsMapper.updateByPrimaryKey(goods);
		if (record > 0) {
			message.setSuccess(true);
			message.setMessage("更新成功");
		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.xc.service.IGoodsService#deleteGoods(com.pb.xc.entity.Goods)
	 */
	/**
	 * 删除商品
	 */
	public Message deleteGoods(Goods goods) throws Exception {
		Message message = new Message();
		// 修改商品状态为不可用
		goods.setState(0);
		int record = goodsMapper.updateByPrimaryKey(goods);
		if (record > 0) {
			// 删除文件
			//FileOperationUtil.deleteFile2(goods.getUrl());
			message.setSuccess(true);
			message.setMessage("更新成功");
		}
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.xc.service.IGoodsService#queryGoods(com.pb.xc.entity.Goods)
	 */
	/**
	 * 查询商品
	 */
	public ResultVo queryGoods(ResultVo param) throws Exception {
		ResultVo resultVo = new ResultVo();
		List<GoodsVo> goodsVos = new ArrayList<GoodsVo>();

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

		// 查询商品名
		GoodsExample goodsExample = new GoodsExample();
		//goodsExample.setOrderByClause("num desc");//按加入订单数量倒序排序
		goodsExample.setOrderByClause("id desc");
		Criteria cr = goodsExample.createCriteria();
		//cr.andStateEqualTo(1);
		cr.andStateNotEqualTo(0);
		if(param.getQueryType()!= 66){
			cr.andTopEqualTo(param.getQueryType());
		}
		cr.andNameLike(param.getQueryText());//查找输入内容
		

		List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
		//修改时间str
		if (!ObjectUtil.collectionIsEmpty(goodsList)) {
			for (Iterator iterator = goodsList.iterator(); iterator.hasNext();) {
				Goods goods = (Goods) iterator.next();
				GoodsVo goodsVo = new GoodsVo();
				BeanUtils.copyProperties(goodsVo, goods);
				Date createTime = goods.getCreatetime();
				goodsVo.setStrCreateTime(DateUtil.getDateStr(DateUtil.DATE_STYLE4, createTime));
				if(goods.getState().equals(1)){
					goodsVo.setDown(true);
				}else {
					goodsVo.setDown(false);
				}
				goodsVos.add(goodsVo);
			}
		}

		this.setReturnPageInfo(psize, pageNum, goodsList, resultVo);
		resultVo.setRows(goodsVos);
		return resultVo;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pb.xc.service.IGoodsService#queryGoods(com.pb.xc.entity.Goods)
	 */
	/**
	 * 查询商品
	 */
	public ResultVo queryIndexGoods(ResultVo param) throws Exception {
		ResultVo resultVo = new ResultVo();
		List<GoodsVo> goodsVos = new ArrayList<GoodsVo>();

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

		// 查询商品名
		GoodsExample goodsExample = new GoodsExample();
		goodsExample.setOrderByClause("num desc");//按加入订单数量倒序排序
		Criteria cr = goodsExample.createCriteria();
		//cr.andStateEqualTo(1);
		cr.andStateEqualTo(2);
		if(param.getQueryType()!= 66){
			cr.andTopEqualTo(param.getQueryType());
		}
		cr.andNameLike(param.getQueryText());//查找输入内容

		List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
		//修改时间str
		if (!ObjectUtil.collectionIsEmpty(goodsList)) {
			for (Iterator iterator = goodsList.iterator(); iterator.hasNext();) {
				Goods goods = (Goods) iterator.next();
				GoodsVo goodsVo = new GoodsVo();
				BeanUtils.copyProperties(goodsVo, goods);
				Date createTime = goods.getCreatetime();
				goodsVo.setStrCreateTime(DateUtil.getDateStr(DateUtil.DATE_STYLE4, createTime));
				if(goods.getState().equals(1)){
					goodsVo.setDown(true);
				}else {
					goodsVo.setDown(false);
				}
				goodsVos.add(goodsVo);
			}
		}

		this.setReturnPageInfo(psize, pageNum, goodsList, resultVo);
		resultVo.setRows(goodsVos);
		return resultVo;
	}

	/*
	 * (non-Javadoc)
	 * @see com.pb.xc.service.IGoodsService#queryGoodsData(com.pb.xc.entity.Goods)
	 */
	/**
	 * 查询商品具体信息
	 */
	public Goods queryGoodsData(Goods goods) throws Exception {
		goods = goodsMapper.selectByPrimaryKey(goods.getId());
		return goods;
	}

	/*
	 * (non-Javadoc)
	 * @see com.pb.xc.service.IGoodsService#downGoods(com.pb.xc.entity.Goods)
	 */
	/**
	 * 修改上下架
	 */
	public Message downGoods(GoodsVo goodsVo) throws Exception {
		Message message = new Message();
		Goods goods = new Goods();
		BeanUtils.copyProperties(goods, goodsVo);
		if(goods.getState().equals(1)){
			goods.setState(2);
		}else if(goods.getState().equals(2)){
			goods.setState(1);
		}
		int record = goodsMapper.updateByPrimaryKeySelective(goods);
		if(record>0){
			message.setSuccess(true);
		}
		return message;
		
	}

}
