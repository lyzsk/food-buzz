package cn.sichu.fda.controller;

import cn.sichu.fda.common.Result;
import cn.sichu.fda.entity.User;
import cn.sichu.fda.service.IUserService;
import cn.sichu.fda.utils.ValidateCodeUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 * <p>
 * 缓存短信验证码
 * 注入RedisTemplate对象, 操作Redis.
 * 在sendMsg方法中, 将随机生成的验证码缓存到Redis中, 并设置有效期5分钟.
 * 在login方法中, 从Redis中获取缓存的验证码, 如果登陆成功则删除Redis中的缓存.
 *
 * @author sichu
 * @since 2022-12-20
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/sendMsg")
    public Result<String> sendMsg(@RequestBody User user, HttpSession session) {
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)) {
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code = {}", code);
            // session 保存
            // session.setAttribute(phone, code);

            // redis缓存验证码
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return Result.success("手机验证码发送成功");
        }
        return Result.error("短信发送失败");
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody Map map, HttpSession session) {
        log.info(map.toString());
        String phone = map.get("phone").toString();
        String code = map.get("code").toString().toString();
        // 从 session 中获取验证码
        // Object codeInSession = session.getAttribute(phone);

        // 从 redis 中获取验证码
        Object codeInRedis = redisTemplate.opsForValue().get(phone);
        if (codeInRedis != null && codeInRedis.equals(code)) {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                user = new User();
                user.setPhone(phone);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());
            // 如果登陆成功, 删除redis中缓存
            redisTemplate.delete(phone);
            return Result.success(user);
        }
        return Result.error("登陆失败");
    }

    /**
     * @param request
     * @return
     */
    @PostMapping("/loginout")
    public Result<String> logout(HttpServletRequest request) {
        request.removeAttribute("user");
        return Result.success("退出成功");
    }
}
