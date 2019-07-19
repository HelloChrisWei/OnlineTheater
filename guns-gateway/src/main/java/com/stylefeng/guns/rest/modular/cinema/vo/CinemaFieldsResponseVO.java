package com.stylefeng.guns.rest.modular.cinema.vo;

import lombok.Data;

import java.util.List;

@Data
public class CinemaFieldsResponseVO {

    private CinemaInfoVO cinemaInfo;

    private List<FilmInfoVO> filmList;

}
