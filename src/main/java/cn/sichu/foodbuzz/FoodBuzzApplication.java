package cn.sichu.foodbuzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author sichu
 */
@EnableCaching
@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement
public class FoodBuzzApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodBuzzApplication.class, args);
    }

}
