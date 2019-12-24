package com.java.addressbook.dao;

import com.java.addressbook.entity.User;
import org.apache.ibatis.annotations.*;
import java.util.ArrayList;
import java.sql.Date;

@Mapper
public interface User_dao {
    @Select("select * from addressbook..user1")
    ArrayList<User> getUserList();

    @Select("select * from addressbook..user1 where userName = #{userName}")
    User getUserByUserName(String userName);

    @Select("select loginTimes from addressbook..user1 where userName = #{userName}")
    Integer getLoginTimesByUserName(String userName);

    @Select("select * from addressbook..user1 where userName = #{userName} and password = #{password}")
    User queryUser(@Param("userName")String userName,@Param("password")String password);

    @Select("select * from addressbook..admin where userName = #{userName}")
    User getAdminByUserName(String userName);

    @Insert("insert into addressbook..admin(userName,password) values(#{userName},#{password})")
    Boolean adminregister(@Param("userName")String userName,@Param("password")String password);

    @Select("select * from addressbook..admin where userName = #{userName} and password = #{password}")
    User adminqueryUser(@Param("userName")String userName,@Param("password")String password);



    @Insert("insert into addressbook..user1(userName,password) values(#{userName},#{password})")
    Boolean addUser(@Param("userName")String userName,@Param("password")String password);

    @Update("update addressbook..user1 set approvalStatus=#{status} where userName=#{userName}")
    Boolean changeApprovalStatus(@Param("userName")String userName,@Param("status")Integer status);

    @Update("update addressbook..user1 set lastLogin = #{lastLogin} where userName = #{userName}")
    Boolean setLastLoginByUserName(@Param("userName")String userName,@Param("lastLogin")Date lastLogin);

    @Update("update addressbook..user1 set loginTimes = #{loginTimes} where userName = #{userName}")
    Boolean setLoginTimesByUserName(@Param("userName")String userName,@Param("loginTimes") Integer loginTimes);

    @Delete("delete from addressbook..user1 where userName=#{userName}")
    Boolean deleteUserAccount(@Param("userName")String userName);

    @Select("select * from addressbook..user1 where userName=#{userName}")
    ArrayList<User> getfindUser(@Param("userName")String userName);

    @Update("update addressbook..user1 " +
            "set name=#{name}," +
            "birth=#{birth}," +
            "profession=#{profession},"+
            "className=#{className}," +
            "entranceTime=#{entranceTime}," +
            "leaveTime=#{leaveTime}," +
            "employmentUnit=#{employmentUnit}," +
            "location=#{location}," +
            "phoneNumber=#{phoneNumber}," +
            "email=#{email}," +
            "description=#{description} where userName=#{userName}")
    Boolean setUserByUserName(@Param("userName") String userName, @Param("name") String name, @Param("birth") String birth,
                              @Param("profession") String profession,
                              @Param("className") String className, @Param("entranceTime") String entranceTime, @Param("leaveTime") String leaveTime,
                              @Param("employmentUnit") String employmentUnit, @Param("location") String location,
                              @Param("phoneNumber") String phoneNumber, @Param("email") String email,
                              @Param("description") String description);


}