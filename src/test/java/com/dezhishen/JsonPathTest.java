package com.dezhishen;

import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class JsonPathTest {

    @Test
    public void test() {
        String json = "{result:{queryCorrected:[\"人生\"],songs:[{id:497572729,name:\"人生浪费指南\",artists:[{id:12383009,name:\"夏日入侵企画\",picUrl:null,alias:[],albumSize:0,picId:0,img1v1Url:\"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg\",img1v1:0,trans:null}],album:{id:84153693,name:\"砳\",artist:{id:0,name:\"\",picUrl:null,alias:[],albumSize:0,picId:0,img1v1Url:\"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg\",img1v1:0,trans:null},publishTime:1576598400000,size:9,copyrightId:-1,status:0,picId:109951164563801940,alia:[\"石头计划3摇滚季\"],mark:0},duration:353676,copyrightId:0,status:0,alias:[],rtype:0,ftype:0,mvid:0,fee:8,rUrl:null,mark:0}],hasMore:true,songCount:3},code:200}";
        List<Map<String, Object>> result = JsonPath.read(json, "$.result.songs");
        System.out.println(result);
        for (Object s : result) {
            List<String> artists =JsonPath.read(s,"$.artists");
            System.out.println(artists);
        }
    }
}
