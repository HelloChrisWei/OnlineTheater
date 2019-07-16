package com.stylefeng.guns.rest.modular.film;

import com.stylefeng.guns.rest.modular.film.VO.BannerVO;
import com.stylefeng.guns.rest.modular.film.VO.FilmInfo;
import com.stylefeng.guns.rest.modular.film.VO.FilmVO;

import java.util.List;

/**
 * 
 * @version 1.0 2019/7/16
 * @author Wei
 */
public interface FilmServiceApi {

    List<BannerVO> getBanners();

    // 获取热映影片
    FilmVO getHotFilms();
    // 获取即将上映影片[受欢迎程度做排序]
    FilmVO getSoonFilms();

    List<FilmInfo> getBoxRanking();

    List<FilmInfo> getExpectRanking();

    List<FilmInfo> getTop();
}
