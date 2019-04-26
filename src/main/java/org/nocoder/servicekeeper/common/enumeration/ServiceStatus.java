package org.nocoder.servicekeeper.common.enumeration;

/**
 * @author jason
 * @date 2019/4/16.
 */
public enum ServiceStatus {
    /**
     * stop
     */
    STOPPED("Stopped"),
    /**
     * running
     */
    RUNNING("Running"),
    /**
     * pending
     */
    PENDING("Pending"),
    /**
     * lost connection
     */
    LOST_CONNECTION("Lost Connection");


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
