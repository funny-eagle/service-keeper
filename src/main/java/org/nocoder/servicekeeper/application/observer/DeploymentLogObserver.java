package org.nocoder.servicekeeper.application.observer;

import org.nocoder.servicekeeper.application.service.DeploymentLogService;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DeploymentLogObserver extends AbstactObserver {

    @Resource
    private DeploymentLogService deploymentLogService;

    public DeploymentLogObserver(DeploymentSubject subject) {
        this.subject = subject;
        this.subject.addObserver(this);
    }

    @Override
    public void update() {
        DeploymentMessage message = this.subject.getDeploymentMessage();
        Path directories = Paths.get(message.getLogFileDirectory());

        if (!Files.exists(directories)) {
            try {
                Files.createDirectories(directories);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Path logfile = Paths.get(message.getLogFileDirectory()+message.getLogFileName());
        if (!Files.exists(logfile)) {
            try {
                Files.createFile(logfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.write(logfile, message.getResultList(), Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
