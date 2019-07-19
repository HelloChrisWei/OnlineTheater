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

    // 获取banners
    List<BannerVO> getBanners();

    /**
     * 获取热映影片
     * @param isLimit 是否有数量限制
     * @param nums 限制数量是多少
     * @return
     */
    FilmVO getHotFilms(boolean isLimit, int nums);

    /**
     * 获取即将上映影片（按受欢迎程度排序）
     * @param isLimit 是否有数量限制
     * @param nums 限制数量是多少
     * @return
     */
    FilmVO getSoonFilms(boolean isLimit, int nums);

    // 获取票房排行榜
    List<FilmInfo> getBoxRanking();
    // 获取人气排行榜
    List<FilmInfo> getExpectRanking();
    // 获取Top100
    List<FilmInfo> getTop();
}
