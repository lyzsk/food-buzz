package cn.sichu.fda.service.impl;

import cn.sichu.fda.entity.Employee;
import cn.sichu.fda.mapper.EmployeeMapper;
import cn.sichu.fda.service.IEmployeeService;
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
