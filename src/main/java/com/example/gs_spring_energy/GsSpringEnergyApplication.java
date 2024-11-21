package com.example.gs_spring_energy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "API de Eletrodomésticos", version = "0.0.1", description = "API RESTful para cadastro e consulta de eletrodomésticos eficientes em consumo de energia."))
public class GsSpringEnergyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsSpringEnergyApplication.class, args);

		openSwaggerUI();
	}

	private static void openSwaggerUI() {
		try {
			String swaggerUrl = "http://localhost:8080/swagger-ui/index.html";
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI(swaggerUrl));
			} else {
				System.out.println("Navegador padrão não suportado para abrir automaticamente o Swagger.");
			}
		} catch (Exception e) {
			System.err.println("Erro ao tentar abrir o Swagger UI automaticamente: " + e.getMessage());
		}
	}
}
