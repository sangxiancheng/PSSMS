package frame;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import customPanel.ChangeCommodityPanel;
import customPanel.AddCommodityPanel;

public class CommodityManageFrame extends JInternalFrame {
	public CommodityManageFrame(){
		setIconifiable(true);
		setClosable(true);
		setTitle("��Ʒ����");
		JTabbedPane tabPane = new JTabbedPane();
		final ChangeCommodityPanel changeCommodityPanel = new ChangeCommodityPanel();
		final AddCommodityPanel addCommodityPanel = new AddCommodityPanel();
		tabPane.addTab("��Ʒ��Ϣ���", null,addCommodityPanel,"��Ʒ���");
		tabPane.addTab("��Ʒ��Ϣ�޸���ɾ��", null, changeCommodityPanel, "�޸���ɾ��");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				changeCommodityPanel.initComboBox();
				changeCommodityPanel.initGysBox();
			}
		});
		addInternalFrameListener(new InternalFrameAdapter(){
			public void internalFrameActivated(InternalFrameEvent e){
				super.internalFrameActivated(e);
				addCommodityPanel.initGysBox();
			}
		});
		pack();
		setVisible(true);
	}

}
