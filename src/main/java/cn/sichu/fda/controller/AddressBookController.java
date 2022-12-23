package cn.sichu.fda.controller;

import cn.sichu.fda.common.BaseContext;
import cn.sichu.fda.common.Result;
import cn.sichu.fda.entity.AddressBook;
import cn.sichu.fda.service.IAddressBookService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sichu
 * @since 2022-12-20
 */
@Slf4j
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {
    @Autowired
    private IAddressBookService addressBookService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 新增地址
     *
     * @param addressBook
     * @return Result<AddressBook>
     */
    @PostMapping
    public Result<AddressBook> save(@RequestBody AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBookService.save(addressBook);
        return Result.success(addressBook);
    }

    /**
     * 设置默认地址
     *
     * @param addressBook
     * @return Result<AddressBook>
     */
    @PutMapping("default")
    public Result<AddressBook> setDefault(
        @RequestBody AddressBook addressBook) {
        LambdaUpdateWrapper<AddressBook> updateWrapper =
            new LambdaUpdateWrapper<>();
        updateWrapper.eq(AddressBook::getUserId, BaseContext.getCurrentId())
            .set(AddressBook::getIsDefault, 0);
        addressBookService.update(updateWrapper);
        addressBook.setIsDefault(1);
        addressBookService.updateById(addressBook);
        return Result.success(addressBook);
    }

    /**
     * 根据id查询地址
     *
     * @param id
     * @return Result<AddressBook>
     */
    @GetMapping("/{id}")
    public Result<AddressBook> get(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        return addressBook == null ? Result.error("没有找到该对象") :
            Result.success(addressBook);
    }

    /**
     * 删除地址
     *
     * @param id
     * @return Result<String>
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam("id") Long id) {
        if (id == null) {
            return Result.error("请求异常");
        }
        LambdaQueryWrapper<AddressBook> queryWrapper =
            new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getId, id)
            .eq(AddressBook::getUserId, BaseContext.getCurrentId());
        addressBookService.remove(queryWrapper);
        return Result.success("删除地址成功");
    }

    /**
     * 修改地址
     *
     * @param addressBook
     * @return Result<String>
     */
    @PutMapping
    public Result<String> update(@RequestBody AddressBook addressBook) {
        if (addressBook == null) {
            return Result.error("请求异常");
        }
        addressBookService.updateById(addressBook);
        return Result.success("修改成功");
    }

    /**
     * 查询默认地址
     *
     * @return Result<AddressBook>
     */
    @GetMapping("default")
    public Result<AddressBook> getDefault() {
        LambdaQueryWrapper<AddressBook> queryWrapper =
            new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId, BaseContext.getCurrentId())
            .eq(AddressBook::getIsDefault, 1);
        AddressBook addressBook = addressBookService.getOne(queryWrapper);
        return addressBook == null ? Result.error("没有找到地址") :
            Result.success(addressBook);
    }

    /**
     * 查询指定用户的全部地址
     *
     * @param addressBook
     * @return Result<List < AddressBook>>
     */
    @GetMapping("/list")
    public Result<List<AddressBook>> list(AddressBook addressBook) {
        log.info("BaseContext: {}, httpServletRequest: {}",
            BaseContext.getCurrentId(),
            (Long)request.getSession().getAttribute("user"));
        // addressBook.setUserId((Long)request.getSession().getAttribute("user"));
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("查询到用户地址: {}", addressBook);
        LambdaQueryWrapper<AddressBook> queryWrapper =
            new LambdaQueryWrapper<>();
        queryWrapper.eq(addressBook.getUserId() != null, AddressBook::getUserId,
            addressBook.getUserId()).orderByAsc(AddressBook::getId);
        return Result.success(addressBookService.list(queryWrapper));
    }
}
