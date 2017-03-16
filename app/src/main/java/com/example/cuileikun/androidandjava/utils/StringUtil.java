package com.example.cuileikun.androidandjava.utils;

import java.util.regex.Pattern;

/**
 * Created by acer on 2016-1-11.
 * 字符串工具
 */
public class StringUtil {
    /**
     * 检查字符串个数
     * @param str 字符串
     * @param limitChineseCount 限制中文字符串个数
     * @param limitChacterCount 限制总字符个数(包括中文和英文)
     * @return
     */
    public static boolean checkChacterCount(String str,int limitChineseCount,int limitChacterCount) {
        int chineseCount = 0;// 中文个数
        int chacterCount = 0;//英文个数
        int sumCount;//总个数
        for (int i = 0; i < str.length(); i++) {// 遍历字符串每一个字符
            // 使用正则表达式判断字符是否属于汉字编码
            boolean matches = Pattern.matches("^[\u4E00-\u9FA5]{0,}$", ""
                    + str.charAt(i));
            if (matches) {// 如果是汉字
                chineseCount++;// 累加计数器
            } else {
                /**
                 * 其他当作是英文(包括数字,小数点,符号)
                 */
                chacterCount++;
            }
        }
        boolean flag = true;//检查是否通过
        /**
         * 判断要不要检查中文汉字 为0是不要检查中文汉字
         */
        if(limitChacterCount !=0) {
            /**
             * 检查中文个数是否超过限制中文个数
             */
            if(chineseCount >limitChacterCount) {
                /**
                 * 超过限制中文个数
                 */
                flag = false;
            }
        }

        /**
         * 检查中文字符个数加上英文字符个数是否超过总字符个数
         */
        if((chineseCount*2+chacterCount)>limitChacterCount) {
            /**
             * 超过
             */
            flag = false;
        }
        return flag;
    }

}
