package com.dezhishen.service.musicsource.util;

import com.dezhishen.domain.Album;
import com.dezhishen.domain.Artist;
import com.dezhishen.domain.Song;
import com.dezhishen.service.musicsource.impl.neteasecloud.NeteaseCloudSong;

import java.util.ArrayList;
import java.util.List;

/**
 * 转换工具类
 *
 * @author dezhishen
 */
public class CovertUtil {
    /**
     * 网易云返回对象转换为系统内部对象
     *
     * @param song 外部对象
     * @return 内部对象
     */
    public static Song neteaseCloudSong2Song(NeteaseCloudSong song) {
        if (song == null) {
            return null;
        }
        Song result = new Song();
        result.setName(song.getName());
        result.setId(song.getId());
        result.setAlbum(neteaseAlbum2Album(song.getAlbum()));
        return result;
    }

    public static Artist neteaseArtist2Artist(NeteaseCloudSong.Artist source) {
        if (source == null) {
            return null;
        }
        Artist result = new Artist();
        result.setId(source.getId());
        result.setName(source.getName());
        result.setImg1v1(source.getImg1v1());
        result.setImg1v1Url(source.getImg1v1Url());
        result.setPicUrl(source.getPicUrl());
        return result;
    }

    public static Album neteaseAlbum2Album(NeteaseCloudSong.Album source) {
        if (source == null) {
            return null;
        }
        Album result = new Album();
        result.setId(source.getId());
        result.setName(source.getName());
        if (source.getArtists() != null && !source.getArtists().isEmpty()) {
            List<Artist> artists = new ArrayList<>();
            for (NeteaseCloudSong.Artist artist : source.getArtists()) {
                artists.add(neteaseArtist2Artist(artist));
            }
            result.setArtists(artists);
        }
        result.setPublishTime(source.getPublishTime());
        result.setSize(source.getSize());
        result.setCopyrightId(source.getCopyrightId());
        result.setStatus(source.getStatus());
        result.setPicId(source.getPicId());
        result.setMark(source.getMark());
        return result;
    }
}

