package cn.itcast.product.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;
import java.util.Random;

/**
 * 启动参数设置
 *
 * @author tiger
 * @version 1.0
 * @date 2021/3/6
 */
@Slf4j
public class StartCommand {

    public StartCommand(String[] args) {
        boolean isServerPort = false;
        String serverPort = "";
        if (Objects.nonNull(args)) {
            for (String arg : args) {
                if (StringUtils.hasText(arg) && arg.startsWith("--server.port")) {
                    isServerPort = true;
                    serverPort = arg;
                    break;
                }
            }
        }
        // 没有指定端口，则随机生成一个可用的端口
        if (!isServerPort) {
            int port = getAvailablePort();
            log.info("current server.port=" + port);
            System.setProperty("server.port", String.valueOf(port));
        } else {
            log.info("current server.port=" + serverPort.split("=")[1]);
            System.setProperty("server.port", serverPort.split("=")[1]);
        }
    }

    /**
     * 获取可用的端口
     *
     * @return 端口
     */
    private int getAvailablePort() {
        int max = 65535;
        int min = 2000;
        Random random = new Random();
        int port = random.nextInt(max) % (max - min + 1) + min;
        boolean using = isAvailablePort(port);
        if (using) {
            return getAvailablePort();
        } else {
            return port;
        }
    }

    /**
     * 检测端口是否可用
     *
     * @return 是:true,否:false
     */
    private boolean isAvailablePort(int port) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(port));
        } catch (IOException e) {
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
