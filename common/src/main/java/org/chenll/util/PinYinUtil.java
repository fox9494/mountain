package org.chenll.util;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * Created by chenll on 2017/2/28.
 * 汉字拼音工具
 * 使用JPinyin开源工具
 */
public class PinYinUtil {


    /**
     * 获取中文字符串的汉语拼音
     * @param chinese
     * @param format
     * @return
     * @throws PinyinException
     */
    public static String getPinyinString(String chinese,PinyinFormat format) throws PinyinException {
        return PinyinHelper.convertToPinyinString(chinese,",", format);
    }

    /**
     * 获取汉语拼音首字母
     * @param chinese
     * @return
     * @throws PinyinException
     */
    public static String getPinyinShort(String chinese) throws PinyinException {
        return PinyinHelper.getShortPinyin(chinese);
    }

    public static void main(String[] args) throws Exception{
        System.out.println("getPinyinString(\"我是中国人\") = " + getPinyinString("我是中国人",PinyinFormat.WITH_TONE_MARK));
    }


}
