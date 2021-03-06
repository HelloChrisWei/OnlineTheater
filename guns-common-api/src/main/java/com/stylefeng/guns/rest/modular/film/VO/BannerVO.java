package com.stylefeng.guns.rest.modular.film.VO;

import lombok.Data;

import java.io.Serializable;


/**
 * 
 * @version 1.0 2019/7/16
 * @author Wei
 */
@Data
public class BannerVO implements Serializable {

    private String bannerId;
    private String bannerAddress;
    private String bannerUrl;

    // getter 和 setter方法，toString方法

}
