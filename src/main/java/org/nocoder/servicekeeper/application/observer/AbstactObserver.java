package org.nocoder.servicekeeper.application.observer;

/**
 * 观察者抽象类
 */
public abstract class AbstactObserver {

    protected DeploymentSubject subject;

    public abstract void update();
}
