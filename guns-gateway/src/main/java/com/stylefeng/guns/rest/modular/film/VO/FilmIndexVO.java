package com.stylefeng.guns.rest.modular.film.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @version 1.0 2019/7/16
 * @author Wei
 */
@Data
public class FilmIndexVO {

    private List<BannerVO> banners;
    private FilmVO hotFilms;
    private FilmVO soonFilms;
    private List<FilmInfo> boxRanking;
    private List<FilmInfo> expectRanking;
    private List<FilmInfo> top100;

}
