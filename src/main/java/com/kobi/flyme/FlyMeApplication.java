package com.kobi.flyme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = "com.kobi.flyme.utils") // Add the package containing UserMapper

public class FlyMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlyMeApplication.class, args);
	}

}
