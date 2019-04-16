package org.nocoder.servicekeeper.common.Enumeration;

/**
 * @author jason
 * @date 2019/4/16.
 */
public enum ServiceStatus {
    /**
     * stop
     */
    STOP("Stop"),
    /**
     * running
     */
    RUNNING("Running"),
    /**
     * pending
     */
    PENDING("Pending");


    ServiceStatus(String status) {
        this.status = status;
    }

    private String status;

    public String status() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
