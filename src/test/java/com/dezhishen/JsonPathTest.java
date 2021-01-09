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

    @Test
    public void testGet() {
        String json = "{songs:[{name:\"人生浪费指南\",id:497572729,pst:0,t:0,ar:[{id:12383009,name:\"夏日入侵企画\",tns:[],alias:[]}],alia:[],pop:100,st:0,rt:\"\",fee:8,v:32,crbt:null,cf:\"\",al:{id:84153693,name:\"砳\",picUrl:\"https://p2.music.126.net/a-4-aPqSD5SA0gL8EXQeZA==/109951164563801943.jpg\",tns:[],pic_str:\"109951164563801943\",pic:109951164563801940},dt:353676,h:{br:320000,fid:0,size:14150052,vd:-64504},m:{br:192000,fid:0,size:8490049,vd:-61948},l:{br:128000,fid:0,size:5660047,vd:-60308},a:null,cd:\"01\",no:1,rtUrl:null,ftype:0,rtUrls:[],djId:0,copyright:0,s_id:0,mark:0,originCoverType:0,originSongSimpleData:null,single:0,noCopyrightRcmd:null,mv:0,rurl:null,rtype:0,mst:9,cp:0,publishTime:1502452431422}],privileges:[{id:497572729,fee:8,payed:0,st:0,pl:128000,dl:0,sp:7,cp:1,subp:1,cs:false,maxbr:999000,fl:128000,toast:false,flag:64,preSell:false,playMaxbr:999000,downloadMaxbr:999000,freeTrialPrivilege:{resConsumable:false,userConsumable:false},chargeInfoList:[{rate:128000,chargeUrl:null,chargeMessage:null,chargeType:0},{rate:192000,chargeUrl:null,chargeMessage:null,chargeType:1},{rate:320000,chargeUrl:null,chargeMessage:null,chargeType:1},{rate:999000,chargeUrl:null,chargeMessage:null,chargeType:1}]}],code:200}";
        Song song = new Song();
        Object songJson = JsonPath.read(json, "$.songs[0]");
        song.setId(JsonPath.read(songJson, "$.id").toString());
        song.setName(JsonPath.read(songJson, "$.name"));
        List<Object> artistsJsonArray = JsonPath.read(songJson, "$.ar");
        if (artistsJsonArray != null && !artistsJsonArray.isEmpty()) {
            List<Artist> artists = new ArrayList<>();
            for (Object artistsJson : artistsJsonArray) {
                Artist artist = new Artist();
                artist.setId(JsonPath.read(artistsJson, "$.id").toString());
                artist.setName(JsonPath.read(artistsJson, "$.name"));
                artists.add(artist);
            }
            song.setArtists(artists);
        }
        System.out.println(JSON.toJSONString(song));
    }
}
