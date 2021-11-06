package com.suhao.utils;

import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class WebUtils {

    public static <T> T copyParameterToBean(Map entries, T bean){
        try {
            BeanUtils.populate(bean,entries);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }

    public static int StringToInt(String s, int defaultValue){
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
