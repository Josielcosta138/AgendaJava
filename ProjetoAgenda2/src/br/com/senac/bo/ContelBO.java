package br.com.senac.bo;

import java.math.BigInteger;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.senac.dao.ContelDAO;
import br.com.senac.dao.IContatoDao;
import br.com.senac.dao.IContelDAO;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.ContelVO;

public class ContelBO implements IContelBO {

	
	private IContelDAO contelDAO;
	
	public ContelBO() {
		contelDAO = new ContelDAO();
	}
	
	
	@Override
	public List<ContelVO> listarContatoTel(ContatoVO contat) throws BOException {
		
		if (contat == null || contat.getId() == null) {
			throw new BOException("Cliente não pode ser nulo na " + "Consulta de contatos. ");
		}
	return contelDAO.listarContatoTel(contat);
	}

	@Override
	public void salvar(ContelVO contelVO) throws BOValidationException, BOException {
		if (contelVO == null) {
			throw new BOException("Contato nulo ou inválido.");
		
		}else if (contelVO.getNumero() == null) {
			throw new BOException("Número: Erro de validação: " + "Número do contato nula. ");
		
		}else if (contelVO.getDddnum().length() < 2) {
			throw new BOValidationException("dddnum: Erro de validação: " + " dddnum do contato é muito curta.");
		
		}else if (contelVO.getEmails() == null) {
			throw new BOException("emails: Erro de validação: " + "emails do contato nula. ");

		}else if (contelVO.getContat() == null) {
			throw new BOException("Contato pessoa: Erro de validação: " + "Contato pessoa do contato nula. ");
		}
		
		contelDAO.salvar(contelVO);

	}

	@Override
	public void excluir(ContelVO contelVO) throws BOValidationException, BOException {
		if (contelVO == null || contelVO.getId() == null) {
			throw new BOException("Produto nulo ou inválido." + "Impossivel de excluir.");
		}
		
		contelDAO.excluir(contelVO);
	}


	@Override
	public ContelVO buscarContatoTelPorId(ContelVO contelVO) throws BOException {
		
		if (contelVO == null || contelVO.getId() == null) {
			//GRAVAR EM ARQUIVO LOG.
			throw new BOException("Código identificador inválido.");
		}
		
		return contelDAO.buscarContatoTelefonePorId(contelVO);
	
	}

}
