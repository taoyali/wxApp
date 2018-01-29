package util;

/**
 * Created by taoyali on 2018/1/29.
 */
public class NetStatus {
    public enum NetStatusType {
        NetStatusTypeSuccess, NetStatusTypeError,
    }
    public TYLError error;
    public NetStatusType status;
    public id data;
}
