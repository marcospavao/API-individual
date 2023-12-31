package com.residencia.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.biblioteca.entities.Aluno;
import com.residencia.biblioteca.repositories.AlunoRepository;

@Service

public class AlunoService {
	// CRUD
	// recuperar todos os alunos
	// recuperar um aluno pela chave primaria
	// salvar um novo aluno
	// atualizar um determinado aluno
	// deletar um determinado aluno

	// • Criar uma injeção de dependência (instancia o repositório e faz uma
	// anotação de @Autowired):
	// • Ao fazer essa anotação eu consigo acessar os métodos que estão dentro do
	// repositório (que é uma interface)
	// • É preciso fazer isso porque é o repositório que vai ao banco, e através do
	// que ele me traz eu apresento pelo método do service.

	@Autowired
	AlunoRepository alunoRepo;

	public List<Aluno> listarAlunos() {
		return alunoRepo.findAll();
	}

	public Aluno buscarAlunoPorId(Integer id) {

		// return alunoRepo.findById(id).get();
		// abaixo nova parte que o professor explicou

		/*
		 * Optional<Aluno> alunoBanco = alunoRepo.findById(id);
		 * if(alunoBanco.isPresent()) return alunoBanco.get(); else return null;
		 */

		return alunoRepo.findById(id).orElse(null);
	}

	public Aluno salvarAluno(Aluno aluno) {
		return alunoRepo.save(aluno);

	}

	public Aluno atualizarAluno(Aluno aluno) {
		return alunoRepo.save(aluno);
	}

	public boolean deletarAluno(Aluno aluno) {
		if (aluno == null)
			return false;

		Aluno alunoExistente = buscarAlunoPorId(aluno.getNumeroMatriculaAluno());

		if (alunoExistente == null)
			return false;

		alunoRepo.delete(aluno);

		Aluno alunoContinuaExistindo = buscarAlunoPorId(aluno.getNumeroMatriculaAluno());

		if (alunoContinuaExistindo == null)
			return true;
		return false;
	}
}
