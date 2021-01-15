package com.dezhishen.music;

import com.dezhishen.music.constant.PathConstants;
import com.dezhishen.music.exception.MusicException;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * 启动类
 *
 * @author dezhishen
 */
@SpringBootApplication
@MapperScan("com.dezhishen.music.mapper")
@EnableAsync
@EnableCaching
@Slf4j
public class Application {
    public static void main(String[] args) {
        createDataVolume();
        SpringApplication.run(Application.class, args);
    }

    /**
     * 创建数据持久化目录
     */
    private static void createDataVolume() {
        //创建目录
        String filePath = System.getProperty(PathConstants.MUSIC_DATA_PATH_PROPERTY_NAME);
        if (StringUtils.isEmpty(filePath)) {
            filePath = System.getenv(PathConstants.MUSIC_DATA_PATH_ENV_NAME);
        } else {
            log.info("成功读取到系统属性 {},设置数据持久化目录为 {}", PathConstants.MUSIC_DATA_PATH_PROPERTY_NAME, filePath);
        }
        if (StringUtils.isEmpty(filePath)) {
            filePath = PathConstants.DEFAULT_MUSIC_DATA_PATH;
            log.info("使用默认的数据持久化目录 {}", filePath);
        } else {
            log.info("成功读取到环境变量 {},设置数据持久化目录为 {}", PathConstants.MUSIC_DATA_PATH_ENV_NAME, filePath);
        }
        File file = new File(filePath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new MusicException("创建数据持久化目录[%s]失败", filePath);
            }
        }
        if (!file.isDirectory()) {
            throw new MusicException("项目数据持久化目录 [%s] 已存在,但不是目录,请修改系统属性[%s](或者环境变量[%s]) 的值,重新设置持久化目录,或者重命名当前已存在的文件"
                    , filePath, PathConstants.MUSIC_DATA_PATH_PROPERTY_NAME, PathConstants.MUSIC_DATA_PATH_ENV_NAME
            );
        }
        log.info("项目持久化目录为 {}", filePath);
    }
}
