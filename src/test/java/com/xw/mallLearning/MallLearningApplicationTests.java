package com.xw.mallLearning;

import com.xw.mallLearning.nosql.elasticsearch.repository.EsProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;

@SpringBootTest
class MallLearningApplicationTests {
    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void testMongoDB() {
        System.out.println(mongoTemplate.getCollectionNames());
    }

}
