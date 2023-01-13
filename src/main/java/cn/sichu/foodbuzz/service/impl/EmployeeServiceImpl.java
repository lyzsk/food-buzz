package cn.sichu.foodbuzz.service.impl;

import cn.sichu.foodbuzz.entity.Employee;
import cn.sichu.foodbuzz.mapper.EmployeeMapper;
import cn.sichu.foodbuzz.service.IEmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
