package frame;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import customPanel.AddSupplierPanel;
import customPanel.ChangeSupplierPanel;
public class VendorManageFrame extends JInternalFrame {
	public VendorManageFrame(){
		setIconifiable(true);
		setClosable(true);
		setTitle("��Ӧ�̹���");
		JTabbedPane tabPane = new JTabbedPane();
		final ChangeSupplierPanel changeSupplierPanel = new ChangeSupplierPanel();
		final AddSupplierPanel addSupplierPanel = new AddSupplierPanel();
		tabPane.addTab("��Ӧ����Ϣ���", null, addSupplierPanel, "��Ӧ�����");
		tabPane.addTab("��Ӧ����Ϣ�޸���ɾ��", null, changeSupplierPanel, "�޸���ɾ��");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeSupplierPanel.initComboBox();
			}
		});
		pack();
		setVisible(true);
	}
}
