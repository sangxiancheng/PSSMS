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
		setTitle("商品管理");
		JTabbedPane tabPane = new JTabbedPane();
		final ChangeCommodityPanel changeCommodityPanel = new ChangeCommodityPanel();
		final AddCommodityPanel addCommodityPanel = new AddCommodityPanel();
		tabPane.addTab("商品信息添加", null,addCommodityPanel,"商品添加");
		tabPane.addTab("商品信息修改与删除", null, changeCommodityPanel, "修改与删除");
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
