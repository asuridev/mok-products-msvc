package com.suarezr.products_msvc;

  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
  import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


  @EnableJpaAuditing
  @EnableJpaRepositories
  @SpringBootApplication
  public class Products_msvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(Products_msvcApplication.class, args);
	}

}
  