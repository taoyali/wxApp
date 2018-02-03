package util;

import static util.NetStatus.NetStatusType.NetStatusTypeError;

/**
 * Created by taoyali on 2018/1/28.
 */
public class Error {

    public static NetStatus error() {
        TYLError error = new TYLError();
        error.errorCode = "1234";
        error.errorInfo = "参数错误";
        error.errorType = TYLError.ErrorType.ErrorParameter;
        NetStatus status = new NetStatus();
        status.error = error;
        status.status = NetStatusTypeError;
        return status;
    }
}

