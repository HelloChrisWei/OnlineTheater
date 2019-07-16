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
