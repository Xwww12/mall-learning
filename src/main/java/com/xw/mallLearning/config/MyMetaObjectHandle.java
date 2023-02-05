package com.xw.mallLearning.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sun.prism.impl.BaseContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandle implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //插入时填充的字段名及填充的值
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新时填充的字段名及填充的值
        metaObject.setValue("updateTime", LocalDateTime.now());
    }
}
