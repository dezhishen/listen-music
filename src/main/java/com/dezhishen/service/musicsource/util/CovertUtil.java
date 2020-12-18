package com.dezhishen.service.musicsource.util;

import com.dezhishen.domain.Album;
import com.dezhishen.domain.Artist;
import com.dezhishen.domain.Song;
import com.dezhishen.service.musicsource.constant.MusicSources;
import com.dezhishen.service.musicsource.impl.neteasecloud.NeteaseCloudSong;
import com.dezhishen.service.musicsource.impl.qqmusic.QqMusicArtist;
import com.dezhishen.service.musicsource.impl.qqmusic.QqMusicSearchSong;
import com.dezhishen.service.musicsource.impl.qqmusic.QqMusicSong;

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
     * @param source 外部对象
     * @return 内部对象
     */
    public static Song neteaseCloudSong2Song(NeteaseCloudSong source) {
        if (source == null) {
            return null;
        }
        Song result = new Song();
        result.setName(source.getName());
        result.setId(source.getId());
        result.setAlbum(neteaseAlbum2Album(source.getAlbum()));
        result.setSource(MusicSources.NETEASE_CLOUD);
        result.setFee(source.getFee());
        if (source.getArtists() != null && !source.getArtists().isEmpty()) {
            List<Artist> artists = new ArrayList<>();
            for (NeteaseCloudSong.Artist artist : source.getArtists()) {
                artists.add(neteaseArtist2Artist(artist));
            }
            result.setArtists(artists);
        }
        if (result.getFee() == 1 || result.getFee() == 8 || result.getFee() == 0) {
            result.setFree(true);
        }
        return result;
    }

    public static Artist neteaseArtist2Artist(NeteaseCloudSong.Artist source) {
        if (source == null) {
            return null;
        }
        Artist result = new Artist();
        result.setId(source.getId());
        result.setName(source.getName());
//        result.setImg1v1(source.getImg1v1());
//        result.setImg1v1Url(source.getImg1v1Url());
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

    /**
     * qq音乐 歌曲对象转为系统 歌曲对象
     *
     * @param source 源
     * @return 转换后的结果
     */
    public static Song qqMusicSong2Song(QqMusicSong source) {
        Song result = new Song();
        result.setId(source.getMid());
        result.setName(source.getName());
        result.setSource(MusicSources.QQ_MUSIC);
        if (source.getSingers() != null && !source.getSingers().isEmpty()) {
            List<Artist> artists = new ArrayList<>();
            for (QqMusicArtist singer : source.getSingers()) {
                artists.add(qqMusicSinger2Artist(singer));
            }
            result.setArtists(artists);
        }

        return result;
    }

    /**
     * qq音乐 歌曲对象转为系统 歌曲对象
     *
     * @param source 源
     * @return 转换后的结果
     */
    public static Song qqMusicSearchSong2Song(QqMusicSearchSong source) {
        Song result = new Song();
        result.setId(source.getSongmid());
        result.setName(source.getSongname());
        result.setSource(MusicSources.QQ_MUSIC);
        if (source.getSingers() != null && !source.getSingers().isEmpty()) {
            List<Artist> artists = new ArrayList<>();
            for (QqMusicArtist singer : source.getSingers()) {
                artists.add(qqMusicSinger2Artist(singer));
            }
            result.setArtists(artists);
        }

        return result;
    }

    public static Artist qqMusicSinger2Artist(QqMusicArtist source) {
        if (source == null) {
            return null;
        }
        Artist result = new Artist();
        result.setId(source.getId());
        result.setName(source.getName());
        return result;
    }
}

