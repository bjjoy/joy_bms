package cn.bjjoy.web.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@ServletComponentScan
public class JoyBmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoyBmsApplication.class, args);
	}
}
