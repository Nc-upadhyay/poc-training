package com.demo.demo;

import com.demo.demo.serviceImpl.JsonAliasDemo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private JsonAliasDemo jsonAliasDemo;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        jsonAliasDemo.jsonAliasSerialization();
        JsonNode jsonNode =new ObjectMapper().createObjectNode().put("name","name")
                .put("password","123");
        jsonAliasDemo.jsonAliasDeserialization(jsonNode);
        jsonNode =new ObjectMapper().createObjectNode().put("userName","name")
                .put("password","456");
        jsonAliasDemo.jsonAliasDeserialization(jsonNode);
        jsonNode =new ObjectMapper().createObjectNode().put("fullName","name")
                .put("password","789");
        jsonAliasDemo.jsonAliasDeserialization(jsonNode);
    }
}
