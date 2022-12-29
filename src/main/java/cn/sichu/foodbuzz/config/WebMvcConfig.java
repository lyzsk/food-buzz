package cn.sichu.foodbuzz.config;

import cn.sichu.foodbuzz.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author sichu
 * @date 2022/12/20
 **/
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 设置静态资源映射
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/backend/**")
            .addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**")
            .addResourceLocations("classpath:/front/");
    }

    /**
     * 扩展MVC框架的消息转换器
     *
     * @param converters
     */
    @Override
    protected void extendMessageConverters(
        List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器...");
        // 创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter =
            new MappingJackson2HttpMessageConverter();
        // 引入JacksonObjectMapper
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        // 把配置好的消息转换器引入MVC框架中, 记得加index头插
        converters.add(0, messageConverter);
    }
}
