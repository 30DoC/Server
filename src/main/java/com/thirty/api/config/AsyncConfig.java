package com.thirty.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by ByeongChan on 2018. 3. 13..
 */

@Configuration
@EnableAsync
public class AsyncConfig extends AsyncConfigurerSupport{

    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(2); // thread 개수
        executor.setMaxPoolSize(2); // thread 최대 개수
        executor.setQueueCapacity(500); // queue size
        executor.initialize();

        return executor;
    }
}
