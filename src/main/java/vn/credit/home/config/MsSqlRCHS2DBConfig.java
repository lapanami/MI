///**
// * @author loc.mh
// */
//package vn.credit.home.config;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//import java.util.stream.Collectors;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PropertiesLoaderUtils;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
///**
// * @author loc.mh
// *
// */
////@Configuration
////@EnableTransactionManagement
////@EnableJpaRepositories(entityManagerFactoryRef = "mssqlRCHS2EntityManager", transactionManagerRef = "mssqlRCHS2TransactionManager", basePackages = {"vn.credit.home.dao.mssqlrchs2"})
////public class MsSqlRCHS2DBConfig {
////
////	@Value("${spring.jpa.properties.hibernate.mssql.dialect}")
////	private String dialect;
////
////	@Bean(name = "mssqlRCHS2DataSource")
////	@ConfigurationProperties(prefix = "spring.datasource.mssql.rchs2")
////	public DataSource mssqlRCHS2DataSource() {
////		return DataSourceBuilder.create().build();
////	}
////
////	@Bean(name = "mssqlRCHS2EntityManager")
////	public LocalContainerEntityManagerFactoryBean mssqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
////		return builder.dataSource(mssqlRCHS2DataSource()).properties(hibernateProperties())
////				.packages("vn.credit.home.dao.mssqlrchs2","vn.credit.home.mssqlrchs2.entity").build();
////	}
////
////	@Bean(name = "mssqlRCHS2TransactionManager")
////	public PlatformTransactionManager mssqlTransactionManager(
////			@Qualifier("mssqlRCHS2EntityManager") EntityManagerFactory entityManagerFactory) {
////		return new JpaTransactionManager(entityManagerFactory);
////	}
////
////	private Map<String, ?> hibernateProperties() {
////
////		Resource resource = new ClassPathResource("hibernate.properties");
////
////		try {
////			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
////			properties.setProperty("hibernate.dialect", dialect);
////			return properties.entrySet().stream()
////					.collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue()));
////		} catch (IOException e) {
////			return new HashMap<String, Object>();
////		}
////	}
////}
