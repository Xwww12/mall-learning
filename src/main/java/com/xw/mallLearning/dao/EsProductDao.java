package com.xw.mallLearning.dao;

import com.xw.mallLearning.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
