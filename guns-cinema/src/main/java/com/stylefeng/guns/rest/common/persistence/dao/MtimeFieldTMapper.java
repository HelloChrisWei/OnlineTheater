package com.stylefeng.guns.rest.common.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.common.persistence.model.MtimeFieldT;
import com.stylefeng.guns.rest.modular.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.rest.modular.cinema.vo.HallInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author cskaoyan
 * @since 2019-07-16
 */
@Repository
public interface MtimeFieldTMapper extends BaseMapper<MtimeFieldT> {

    List<FilmInfoVO> getFilmInfos(@Param("cinemaId") int cinemaId);

    HallInfoVO getHallInfo(@Param("fieldId") int fieldId);

    FilmInfoVO getFilmInfoById(@Param("fieldId") int fieldId);

}
