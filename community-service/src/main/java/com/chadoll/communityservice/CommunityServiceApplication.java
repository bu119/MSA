package com.chadoll.communityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
// 유레카 클라이언트로 등록
@EnableDiscoveryClient
public class CommunityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityServiceApplication.class, args);
	}

}
