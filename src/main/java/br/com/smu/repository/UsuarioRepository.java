package br.com.smu.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import br.com.smu.entity.Usuario;

public class UsuarioRepository {

	public UsuarioRepository() {
		mockar();
	}

	private Map<Long, Usuario> usuariosDb = null;

	public Usuario buscarPorId(Long idUsuario) {
		return usuariosDb.get(idUsuario);
	}

	public List<Usuario> listarTodos() {
		List<Usuario> usuarios = new ArrayList<>();
		usuariosDb.forEach((k, v) -> {
			usuarios.add(v);
		});
		return usuarios;
	}

	public Usuario salvar(Usuario usuario) {
		usuario.setId(getNextId());
		usuariosDb.put(usuario.getId(), usuario);
		return usuario;
	}

	public Usuario atualizar(Usuario usuario) {
		usuariosDb.put(usuario.getId(), usuario);
		return usuario;
	}

	private void mockar() {
		usuariosDb = new TreeMap<>();

		Usuario usuarioA = new Usuario();
		usuarioA.setNome("Randy Rhoads");
		usuarioA.setEmail("randyrhoads@odair.io");
		usuarioA.setId(1l);

		Usuario usuarioB = new Usuario();
		usuarioB.setNome("David Gilmour");
		usuarioB.setEmail("davidgilmour@odair.io");
		usuarioB.setId(2l);

		Usuario usuarioC = new Usuario();
		usuarioC.setNome("Synyster Gates");
		usuarioC.setEmail("synystergates@odair.io");
		usuarioC.setId(3l);

		Usuario usuarioD = new Usuario();
		usuarioD.setNome("Dimebag Darrell");
		usuarioD.setEmail("dimebagdarrel@odair.io");
		usuarioD.setId(4l);

		usuariosDb.put(usuarioA.getId(), usuarioA);
		usuariosDb.put(usuarioB.getId(), usuarioB);
		usuariosDb.put(usuarioC.getId(), usuarioC);
		usuariosDb.put(usuarioD.getId(), usuarioD);
	}

	private Long getNextId() {
		if (usuariosDb.keySet() != null && usuariosDb.keySet().size() > 0) {
			Set<Long> ids = usuariosDb.keySet();
			TreeSet<Long> idsOrdenados = new TreeSet<>();
			idsOrdenados.addAll(ids);
			return idsOrdenados.pollLast() + 1;
		} else {
			return 1l;
		}
	}
}