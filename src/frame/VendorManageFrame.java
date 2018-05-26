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
		setTitle("供应商管理");
		JTabbedPane tabPane = new JTabbedPane();
		final ChangeSupplierPanel changeSupplierPanel = new ChangeSupplierPanel();
		final AddSupplierPanel addSupplierPanel = new AddSupplierPanel();
		tabPane.addTab("供应商信息添加", null, addSupplierPanel, "供应商添加");
		tabPane.addTab("供应商信息修改与删除", null, changeSupplierPanel, "修改与删除");
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
