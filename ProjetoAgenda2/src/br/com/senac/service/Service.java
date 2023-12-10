package br.com.senac.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import br.com.senac.bo.IContatoBO;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;

public class Service implements IService {

	
	
	@Override
	public ContatoVO buscarContatosPorId(ContatoVO contatVO) throws BOException {

		return null;
	}

	@Override
	public List<ContatoVO> listarContato(ContatoVO contatVO, BigInteger id, String descri, Date datnas,
			String observ) throws BOException {
	
		return null;
	}

	@Override
	public void salvar(ContatoVO contatoVO) throws BOValidationException, BOException {
		

	}

	@Override
	public void excluir(ContatoVO contatoVO) throws BOValidationException, BOException {
		

	}

}
