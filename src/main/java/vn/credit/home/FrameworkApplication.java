package vn.credit.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "vn.credit.home.controller", "vn.credit.home.entity", "vn.credit.home.dao",
		"vn.credit.home.service", "vn.credit.home.config" })
public class FrameworkApplication extends WebMvcConfigurerAdapter {
	// @Override
	// public void addInterceptors(InterceptorRegistry registry) {
	// registry.addInterceptor(new
	// SecurityInterceptor()).addPathPatterns("/*").excludePathPatterns("/");
	// }

	// @Bean
	// EmbeddedServletContainerCustomizer containerCustomizer(
	//
	// @Value("${server.port}") final int port, @Value("${keystore.file}") Resource
	// keystoreFile,
	// @Value("${keystore.alias}") final String alias,
	// @Value("${keystore.password}") final String keystorePass,
	// @Value("${keystore.type}") final String keystoreType) throws Exception {
	// final String absoluteKeystoreFile = keystoreFile.getFile().getAbsolutePath();
	// return new EmbeddedServletContainerCustomizer() {
	//
	// @Override
	// public void customize(ConfigurableEmbeddedServletContainer container) {
	// TomcatEmbeddedServletContainerFactory tomcat =
	// (TomcatEmbeddedServletContainerFactory) container;
	// tomcat.addConnectorCustomizers(new TomcatConnectorCustomizer() {
	//
	// @Override
	// public void customize(Connector connector) {
	// connector.setPort(port);
	// connector.setSecure(true);
	// connector.setScheme("https");
	// Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
	// proto.setSSLEnabled(true);
	// proto.setKeystoreFile(absoluteKeystoreFile);
	// proto.setKeyAlias(alias);
	// proto.setKeystorePass(keystorePass);
	// proto.setKeystoreType(keystoreType);
	// }
	// });
	// };
	// };
	// }

	public static void main(String[] args) {
		SpringApplication.run(FrameworkApplication.class, args);
	}
}
