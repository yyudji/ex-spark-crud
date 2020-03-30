package br.com.smu.validator;

import spark.Request;
import spark.utils.StringUtils;

public class UsuarioValidator {

	public boolean isValido(Request req) {
		return (req != null && !StringUtils.isEmpty(req));
	}

}
