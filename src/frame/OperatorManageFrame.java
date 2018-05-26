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
		setTitle("����Ա����");
		JTabbedPane tabPane = new JTabbedPane();
		final AddOperatorFrame addOperatorFrame = new AddOperatorFrame();
		final DeleteOperatorFrame deleteOperatorFrame = new DeleteOperatorFrame();
		tabPane.addTab("��Ӳ���Ա", null,addOperatorFrame,"��Ӳ���Ա");
		tabPane.addTab("ɾ������Ա", null, deleteOperatorFrame, "ɾ������Ա");
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
