package com.huang.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.Hutool;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huang.common.BaseResponse;
import com.huang.common.ErrorCode;
import com.huang.common.ResultUtils;
import com.huang.exception.BusinessException;
import com.huang.model.entity.User;
import com.huang.model.vo.LoginUserVO;
import com.huang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.huang.service.impl.UserServiceImpl.SALT;


@RestController
@RequestMapping("/sms")
@Slf4j
public class SmsController {

    private final String CODE_PREFIX = "verification:"; // Redis Key 的前缀

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 模拟发送验证码
     */
    @GetMapping("/sendSms")
    public BaseResponse<String> sendSmsCode(@RequestParam("phoneNumber") String phoneNumber) {
        log.info("发送验证码，手机号：{}", phoneNumber);
        if (StringUtils.isBlank(phoneNumber)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "手机号不能为空");
        }

        boolean verifyPhoneNumber = userService.verifyPhoneNumber(phoneNumber);


//        if (!verifyPhoneNumber) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "手机号不存在");
//        }

        String code  = RandomUtil.randomNumbers(4);
        String key = CODE_PREFIX + phoneNumber;
        redisTemplate.opsForValue().set(key, code, 2, TimeUnit.MINUTES); // 有效期为 2 分钟

        return ResultUtils.success("模拟短信验证码："+code);
    }

    @PostMapping("/verify")
    public BaseResponse<LoginUserVO> verifyCode(@RequestParam String phoneNumber, @RequestParam String code) {

        String key = CODE_PREFIX + phoneNumber;
        String redisCode = redisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(redisCode)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "验证码已过期");
        }

        String storedCode = redisTemplate.opsForValue().get(key);


//        如果手机号不存在就注册
        boolean verifyPhoneNumber = userService.verifyPhoneNumber(phoneNumber);
        if (!verifyPhoneNumber) {
            User user = new User();
            user.setPhoneNumber(phoneNumber);
            user.setUserAccount(phoneNumber);
            user.setUserName(phoneNumber);
            String defaultPassword = "12345678";
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + defaultPassword).getBytes());
            user.setUserPassword(encryptPassword);
            userService.save(user);
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phoneNumber", phoneNumber);
            User registerUser = userService.getOne(queryWrapper);
            StpUtil.login(registerUser.getId());
            LoginUserVO loginUserVO = userService.getLoginUserVO(user, StpUtil.getTokenInfo());
            return ResultUtils.success(loginUserVO);
        }

        // 验证成功
        if (storedCode != null && storedCode.equals(code)) {
            redisTemplate.delete(key); // 验证通过后删除验证码
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phoneNumber", phoneNumber);
            User user = userService.getOne(queryWrapper);
            StpUtil.login(user.getId());
            LoginUserVO loginUserVO = userService.getLoginUserVO(user, StpUtil.getTokenInfo());
            return ResultUtils.success(loginUserVO);
        }else{
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "验证码错误");
        }
    }
}
