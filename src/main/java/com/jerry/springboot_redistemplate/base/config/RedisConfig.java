package com.jerry.springboot_redistemplate.base.config;

import com.jerry.springboot_redistemplate.base.utils.FastJson2JsonRedisSerializer;
import com.jerry.springboot_redistemplate.base.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import com.jerry.springboot_redistemplate.base.utils.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@PropertySource("classpath:redis.properties")
@Slf4j
public class RedisConfig {
    @Value("${redis.hostName}")
    public String hostName;
    @Value("${redis.port}")
    public Integer port;
    @Bean
    public JedisConnectionFactory jedisConnectionFactory()
    {
        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(hostName);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(RedisPassword.none());
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfigurationBuilder=JedisClientConfiguration.builder();
        JedisConnectionFactory factory=new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfigurationBuilder.build());
        return factory;
    }
    @Bean
    public RedisTemplate functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        log.info("RedisTemplate实例化成功");
        RedisTemplate redisTemplate=new RedisTemplate();
        initDomainRedisTemplate(redisTemplate,redisConnectionFactory);
        return redisTemplate;
    }
    @Bean
    public RedisSerializer fastJson2JsonRedisSerializer()
    {
        return new FastJson2JsonRedisSerializer<Object>(Object.class);
    }
    private void initDomainRedisTemplate(RedisTemplate redisTemplate,RedisConnectionFactory factory)
    {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer());

        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }
    @Bean(name = "redisUtil")
    public RedisUtil redisUtil(RedisTemplate redisTemplate)
    {
        log.info("redisUtil success");
        RedisUtil redisUtil=new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }
}
