package com.dezhishen.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "cache")
public class RedisConfig {

    private Long defaultExpires;

    private Map<String, Long> expires;

    public Long getDefaultExpires() {
        return defaultExpires;
    }

    public void setDefaultExpires(Long defaultExpires) {
        this.defaultExpires = defaultExpires;
    }

    public Map<String, Long> getExpires() {
        return expires;
    }

    public void setExpires(Map<String, Long> expires) {
        this.expires = expires;
    }

    @Bean
    public RedisTemplate redisTemplate(@Autowired RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        RedisSerializer<String> redisSerializer;
        redisSerializer = new StringRedisSerializer();

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setConnectionFactory(factory);
        //key序列化方式
        template.setKeySerializer(redisSerializer);
        //value序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //value hashmap序列化
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        return template;
    }


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        RedisSerializationContext.SerializationPair pair =
                RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer);
        if (defaultExpires == null) {
            defaultExpires = 86400L;
        }
        RedisCacheConfiguration defaultCacheConfig =
                RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair)
                        //设置默认超过期时间是30秒
                        .entryTtl(Duration.ofSeconds(defaultExpires));
        //去除前缀
        //初始化RedisCacheManager
        RedisCacheManager cacheManager;
        if (expires != null && !expires.isEmpty()) {
            Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
            for (Map.Entry<String, Long> e : expires.entrySet()) {
                RedisCacheConfiguration config =
                        RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair).entryTtl(
                                Duration.ofSeconds(e.getValue()));
                redisCacheConfigurationMap.put(e.getKey(), config);
            }
            cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig, redisCacheConfigurationMap);
        } else {
            cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
        }
        return cacheManager;
    }
}
