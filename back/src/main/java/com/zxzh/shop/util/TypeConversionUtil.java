package com.zxzh.shop.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//用于数组转链表
public class TypeConversionUtil {
    public static List<Map<String, String>> stringToTagList(String tag){
        String[] tagArray=tag.split("&");
        List<Map<String, String>> tagList=new ArrayList<>();
        Map<String , String> map=new HashMap<>();
        for(String s:tagArray){
            map.put("name",s);
            tagList.add(map);
        }
        return tagList;
    }
}
