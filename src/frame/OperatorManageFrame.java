package frame;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OperatorManageFrame extends JInternalFrame {
	public OperatorManageFrame(){
		setIconifiable(true);
		setClosable(true);
		setBounds(100,100,491,287);
		setTitle("操作员管理");
		JTabbedPane tabPane = new JTabbedPane();
		final AddOperatorFrame addOperatorFrame = new AddOperatorFrame();
		final DeleteOperatorFrame deleteOperatorFrame = new DeleteOperatorFrame();
		tabPane.addTab("添加操作员", null,addOperatorFrame,"添加操作员");
		tabPane.addTab("删除操作员", null, deleteOperatorFrame, "删除操作员");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent s){
				deleteOperatorFrame.initTable();
			}
		});
		pack();
		setVisible(true);
	}
}
