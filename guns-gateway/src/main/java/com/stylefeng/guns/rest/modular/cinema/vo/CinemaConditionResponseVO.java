package com.stylefeng.guns.rest.modular.cinema.vo;

import lombok.Data;

import java.util.List;

@Data
public class CinemaConditionResponseVO {

    private List<BrandVO> brandList;

    private List<AreaVO> areaList;

    private List<HallTypeVO> halltypeList;

}
