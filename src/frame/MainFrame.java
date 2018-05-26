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
		mainFrame = new JFrame("进销存管理系统");
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
		
		tabbedPane.addTab("基础信息管理", null,baseManagePanel,"基础信息管理");
		
		JButton CustomerManageButton = new JButton("客户信息管理");
		CustomerManageButton.addActionListener(new buttonActionListener("CustomerManageFrame"));
	
		baseManagePanel.add(CustomerManageButton);
		
		JButton CommodityManageButton = new JButton("商品信息管理");
		CommodityManageButton.addActionListener(new buttonActionListener("CommodityManageFrame"));
		baseManagePanel.add(CommodityManageButton);
		
		JButton VendorManageButton = new JButton("供应商信息管理");
		VendorManageButton.addActionListener(new buttonActionListener("VendorManageFrame"));
		baseManagePanel.add(VendorManageButton);
		
		tabbedPane.addTab("进货管理", null,stockManagePanel,"进货管理");
		
		JButton ImportBillButton = new JButton("进货单");
		ImportBillButton.addActionListener(new buttonActionListener("ImportBillFrame"));
		stockManagePanel.add(ImportBillButton);
		
		JButton PurchaseAndReturnButton = new JButton("进货退货");
		PurchaseAndReturnButton.addActionListener(new buttonActionListener("PurchaseAndReturnFrame"));
		stockManagePanel.add(PurchaseAndReturnButton);
		
		tabbedPane.addTab("销售管理", null,saleManagePanel,"销售管理");
		
		JButton SaleBillButton = new JButton("销售单");
		SaleBillButton.addActionListener(new buttonActionListener("SaleBillFrame"));
		saleManagePanel.add(SaleBillButton);
		
		JButton SaleReturnButton = new JButton("销售退货");
		SaleReturnButton.addActionListener(new buttonActionListener("SaleReturnFrame"));
		saleManagePanel.add(SaleReturnButton);
		tabbedPane.addTab("查询统计", null,searchStatisticPanel,"查询统计");
		
		JButton CustomerQueryButton = new JButton("客户查询");
		CustomerQueryButton.addActionListener(new buttonActionListener("CustomerQueryFrame"));
		searchStatisticPanel.add(CustomerQueryButton);
		
		JButton CommodityQueryButton = new JButton("商品查询");
		CommodityQueryButton.addActionListener(new buttonActionListener("CommodityQueryFrame"));
		searchStatisticPanel.add(CommodityQueryButton);
		
		JButton VendorQueryButton = new JButton("供应商查询");
		VendorQueryButton.addActionListener(new buttonActionListener("VendorQueryFrame"));
		searchStatisticPanel.add(VendorQueryButton);
		
		JButton SaleQueryButton = new JButton("销售查询");
		SaleQueryButton.addActionListener(new buttonActionListener("SaleQueryFrame"));
		searchStatisticPanel.add(SaleQueryButton);
		
		JButton SaleReturnButton1 = new JButton("销售退货查询");
		SaleReturnButton1.addActionListener(new buttonActionListener("SaleReturnFrame"));
		searchStatisticPanel.add(SaleReturnButton1);
		
		JButton WarehouseQueryButton = new JButton("入库查询");
		WarehouseQueryButton.addActionListener(new buttonActionListener("WarehouseQueryFrame"));
		searchStatisticPanel.add(WarehouseQueryButton);
		
		JButton WarhosRtnQueryButton = new JButton("入库退货查询");
		WarhosRtnQueryButton.addActionListener(new buttonActionListener("WarhosRtnQueryFrame"));
		searchStatisticPanel.add(WarhosRtnQueryButton);
		
		JButton SaleRankButton = new JButton("销售排行");
		SaleRankButton.addActionListener(new buttonActionListener("SaleRankFrame"));
		searchStatisticPanel.add(SaleRankButton);
		
		tabbedPane.addTab("库存信息", null,warehouseManagePanel,"库存信息");
		
		JButton InventoryButton = new JButton("库存信息");
		InventoryButton.addActionListener(new buttonActionListener("InventoryFrame"));
		warehouseManagePanel.add(InventoryButton);
		
		JButton PriceAdjustButton = new JButton("价格调整");
		PriceAdjustButton.addActionListener(new buttonActionListener("PriceAdjustFrame"));
		warehouseManagePanel.add(PriceAdjustButton);
		
		tabbedPane.addTab("系统管理", null,sysManagePanel,"系统管理");
		
		JButton OperatorManageButton = new JButton("操作员管理");
		OperatorManageButton.addActionListener(new buttonActionListener("OperatorManageFrame"));
		sysManagePanel.add(OperatorManageButton);
		
		JButton ChangePasswordButton = new JButton("更改密码");
		ChangePasswordButton.addActionListener(new buttonActionListener("ChangePasswordFrame"));
		sysManagePanel.add(ChangePasswordButton);
		
		JButton PowerManageButton = new JButton("权限管理");
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
	//Action action = new openFrameAction("客户信息管理","CustomerManageFrame");
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
