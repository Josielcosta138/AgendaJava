package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.xml.crypto.Data;

import br.com.senac.exception.BOValidationException;
import br.com.senac.view.RowData;
//import br.com.senac.vo.ContatVO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastroPessoaView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JFormattedTextField ftfCodigo;
	private TableModel tableModel;
	private JFormattedTextField ftfNome;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.   
	 */
	public CadastroPessoaView() {
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
		btnPesquisar.setBounds(523, 29, 85, 21);
		panel.add(btnPesquisar);
		
		JButton btnAdcionar = new JButton("Adcionar");
		btnAdcionar.setMnemonic('A');
		btnAdcionar.setBounds(25, 139, 85, 21);
		contentPane.add(btnAdcionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(153, 139, 85, 21);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(281, 139, 85, 21);
		contentPane.add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 171, 595, 207);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	//Add Pesquisar teste 
	private void pesquisar() {
		tableModel.clearTable();
		BigInteger id = null;
		String descri = null;
		Date datnas = null;
		
try {
			
			if (this.ftfCodigo.getText() != null &&  
					this.ftfCodigo.getText().trim().length() > 0) {
						try {
							id = new BigInteger(ftfCodigo.getText().trim());
						} catch (Exception e) {
							throw new BOValidationException("Código: erro de validação" + " valor inválido");
						}
				}
			
			if (this.ftfNome.getText() != null && ftfNome.getText().trim().length() > 0) {
				descri = ftfNome.getText().trim();
			}
			
			
			//IService service = new Service();
			//ContatVO c1 = new ContatVO(BigInteger.ONE);
			//List<ContatVO> contatos = service.listarProduto(c1, id, descricao, status, codBar, Integer.MAX_VALUE, 0);
			
			
			DecimalFormat df= new DecimalFormat("R$#,##0.00");
			DecimalFormat dfQtd= new DecimalFormat("###,##0.000");
			
			/*
			for (ProdutoVO produtoVO : produtos) {
				RowData rowData= new RowData();
				rowData.getValues().put(0, produtoVO.getId().toString());
				rowData.getValues().put(1, produtoVO.getDescri());
				rowData.getValues().put(2, dfQtd.format(produtoVO.getQtdest()));
				
				if (produtoVO.getStatus().equals("A")) {
					rowData.getValues().put(3, "Ativo");   //produtoVO.getStatus());
				} else if (produtoVO.getStatus().equals("I")) {
					rowData.getValues().put(3, "Inativo"); // produtoVO.getStatus());
				}
				
				
				rowData.getValues().put(4, df.format(produtoVO.getValcom()));
				rowData.getValues().put(5, df.format(produtoVO.getValven()));
				rowData.setElement(produtoVO);
				tableModel.addRow(rowData);
			} */
			
		} catch (BOValidationException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de validação", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de sistema", JOptionPane.ERROR_MESSAGE);
		}
	}
		
	}




























