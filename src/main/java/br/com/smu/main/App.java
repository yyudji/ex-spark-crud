package br.com.smu.main;
import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.Date;

import br.com.smu.transformer.JsonTransformer;
import br.com.smu.validator.UsuarioValidator;


public class App {
	public static void main(String[] args) {

		before((req, res) -> {
			UsuarioValidator validator = new UsuarioValidator();
			validator.isValido(req);
			System.out.println("Requisicao iniciada as %s" + new Date());
		});

		after((req, res) -> {
			System.out.println("Requisicao recebida as %s" + new Date());
		});

		get("/user/:id", (req, res) -> {			
			return "Usuario encontrado";
		}, new JsonTransformer());
		
		
		post("/user", (req, res) -> {
			return "Usuario criado";			
		}, new JsonTransformer());
		

		put("/user/:id", (req, res) -> {
			return "Usuario atualizado";
		}, new JsonTransformer());
		
		
		delete("/user/:id",(req, res) ->{
			return "Usuario deletado";
		}, new JsonTransformer());
	}

}
