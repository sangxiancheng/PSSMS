package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class MainFrame {
	
	private JFrame mainFrame;
	private JDesktopPane desktopPane;
	private Map<String,JInternalFrame> ifs = new HashMap<String,JInternalFrame>();
	
	public MainFrame(){
		mainFrame = new JFrame("进销存管理系统");
		mainFrame.getContentPane().setBackground(new Color(1790,188,120));
		//mainFrame.addComponentListener(new FrameListener());
		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.setBounds(100, 100, 800, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFocusable(false);
		tabbedPane.setBackground(new Color(211,230,192));
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		JPanel baseManagePanel = new JPanel();
		baseManagePanel.setBackground(new Color(215,223,194));
		baseManagePanel.setLayout(new BoxLayout(baseManagePanel,BoxLayout.X_AXIS));
		baseManagePanel.add(createButton("客户信息管理",""));
		baseManagePanel.add(createButton("商品信息管理",""));
		baseManagePanel.add(createButton("供应商信息管理",""));
		
		
	}
	
	private JButton createButton(String fName,String cName){
		JButton button = new JButton();
		Action action = new openFrameAction(fName,cName);
		return button;
	}
	
	protected final class openFrameAction extends AbstractAction {
		private String frameName = null;
		public openFrameAction(String cName,String frameName){
			this.frameName = frameName;
			putValue(Action.NAME,cName);
			putValue(Action.SHORT_DESCRIPTION,cName);
		}
		public void actionPerformed(final ActionEvent e){
			JInternalFrame jf = getIFrame(frameName);
			jf.addInternalFrameListener(new InternalFrameAdapter(){
				public void internalFrameClosed(InternalFrameEvent e){
					ifs.remove(frameName);
				}
			});
			if(jf.getDesktopPane() == null){
				desktopPane.add(jf);
				jf.setVisible(true);
			}
			try{
				jf.setSelected(true);
			}catch(PropertyVetoException e1){
				e1.printStackTrace();
			}
		}
	}
	static {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private JInternalFrame getIFrame(String frameName){
		JInternalFrame jf = null;
		if(!ifs.containsKey(frameName)){
			try{
				Class fClass = Class.forName(""+frameName);
				Constructor constructor = fClass.getConstructor();
				jf = (JInternalFrame)constructor.newInstance(null);
				ifs.put(frameName, jf);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else
			jf = ifs.get(frameName);
		return jf;
	}
	
}
