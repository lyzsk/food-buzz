package cn.sichu.fda.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author sichu
 * @date 2022/12/21
 **/
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Autowired
    private HttpServletRequest request;

    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]...");
        log.info(metaObject.toString());
        metaObject.setValue("updateTime", LocalDateTime.now());
        long id = Thread.currentThread().getId();
        log.info("线程id为:{}, 当前用户id为: {}", id, BaseContext.getCurrentId());
        // metaObject.setValue("updateUser", BaseContext.getCurrentId());
        Long userId = (Long)request.getSession().getAttribute("employee");
        log.info("通过httpServletRequest得到的用户id为: {}", userId);
        metaObject.setValue("updateUser", userId);
    }
}
