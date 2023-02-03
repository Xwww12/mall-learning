package com.xw.mallLearning.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用于生产MBG的代码
 */
public class Generator {
    // 基础信息配置
    private static final String URL = "jdbc:mysql://localhost:3306/mall?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String PARENT_PACKAGE_NAME = "com.xw.mallLearning.generator";  // 包名
    private static final String PROJECT_ROOT_PATH = System.getProperty("user.dir");
    private static final String PROJECT_NAME = "mall-learning";     // 当前项目名称
    private static final String MODULE_NAME = "mbg";      // 模块名称, 代码会生成在generator/MODULE_NAME下


    /**
     * 执行此处
     */
    public static void main(String[] args) {
        simpleGenerator();
    }

    protected static void simpleGenerator() {
        // 添加需要自动生成代码的表名, 为空则生成所有表对应代码
        List<String> tables = new ArrayList<>();
        tables.add("pms_brand");

        // 包路径
        String packagePath = PROJECT_ROOT_PATH + "/" + PROJECT_NAME + "/src/main/java";
        // XML文件的路径
        String mapperXmlPath = PROJECT_ROOT_PATH + "/" + PROJECT_NAME + "/src/main/resources/mapper";

        // 开始执行代码生成
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                // 1. 全局配置
                .globalConfig(builder -> builder
                        // 作者名称
                        .author("xw")
                        // 开启覆盖已生成的文件。注释掉则关闭覆盖。
                        .fileOverride()
                        // 禁止打开输出目录。注释掉则生成完毕后，自动打开生成的文件目录。
                        .disableOpenDir()
                        // 指定输出目录。如果指定，Windows生成至D盘根目录下，Linux or MAC 生成至 /tmp 目录下。
                        .outputDir(packagePath)
                        // 开启swagger2.注释掉则默认关闭。
                        // .enableSwagger()
                        // 指定时间策略。
                        .dateType(DateType.TIME_PACK)
                        // 注释时间策略。
                        .commentDate("yyyy-MM-dd")
                )

                // 2. 包配置
                .packageConfig(builder -> builder
                        // 设置父表名
                        .parent(PARENT_PACKAGE_NAME)
                        .moduleName(MODULE_NAME)
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                        .mapper("mapper")
                        .xml("mapper")
                        // mapper.xml 文件的路径。单模块下，其他文件路径默认即可。
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mapperXmlPath))
                )

                // 3. 策略配置
                .strategyConfig(builder -> builder.addInclude(tables)
                        // 阶段1：Entity实体类策略配置
                        .entityBuilder()
                        // 开启生成实体时生成字段注解。
                        // 会在实体类的属性前，添加[@TableField("nickname")]
                        .enableTableFieldAnnotation()
                        // 逻辑删除字段名(数据库)。
                        //.logicDeleteColumnName("is_delete")
                        // 逻辑删除属性名(实体)。
                        // 会在实体类的该字段属性前加注解[@TableLogic]
                        //.logicDeletePropertyName("isDelete")
                        // 会在实体类的该字段上追加注解[@TableField(value = "create_time", fill = FieldFill.INSERT)]
                        .addTableFills(new Column("create_time", FieldFill.INSERT))
                        // 会在实体类的该字段上追加注解[@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)]
                        .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                        // 阶段2：Mapper策略配置
                        .mapperBuilder()
                        // 开启 @Mapper 注解。
                        // 会在mapper接口上添加注解[@Mapper]
                        .enableMapperAnnotation()
                        // 启用 BaseResultMap 生成。
                        // 会在mapper.xml文件生成[通用查询映射结果]配置。
                        .enableBaseResultMap()
                        // 启用 BaseColumnList。
                        // 会在mapper.xml文件生成[通用查询结果列 ]配置
                        .enableBaseColumnList()
                        // 阶段4：Controller策略配置
                        .controllerBuilder()
                        // 会在控制类中加[@RestController]注解。
                        .enableRestStyle()
                        // 开启驼峰转连字符
                        .enableHyphenStyle()
                        .build()
                )

                // 4. 模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                //.templateEngine(new BeetlTemplateEngine())
                .templateEngine(new FreemarkerTemplateEngine())

                // 5. 执行
                .execute();
    }
}
