package edu.kh.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
					// Spring Security의 자동 설정 중 로그인 페이지이용 안 함!
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 해당 프로젝트 서버 실행 시 스케쥴러를 활성화하여 이용하겠다!
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class BoardProjectBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardProjectBootApplication.class, args);
	}

}
