package cn.sichu.foodbuzz.dto;

import cn.sichu.foodbuzz.entity.Setmeal;
import cn.sichu.foodbuzz.entity.SetmealDish;

import java.util.List;

/**
 * @author sichu
 * @date 2022/12/22
 **/
public class SetmealDto extends Setmeal {
    private List<SetmealDish> setmealDishes;

    private String categoryName;

    public SetmealDto() {
    }

    public SetmealDto(List<SetmealDish> setmealDishes, String categoryName) {
        this.setmealDishes = setmealDishes;
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "SetmealDto{" + "setmealDishes=" + setmealDishes
            + ", categoryName='" + categoryName + '\'' + '}';
    }

    public List<SetmealDish> getSetmealDishes() {
        return setmealDishes;
    }

    public void setSetmealDishes(List<SetmealDish> setmealDishes) {
        this.setmealDishes = setmealDishes;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
