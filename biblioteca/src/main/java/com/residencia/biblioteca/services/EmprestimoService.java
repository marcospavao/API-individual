package com.residencia.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.biblioteca.entities.Emprestimo;

import com.residencia.biblioteca.repositories.EmprestimoRepository;

@Service

public class EmprestimoService {

	@Autowired
	EmprestimoRepository emprestimoRepo;
	
	public List<Emprestimo> listarEmprestimos() {
	return emprestimoRepo.findAll();
		}
	
	public Emprestimo buscarEmprestimoPorId(Integer id) {
		return emprestimoRepo.findById(id).orElse(null);
		
	}
	
	public Emprestimo salvarEmprestimo(Emprestimo emprestimo) {
		return emprestimoRepo.save(emprestimo);
		
	}
	
	public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) {
		return emprestimoRepo.save(emprestimo);
	}
	
	public Boolean deletarEmprestimo(Emprestimo emprestimo) {
		if (emprestimo == null)
			return false;

		Emprestimo emprestimoExistente = buscarEmprestimoPorId(emprestimo.getCodigoEmprestimo());
		if (emprestimoExistente == null)
			return false;

		emprestimoRepo.delete(emprestimo);

		Emprestimo emprestimoContinuaExistindo = buscarEmprestimoPorId(emprestimo.getCodigoEmprestimo());
		if (emprestimoContinuaExistindo == null)
			return true;

		return false;
		
	}
	
}

