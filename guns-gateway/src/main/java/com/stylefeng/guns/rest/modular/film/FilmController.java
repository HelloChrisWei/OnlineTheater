package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.modular.film.VO.ConditionListResult;
import com.stylefeng.guns.rest.modular.film.VO.FilmIndexVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @version 1.0 2019/7/16
 * @author Wei
 */
@RestController
@RequestMapping("/film")
public class FilmController {

    private static final String img_pre = "http://img.meetingshop.cn/";

    @Reference(interfaceClass = FilmServiceApi.class, check = false)
    private FilmServiceApi filmServiceApi;



    // 获取首页信息接口
    @RequestMapping(value = "/getIndex", method = RequestMethod.GET)
    public ResponseVO<FilmIndexVO> getIndex() {

        FilmIndexVO filmIndexVO = new FilmIndexVO();

        // 获取banner信息
        filmIndexVO.setBanners(filmServiceApi.getBanners());
        // 获取正在热映的电影
        filmIndexVO.setHotFilms(filmServiceApi.getHotFilms());
        // 即将上映的电影
        filmIndexVO.setSoonFilms(filmServiceApi.getSoonFilms());
        // 票房排行榜
        filmIndexVO.setBoxRanking(filmServiceApi.getBoxRanking());
        // 获取受欢迎的榜单
        filmIndexVO.setExpectRanking(filmServiceApi.getExpectRanking());
        // 获取前一百
        filmIndexVO.setTop100(filmServiceApi.getTop());

        return ResponseVO.success(img_pre, filmIndexVO);
    }



    @RequestMapping("getConditionList")
    public ConditionListResult getConditionList(int catId, int sourceId, int yearId) {


//        @Reference
//        FilmService filmService;
//
//        service.getcatInfoByCatId



        return null;
    }
}
