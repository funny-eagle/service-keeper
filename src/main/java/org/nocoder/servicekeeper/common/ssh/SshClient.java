package org.nocoder.servicekeeper.common.ssh;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ssh client
 *
 * @author YangJinlong
 */
public class SshClient {

    private static Logger logger = LoggerFactory.getLogger(SshClient.class);

    public static List<String> execCommands(Certification c, List<String> commandList) {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(c.getUser(), c.getHost(), c.getPort());
            session.setPassword(c.getPassword());

            session.setUserInfo(new SshUserInfo());
            List resultList = new ArrayList();
            try {
                session.connect(30000);
            } catch (Exception e) {
                // retry after 3s.
                Thread.sleep(3000);
                try {
                    session.connect(30000);
                } catch (Exception ee) {
                    logger.error("create ssh connection failed, host={}", c.getHost());
                    resultList.add("can not connect to server " + c.getHost());
                    return resultList;
                }
            }

            logger.info("create ssh connection success, host={}", c.getHost());

            commandList.forEach(command -> {
                try {
                    StringBuilder executeResult = executeCommand(session, command);
                    resultList.add(command);
                    resultList.add(executeResult.toString());
                } catch (JSchException e) {
                    e.printStackTrace();
                    logger.error("{}", e.getMessage());
                } catch (IOException e) {
                    logger.error("{}", e.getMessage());
                }
            });
            session.disconnect();
            return resultList;
        } catch (Exception e) {
            logger.error("{}", e.getMessage());
        }
        return Collections.emptyList();
    }

    private static StringBuilder executeCommand(Session session, String command) throws JSchException, IOException {
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);
        logger.info("start to execute command: {}", command);
        channel.setInputStream(null);

        ((ChannelExec) channel).setErrStream(System.err);

        InputStream in = channel.getInputStream();

        channel.connect();

        StringBuilder executeResult = new StringBuilder();
        byte[] bytes = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(bytes, 0, 1024);
                if (i < 0) {
                    break;
                }
                String s = new String(bytes, 0, i);
                executeResult.append(s);
            }
            if (channel.isClosed()) {
                if (in.available() > 0) {
                    continue;
                }
                logger.debug("channel is closed, exist-status={}", channel.getExitStatus());
                break;
            }
        }
        channel.disconnect();
        return executeResult;
    }


    public static void main(String[] args) {
        Certification certification = new Certification();
        certification.setHost("192.168.28.155");
        certification.setPort(22);
        certification.setUser("jason");
        certification.setPassword("jasonyang");
        List<String> commondList = new ArrayList<>();
        commondList.add("abcdedfg");
        commondList.add("df -h");
        SshClient.execCommands(certification, commondList);
    }
}
