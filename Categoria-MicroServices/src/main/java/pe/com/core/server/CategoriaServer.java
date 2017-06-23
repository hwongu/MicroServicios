package pe.com.core.server;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
@Configuration
@ComponentScan(value = "pe.com.core")
public class CategoriaServer {
	
	
	@Bean
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSource dataSource() {
              
		return new org.apache.tomcat.jdbc.pool.DataSource();
    }

   

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
    
    public static void main(String[] args) {
        SpringApplication.run(CategoriaServer.class, args);
    }
    
}