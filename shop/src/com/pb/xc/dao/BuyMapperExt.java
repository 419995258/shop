package com.pb.xc.dao;

import com.pb.xc.controller.vo.BuyVo;
import com.pb.xc.entity.Buy;
import com.pb.xc.entity.BuyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BuyMapperExt {
	/**
     * 查询未处理订单
     * @return
     */
    List<BuyVo> queryWaitOrder();
    
    /**
     * 查询未处理订单By name
     * @return
     */
    List<BuyVo> queryWaitOrderByName(String name);
    
    /**
     * 查询未处理订单By tel
     * @return
     */
    List<BuyVo> queryWaitOrderByTel(String tel);
    
  
    
    /**
     * 查询全部订单
     * @return
     */
    List<BuyVo> queryAllOrder();
    /**
     * 查询已经处理订单By name
     * @return
     */
    List<BuyVo> queryAllOrderByName(String name);
    
    /**
     * 查询已经处理订单By tel
     * @return
     */
    List<BuyVo> queryAllOrderByTel(String tel);
}