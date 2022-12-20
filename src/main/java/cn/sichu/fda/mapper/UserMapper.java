package cn.sichu.fda.mapper;

import cn.sichu.fda.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sichu
 * @since 2022-12-20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
