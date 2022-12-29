package cn.sichu.fda;

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
public class FoodDeliveryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodDeliveryAppApplication.class, args);
    }

}
