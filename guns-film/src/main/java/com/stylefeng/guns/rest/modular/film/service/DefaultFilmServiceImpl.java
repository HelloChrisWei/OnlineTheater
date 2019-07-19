package com.stylefeng.guns.rest.modular.film.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.MtimeBannerT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeFilmT;
import com.stylefeng.guns.rest.modular.film.FilmServiceApi;
import com.stylefeng.guns.rest.modular.film.VO.BannerVO;
import com.stylefeng.guns.rest.modular.film.VO.FilmInfo;
import com.stylefeng.guns.rest.modular.film.VO.FilmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @version 1.0 2019/7/16
 * @author Wei
 */
@Component
@Service(interfaceClass = FilmServiceApi.class)
public class DefaultFilmServiceImpl implements FilmServiceApi{

    @Autowired
    MtimeActorTMapper mtimeActorTMapper;

    @Autowired
    MtimeBannerTMapper mtimeBannerTMapper;

    @Autowired
    MtimeCatDictTMapper mtimeCatDictTMapper;

    @Autowired
    MtimeFilmInfoTMapper mtimeFilmInfoTMapper;

    @Autowired
    MtimeFilmTMapper mtimeFilmTMapper;

    @Autowired
    MtimeSourceDictTMapper mtimeSourceDictTMapper;

    @Autowired
    MtimeYearDictTMapper mtimeYearDictTMapper;

    @Override
    public List<BannerVO> getBanners() {
        List<BannerVO> result = new ArrayList<>();
        // 参数传null是获取所有列表
        List<MtimeBannerT> mtimeBanners = mtimeBannerTMapper.selectList(null);

        for (MtimeBannerT mtimeBanner : mtimeBanners) {
            BannerVO bannerVO = new BannerVO();
            bannerVO.setBannerId(mtimeBanner.getUuid() + "");
            bannerVO.setBannerAddress(mtimeBanner.getBannerAddress());
            bannerVO.setBannerUrl(mtimeBanner.getBannerUrl());
            result.add(bannerVO);
        }
        return result;
    }

    @Override
    public FilmVO getHotFilms(boolean isLimit, int nums) {
        FilmVO result = new FilmVO();

        ArrayList<FilmInfo> filmInfos = new ArrayList<>();

        // 正在上映影片的限制条件
        EntityWrapper<MtimeFilmT> mtimeFilmTEntityWrapper = new EntityWrapper<>();
        mtimeFilmTEntityWrapper.eq("film_status", 1);


        // 判断是否是首页需要的内容
        if (isLimit) {
            // 如果是，则限制条数，限制内容为热映影片
            Page<MtimeFilmT> page = new Page<>(1, nums);
            List<MtimeFilmT> mtimeFilms = mtimeFilmTMapper.selectPage(page, mtimeFilmTEntityWrapper);
            // 组织FilmInfo
            filmInfos = getFilmInfos(mtimeFilms);

            result.setFilmInfo(filmInfos);
            result.setFilmNum(mtimeFilms.size());

        } else {
            // 如果不是，则是列表页，同样需要限制内容为热映影片
        }


        return result;
    }

    /**
     * 获取FilmInfo列表
     * @param mtimeFilms
     * @return
     */
    private ArrayList<FilmInfo> getFilmInfos(List<MtimeFilmT> mtimeFilms) {
        ArrayList<FilmInfo> filmInfos = new ArrayList<>();

/*        private String filmId;
        private int filmType;
        private String imgAddress;
        private String filmName;
        private String filmScore;
        private int expectNum;
        private String showTime;
        private int boxNum;
        private String score;*/
        for (MtimeFilmT mtimeFilm : mtimeFilms) {
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setFilmId(mtimeFilm.getUuid() + "");
            filmInfo.setFilmType(mtimeFilm.getFilmType());
            filmInfo.setImgAddress(mtimeFilm.getImgAddress());
            filmInfo.setFilmName(mtimeFilm.getFilmName());
            filmInfo.setFilmScore(mtimeFilm.getFilmScore());
            filmInfo.setExpectNum(mtimeFilm.getFilmPresalenum());
            filmInfo.setShowTime(DateUtil.getDay(mtimeFilm.getFilmTime()));
            filmInfo.setBoxNum(mtimeFilm.getFilmBoxOffice());
            filmInfo.setScore(mtimeFilm.getFilmScore());
            filmInfos.add(filmInfo);
        }

        return filmInfos;
    }

    @Override
    public FilmVO getSoonFilms(boolean isLimit, int nums) {
        FilmVO result = new FilmVO();
        ArrayList<FilmInfo> filmInfos = new ArrayList<>();

        // 即将上映影片的限制条件
        EntityWrapper<MtimeFilmT> mtimeFilmTEntityWrapper = new EntityWrapper<>();
        mtimeFilmTEntityWrapper.eq("film_status", 2);

        // 判断是否是首页需要的内容
        if (isLimit) {
            // 如果是，则限制条数，限制内容为热映影片
            Page<MtimeFilmT> page = new Page<>(1, nums);
            List<MtimeFilmT> mtimeFilms = mtimeFilmTMapper.selectPage(page, mtimeFilmTEntityWrapper);
            // 组织FilmInfo
            filmInfos = getFilmInfos(mtimeFilms);

            result.setFilmInfo(filmInfos);
            result.setFilmNum(mtimeFilms.size());

        } else {
            // 如果不是，则是列表页，同样需要限制内容为热映影片
        }

        return result;
    }


    /**
     * 票房排行榜
     * @return
     */
    @Override
    public List<FilmInfo> getBoxRanking() {
        // 条件 -> 正在上映的，票房前10名
        EntityWrapper<MtimeFilmT> mtimeFilmTEntityWrapper = new EntityWrapper<>();
        mtimeFilmTEntityWrapper.eq("film_status", 1);


        Page<MtimeFilmT> page = new Page<>(1, 10, "film_box_office", false);
        List<MtimeFilmT> mtimeFilms = mtimeFilmTMapper.selectPage(page, mtimeFilmTEntityWrapper);

        List<FilmInfo> filmInfos = getFilmInfos(mtimeFilms);

        return filmInfos;
    }

    /**
     * 最受欢迎的电影
     * @return
     */
    @Override
    public List<FilmInfo> getExpectRanking() {
        // 条件 -> 即将上映的，预售前10名
        EntityWrapper<MtimeFilmT> mtimeFilmTEntityWrapper = new EntityWrapper<>();
        mtimeFilmTEntityWrapper.eq("film_status", 2);


        Page<MtimeFilmT> page = new Page<>(1, 10, "film_preSaleNum", false);
        List<MtimeFilmT> mtimeFilms = mtimeFilmTMapper.selectPage(page, mtimeFilmTEntityWrapper);

        List<FilmInfo> filmInfos = getFilmInfos(mtimeFilms);

        return filmInfos;
    }

    /**
     * 打分前xx名
     * @return
     */
    @Override
    public List<FilmInfo> getTop() {
        // 条件 -> 正在上映的，评分前10名
        EntityWrapper<MtimeFilmT> mtimeFilmTEntityWrapper = new EntityWrapper<>();
        mtimeFilmTEntityWrapper.eq("film_status", 1);


        Page<MtimeFilmT> page = new Page<>(1, 10, "film_score", false);
        List<MtimeFilmT> mtimeFilms = mtimeFilmTMapper.selectPage(page, mtimeFilmTEntityWrapper);

        List<FilmInfo> filmInfos = getFilmInfos(mtimeFilms);

        return filmInfos;
    }
}
