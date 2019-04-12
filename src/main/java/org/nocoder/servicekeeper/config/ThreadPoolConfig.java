package org.nocoder.servicekeeper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jason
 * @date 2019/4/13.
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                8, 64, 300000L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolExecutor;
    }
}
