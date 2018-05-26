package priv.linjiayun.notetakingsystem.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static boolean isEmpty(String s) {//static 修饰可以直接被该类引用
		if(s==null||"".equals(s.trim())) {//trim()可以去掉前后空格
			return true;
		}else { 
			return false;
		}
	}
	/**
	 * 判断是否不是空
	 * @param s
	 * @return
	 */
	public static boolean notEmpty(String s) {
		if(s!=null||!"".equals(s.trim())) {//trim()可以去掉前后空格
			return true;
		}else { 
			return false;
		}
	}
	/**
	 * 判断字符串是否只由数字和字母组成
	 * @param s
	 * @return
	 */
    public static boolean intergeAndletter(String s) {
   	 Pattern pattern = Pattern.compile("^[0-9a-zA-Z]+$"); 
    	
         char c[] = s.toCharArray();  
         for(int i=0;i<c.length;i++){  
             Matcher matcher = pattern.matcher(String.valueOf(c[i])); //查找
             if(!matcher.matches()){  
                 return false;  
             }  
         }  
         return true;  
  
    	
    }
    /**
     * 判断字符串是否只由数字和字母，中文组成
     * @param s
     * @return
     */
    public static boolean intergeAndletterOrchinese(String s) {
      	 Pattern pattern = Pattern.compile("^[0-9a-zA-Z\\u4e00-\\u9fa5]+$"); 
       	
            char c[] = s.toCharArray();  
            for(int i=0;i<c.length;i++){  
                Matcher matcher = pattern.matcher(String.valueOf(c[i])); //查找
                if(!matcher.matches()){  
                    return false;  
                }  
            }  
            return true;  
     
       	
       }
    /**
     * html标签转换
     * @param str
     * @return
     */
    public static String htmlReplace(String str){
        str=str.replace("\"", "&quot");
        str=str.replace("'", "&#039;");
        str=str.replace("<", "&lt;");
        str=str.replace(">", "&gt;");
        str = str.replace("&mdash;", "—");
        str = str.replace("&ndash;", "–");
        return str;
    }

}
