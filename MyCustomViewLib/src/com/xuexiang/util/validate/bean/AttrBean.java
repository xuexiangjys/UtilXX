package com.xuexiang.util.validate.bean;

import java.util.LinkedList;

/**
 * Created by CXY on 2016/11/10.
 */

public class AttrBean {
    public static final int ET = 0x1100;
    public static final int TV = 0x0011;

    public Integer index ;
    public String name ;
    public Object view ;
    public boolean isEt ;

    public LinkedList<Basebean> annos ;
}
