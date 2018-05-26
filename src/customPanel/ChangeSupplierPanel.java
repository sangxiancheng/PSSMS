package customPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import databaseOperation.DBOper;
import frame.Item;
import tableInfo.SupplierInfo;

public class ChangeSupplierPanel extends JPanel {
	private JTextField EMailF;
	private JTextField yinHangF;
	private JTextField lianXiRenDianHuaF;
	private JTextField lianXiRenF;
	private JTextField chuanZhenF;
	private JTextField dianHuaF;
	private JTextField diZhiF;
	private JTextField bianMaF;
	private JTextField jianChengF;
	private JTextField quanChengF;
	private JComboBox gys;
	public ChangeSupplierPanel() {
		setLayout(new GridBagLayout());
		setBounds(10, 10, 510, 302);

		setupComponet(new JLabel("��Ӧ��ȫ�ƣ�"), 0, 0, 1, 1, false);
		quanChengF = new JTextField();
		quanChengF.setEditable(false);
		setupComponet(quanChengF, 1, 0, 3, 400, true);

		setupComponet(new JLabel("��ƣ�"), 0, 1, 1, 1, false);

		jianChengF = new JTextField();
		setupComponet(jianChengF, 1, 1, 1, 160, true);

		setupComponet(new JLabel("�������룺"), 2, 1, 1, 1, false);
		bianMaF = new JTextField();
		bianMaF.addKeyListener(new InputKeyListener());
		setupComponet(bianMaF, 3, 1, 1, 0, true);

		setupComponet(new JLabel("��ַ��"), 0, 2, 1, 1, false);
		diZhiF = new JTextField();
		setupComponet(diZhiF, 1, 2, 3, 0, true);

		setupComponet(new JLabel("�绰��"), 0, 3, 1, 1, false);
		dianHuaF = new JTextField();
		dianHuaF.addKeyListener(new InputKeyListener());
		setupComponet(dianHuaF, 1, 3, 1, 0, true);

		setupComponet(new JLabel("���棺"), 2, 3, 1, 1, false);
		chuanZhenF = new JTextField();
		chuanZhenF.addKeyListener(new InputKeyListener());
		setupComponet(chuanZhenF, 3, 3, 1, 0, true);

		setupComponet(new JLabel("��ϵ�ˣ�"), 0, 4, 1, 1, false);
		lianXiRenF = new JTextField();
		setupComponet(lianXiRenF, 1, 4, 1, 0, true);

		setupComponet(new JLabel("��ϵ�˵绰��"), 2, 4, 1, 1, false);
		lianXiRenDianHuaF = new JTextField();
		lianXiRenDianHuaF.addKeyListener(new InputKeyListener());
		setupComponet(lianXiRenDianHuaF, 3, 4, 1, 0, true);

		setupComponet(new JLabel("�������У�"), 0, 5, 1, 1, false);
		yinHangF = new JTextField();
		setupComponet(yinHangF, 1, 5, 1, 0, true);

		setupComponet(new JLabel("�������䣺"), 2, 5, 1, 1, false);
		EMailF = new JTextField();
		setupComponet(EMailF, 3, 5, 1, 0, true);

		setupComponet(new JLabel("ѡ��Ӧ��"), 0, 7, 1, 0, false);
		gys = new JComboBox();
		gys.setPreferredSize(new Dimension(230, 21));
		initComboBox();// ��ʼ������ѡ���
		// ����Ӧ����Ϣ������ѡ����ѡ���¼�
		gys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGysSelectAction();
			}
		});
		
		// ��λ��Ӧ����Ϣ������ѡ���
		setupComponet(gys, 1, 7, 2, 0, true);
		JButton modifyButton = new JButton("�޸�");
		JButton delButton = new JButton("ɾ��");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// ��λ��ť
		setupComponet(panel, 3, 7, 1, 0, false);
		// ����ɾ����ť�ĵ����¼�
		delButton.addActionListener(new DelActionListener());
		// �����޸İ�ť�ĵ����¼�
		modifyButton.addActionListener(new ModifyActionListener());
	}
	
	// ��ʼ����Ӧ������ѡ���
	public void initComboBox() {
		List gysInfo = DBOper.getGysInfos();
		List<Item> items = new ArrayList<Item>();
		gys.removeAllItems();
		for (Iterator iter = gysInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))
				continue;
			items.add(item);
			gys.addItem(item);
		}
		doGysSelectAction();
	}
	
	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		add(component, gridBagConstrains);
	}
	
	// ����Ӧ��ѡ���¼�
	private void doGysSelectAction() {
		Item selectedItem;
		if (!(gys.getSelectedItem() instanceof Item)) {
			return;
		}
		selectedItem = (Item) gys.getSelectedItem();
		SupplierInfo gysInfo = DBOper.getGysInfo(selectedItem);
		quanChengF.setText(gysInfo.getName());
		diZhiF.setText(gysInfo.getAddress());
		jianChengF.setText(gysInfo.getJc());
		bianMaF.setText(gysInfo.getBianma());
		dianHuaF.setText(gysInfo.getTel());
		chuanZhenF.setText(gysInfo.getFax());
		lianXiRenF.setText(gysInfo.getLian());
		lianXiRenDianHuaF.setText(gysInfo.getLtel());
		EMailF.setText(gysInfo.getMail());
		yinHangF.setText(gysInfo.getYh());
	}
	
	//�޸İ�ť���¼�������
	class ModifyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Item item = (Item) gys.getSelectedItem();
			SupplierInfo gysInfo = new SupplierInfo();
			gysInfo.setId(item.getId());
			gysInfo.setAddress(diZhiF.getText().trim());
			gysInfo.setBianma(bianMaF.getText().trim());
			gysInfo.setFax(chuanZhenF.getText().trim());
			gysInfo.setYh(yinHangF.getText().trim());
			gysInfo.setJc(jianChengF.getText().trim());
			gysInfo.setName(quanChengF.getText().trim());
			gysInfo.setLian(lianXiRenF.getText().trim());
			gysInfo.setLtel(lianXiRenDianHuaF.getText().trim());
			gysInfo.setMail(EMailF.getText().trim());
			gysInfo.setTel(dianHuaF.getText().trim());
			if (DBOper.updateGys(gysInfo) == 1)
				JOptionPane.showMessageDialog(ChangeSupplierPanel.this, "�޸����");
			else
				JOptionPane.showMessageDialog(ChangeSupplierPanel.this, "�޸�ʧ��");
		}
	}
	
	//ɾ����ť���¼�������
	class DelActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Item item = (Item) gys.getSelectedItem();
			if (item == null || !(item instanceof Item))
				return;
			int confirm = JOptionPane.showConfirmDialog(ChangeSupplierPanel.this, "ȷ��ɾ����Ӧ����Ϣ��");
			if (confirm == JOptionPane.YES_OPTION) {
				int rs = DBOper.delete("delete tb_gysInfo where id='"+ item.getId() + "'");
				if (rs > 0) {
					JOptionPane.showMessageDialog(ChangeSupplierPanel.this,"��Ӧ�̣�" + item.getName() + "��ɾ���ɹ�");
					gys.removeItem(item);
				} else {
					JOptionPane.showMessageDialog(ChangeSupplierPanel.this,"�޷�ɾ���ͻ���" + item.getName() + "��");
				}
			}
		}
	}
	
	public class InputKeyListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String key="-0123456789"+(char)8;
			if(key.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
}
