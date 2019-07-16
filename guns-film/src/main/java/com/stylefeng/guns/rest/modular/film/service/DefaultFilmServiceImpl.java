package com.stylefeng.guns.rest.modular.film.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.rest.modular.film.FilmServiceApi;
import org.springframework.stereotype.Component;

/**
 * 
 * @version 1.0 2019/7/16
 * @author Wei
 */
@Component
@Service(interfaceClass = FilmServiceApi.class)
public class DefaultFilmServiceImpl {

}
