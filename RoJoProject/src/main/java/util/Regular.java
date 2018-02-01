package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by taoyali on 2018/2/2.
 */
public class Regular {

    public static List<String> regular(String string, String regular) {

        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(string);
        List<String> strings = new ArrayList<String>();
        while (matcher.find()) {
            strings.add(matcher.group());   //group方法返回由以前匹配操作所匹配的输入子序列。
        }
        return strings;
    }
}
