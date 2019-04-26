package org.nocoder.servicekeeper.application.observer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DeploymentLogObserver extends AbstactObserver {

    public DeploymentLogObserver(DeploymentSubject subject) {
        this.subject = subject;
        this.subject.addObserver(this);
    }

    @Override
    public void update() {
        DeploymentMessage message = this.subject.getDeploymentMessage();
        Path directories = Paths.get("/Users/jason/local/logs/service-keeper/");

        if (!Files.exists(directories)) {
            try {
                Files.createDirectories(directories);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Path logfile = Paths.get(directories.toString() + "/deployment-" + message.getDeploymentLogId() + ".log");
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
