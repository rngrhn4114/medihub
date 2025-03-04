package oop_kiosk_medihub;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminMain extends JFrame {
	public JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMain window = new AdminMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminMain() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("AdminLogin");
		frame.setBounds(100, 100, 1500, 800);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel startPage = new JPanel();
		startPage.setBackground(new Color(234, 255, 239));
		startPage.setBounds(0, 0, 1500, 800);
		frame.getContentPane().add(startPage);
		JLabel label = new JLabel("ID : ");
		label.setBounds(441, 294, 61, 55);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		JLabel pawrd = new JLabel("PASSWORD : ");
		pawrd.setBounds(303, 380, 201, 55);
		pawrd.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		JTextField txtID = new JTextField(10);
		txtID.setBounds(506, 294, 236, 55);
		JPasswordField txtPass = new JPasswordField(10);
		txtPass.setBounds(506, 380, 236, 55);
		startPage.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MediHub");
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setBounds(25, 23, 236, 55);
		lblNewLabel.setForeground(new Color(57, 194, 46));
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		startPage.add(lblNewLabel);
		
		startPage.add(label);
		startPage.add(pawrd);
		startPage.add(txtID);
		startPage.add(txtPass);
		
		JPanel endPage = new JPanel();
		endPage.setBackground(new Color(234, 255, 239));
		endPage.setBounds(0, 0, 1500, 800);
		frame.getContentPane().add(endPage);
		
		JButton btnNext = new JButton("로그인");
		btnNext.setBounds(640, 505, 155, 73);
		btnNext.setFont(new Font("맑은 고딕", Font.BOLD, 26));
		startPage.add(btnNext);
		
		JLabel lblNewLabel_1 = new JLabel("관리자 전용 페이지입니다.");
		lblNewLabel_1.setBounds(406, 190, 408, 83);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 33));
		startPage.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("<홈 화면으로");
		btnNewButton.setBounds(391, 505, 207, 73);
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 26));
		startPage.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(247, 160, 723, 458);
		startPage.add(panel);
		
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = "Lee";
				String pass = "1234";
				
				if(id.equals(txtID.getText()) && pass.equals(txtPass.getText())) {
					//endPage.setVisible(true);
		            frame.setVisible(false);
		            JOptionPane.showMessageDialog(null, "로그인 성공!");
		            
					// 로그인 성공 시 CustomerApp 실행
		            EventQueue.invokeLater(new Runnable() {
		                public void run() {
		                    try {
		                        PatientsManage patientsManage = new PatientsManage();
		                        patientsManage.frame.setVisible(true);
		                    } catch (Exception e) {
		                        e.printStackTrace();
		                    }
		                }
		            });
				} else {
					JOptionPane.showMessageDialog(null, "로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
				}
			}
		});		
		frame.setVisible(true);
	}
}