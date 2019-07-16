package com.stylefeng.guns.rest.modular.film;

import com.stylefeng.guns.rest.modular.film.VO.ConditionListResult;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @version 1.0 2019/7/16
 * @author Wei
 */
@Controller
@RequestMapping("/cinema")
public class FilmController {

    @RequestMapping("getConditionList")
    @ResponseBody
    public ConditionListResult getConditionList(int catId, int sourceId, int yearId) {


//        @Reference
//        FilmService filmService;
//
//        service.getcatInfoByCatId



        return null;
    }
}
