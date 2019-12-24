package com.java.addressbook.entity;

import java.sql.Date;

public class Util {
    //将String 转化为　Datez
    public static java.sql.Date handleDate(String s){
        java.sql.Date date = Date.valueOf(s);
        return date;
    }
}
