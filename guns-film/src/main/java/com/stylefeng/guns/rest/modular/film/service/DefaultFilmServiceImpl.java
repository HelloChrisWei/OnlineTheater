package com.stylefeng.guns.rest.modular.film.service;

import com.alibaba.dubbo.config.annotation.Service;
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
    public FilmVO getHotFilms() {
        FilmVO result = new FilmVO();
        List<MtimeFilmT> mtimeFilms = mtimeFilmTMapper.selectList(null);

        //TODO
//        filmInfos = getFilmInfos(moocFilms);

        result.setFilmNum(mtimeFilms.size());


        return result;
    }

    @Override
    public FilmVO getSoonFilms() {
        FilmVO result = new FilmVO();



        return result;
    }

    @Override
    public List<FilmInfo> getBoxRanking() {
        return null;
    }

    @Override
    public List<FilmInfo> getExpectRanking() {
        return null;
    }

    @Override
    public List<FilmInfo> getTop() {
        return null;
    }
}
