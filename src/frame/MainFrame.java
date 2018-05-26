package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionListener;

public class MainFrame {
	
	private JFrame mainFrame;
	private JDesktopPane desktopPane;
	private Map<String,JInternalFrame> ifs = new HashMap<String,JInternalFrame>();
	
	public MainFrame(){
		mainFrame = new JFrame("���������ϵͳ");
		mainFrame.getContentPane().setBackground(new Color(179,188,120));
		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.setBounds(100, 100, 800, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		mainFrame.getContentPane().add(desktopPane);
		
		JTabbedPane navigationPane = createNavigationPane();
		
		mainFrame.getContentPane().add(navigationPane,BorderLayout.NORTH);
		mainFrame.setVisible(true);
		
		
	}
	
	private JTabbedPane createNavigationPane(){
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFocusable(false);
		tabbedPane.setBackground(new Color(211,230,192));
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		JPanel baseManagePanel = new JPanel();
		baseManagePanel.setBackground(new Color(215,223,194));
		baseManagePanel.setLayout(new BoxLayout(baseManagePanel,BoxLayout.X_AXIS));
		
		JPanel warehouseManagePanel = new JPanel();
		warehouseManagePanel.setBackground(new Color(215,223,194));
		warehouseManagePanel.setLayout(new BoxLayout(warehouseManagePanel,BoxLayout.X_AXIS));

		JPanel saleManagePanel = new JPanel();
		saleManagePanel.setBackground(new Color(215,223,194));
		saleManagePanel.setLayout(new BoxLayout(saleManagePanel,BoxLayout.X_AXIS));

		JPanel searchStatisticPanel = new JPanel();
		searchStatisticPanel.setBounds(0, 0, 600, 41);
		searchStatisticPanel.setName("searchStatisticPanel");
		searchStatisticPanel.setBackground(new Color(215,223,194));
		searchStatisticPanel.setLayout(new BoxLayout(searchStatisticPanel,BoxLayout.X_AXIS));
		
		JPanel stockManagePanel = new JPanel();
		stockManagePanel.setBackground(new Color(215,223,194));
		stockManagePanel.setLayout(new BoxLayout(stockManagePanel,BoxLayout.X_AXIS));
		
		JPanel sysManagePanel = new JPanel();
		sysManagePanel.setBackground(new Color(215,223,194));
		sysManagePanel.setLayout(new BoxLayout(sysManagePanel,BoxLayout.X_AXIS));
		
		tabbedPane.addTab("������Ϣ����", null,baseManagePanel,"������Ϣ����");
		
		JButton CustomerManageButton = new JButton("�ͻ���Ϣ����");
		CustomerManageButton.addActionListener(new buttonActionListener("CustomerManageFrame"));
	
		baseManagePanel.add(CustomerManageButton);
		
		JButton CommodityManageButton = new JButton("��Ʒ��Ϣ����");
		CommodityManageButton.addActionListener(new buttonActionListener("CommodityManageFrame"));
		baseManagePanel.add(CommodityManageButton);
		
		JButton VendorManageButton = new JButton("��Ӧ����Ϣ����");
		VendorManageButton.addActionListener(new buttonActionListener("VendorManageFrame"));
		baseManagePanel.add(VendorManageButton);
		
		tabbedPane.addTab("��������", null,stockManagePanel,"��������");
		
		JButton ImportBillButton = new JButton("������");
		ImportBillButton.addActionListener(new buttonActionListener("ImportBillFrame"));
		stockManagePanel.add(ImportBillButton);
		
		JButton PurchaseAndReturnButton = new JButton("�����˻�");
		PurchaseAndReturnButton.addActionListener(new buttonActionListener("PurchaseAndReturnFrame"));
		stockManagePanel.add(PurchaseAndReturnButton);
		
		tabbedPane.addTab("���۹���", null,saleManagePanel,"���۹���");
		
		JButton SaleBillButton = new JButton("���۵�");
		SaleBillButton.addActionListener(new buttonActionListener("SaleBillFrame"));
		saleManagePanel.add(SaleBillButton);
		
		JButton SaleReturnButton = new JButton("�����˻�");
		SaleReturnButton.addActionListener(new buttonActionListener("SaleReturnFrame"));
		saleManagePanel.add(SaleReturnButton);
		tabbedPane.addTab("��ѯͳ��", null,searchStatisticPanel,"��ѯͳ��");
		
		JButton CustomerQueryButton = new JButton("�ͻ���ѯ");
		CustomerQueryButton.addActionListener(new buttonActionListener("CustomerQueryFrame"));
		searchStatisticPanel.add(CustomerQueryButton);
		
		JButton CommodityQueryButton = new JButton("��Ʒ��ѯ");
		CommodityQueryButton.addActionListener(new buttonActionListener("CommodityQueryFrame"));
		searchStatisticPanel.add(CommodityQueryButton);
		
		JButton VendorQueryButton = new JButton("��Ӧ�̲�ѯ");
		VendorQueryButton.addActionListener(new buttonActionListener("VendorQueryFrame"));
		searchStatisticPanel.add(VendorQueryButton);
		
		JButton SaleQueryButton = new JButton("���۲�ѯ");
		SaleQueryButton.addActionListener(new buttonActionListener("SaleQueryFrame"));
		searchStatisticPanel.add(SaleQueryButton);
		
		JButton SaleReturnButton1 = new JButton("�����˻���ѯ");
		SaleReturnButton1.addActionListener(new buttonActionListener("SaleReturnFrame"));
		searchStatisticPanel.add(SaleReturnButton1);
		
		JButton WarehouseQueryButton = new JButton("����ѯ");
		WarehouseQueryButton.addActionListener(new buttonActionListener("WarehouseQueryFrame"));
		searchStatisticPanel.add(WarehouseQueryButton);
		
		JButton WarhosRtnQueryButton = new JButton("����˻���ѯ");
		WarhosRtnQueryButton.addActionListener(new buttonActionListener("WarhosRtnQueryFrame"));
		searchStatisticPanel.add(WarhosRtnQueryButton);
		
		JButton SaleRankButton = new JButton("��������");
		SaleRankButton.addActionListener(new buttonActionListener("SaleRankFrame"));
		searchStatisticPanel.add(SaleRankButton);
		
		tabbedPane.addTab("�����Ϣ", null,warehouseManagePanel,"�����Ϣ");
		
		JButton InventoryButton = new JButton("�����Ϣ");
		InventoryButton.addActionListener(new buttonActionListener("InventoryFrame"));
		warehouseManagePanel.add(InventoryButton);
		
		JButton PriceAdjustButton = new JButton("�۸����");
		PriceAdjustButton.addActionListener(new buttonActionListener("PriceAdjustFrame"));
		warehouseManagePanel.add(PriceAdjustButton);
		
		tabbedPane.addTab("ϵͳ����", null,sysManagePanel,"ϵͳ����");
		
		JButton OperatorManageButton = new JButton("����Ա����");
		OperatorManageButton.addActionListener(new buttonActionListener("OperatorManageFrame"));
		sysManagePanel.add(OperatorManageButton);
		
		JButton ChangePasswordButton = new JButton("��������");
		ChangePasswordButton.addActionListener(new buttonActionListener("ChangePasswordFrame"));
		sysManagePanel.add(ChangePasswordButton);
		
		JButton PowerManageButton = new JButton("Ȩ�޹���");
		PowerManageButton.addActionListener(new buttonActionListener("PowerManageFrame"));
		sysManagePanel.add(PowerManageButton);
		
		return tabbedPane;
	}
	private class buttonActionListener implements ActionListener{
		private String frameName = null;
		public buttonActionListener(String frameName){
			this.frameName = frameName;
		}
		public void actionPerformed(ActionEvent e) {
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
				Class fClass = Class.forName("frame."+frameName);
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
	
	public static void main(String[] args){
		//MainFrame mainFrame = new MainFrame();
		SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			new LoginFrame();
		}
	});
	}
}

/*btnNewButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
	JInternalFrame jf = getIFrame("CustomerManageFrame");
	//Action action = new openFrameAction("�ͻ���Ϣ����","CustomerManageFrame");
	//btnNewButton.addActionListener(action);
	jf.addInternalFrameListener(new InternalFrameAdapter(){
		public void internalFrameClosed(InternalFrameEvent e){
			ifs.remove("CustomerManageFrame");
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
});*/
