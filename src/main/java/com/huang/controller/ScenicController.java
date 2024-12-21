package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.annotation.AuthCheck;
import com.huang.common.BaseResponse;
import com.huang.common.DeleteRequest;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.constant.UserConstant;
import com.huang.exception.BusinessException;
import com.huang.exception.ThrowUtils;
import com.huang.model.dto.scenic.ScenicAddRequest;

import com.huang.model.dto.scenic.ScenicQueryRequest;
import com.huang.model.dto.scenic.ScenicUpdateRequest;
import com.huang.model.entity.Scenic;
import com.huang.model.entity.User;
import com.huang.model.vo.ScenicVO;
import com.huang.service.ScenicService;
import com.huang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 景区管理接口
 *
 * 
 * 
 */
@RestController
@RequestMapping("/scenic")
@Slf4j
public class ScenicController {

    @Resource
    private ScenicService scenicService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建
     *
     * @param scenicAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addScenic(@RequestBody ScenicAddRequest scenicAddRequest) {
        if (scenicAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        Scenic scenic = new Scenic();
        BeanUtils.copyProperties(scenicAddRequest, scenic);
        List<String> carouselImages = scenicAddRequest.getCarouselImages();
        List<String> tags = scenicAddRequest.getTags();
        if (tags != null) {
            scenic.setTags(JSONUtil.toJsonStr(tags));
        }
        if (carouselImages != null) {
            scenic.setCarouselImages(JSONUtil.toJsonStr(carouselImages));
        }
        scenicService.validScenic(scenic, true);
        boolean result = scenicService.save(scenic);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newScenicId = scenic.getId();
        return ResultUtils.success(newScenicId);
    }

    /**
     * 删除(仅管理员)
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteScenic(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 先判断是否已登录
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // 仅管理员可删除
        if (!StpUtil.hasRole(UserConstant.ADMIN_ROLE)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        long id = deleteRequest.getId();
        // 判断是否存在
        Scenic oldScenic = scenicService.getById(id);
        ThrowUtils.throwIf(oldScenic == null, ErrorCode.NOT_FOUND_ERROR);
        boolean b = scenicService.removeById(id);
        return ResultUtils.success(b);
    }
//
    /**
     * 更新（仅管理员）
     *
     * @param scenicUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateScenic(@RequestBody ScenicUpdateRequest scenicUpdateRequest) {
        System.out.println("更新"+scenicUpdateRequest);
        if (scenicUpdateRequest == null || scenicUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Scenic scenic = new Scenic();
        BeanUtils.copyProperties(scenicUpdateRequest, scenic);
        List<String> tags = scenicUpdateRequest.getTagList();
        List<String> carouselImages = scenicUpdateRequest.getCarouselImagesList();
        System.out.println("轮播图"+carouselImages);
        if (tags != null) {
            scenic.setTags(JSONUtil.toJsonStr(tags));
        }
        if (carouselImages != null) {
            scenic.setCarouselImages(JSONUtil.toJsonStr(carouselImages));
        }
        // 参数校验
        scenicService.validScenic(scenic, false);
        long id = scenicUpdateRequest.getId();
        // 判断是否存在
        Scenic oldScenic = scenicService.getById(id);
        ThrowUtils.throwIf(oldScenic == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = scenicService.updateById(scenic);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取（VO）
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<ScenicVO> getScenicVOById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Scenic scenic = scenicService.getById(id);
        if (scenic == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(ScenicVO.objToVo(scenic));
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Scenic> getScenicById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Scenic scenic = scenicService.getById(id);
        if (scenic == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(scenic);
    }

    /**
     *
     *
     * @param scenicQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<Scenic>> listScenicByPage(@RequestBody ScenicQueryRequest scenicQueryRequest) {
        long current = scenicQueryRequest.getCurrent();
        long size = scenicQueryRequest.getPageSize();
        Page<Scenic> scenicPage = scenicService.page(new Page<>(current, size),
                scenicService.getQueryWrapper(scenicQueryRequest));
        return ResultUtils.success(scenicPage);
    }

    /**
     * 分页获取列表（封装类）
     *
     * @param scenicQueryRequest
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<ScenicVO>> listScenicVOByPage(@RequestBody ScenicQueryRequest scenicQueryRequest) {
        long current = scenicQueryRequest.getCurrent();
        long size = scenicQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 101, ErrorCode.PARAMS_ERROR);
        Page<Scenic> scenicPage = scenicService.page(new Page<>(current, size),
                scenicService.getQueryWrapper(scenicQueryRequest));
        return ResultUtils.success(scenicService.getScenicVOPage(scenicPage));
    }
//
//    /**
//     * 分页获取当前用户创建的资源列表
//     *
//     * @param scenicQueryRequest
//     * @return
//     */
//    @PostMapping("/my/list/page/vo")
//    public BaseResponse<Page<ScenicVO>> listMyScenicVOByPage(@RequestBody ScenicQueryRequest scenicQueryRequest) {
//        if (scenicQueryRequest == null) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 先判断是否已登录
//        if (!StpUtil.isLogin()) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
//        }
//        User loginUser = userService.getLoginUser(StpUtil.getLoginIdAsLong());
//        scenicQueryRequest.setUserId(loginUser.getId());
//        long current = scenicQueryRequest.getCurrent();
//        long size = scenicQueryRequest.getPageSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
//        Page<Scenic> scenicPage = scenicService.page(new Page<>(current, size),
//                scenicService.getQueryWrapper(scenicQueryRequest));
//        // TODO 点赞业务有问题
//        return ResultUtils.success(scenicService.getScenicVOPage(scenicPage));
//    }
//
//    // endregion
//
//    /**
//     * 分页搜索（从 ES 查询，封装类）
//     *
//     * @param scenicQueryRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/search/page/vo")
//    public BaseResponse<Page<ScenicVO>> searchScenicVOByPage(@RequestBody ScenicQueryRequest scenicQueryRequest,
//            HttpServletRequest request) {
//        long size = scenicQueryRequest.getPageSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
//        Page<Scenic> scenicPage = scenicService.searchFromEs(scenicQueryRequest);
//        return ResultUtils.success(scenicService.getScenicVOPage(scenicPage));
//    }
//
//    /**
//     * 编辑（用户）
//     *
//     * @param scenicEditRequest
//     * @return
//     */
//    @PostMapping("/edit")
//    public BaseResponse<Boolean> editScenic(@RequestBody ScenicEditRequest scenicEditRequest) {
//        if (scenicEditRequest == null || scenicEditRequest.getId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 先判断是否已登录
//        if (!StpUtil.isLogin()) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
//        }
//        Scenic scenic = new Scenic();
//        BeanUtils.copyProperties(scenicEditRequest, scenic);
//        List<String> tags = scenicEditRequest.getTags();
//        if (tags != null) {
//            scenic.setTags(JSONUtil.toJsonStr(tags));
//        }
//        // 参数校验
//        scenicService.validScenic(scenic, false);
//        long id = scenicEditRequest.getId();
//        // 判断是否存在
//        Scenic oldScenic = scenicService.getById(id);
//        ThrowUtils.throwIf(oldScenic == null, ErrorCode.NOT_FOUND_ERROR);
//        // 仅本人或管理员可编辑
//        // todo 验证用户权限
//        if (!oldScenic.getUserId().equals(StpUtil.getLoginIdAsLong()) && !userService.isAdmin(StpUtil.getLoginIdAsLong())) {
//            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//        }
//        boolean result = scenicService.updateById(scenic);
//        return ResultUtils.success(result);
//    }

}
