package cn.sichu.foodbuzz.service.impl;

import cn.sichu.foodbuzz.entity.User;
import cn.sichu.foodbuzz.mapper.UserMapper;
import cn.sichu.foodbuzz.service.IUserService;
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
