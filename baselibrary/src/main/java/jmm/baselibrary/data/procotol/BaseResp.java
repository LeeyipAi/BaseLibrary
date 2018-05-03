package jmm.baselibrary.data.procotol;

public class BaseResp<T> {

    private String status;
    private String message;
    private String code;
    private T data;

    public BaseResp() {
    }

    public BaseResp(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "BaseResp{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
