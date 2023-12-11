package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.service.IService;
import br.com.senac.service.Service;
import br.com.senac.view.RowData;
import br.com.senac.view.TableModel;
import br.com.senac.vo.ContatoVO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastroPessoaView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JFormattedTextField ftfCodigo;
	private TableModel tableModel;
	private JFormattedTextField ftfNome;
	private TelaAcessosView telaAcessos;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPessoaView frame = new CadastroPessoaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
	}
	

	public CadastroPessoaView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				 abrirTelaAcessos();
			}
		});
		
		

		table = new JTable();
		tableModel = new TableModel();
		table.setModel(tableModel);

		setTitle("Cadastro de pessoas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setBounds(10, 21, 635, 79);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(10, 10, 74, 13);
		panel.add(lblCodigo);

		ftfCodigo = new JFormattedTextField();
		ftfCodigo.setBounds(10, 30, 105, 19);
		panel.add(ftfCodigo);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(160, 10, 54, 13);
		panel.add(lblNome);

		ftfNome = new JFormattedTextField();
		ftfNome.setBounds(159, 30, 312, 19);
		panel.add(ftfNome);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pesquisar();
			}
		});
		btnPesquisar.setMnemonic('P');
		btnPesquisar.setBounds(508, 29, 100, 21);
		panel.add(btnPesquisar);

		JButton btnAdcionar = new JButton("Adcionar");
		btnAdcionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaEdicao();

			}
		});
		btnAdcionar.setMnemonic('A');
		btnAdcionar.setBounds(20, 139, 85, 21);
		contentPane.add(btnAdcionar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				editarRegistro();
			}
		});
		btnEditar.setBounds(165, 139, 85, 21);
		contentPane.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();

			}
		});
		btnExcluir.setBounds(308, 139, 85, 21);
		contentPane.add(btnExcluir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 171, 595, 207);
		contentPane.add(scrollPane);

		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Descrição");
		tableModel.addColumn("Data");
		tableModel.addColumn("Observação");

		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(100);
		tcm.getColumn(1).setPreferredWidth(180);
		tcm.getColumn(2).setPreferredWidth(120);
		tcm.getColumn(3).setPreferredWidth(180);

		scrollPane.setViewportView(table);

	}


	protected void excluir() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro! ", "Mensagem de aviso",
					JOptionPane.WARNING_MESSAGE);
		} else {

			Object[] options = { "Sim!", "Não" };
			int n = JOptionPane.showOptionDialog(this, // não deixa clicar na tela de baixo
					"Deseja realmente exluir o registro? ", "Confirmação", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (n == 0) {
				ContatoVO contato = (ContatoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();

				Service service = new Service();

				try {
					service.excluir(contato);

					JOptionPane.showMessageDialog(this, "Registro exluido com sucesso!", "Mensagem de aviso",
							JOptionPane.INFORMATION_MESSAGE);

					pesquisar();

				} catch (BOValidationException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de aviso",
							JOptionPane.WARNING_MESSAGE);

				} catch (BOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	private void editarRegistro() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro!", "Mensagem de aviso.",
					JOptionPane.WARNING_MESSAGE);
		} else {

			try {

				EditarPessoa edt = new EditarPessoa();
				ContatoVO aux = (ContatoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();

				edt.editar(aux);
				edt.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocooreu um erro", "Erro.", JOptionPane.WARNING_MESSAGE);
			}
		}

	}

	// Add Pesquisar teste
	private void pesquisar() {

		if (tableModel != null) {
			tableModel.clearTable();
		}
		BigInteger id = null;
		String nome = null;
		Date datnas = null;
		String observ = null;

		try {

			if (this.ftfCodigo.getText() != null && this.ftfCodigo.getText().trim().length() > 0) {
				try {
					id = new BigInteger(ftfCodigo.getText().trim());
				} catch (Exception e) {
					throw new BOValidationException("Código: erro de validação" + " valor inválido");
				}
			}

			if (this.ftfNome.getText() != null && ftfNome.getText().trim().length() > 0) {
				nome = ftfNome.getText().trim();
			}

			if (this.ftfNome.getText() != null && ftfNome.getText().trim().length() > 0) {
				nome = ftfNome.getText().trim();
			}

			System.out.println("********************Iniciando consulta de contatos**********************");
			EntityManager em = HibernateUtil.getEntityManager();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ContatoVO> criteria = cb.createQuery(ContatoVO.class);

			// Clausula from
			Root<ContatoVO> contatosFrom = criteria.from(ContatoVO.class);
			criteria.select(contatosFrom);

			if (id != null) {
				criteria.where(cb.equal(contatosFrom.get("id"), id));
			}

			if (nome != null) {
				String searchTerm = "%" + nome.toLowerCase() + "%";
				criteria.where(cb.like(cb.lower(contatosFrom.get("nome")), searchTerm));
			}
			
			
			TypedQuery<ContatoVO> query = em.createQuery(criteria);
			Order contatoOrderBy = cb.asc(contatosFrom.get("nome"));
			criteria.orderBy(contatoOrderBy);
			// query.setMaxResults(100);

			List<ContatoVO> listaContat = query.getResultList();

			IService service = new Service();

			System.out.println(listaContat);
			Collections.sort(listaContat, (contato1, contato2) -> contato1.getNome().compareTo(contato2.getNome()));

			for (ContatoVO contatoVO : listaContat) {
				RowData rowData = new RowData();
				rowData.getValues().put(0, contatoVO.getId().toString());
				rowData.getValues().put(1, contatoVO.getNome());
				rowData.getValues().put(2, contatoVO.getDatnas());
				rowData.getValues().put(3, contatoVO.getObserv());

				System.out.println();

				rowData.setElement(contatoVO);
				tableModel.addRow(rowData);
			}

		} catch (BOValidationException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de validação", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de sistema", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void abrirTelaEdicao() {
		try {
			EditarPessoa editarPessoa = new EditarPessoa();
			editarPessoa.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de aviso.",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	protected void abrirTelaAcessos() {
		  if (telaAcessos == null) {
	            telaAcessos = new TelaAcessosView();
	        }
	        telaAcessos.setVisible(true);
	    }

}
