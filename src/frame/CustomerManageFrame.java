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
		setTitle("�ͻ���Ϣ����");
		JTabbedPane tabPane = new JTabbedPane();
		final ChangeCustomerPanel changeCustomerPanel = new ChangeCustomerPanel();
		final AddCustomerPanel addCustomerPanel = new AddCustomerPanel();
		tabPane.addTab("�ͻ���Ϣ���", null, addCustomerPanel, "�ͻ����");
		tabPane.addTab("�ͻ���Ϣ�޸���ɾ��", null, changeCustomerPanel, "�޸���ɾ��");
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