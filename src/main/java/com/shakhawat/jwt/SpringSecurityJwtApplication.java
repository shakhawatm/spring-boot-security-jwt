package com.shakhawat.jwt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shakhawat.jwt.entity.User;
import com.shakhawat.jwt.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityJwtApplication {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void initUsers() {
		User[] data = {new User(101, "shakhawat", "123", "shakhawat@mail.com"), 
				new User(102, "tawfiq", "123", "tawfiq@mail.com"),
				new User(103, "user1", "123", "user1@mail.com"),
				new User(104, "user2", "123", "user2@mail.com")};
		
		List<User> uList = Stream.of(data).collect(Collectors.toList());
		
		userRepository.saveAll(uList);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

}
