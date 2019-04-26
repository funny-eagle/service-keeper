package org.nocoder.servicekeeper.common.response;

/**
 * @author jason
 * @date 2019/4/26.
 */
public class DeploymentResponse {

    public static final Integer SUCCESS = 1;
    public static final Integer FAILED = 0;

    private Integer status;
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
