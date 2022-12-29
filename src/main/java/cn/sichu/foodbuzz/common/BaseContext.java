package cn.sichu.foodbuzz.common;

/**
 * 基于ThreadLocal封装的工具类, 用于保存和获取当前登录用户的id
 *
 * @author sichu
 * @date 2022/12/21
 **/
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }
}
