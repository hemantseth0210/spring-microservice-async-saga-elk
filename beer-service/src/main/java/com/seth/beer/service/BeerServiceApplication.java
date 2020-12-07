package com.seth.beer.service;

import com.seth.brewery.model.BeerDto;
import com.seth.brewery.model.BeerPagedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

@EnableFeignClients
@SpringBootApplication
public class BeerServiceApplication {


    public static void main(String[] args) {

        SpringApplication.run(BeerServiceApplication.class, args);
    }


}
