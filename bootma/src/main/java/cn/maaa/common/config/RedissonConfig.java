package cn.maaa.common.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :  mazh
 * @date :  2019/11/12 18:12
 */
/*
@Configuration
public class RedissonConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public RedissonClient redissonClient()  {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://"+redisProperties.getHost()+":"+redisProperties.getPort())
                .setDatabase(redisProperties.getDatabase())
                .setTimeout(redisProperties.getTimeout())
                .setPassword(StringUtils.isEmpty(redisProperties.getPassword()) ? null : redisProperties.getPassword());
        return Redisson.create(config);
    }


}*/
