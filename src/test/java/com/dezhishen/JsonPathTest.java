package com.dezhishen;

import com.alibaba.fastjson.JSON;
import com.dezhishen.domain.Artist;
import com.dezhishen.domain.Song;
import com.github.pagehelper.PageInfo;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class JsonPathTest {

    @Test
    public void test() {
        String json = "{result:{queryCorrected:[\"人生\"],songs:[{id:497572729,name:\"人生浪费指南\",artists:[{id:12383009,name:\"夏日入侵企画\",picUrl:null,alias:[],albumSize:0,picId:0,img1v1Url:\"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg\",img1v1:0,trans:null}],album:{id:84153693,name:\"砳\",artist:{id:0,name:\"\",picUrl:null,alias:[],albumSize:0,picId:0,img1v1Url:\"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg\",img1v1:0,trans:null},publishTime:1576598400000,size:9,copyrightId:-1,status:0,picId:109951164563801940,alia:[\"石头计划3摇滚季\"],mark:0},duration:353676,copyrightId:0,status:0,alias:[],rtype:0,ftype:0,mvid:0,fee:8,rUrl:null,mark:0}],hasMore:true,songCount:3},code:200}";
        PageInfo<Song> result = new PageInfo<>();
        result.setPageNum(1);
        result.setPageSize(1);
        Object total = JsonPath.read(json, "$.result.songCount");
        if (total == null) {
            result.setTotal(0);
        } else {
            result.setTotal(Long.parseLong(total.toString()));
        }
        List<Object> songJsonArray = JsonPath.read(json, "$.result.songs");
        if (songJsonArray != null && !songJsonArray.isEmpty()) {
            List<Song> list = new ArrayList<>();
            for (Object songJson : songJsonArray) {
                Song song = new Song();
                song.setId(JsonPath.read(songJson, "$.id").toString());
                song.setName(JsonPath.read(songJson, "$.name"));
                List<Object> artistsJsonArray = JsonPath.read(songJson, "$.artists");
                if (artistsJsonArray != null && !artistsJsonArray.isEmpty()) {
                    List<Artist> artists = new ArrayList<>();
                    for (Object artistsJson : artistsJsonArray) {
                        Artist artist = new Artist();
                        artist.setId(JsonPath.read(artistsJson, "$.id").toString());
                        artist.setName(JsonPath.read(artistsJson, "$.name"));
                        artist.setPicUrl(JsonPath.read(artistsJson, "$.img1v1Url"));
                        artists.add(artist);
                    }
                    song.setArtists(artists);
                }
                list.add(song);
            }
            result.setList(list);
        }
        System.out.println(JSON.toJSONString(result));
    }
}
