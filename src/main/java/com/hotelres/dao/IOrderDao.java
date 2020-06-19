package com.hotelres.dao;

import com.hotelres.bean.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author scqzy
 */
@Repository
public interface IOrderDao {
    @Insert("insert into horder values (#{oid},#{contactName},#{hotelName},#{roomType},#{checkin},#{checkout},#{totalPrice},#{orderTime},#{username})")
    public void addOrder(Order order);

    @Select("<script>"+"select * from horder where username = #{username} <if test=\"hotelName!=null and hotelName !=''\">" +
            "AND hotelName LIKE CONCAT('%',#{hotelName},'%')" +
            "</if> order by oid desc"+"</script> ")
    public List<Order> findOrders(@Param("hotelName") String hotelName,@Param("username") String username);

    @Delete("delete from horder where oid = #{oid}")
    public void delete(@Param("oid")int oid);
}
