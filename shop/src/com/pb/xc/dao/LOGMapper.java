package com.pb.xc.dao;

import com.pb.xc.entity.LOG;
import com.pb.xc.entity.LOGExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LOGMapper {
    int deleteByExample(LOGExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LOG record);

    int insertSelective(LOG record);

    List<LOG> selectByExample(LOGExample example);

    LOG selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LOG record, @Param("example") LOGExample example);

    int updateByExample(@Param("record") LOG record, @Param("example") LOGExample example);

    int updateByPrimaryKeySelective(LOG record);

    int updateByPrimaryKey(LOG record);
}