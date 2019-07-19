package com.stylefeng.guns.rest.modular.cinema;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.modular.cinema.vo.*;

import java.util.List;

public interface CinemaService {

    Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO);

    List<BrandVO> getBrands(int brandId);

    List<HallTypeVO> getHallTypes(int hallType);

    List<AreaVO> getAreas(int areaId);

    CinemaInfoVO getCinemaInfoById(int cinemaId);

    List<FilmInfoVO> getFilmInfoByCinemaId(int cinemaId);

    HallInfoVO getFilmFieldInfo(int fieldId);

    FilmInfoVO getFilmInfoByFieldId(int fieldId);

}
