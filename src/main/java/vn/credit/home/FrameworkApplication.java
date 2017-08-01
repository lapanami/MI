package vn.credit.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "vn.credit.home.controller", "vn.credit.home.entity", "vn.credit.home.dao",
		"vn.credit.home.service", "vn.credit.home.config" })
public class FrameworkApplication extends WebMvcConfigurerAdapter {
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}

	public static void main(String[] args) {
		SpringApplication.run(FrameworkApplication.class, args);
	}
}
