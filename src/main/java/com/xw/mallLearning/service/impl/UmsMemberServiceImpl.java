package com.xw.mallLearning.service.impl;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.xw.mallLearning.common.api.CommonResult;
import com.xw.mallLearning.generator.mbg.entity.UmsMember;
import com.xw.mallLearning.generator.mbg.mapper.UmsMemberMapper;
import com.xw.mallLearning.service.IUmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.mallLearning.utils.CacheClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author xw
 * @since 2023-02-05
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements IUmsMemberService {

    @Resource
    private CacheClient cacheClient;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public CommonResult generateAuthCode(String telephone) {
        if (telephone == null || "".equals(telephone)) {
            return CommonResult.failed("请输入手机号");
        }
        if (!PhoneUtil.isMobile(telephone)) {
            return CommonResult.failed("手机号格式不正确");
        }

        // 生成6位验证码
        String code = RandomUtil.randomNumbers(6);
        // 存到redis
        String key = REDIS_KEY_PREFIX_AUTH_CODE + telephone;
        cacheClient.set(key, code);
        cacheClient.expire(key, AUTH_CODE_EXPIRE_SECONDS, TimeUnit.SECONDS);
        return CommonResult.success(code, "获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (StrUtil.isEmpty(authCode)) {
            return CommonResult.failed("请输入验证码");
        }
        String key = REDIS_KEY_PREFIX_AUTH_CODE + telephone;
        String code = cacheClient.get(key);
        if (!authCode.equals(code)) {
            return CommonResult.failed("验证码不正确或已过期");
        }
        return CommonResult.success(null, "验证码校验成功");
    }
}
