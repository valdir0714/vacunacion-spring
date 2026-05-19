package co.sena.cimm.adso.vacunacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicacion Spring Boot.
 *
 * @SpringBootApplication equivale a tres anotaciones en una:
 * @Configuration -> esta clase puede definir beans de Spring
 * @EnableAutoConfiguration -> Spring configura automaticamente todo lo que
 * detecte en el classpath (Thymeleaf, JPA, etc.)
 * @ComponentScan -> busca @Controller, @Service, @Repository, etc.
 * en este paquete y subpaquetes automaticamente
 */
@SpringBootApplication
public class VacunacionSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacunacionSpringApplication.class, args);
    }
}
