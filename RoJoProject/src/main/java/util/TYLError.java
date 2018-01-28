package util;

/**
 * Created by taoyali on 2018/1/28.
 */
public class TYLError {
    public String errorCode;
    public enum ErrorType {
        ErrorParameter, ErrorNUll, ErrorSystem,
    }
    public String errorInfo;
    public  ErrorType errorType;
}
