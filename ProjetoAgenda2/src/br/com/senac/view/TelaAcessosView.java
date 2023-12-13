package br.com.senac.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TelaAcessosView extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAcessosView frame = new TelaAcessosView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaAcessosView() {
		setForeground(new Color(0, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaAcessosView.class.getResource("/br/com/senac/view/novaGeracaoAgenda.jpg")));
		setTitle("AGENDA - NOVA GERAÇÃO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 435);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnBtnSystem = new JButton("");
		btnBtnSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPessoaView cadastroPessoaView= new CadastroPessoaView();
				cadastroPessoaView.setVisible(true);
				dispose();
			}
		});
		btnBtnSystem
				.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/novaGeracaoAgenda.jpg")));
		btnBtnSystem.setBounds(126, 84, 220, 217);
		contentPane.add(btnBtnSystem);
	}
}
