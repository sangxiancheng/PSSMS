package frame;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class CommodityManageFrame extends JInternalFrame {
	public CommodityManageFrame(){
		setIconifiable(true);
		setClosable(true);
		setTitle("商品管理");
		JTabbedPane tabPane = new JTabbedPane();
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				
			}
		});
		addInternalFrameListener(new InternalFrameAdapter(){
			public void internalFrameActivated(InternalFrameEvent e){
				
			}
		});
		pack();
		setVisible(true);
	}

}
