package cn.sichu.fda.service.impl;

import cn.sichu.fda.entity.AddressBook;
import cn.sichu.fda.mapper.AddressBookMapper;
import cn.sichu.fda.service.IAddressBookService;
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
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements IAddressBookService {

}
