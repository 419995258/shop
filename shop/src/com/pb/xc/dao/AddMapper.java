package com.pb.xc.dao;

import com.pb.xc.entity.Add;
import com.pb.xc.entity.AddExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AddMapper {
    int deleteByExample(AddExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Add record);

    int insertSelective(Add record);

    List<Add> selectByExample(AddExample example);

    Add selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Add record, @Param("example") AddExample example);

    int updateByExample(@Param("record") Add record, @Param("example") AddExample example);

    int updateByPrimaryKeySelective(Add record);

    int updateByPrimaryKey(Add record);
}