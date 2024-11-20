package com.example.gs_spring_energy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "API de Eletrodomésticos", version = "0.0.1", description = "API RESTful para cadastro e consulta de eletrodomésticos eficientes em consumo de energia."))
public class GsSpringEnergyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsSpringEnergyApplication.class, args);
	}

}
