package kr.or.connect.reservation.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement //Java Config 파일에서 트랜잭션을 활성화할 때 사용하는 어노테이션
public class DBClass implements TransactionManagementConfigurer {
	//PlatformTransactionManager 타입의 Bean을 등록하면, 그 중 하나를 TransactionManagement로 사용하게 된다.
	//트랜잭션 매니져가 두 개 이상 등록되어 있어서 어느 트랜잭션 매니져 Bean을 사용할지 명시적으로 지정하고 싶다면 TransactionManagementConfigurer 인터페이스를 구현하면 된다
	private String driverClassName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/reservation?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=utf8";
	private String username = "connectuser";
	private String password = "connect123!@#";
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManger() { //해당 db의 트랜잭션을 관리하게 된다
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() { //트랜잭션 매니저를 리턴하는 함수 
		return transactionManger();
	}

	
}
