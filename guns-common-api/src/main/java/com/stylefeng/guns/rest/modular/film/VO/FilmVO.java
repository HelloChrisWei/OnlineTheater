package com.stylefeng.guns.rest.modular.film.VO;

import lombok.Data;

import java.util.List;

/**
 * 
 * @version 1.0 2019/7/16
 * @author Wei
 */
@Data
public class FilmVO {

    private int filmNum;
    // 分页
    private int nowPage;
    private int totalPage;
    private List<FilmInfo> filmInfo;



}
