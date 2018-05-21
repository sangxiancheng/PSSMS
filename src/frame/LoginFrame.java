package frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class LoginFrame extends JFrame {
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JTextField userName;
	private JPasswordField password;
	private JButton login;
	private JButton exit;
	
	public LoginFrame(){
		this.setTitle("���������ϵͳ--��¼");
		final JPanel panel = new JPanel();
		panel.setSize(360, 230);
		panel.setLayout(null);
		getContentPane().add(panel);
		setBounds(240,150,panel.getWidth(),panel.getHeight());
		userNameLabel = new JLabel();
		userNameLabel.setText("�û���:");
		userNameLabel.setBounds(78, 60, 48, 18);
		panel.add(userNameLabel);
		userName = new JTextField();
		userName.setBounds(136, 60, 120, 18);
		panel.add(userName);
		passwordLabel = new JLabel();
		passwordLabel.setText("\u5BC6  \u7801:");//��¼Label
		passwordLabel.setBounds(78, 95, 48, 18);
		panel.add(passwordLabel);
		JPasswordField password = new JPasswordField();
		password.addKeyListener(new KeyAdapter(){//�������볤�Ȳ�С��6
			public void keyPressed(final KeyEvent e){
				if(e.getKeyCode() >= 6)
					login.doClick();
			}
		});
		password.setBounds(136, 95, 120, 18);
		panel.add(password);
		login = new JButton();
		login.addActionListener(new ActionListener(){//��¼��ť�¼�
			public void actionPerformed(final ActionEvent e){
				
			}
		});
		login.setText("��¼");
		login.setBounds(126, 134, 60, 18);
		panel.add(login);
		exit = new JButton();
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e){
				System.exit(0);
			}
		});
		exit.setText("�˳�");
		exit.setBounds(196, 134, 60, 18);
		panel.add(exit);
		getContentPane().add(panel);
		this.setPreferredSize(new Dimension(250, 170));
		this.setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}
	public static void main(String[] args) {
			JFrame loginFrame =	new LoginFrame();
	}
	
}
