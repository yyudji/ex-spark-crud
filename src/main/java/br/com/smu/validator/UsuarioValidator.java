package br.com.smu.validator;

import br.com.smu.entity.Usuario;
import spark.utils.StringUtils;

public class UsuarioValidator {

	public static boolean isValido(Usuario usuario) {
		return (usuario != null && !StringUtils.isEmpty(usuario.getEmail()) && !StringUtils.isEmpty(usuario.getNome()));
	}

}
