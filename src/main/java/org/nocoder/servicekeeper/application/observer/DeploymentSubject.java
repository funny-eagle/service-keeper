package org.nocoder.servicekeeper.application.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者（发布者）
 */
public class DeploymentSubject {

    /**
     * 观察者
     */
    private List<AbstactObserver> observers = new ArrayList<AbstactObserver>();

    private DeploymentMessage deploymentMessage;

    public DeploymentMessage getDeploymentMessage() {
        return deploymentMessage;
    }

    public void setDeploymentMessage(DeploymentMessage deploymentMessage) {
        this.deploymentMessage = deploymentMessage;
        notifyAllObservers();
    }

    /**
     * 添加订阅
     *
     * @param observer
     */
    public void addObserver(AbstactObserver observer) {
        observers.add(observer);
    }

    /**
     * 通知已订阅的观察者
     */
    public void notifyAllObservers() {
        for (AbstactObserver observer : observers) {
            observer.update();
        }
    }
}
