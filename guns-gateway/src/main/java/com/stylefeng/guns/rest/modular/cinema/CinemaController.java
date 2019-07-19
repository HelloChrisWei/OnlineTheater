package com.stylefeng.guns.rest.modular.cinema;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.modular.cinema.vo.*;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cinema/")
public class CinemaController {

    private static final String IMG_PRE = "http://img.meetingshop.cn/";
    @Reference(interfaceClass = CinemaService.class, connections = 10, cache = "lru", check = false)
    private CinemaService cinemaService;

    @RequestMapping(value = "getCinemas")
    public ResponseVO getCinemas(CinemaQueryVO cinemaQueryVO) {
        try {
            Page<CinemaVO> cinemas = cinemaService.getCinemas(cinemaQueryVO);
            if (cinemas.getRecords() == null || cinemas.getRecords().size() == 0) {
                return ResponseVO.success("没有影院可查");
            } else {
                return ResponseVO.success(cinemas.getCurrent(), (int) cinemas.getPages(), "", cinemas.getRecords());
            }
        } catch (Exception e) {
            log.error("获取影院列表异常", e);
            return ResponseVO.serviceFail("查询影院列表失败");
        }
    }

    @RequestMapping(value = "getCondition")
    public ResponseVO getCondition(CinemaQueryVO cinemaQueryVO) {
        try {
            List<BrandVO> brands = cinemaService.getBrands(cinemaQueryVO.getBrandId());
            List<AreaVO> areas = cinemaService.getAreas(cinemaQueryVO.getDistrictId());
            List<HallTypeVO> hallTypes = cinemaService.getHallTypes(cinemaQueryVO.getHallType());

            CinemaConditionResponseVO cinemaConditionResponseVO = new CinemaConditionResponseVO();
            cinemaConditionResponseVO.setAreaList(areas);
            cinemaConditionResponseVO.setBrandList(brands);
            cinemaConditionResponseVO.setHalltypeList(hallTypes);

            return ResponseVO.success(cinemaConditionResponseVO);

        } catch (Exception e) {
            log.error("获取条件列表失败", e);
            return ResponseVO.serviceFail("获取影院查询条件失败");
        }
    }

    @RequestMapping(value = "getFields")
    public ResponseVO getFields(Integer cinemaId) {
        try {
            CinemaInfoVO cinemaInfoById = cinemaService.getCinemaInfoById(cinemaId);
            List<FilmInfoVO> filmInfoByCinemaId = cinemaService.getFilmInfoByCinemaId(cinemaId);

            CinemaFieldsResponseVO cinemaFieldsResponseVO = new CinemaFieldsResponseVO();
            cinemaFieldsResponseVO.setCinemaInfo(cinemaInfoById);
            cinemaFieldsResponseVO.setFilmList(filmInfoByCinemaId);

            return ResponseVO.success(IMG_PRE, cinemaFieldsResponseVO);
        } catch (Exception e) {
            log.error("获取播放场次失败", e);
            return ResponseVO.serviceFail("获取播放场次失败");
        }
    }

    @RequestMapping(value = "getFieldInfo", method = RequestMethod.POST)
    public ResponseVO getFieldInfo(Integer cinemaId, Integer fieldId) {
        try {
            CinemaInfoVO cinemaInfoById = cinemaService.getCinemaInfoById(cinemaId);
            FilmInfoVO filmInfoByFieldId = cinemaService.getFilmInfoByFieldId(fieldId);
            HallInfoVO filmFieldInfo = cinemaService.getFilmFieldInfo(fieldId);

            // 测试造假
            filmFieldInfo.setSoldSeats("1,2,3,4");

            CinemaFieldResponseVO cinemaFieldResponseVO = new CinemaFieldResponseVO();
            cinemaFieldResponseVO.setCinemaInfo(cinemaInfoById);
            cinemaFieldResponseVO.setFilmInfo(filmInfoByFieldId);
            cinemaFieldResponseVO.setHallInfo(filmFieldInfo);

            return ResponseVO.success(IMG_PRE, cinemaFieldResponseVO);
        } catch (Exception e) {
            log.error("获取选座信息失败", e);
            return ResponseVO.serviceFail("获取选座信息失败");
        }
    }
}
