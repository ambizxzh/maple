package com.zxzh.shop.util;

import com.zxzh.shop.vo.PublicResponseBodyVO;
import org.springframework.beans.factory.annotation.Autowired;

public class ResponseBodyResultUtil {
    public static PublicResponseBodyVO success(Object data){
        PublicResponseBodyVO publicResponseBodyVO=new PublicResponseBodyVO();
        publicResponseBodyVO.setCode(0);
        publicResponseBodyVO.setMsg("成功");
        publicResponseBodyVO.setData(data);
        return publicResponseBodyVO;
    }
    public static PublicResponseBodyVO fail(String error){
        PublicResponseBodyVO publicResponseBodyVO=new PublicResponseBodyVO();
        publicResponseBodyVO.setCode(1);
        publicResponseBodyVO.setMsg(error);
        publicResponseBodyVO.setData(null);
        return publicResponseBodyVO;
    }
}
