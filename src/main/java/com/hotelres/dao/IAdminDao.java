package com.hotelres.dao;

import com.hotelres.bean.Hadmin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author scqzy
 */
@Repository
public interface IAdminDao {

    @Select("select * from hadmin where aname = #{aname} and apassword = #{apassword}")
    public Hadmin login(@Param("aname") String aname, @Param("apassword") String apassword);
}
