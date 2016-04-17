package cn.com.se.sharepictrue.utils;

/**
 * 登录相关公用方法
 * Created by KIDNG on 2015/11/3.
 */
public class LoginUtils {
    private LoginUtils(){}

    public static boolean validatePassword(String password) {
        return password.length() > 5;
    }

    public static boolean validatePhone(String phone) {
        return phone.length() == 11;
    }

    public static boolean validateSamePassword(String oldP ,String newP) {
        return oldP.equals(newP);
    }

    public static boolean validateEnsurePassword(String first,String second){
        return first.equals(second);
    }
}
