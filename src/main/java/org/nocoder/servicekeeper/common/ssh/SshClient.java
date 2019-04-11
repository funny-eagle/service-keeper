package org.nocoder.servicekeeper.common.ssh;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class SshClient {
    
    private static Logger logger = LoggerFactory.getLogger(SshClient.class);

    public static void execCommand(Certification c, String command){
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(c.getUser(), c.getHost(), c.getPort());
            session.setPassword(c.getPassword());

            session.setUserInfo(new SshUserInfo());

            try{
                logger.info("开始建立SSH连接...");
                session.connect(30000);
                logger.info("SSH连接成功");
            }catch(Exception e){
                logger.info("SSH连接失败，3s 后开始重试...");
                Thread.sleep(3000);
                try{
                    logger.info("重新尝试建立SSH连接...");
                    session.connect(30000);
                    logger.info("SSH连接成功");
                }catch (Exception ee){
                    logger.info("SSH重试连接失败！");
                    return;
                }
            }

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            logger.info("开始执行命令:" + command);
            channel.setInputStream(null);

            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();

            channel.connect();

            byte[] bytes = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(bytes, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    System.out.print(new String(bytes, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) {
                        continue;
                    }
                    logger.info("关闭SSH连接: exist-status=" + channel.getExitStatus());
                    break;
                }
            }
            channel.disconnect();
            session.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Certification certification = new Certification();
        certification.setHost("192.168.28.155");
        certification.setPort(22);
        certification.setUser("jason");
        certification.setPassword("jasonyang");
        SshClient.execCommand(certification, "ps aux | grep java");
    }
}
