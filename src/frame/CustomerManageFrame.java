package frame;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import customPanel.AddCustomerPanel;
import customPanel.ChangeCustomerPanel;

public class CustomerManageFrame extends JInternalFrame {
	public CustomerManageFrame(){
		setIconifiable(true);
		setClosable(true);
		setTitle("客户信息管理");
		JTabbedPane tabPane = new JTabbedPane();
		final ChangeCustomerPanel changeCustomerPanel = new ChangeCustomerPanel();
		final AddCustomerPanel addCustomerPanel = new AddCustomerPanel();
		tabPane.addTab("客户信息添加", null, addCustomerPanel, "客户添加");
		tabPane.addTab("客户信息修改与删除", null, changeCustomerPanel, "修改与删除");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				changeCustomerPanel.initComboBox();
			}
		});
		pack();
		setVisible(true);
	}	
}