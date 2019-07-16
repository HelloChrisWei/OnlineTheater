package com.stylefeng.guns.rest.modular.film.VO;

import lombok.Data;

/**
 * 
 * @version 1.0 2019/7/16
 * @author Wei
 */
@Data
public class FilmInfo {
    private String filmId;
    private int filmType;
    private String imgAddress;
    private String filmName;
    private String filmScore;
    private int expectNum;
    private String showTime;
    private int boxNum;
    private String score;
}
