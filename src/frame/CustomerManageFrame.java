package frame;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CustomerManageFrame extends JInternalFrame {
	public CustomerManageFrame(){
		setIconifiable(true);
		setClosable(true);
		setTitle("客户信息管理");
		JTabbedPane tabPane = new JTabbedPane();
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
			
			}
		});
		pack();
		setVisible(true);
	}	
}