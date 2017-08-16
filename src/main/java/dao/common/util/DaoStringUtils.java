/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common.dao.dataAccessActionTemplate.util;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author SuslovAI
 */
public class DaoStringUtils {
    
    public static String convertToCommaDelimetedStrings(List<String> input){
        return StringUtils.join(input,',');
    }
    
    public static String convertToCommaDelimetedStringsWithColon(List<String> input){
        return ":"+StringUtils.join(input,",:");
    }
    
    public static String convertToKeyValueCommaDelimetedStrings(String[] input){
        StringBuilder b=new StringBuilder();
        for(String element: input){
            b.append(element+"=:"+element+",");
        }
        b.deleteCharAt(b.length()-1);
        return b.toString();
    }
}
