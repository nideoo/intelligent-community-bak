package com.enc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@EnableTransactionManagement
@MapperScan("com.enc.dao.mapper")
@ServletComponentScan
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class InterfaceApp {

	public static void main(String[] args) {
		SpringApplication.run(InterfaceApp.class, args);
	}
}
