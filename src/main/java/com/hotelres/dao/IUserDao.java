package com.hotelres.dao;

import com.hotelres.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author scqzy
 */
@Repository
public interface IUserDao {

    @Select("<script>"+"select * from user where 1=1 <if test=\"username!=null and username !=''\">" +
            "AND username LIKE CONCAT('%',#{username},'%')" +
            "</if> order by id asc"+"</script> ")
    public List<User> findAll(@Param("username")String username);

    @Insert("insert into user values (#{id},#{username},#{password},#{tel},#{idcard},#{realname},#{jiudianweizhi},#{weishengqingjie},#{shebeisheshi},#{fuwuzhiliang})")
    public void saveUser(User user);

    @Select("select * from user where username = #{username} and password = #{password}")
    public User login(@Param("username") String username,@Param("password") String password);

    @Select("select * from user where idcard = #{idcard}")
    public User findByIdcard(@Param("idcard") String idcard);

    @Select("select * from user where username = #{username}")
    public User findByUsername(@Param("username") String username);

    @Select("select * from user where idcard = #{idcard} and realname = #{realname}")
    public User findByIdcardAndRealname(@Param("idcard") String idcard,@Param("realname") String realname);

    @Update("update user set password = #{password} where username = #{username}")
    public void updatePassword(@Param("username") String username,@Param("password") String password);

    @Update("update user set jiudianweizhi = #{jiudianweizhi},weishengqingjie = #{weishengqingjie},shebeisheshi = #{shebeisheshi},fuwuzhiliang = #{fuwuzhiliang} where username = #{username}")
    public void updateInfo(@Param("jiudianweizhi")double jiudianweizhi,@Param("weishengqingjie")double weishengqingjie,@Param("shebeisheshi")double shebeisheshi,@Param("fuwuzhiliang")double fuwuzhiliang,@Param("username")String username);

    @Update("update user set username = #{username},password = #{password},tel = #{tel},idcard = #{idcard},realname = #{realname},jiudianweizhi = #{jiudianweizhi},weishengqingjie = #{weishengqingjie},shebeisheshi = #{shebeisheshi},fuwuzhiliang = #{fuwuzhiliang} where id = #{id}")
    public void updateUser(User user);

    @Delete("delete from user where id = #{id}")
    public void removeUser(@Param("id")int id);
}
