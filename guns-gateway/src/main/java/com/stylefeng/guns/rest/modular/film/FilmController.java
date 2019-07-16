package com.stylefeng.guns.rest.modular.film;

import com.stylefeng.guns.rest.modular.film.VO.ConditionListResult;
import com.stylefeng.guns.rest.modular.film.VO.FilmIndexVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import jdk.nashorn.internal.ir.annotations.Reference;
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


    @RequestMapping(value = "/getIndex", method = RequestMethod.GET)
    public ResponseVO<FilmIndexVO> getIndex() {

        FilmIndexVO filmIndexVO = new FilmIndexVO();

//        // 获取banner信息
//        filmIndexVO.setBanners(filmServiceApi.getBanners());
//        // 获取正在热映的电影
//        filmIndexVO.setHotFilms(filmServiceApi.getHotFilms(true, 4, 1, 1, 99, 99, 99));
//        // 即将上映的电影
//        filmIndexVO.setSoonFilms(filmServiceApi.getSoonFilms(true, 4, 1, 1, 99, 99, 99));
//        // 票房排行榜
//        filmIndexVO.setBoxRanking(filmServiceApi.getBoxRanking());
//        // 获取受欢迎的榜单
//        filmIndexVO.setExpectRanking(filmServiceApi.getExpectRanking());
//        // 获取前一百
//        filmIndexVO.setTop100(filmServiceApi.getTop());

        return ResponseVO.success(filmIndexVO);

        return null;

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
