package br.usp.nidaba;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.usp.nidaba.socket.Client;
import br.usp.nidaba.window.NidabaApplicationWindow;

@SpringBootApplication
public class NidabaApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(NidabaApplication.class, args);
	}

}
