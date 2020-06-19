package com.hotelres.dao;

import com.hotelres.bean.Hotel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author scqzy
 */
@Repository
public interface IHotelDao {

    @Insert("insert into hotel values (#{hid},#{haddr},#{hname},#{hlevel} ,#{jiudianweizhi} ,#{weishengqingjie} , #{shebeisheshi} ,#{fuwuzhiliang})")
    public void addHotel(Hotel hotel);
    @Select("select * from hotel where hid = #{hid}")
    public Hotel findByHid(@Param("hid") int hid);

    @Select("<script>" + "select * from hotel where 1=1 <if test=\"hotelName!=null and hotelName !=''\">" +
            "AND hname LIKE CONCAT('%',#{hotelName},'%')" +
            "</if> order by hid asc" + "</script> ")
    public List<Hotel> findAll(@Param("hotelName") String hotelName);

    @Update("update hotel set haddr = #{haddr},hname = #{hname} , hlevel= #{hlevel} , jiudianweizhi= #{jiudianweizhi} ,weishengqingjie = #{weishengqingjie} , shebeisheshi= #{shebeisheshi} , fuwuzhiliang= #{fuwuzhiliang} where hid = #{hid}")
    public void update(Hotel hotel);

    @Delete("delete from hotel where hid = #{hid}")
    public void delete(@Param("hid")int hid);
}
