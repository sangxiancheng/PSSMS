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
	private JComboBox gysQuanCheng;//��Ӧ��ȫ��
	private JTextField beiZhu;//��ע
	private JTextField wenHao;//��׼�ĺ�
	private JTextField piHao;//����
	private JTextField baoZhuang;//��װ
	private JTextField guiGe;//���
	private JTextField danWei;//��λ
	private JTextField chanDi;//����
	private JTextField jianCheng;//���
	private JTextField quanCheng;//ȫ��
	private JButton resetButton;//���ð�ť
	
	public AddCommodityPanel(){
		setLayout(new GridBagLayout());
		setBounds(10, 10, 550, 400);
		setupComponent(new JLabel("��Ʒ���ƣ�"), 0, 0, 1, 1, false);
		quanCheng = new JTextField();
		setupComponent(quanCheng, 1, 0, 3, 1, true);
		setupComponent(new JLabel("��ƣ�"), 0, 1, 1, 1, false);
		jianCheng = new JTextField();
		setupComponent(jianCheng, 1, 1, 3, 10, true);
		setupComponent(new JLabel("���أ�"), 0, 2, 1, 1, false);
		chanDi = new JTextField();
		setupComponent(chanDi, 1, 2, 3, 300, true);
		setupComponent(new JLabel("��λ��"), 0, 3, 1, 1, false);
		danWei = new JTextField();
		setupComponent(danWei, 1, 3, 1, 130, true);
		setupComponent(new JLabel("���"), 2, 3, 1, 1, false);
		guiGe = new JTextField();
		setupComponent(guiGe, 3, 3, 1, 1, true);
		setupComponent(new JLabel("��װ��"), 0, 4, 1, 1, false);
		baoZhuang = new JTextField();
		setupComponent(baoZhuang, 1, 4, 1, 1, true);
		setupComponent(new JLabel("���ţ�"), 2, 4, 1, 1, false);
		piHao = new JTextField();
		setupComponent(piHao, 3, 4, 1, 1, true);
		setupComponent(new JLabel("��׼�ĺţ�"), 0, 5, 1, 1, false);
		wenHao = new JTextField();
		setupComponent(wenHao, 1, 5, 3, 1, true);
		setupComponent(new JLabel("��Ӧ��ȫ�ƣ�"), 0, 6, 1, 1, false);
		gysQuanCheng = new JComboBox();
		gysQuanCheng.setMaximumRowCount(5);
		setupComponent(gysQuanCheng, 1, 6, 3, 1, true);
		setupComponent(new JLabel("��ע��"), 0, 7, 1, 1, false);
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
					JOptionPane.showMessageDialog(AddCommodityPanel.this,"�����δ��д����Ϣ��", "��Ʒ���", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ResultSet haveUser = DBOper.query("select * from tb_spinfo where spname='"
								+ quanCheng.getText().trim() + "'");
				try {
					if (haveUser.next()) {
						System.out.println("error");
						JOptionPane.showMessageDialog(AddCommodityPanel.this, "��Ʒ��Ϣ���ʧ�ܣ�����ͬ����Ʒ","�ͻ������Ϣ", JOptionPane.INFORMATION_MESSAGE);
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
				JOptionPane.showMessageDialog(AddCommodityPanel.this,"��Ʒ��Ϣ�Ѿ��ɹ����", "��Ʒ���", JOptionPane.INFORMATION_MESSAGE);
				resetButton.doClick();
			}
		});
		addButton.setText("���");
		setupComponent(addButton, 1, 8, 1, 1, false);
		final GridBagConstraints gridBagConstraints_20 = new GridBagConstraints();
		gridBagConstraints_20.weighty = 1.0;
		gridBagConstraints_20.insets = new Insets(0, 65, 0, 15);
		gridBagConstraints_20.gridy = 8;
		gridBagConstraints_20.gridx = 1;
		// ����ť���¼�������
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
		resetButton.setText("�������");
	}
	
	// �������λ�ò���ӵ�������
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
	
	// ��ʼ����Ӧ������ѡ���
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