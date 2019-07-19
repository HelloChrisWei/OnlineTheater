package com.stylefeng.guns.rest.modular.cinema.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderQueryVO implements Serializable {

    private String cinemaId;

    private String filmPrice;

}
