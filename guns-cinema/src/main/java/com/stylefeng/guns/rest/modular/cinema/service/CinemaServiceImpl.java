package com.stylefeng.guns.rest.modular.cinema.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.MtimeAreaDictT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeBrandDictT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeHallDictT;
import com.stylefeng.guns.rest.modular.cinema.CinemaService;
import com.stylefeng.guns.rest.modular.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Service(interfaceClass = CinemaService.class, executes = 10)
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private MtimeCinemaTMapper mtimeCinemaTMapper;

    @Autowired
    private MtimeAreaDictTMapper mtimeAreaDictTMapper;

    @Autowired
    private MtimeBrandDictTMapper mtimeBrandDictTMapper;

    @Autowired
    private MtimeHallDictTMapper mtimeHallDictTMapper;

    @Autowired
    private MtimeHallFilmInfoTMapper mtimeHallFilmInfoTMapper;

    @Autowired
    private MtimeFieldTMapper mtimeFieldTMapper;


    @Override
    public Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO) {

        EntityWrapper<MtimeCinemaT> entityWrapper = new EntityWrapper<>();
        Page<MtimeCinemaT> page = new Page<>(cinemaQueryVO.getNowPage(), cinemaQueryVO.getPageSize());
        List<CinemaVO> cinemas = new ArrayList<>();

        if (cinemaQueryVO.getBrandId() != 99) {
            entityWrapper.eq("brand_id", cinemaQueryVO.getBrandId());
        }
        if (cinemaQueryVO.getDistrictId() != 99) {
            entityWrapper.eq("area_id", cinemaQueryVO.getDistrictId());
        }
        if (cinemaQueryVO.getHallType() != 99) {
            entityWrapper.like("hall_ids", "%#+" + cinemaQueryVO.getHallType() + "%#+");
        }

        List<MtimeCinemaT> mtimeCinemaTS = mtimeCinemaTMapper.selectPage(page, entityWrapper);
        for (MtimeCinemaT mtimeCinemaT : mtimeCinemaTS) {
            CinemaVO cinemaVO = new CinemaVO();

            cinemaVO.setUuid(mtimeCinemaT.getUuid() + "");
            cinemaVO.setCinemaName(mtimeCinemaT.getCinemaName());
            cinemaVO.setAddress(mtimeCinemaT.getCinemaAddress());
            cinemaVO.setMinimumPrice(mtimeCinemaT.getMinimumPrice() + "");

            cinemas.add(cinemaVO);
        }

        Integer count = mtimeCinemaTMapper.selectCount(entityWrapper);

        Page<CinemaVO> result = new Page<>();
        result.setRecords(cinemas);
        result.setSize(cinemaQueryVO.getPageSize());
        result.setTotal(count);

        return result;
    }

    @Override
    public List<BrandVO> getBrands(int brandId) {
        boolean flag = false;
        List<BrandVO> brandVOS = new ArrayList<>();
        MtimeBrandDictT brandDictT = mtimeBrandDictTMapper.selectById(brandId);

        if (brandId == 99 || brandDictT == null || brandDictT.getUuid() == null) {
            flag = true;
        }

        List<MtimeBrandDictT> mtimeBrandDictTS = mtimeBrandDictTMapper.selectList(null);
        for (MtimeBrandDictT brand : mtimeBrandDictTS) {
            BrandVO brandVO = new BrandVO();
            brandVO.setBrandId(brand.getUuid() + "");
            brandVO.setBrandName(brand.getShowName());

            if (flag) {
                if (brand.getUuid() == 99) {
                    brandVO.setActive(true);
                }
            } else {
                if (brand.getUuid() == brandId) {
                    brandVO.setActive(true);
                }
            }

            brandVOS.add(brandVO);
        }
        return brandVOS;
    }

    @Override
    public List<HallTypeVO> getHallTypes(int hallType) {
        boolean flag = false;
        List<HallTypeVO> hallTypeVOS = new ArrayList<>();
        MtimeHallDictT mtimeHallDictT = mtimeHallDictTMapper.selectById(hallType);

        if (hallType == 99 || mtimeHallDictT == null || mtimeHallDictT.getUuid() == null) {
            flag = true;
        }

        List<MtimeHallDictT> mtimeHallDictTS = mtimeHallDictT.selectList(null);
        for (MtimeHallDictT hall : mtimeHallDictTS) {
            HallTypeVO hallTypeVO = new HallTypeVO();
            hallTypeVO.setHalltypeId(hall.getUuid() + "");
            hallTypeVO.setHalltypeName(hall.getShowName());

            if (flag) {
                if (hall.getUuid() == 99) {
                    hallTypeVO.setActive(true);
                }
            } else {
                if (hall.getUuid() == hallType) {
                    hallTypeVO.setActive(true);
                }
            }
            hallTypeVOS.add(hallTypeVO);
        }
        return hallTypeVOS;
    }

    @Override
    public List<AreaVO> getAreas(int areaId) {
        boolean flag = false;
        List<AreaVO> areaVOS = new ArrayList<>();
        MtimeAreaDictT mtimeAreaDictT = mtimeAreaDictTMapper.selectById(areaId);

        if (areaId == 99 || mtimeAreaDictT == null || mtimeAreaDictT.getUuid() == null) {
            flag = true;
        }

        List<MtimeAreaDictT> mtimeAreaDictTS = mtimeAreaDictTMapper.selectList(null);
        for (MtimeAreaDictT area : mtimeAreaDictTS) {
            AreaVO areaVO = new AreaVO();
            areaVO.setAreaId(area.getUuid() + "");
            areaVO.setAreaName(area.getShowName());

            if (flag) {
                if (area.getUuid() == 99) {
                    areaVO.setActive(true);
                }
            } else {
                if (area.getUuid() == areaId) {
                    areaVO.setActive(true);
                }
            }
            areaVOS.add(areaVO);
        }
        return areaVOS;
    }

    @Override
    public CinemaInfoVO getCinemaInfoById(int cinemaId) {
        MtimeCinemaT mtimeCinemaT = mtimeCinemaTMapper.selectById(cinemaId);
        CinemaInfoVO cinemaInfoVO = new CinemaInfoVO();

        cinemaInfoVO.setCinemaId(mtimeCinemaT.getUuid() + "");
        cinemaInfoVO.setImgUrl(mtimeCinemaT.getImgAddress());
        cinemaInfoVO.setCinemaName(mtimeCinemaT.getCinemaName());
        cinemaInfoVO.setCinemaAddress(mtimeCinemaT.getCinemaAddress());
        cinemaInfoVO.setCinemaPhone(mtimeCinemaT.getCinemaPhone());

        return cinemaInfoVO;
    }

    @Override
    public List<FilmInfoVO> getFilmInfoByCinemaId(int cinemaId) {
        List<FilmInfoVO> filmInfos = mtimeFieldTMapper.getFilmInfos(cinemaId);
        return filmInfos;
    }

    @Override
    public HallInfoVO getFilmFieldInfo(int fieldId) {
        HallInfoVO hallInfo = mtimeFieldTMapper.getHallInfo(fieldId);
        return hallInfo;
    }

    @Override
    public FilmInfoVO getFilmInfoByFieldId(int fieldId) {
        FilmInfoVO filmInfo = mtimeFieldTMapper.getFilmInfoById(fieldId);
        return filmInfo;
    }
}
