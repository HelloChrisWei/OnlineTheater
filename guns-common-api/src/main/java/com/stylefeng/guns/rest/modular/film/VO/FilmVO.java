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
public class FilmVO implements Serializable {

    private int filmNum;

    private List<FilmInfo> filmInfo;



}
