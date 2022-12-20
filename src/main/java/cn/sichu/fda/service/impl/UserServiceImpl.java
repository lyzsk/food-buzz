package cn.sichu.fda.service.impl;

import cn.sichu.fda.entity.User;
import cn.sichu.fda.mapper.UserMapper;
import cn.sichu.fda.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sichu
 * @since 2022-12-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
