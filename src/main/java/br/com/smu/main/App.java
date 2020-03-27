package br.com.smu.main;
import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.Date;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.http.MimeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import br.com.smu.entity.Usuario;
import br.com.smu.service.UsuarioService;
import br.com.smu.transformer.JsonTransformer;
import spark.utils.StringUtils;

public class App {
	public static void main(String[] args) {		
		Logger logger = LoggerFactory.getLogger(App.class);
		UsuarioService usuarioService = new UsuarioService();

		
		//interceptors
		before((req, res) -> {
			logger.info(String.format("Requisicao recebida as %s", new Date()));
		});

		
		after((req, res) -> {
			logger.info(String.format("Requisicao encerrada as %s", new Date()));
		});

		//api
		get("/user/:id", MimeTypes.Type.APPLICATION_JSON.asString(), (req, res) -> {			
			Long idUsuario = Long.valueOf(!StringUtils.isEmpty(req.params("id")) ? req.params("id") : "0");
			Usuario usuario = usuarioService.buscarPorId(idUsuario);
			if (usuario == null) {
				res.status(HttpStatus.NO_CONTENT_204);
			}
			return usuario;
		}, new JsonTransformer());
		
		
		get("/", MimeTypes.Type.APPLICATION_JSON.asString(), (req, res) -> {
			return usuarioService.listarTodos();
		}, new JsonTransformer());
		
		
		post("/user", MimeTypes.Type.APPLICATION_JSON.asString(), (req, res) -> {
			Usuario usuario = new Gson().fromJson(req.body(), Usuario.class);
			usuarioService.salvar(usuario);
			
			if(usuario.getId() == null || usuario.getId() < 1l) {
				res.status(HttpStatus.BAD_REQUEST_400);
			}
			
			return usuario;			
		}, new JsonTransformer());
		

		put("/user/:id", MimeTypes.Type.APPLICATION_JSON.asString(), (req, res) -> {
			Long idUsuario = Long.valueOf(!StringUtils.isEmpty(req.params("id")) ? req.params("id") : "0");
			Usuario usuario = new Gson().fromJson(req.body(), Usuario.class);
			usuarioService.atualizar(usuario, idUsuario);
			
			if(usuario.getId() == null || usuario.getId() < 1l) {
				res.status(HttpStatus.BAD_REQUEST_400);
			}
			
			return usuario;			
		}, new JsonTransformer());
	}

}
