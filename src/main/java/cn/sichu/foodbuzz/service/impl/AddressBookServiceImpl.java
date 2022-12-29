package cn.sichu.foodbuzz.service.impl;

import cn.sichu.foodbuzz.entity.AddressBook;
import cn.sichu.foodbuzz.mapper.AddressBookMapper;
import cn.sichu.foodbuzz.service.IAddressBookService;
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
