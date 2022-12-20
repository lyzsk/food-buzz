package cn.sichu.fda;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author sichu
 * @date 2022/12/20
 **/
public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                "jdbc:mysql://localhost:3306/reggie?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "root").globalConfig(builder -> {
                builder.author("sichu") // 设置作者
                    // .enableSwagger() // 开启 swagger 模式
                    .fileOverride() // 覆盖已生成文件
                    .outputDir("C://users/sichu/dev/mpGenerator"); // 指定输出目录
            }).packageConfig(builder -> {
                builder.parent("cn.sichu") // 设置父包名
                    .moduleName("fda") // 设置父包模块名
                    .pathInfo(Collections.singletonMap(OutputFile.xml,
                        "C://users/sichu/dev/mpGenerator")); // 设置mapperXml生成路径
            }).strategyConfig(builder -> {
                builder.addInclude("address_book", "category", "dish",
                    "dish_flavor", "employee", "order_detail", "orders", "setmeal",
                    "setmeal_dish", "shopping_cart", "user"); // 设置需要生成的表名
            }).templateEngine(
                new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
    }
}
