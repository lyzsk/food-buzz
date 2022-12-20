package cn.sichu.fda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author sichu
 */
@ServletComponentScan
@SpringBootApplication
public class FoodDeliveryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodDeliveryAppApplication.class, args);
    }

}
