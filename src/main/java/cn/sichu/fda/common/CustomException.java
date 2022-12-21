package cn.sichu.fda.common;

/**
 * @author sichu
 * @date 2022/12/21
 **/
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
