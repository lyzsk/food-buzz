package cn.sichu.foodbuzz.dto;

import cn.sichu.foodbuzz.entity.Dish;
import cn.sichu.foodbuzz.entity.DishFlavor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sichu
 * @date 2022/12/21
 **/
public class DishDto extends Dish {
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;

    public DishDto() {
    }

    public DishDto(List<DishFlavor> flavors, String categoryName,
        Integer copies) {
        this.flavors = flavors;
        this.categoryName = categoryName;
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "DishDto{" + "flavors=" + flavors + ", categoryName='"
            + categoryName + '\'' + ", copies=" + copies + '}';
    }

    public List<DishFlavor> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<DishFlavor> flavors) {
        this.flavors = flavors;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }
}
