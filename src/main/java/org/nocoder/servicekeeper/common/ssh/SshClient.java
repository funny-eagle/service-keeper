package org.nocoder.servicekeeper.common.ssh;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * ssh client
 * @author YangJinlong
 */
public class SshClient {
    
    private static Logger logger = LoggerFactory.getLogger(SshClient.class);

    public static List<String> execCommands(Certification c, List<String> commandList){
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(c.getUser(), c.getHost(), c.getPort());
            session.setPassword(c.getPassword());

            session.setUserInfo(new SshUserInfo());

            try{
                logger.info("connecting to ssh server...");
                session.connect(30000);
                logger.info("connected to ssh server.");
            }catch(Exception e){
                logger.info("connect failed, retry after 3s...");
                Thread.sleep(3000);
                try{
                    logger.info("try to connect to ssh server again...");
                    session.connect(30000);
                    logger.info("connected to ssh server.");
                }catch (Exception ee){
                    logger.info("retry ssh connection failed.");
                    return null;
                }
            }

            List list = new ArrayList();
            commandList.forEach(command -> {
                try {
                    StringBuilder executeResult = executeCommand(session, command);
                    list.add(executeResult.toString());
                } catch (JSchException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            session.disconnect();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
                logger.info(s);
                executeResult.append(s);
            }
            if (channel.isClosed()) {
                if (in.available() > 0) {
                    continue;
                }
                logger.info("channel is closed, exist-status={}", channel.getExitStatus());
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
