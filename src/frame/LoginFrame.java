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

import databaseOperation.DBOper;
import tableInfo.UserInfo;
public class LoginFrame extends JFrame {
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JTextField userName;
	private JPasswordField password;
	private JButton login;
	private JButton exit;
	private static UserInfo user;
	
	public LoginFrame(){
		this.setTitle("进销存管理系统--登录");
		final JPanel panel = new JPanel();
		panel.setSize(360, 230);
		panel.setLayout(null);
		getContentPane().add(panel);
		setBounds(240,150,panel.getWidth(),panel.getHeight());
		userNameLabel = new JLabel();
		userNameLabel.setText("用户名:");
		userNameLabel.setBounds(78, 60, 48, 18);
		panel.add(userNameLabel);
		userName = new JTextField();
		userName.setBounds(136, 60, 120, 18);
		panel.add(userName);
		passwordLabel = new JLabel();
		passwordLabel.setText("\u5BC6  \u7801:");//登录Label
		passwordLabel.setBounds(78, 95, 48, 18);
		panel.add(passwordLabel);
		JPasswordField password = new JPasswordField();
		password.addKeyListener(new KeyAdapter(){//密码输入长度不小于6
			public void keyPressed(final KeyEvent e){
				if(e.getKeyCode() >= 6)
					login.doClick();
			}
		});
		password.setBounds(136, 95, 120, 18);
		panel.add(password);
		login = new JButton();
		login.addActionListener(new ActionListener(){//登录按钮事件
			public void actionPerformed(final ActionEvent e){
				
			}
		});
		
		exit = new JButton();
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e){
				user = DBOper.getUser(userName.getText(),String.valueOf(password.getPassword()));
				if (user.getUsername() == null || user.getName() == null){
					userName.setText(null);
					password.setText(null);
					return;
				}
				setVisible(false);
				new MainFrame();
				//System.exit(0);
			}
		});
		login.setText("登录");
		login.setBounds(108, 134, 60, 18);
		panel.add(login);
		exit = new JButton();
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e){
				System.exit(0);
			}
		});
		exit.setText("退出");
		exit.setBounds(180, 134, 60, 18);
		panel.add(exit);
		getContentPane().add(panel);
		this.setPreferredSize(new Dimension(250, 170));
		this.setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public static UserInfo getUser(){
		return user;
	}
	
	public static void setUser(UserInfo user){
		LoginFrame.user = user;
	}
	
	/*public static void main(String[] args) {
			JFrame loginFrame =	new LoginFrame();
	}*/
	
}
