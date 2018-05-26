package customPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frame.Item;
import tableInfo.CommodityInfo;
import databaseOperation.DBOper;

public class AddCommodityPanel extends JPanel {
	private JComboBox gysQuanCheng;//供应商全称
	private JTextField beiZhu;//备注
	private JTextField wenHao;//批准文号
	private JTextField piHao;//批号
	private JTextField baoZhuang;//包装
	private JTextField guiGe;//规格
	private JTextField danWei;//单位
	private JTextField chanDi;//产地
	private JTextField jianCheng;//简称
	private JTextField quanCheng;//全称
	private JButton resetButton;//重置按钮
	
	public AddCommodityPanel(){
		setLayout(new GridBagLayout());
		setBounds(10, 10, 550, 400);
		setupComponent(new JLabel("商品名称："), 0, 0, 1, 1, false);
		quanCheng = new JTextField();
		setupComponent(quanCheng, 1, 0, 3, 1, true);
		setupComponent(new JLabel("简称："), 0, 1, 1, 1, false);
		jianCheng = new JTextField();
		setupComponent(jianCheng, 1, 1, 3, 10, true);
		setupComponent(new JLabel("产地："), 0, 2, 1, 1, false);
		chanDi = new JTextField();
		setupComponent(chanDi, 1, 2, 3, 300, true);
		setupComponent(new JLabel("单位："), 0, 3, 1, 1, false);
		danWei = new JTextField();
		setupComponent(danWei, 1, 3, 1, 130, true);
		setupComponent(new JLabel("规格："), 2, 3, 1, 1, false);
		guiGe = new JTextField();
		setupComponent(guiGe, 3, 3, 1, 1, true);
		setupComponent(new JLabel("包装："), 0, 4, 1, 1, false);
		baoZhuang = new JTextField();
		setupComponent(baoZhuang, 1, 4, 1, 1, true);
		setupComponent(new JLabel("批号："), 2, 4, 1, 1, false);
		piHao = new JTextField();
		setupComponent(piHao, 3, 4, 1, 1, true);
		setupComponent(new JLabel("批准文号："), 0, 5, 1, 1, false);
		wenHao = new JTextField();
		setupComponent(wenHao, 1, 5, 3, 1, true);
		setupComponent(new JLabel("供应商全称："), 0, 6, 1, 1, false);
		gysQuanCheng = new JComboBox();
		gysQuanCheng.setMaximumRowCount(5);
		setupComponent(gysQuanCheng, 1, 6, 3, 1, true);
		setupComponent(new JLabel("备注："), 0, 7, 1, 1, false);
		beiZhu = new JTextField();
		setupComponent(beiZhu, 1, 7, 3, 1, true);
		final JButton addButton = new JButton();
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (baoZhuang.getText().equals("")
						|| chanDi.getText().equals("")
						|| danWei.getText().equals("")
						|| guiGe.getText().equals("")
						|| jianCheng.getText().equals("")
						|| piHao.getText().equals("")
						|| wenHao.getText().equals("")
						|| quanCheng.getText().equals("")) {
					JOptionPane.showMessageDialog(AddCommodityPanel.this,"请完成未填写的信息。", "商品添加", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ResultSet haveUser = DBOper.query("select * from tb_spinfo where spname='"
								+ quanCheng.getText().trim() + "'");
				try {
					if (haveUser.next()) {
						System.out.println("error");
						JOptionPane.showMessageDialog(AddCommodityPanel.this, "商品信息添加失败，存在同名商品","客户添加信息", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (Exception er) {
					er.printStackTrace();
				}
				ResultSet set = DBOper.query("select max(id) from tb_spinfo");
				String id = null;
				try {
					if (set != null && set.next()) {
						String sid = set.getString(1);
						if (sid == null)
							id = "sp1001";
						else {
							String str = sid.substring(2);
							id = "sp" + (Integer.parseInt(str) + 1);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				CommodityInfo commodityInfo = new CommodityInfo();
				commodityInfo.setId(id);
				commodityInfo.setBz(baoZhuang.getText().trim());
				commodityInfo.setCd(chanDi.getText().trim());
				commodityInfo.setDw(danWei.getText().trim());
				commodityInfo.setGg(guiGe.getText().trim());
				commodityInfo.setGysname(gysQuanCheng.getSelectedItem().toString().trim());
				commodityInfo.setJc(jianCheng.getText().trim());
				commodityInfo.setMemo(beiZhu.getText().trim());
				commodityInfo.setPh(piHao.getText().trim());
				commodityInfo.setPzwh(wenHao.getText().trim());
				commodityInfo.setSpname(quanCheng.getText().trim());
				DBOper.addSp(commodityInfo);
				JOptionPane.showMessageDialog(AddCommodityPanel.this,"商品信息已经成功添加", "商品添加", JOptionPane.INFORMATION_MESSAGE);
				resetButton.doClick();
			}
		});
		addButton.setText("添加");
		setupComponent(addButton, 1, 8, 1, 1, false);
		final GridBagConstraints gridBagConstraints_20 = new GridBagConstraints();
		gridBagConstraints_20.weighty = 1.0;
		gridBagConstraints_20.insets = new Insets(0, 65, 0, 15);
		gridBagConstraints_20.gridy = 8;
		gridBagConstraints_20.gridx = 1;
		// 重添按钮的事件监听类
		resetButton = new JButton();
		setupComponent(addButton, 3, 8, 1, 1, false);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				baoZhuang.setText("");
				chanDi.setText("");
				danWei.setText("");
				guiGe.setText("");
				jianCheng.setText("");
				beiZhu.setText("");
				piHao.setText("");
				wenHao.setText("");
				quanCheng.setText("");
			}
		});
		resetButton.setText("重新添加");
	}
	
	// 设置组件位置并添加到容器中
	private void setupComponent(JComponent component, int gridx, int gridy,int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		add(component, gridBagConstrains);
	}
	
	// 初始化供应商下拉选择框
	public void initGysBox() {
		List gysInfo = DBOper.getGysInfos();
		List<Item> items = new ArrayList<Item>();
		gysQuanCheng.removeAllItems();
		for (Iterator iter = gysInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))
				continue;
			items.add(item);
			gysQuanCheng.addItem(item);
		}
	}
}