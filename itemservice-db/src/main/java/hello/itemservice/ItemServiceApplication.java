package hello.itemservice;

import hello.itemservice.config.*;
import hello.itemservice.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


//@Import(MemoryConfig.class)
//@Import(JdbcTemplateV1Config.class)
//@Import(JdbcTemplateV2Config.class)
//@Import(JdbcTemplateV3Config.class)
//@Import(MybatisConfig.class)
@Import(JpaConfig.class)
/**
 * 여기서는 컨트롤러만 컴포넌트 스캔을 사용하고 나머지는 직접 수동 등록한다.
 * 지정하지 않으면 현재 패키지 있는 위치와 그 하위가 전부 컴포넌트 스캔대상이된다.
 */
@SpringBootApplication(scanBasePackages = "hello.itemservice.web")
@Slf4j
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	/**
	 * 특정 프로필인 경우에만 해당 스프링 빈을 등록한다. 여기서는 local이라는 이름의 프로필이 사용되는 경우에만 등록한다.
	 * -> 스프링은 로딩 시점에 application.properties 의 spring.profiles.active 속성을 읽어서 프로필로 사용한다.
	 * 이 프로필은 로컬(나의 PC), 운영 환경, 테스트 실행 등등 다양한 환경에 따라서 다른 설정을 할 때 사용하는 정보이다.
	 * 참고로 프로필을 지정하지 않으면 default 프로필이 실행된다.
	 * @param itemRepository
	 * @return
	 */
	@Bean
	@Profile("local")
	public TestDataInit testDataInit(ItemRepository itemRepository) {
		return new TestDataInit(itemRepository);
	}
	/*@Bean
	@Profile("test")
	public DataSource dataSource() {
		log.info("메모리 데이터베이스 초기화");

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}*/

}
