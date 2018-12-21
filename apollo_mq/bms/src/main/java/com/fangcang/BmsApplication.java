package com.fangcang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages={"com.fangcang.*.mapper","com.fangcang.*.*.mapper","com.fangcang.*.*.*.mapper"})
@EnableTransactionManagement
@EnableAsync
@EnableScheduling

public class BmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsApplication.class, args);
	}
}
