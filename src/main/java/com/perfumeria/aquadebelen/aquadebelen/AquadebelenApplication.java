package com.perfumeria.aquadebelen.aquadebelen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.perfumeria.aquadebelen.aquadebelen.model.clientes.domain.Cliente;
import com.perfumeria.aquadebelen.aquadebelen.model.clientes.domain.NivelFidelidad;
import com.perfumeria.aquadebelen.aquadebelen.model.clientes.domain.Ubicacion;
import com.perfumeria.aquadebelen.aquadebelen.model.clientes.repository.ClienteDAO;
import com.perfumeria.aquadebelen.aquadebelen.model.clientes.repository.NivelFidelidadDAO;
import com.perfumeria.aquadebelen.aquadebelen.model.clientes.repository.UbicacionDAO;

@SpringBootApplication
public class AquadebelenApplication {

	public static void main(String[] args) {
		SpringApplication.run(AquadebelenApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ClienteDAO clienteDAO, NivelFidelidadDAO nivelFidelidadDAO, UbicacionDAO ubicacionDAO){
		return runner -> {
			Cliente cliente = new Cliente("Kevin", "Guzman", "60259580","12594527",
											"Calle General Trigo entre 15 de Agosto y 14 de Julio" ,
											nivelFidelidadDAO.findById(1), ubicacionDAO.findById(2));
			clienteDAO.save(cliente);
		};
	}

}
