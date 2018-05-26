package databaseOperation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import frame.Item;
import tableInfo.SupplierInfo;
import tableInfo.CustomerInfo;
import tableInfo.InventoryInfo;
import tableInfo.WarhosRtDetailInfo;
import tableInfo.WarhosRtMainInfo;
import tableInfo.WarehouseDetailInfo;
import tableInfo.WarehouseMainInfo;
import tableInfo.SaleDetailInfo;
import tableInfo.SaleMainInfo;
import tableInfo.CommodityInfo;
import tableInfo.UserInfo;
import tableInfo.SaleReturnDetailInfo;
import tableInfo.SaleReturnMainInfo;

public class DBOper {
	protected static String dbClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	protected static String dbUrl = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=PSSMS;SelectMethod=Cursor";
	protected static String dbUser = "sa";
	protected static String dbPwd = "123";
	protected static String second = null;
	public static Connection conn = null;
	static {
		try{
			if (conn == null) 
			{
				Class.forName(dbClassName).newInstance();
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// 读取所有客户信息
	public static List getKhInfos() {
		List list = findForList("select id,khname from tb_CustomerInfo");
		return list;
	}
	
	// 读取所有供应商信息
	public static List getGysInfos() {
		List list = findForList("select id,name from tb_SuplierInfo");
		return list;
	}
	
	// 读取客户信息
	public static CustomerInfo getKhInfo(Item item) {
		String where = "khname='" + item.getName() + "'";
		if (item.getId() != null)
			where = "id='" + item.getId() + "'";
		CustomerInfo info = new CustomerInfo();
		ResultSet set = findForResultSet("select * from tb_CustomerInfo where "+ where);
		try {
			if (set.next()) {
				info.setId(set.getString("id").trim());
				info.setKhname(set.getString("khname").trim());
				info.setJian(set.getString("jian").trim());
				info.setAddress(set.getString("address").trim());
				info.setBianma(set.getString("bianma").trim());
				info.setFax(set.getString("fax").trim());
				info.setHao(set.getString("hao").trim());
				info.setLian(set.getString("lian").trim());
				info.setLtel(set.getString("ltel").trim());
				info.setMail(set.getString("mail").trim());
				info.setTel(set.getString("tel").trim());
				info.setXinhang(set.getString("xinhang").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return info;
	}
		
	// 读取指定供应商信息
	public static SupplierInfo getGysInfo(Item item) {	
		String where = "name='" + item.getName() + "'";
		if (item.getId() != null)
			where = "id='" + item.getId() + "'";
		SupplierInfo info = new SupplierInfo();
		ResultSet set = findForResultSet("select * from tb_SuplierInfo where "+ where);
		try {
			if (set.next()) {
				info.setId(set.getString("id").trim());
				info.setAddress(set.getString("address").trim());
				info.setBianma(set.getString("bianma").trim());
				info.setFax(set.getString("fax").trim());
				info.setJc(set.getString("jc").trim());
				info.setLian(set.getString("lian").trim());
				info.setLtel(set.getString("ltel").trim());
				info.setMail(set.getString("mail").trim());
				info.setName(set.getString("name").trim());
				info.setTel(set.getString("tel").trim());
				info.setYh(set.getString("yh").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return info;
	}
		
	// 读取用户
	public static UserInfo getUser(String name, String password) {
		UserInfo user = new UserInfo();
		ResultSet rs = findForResultSet("select * from tb_UserInfo where username='"+ name + "'");
		try {
			if (rs.next()) {
				user.setUsername(name);
				user.setPass(rs.getString("pass"));
				if (user.getPass().equals(password)) {
					user.setName(rs.getString("name"));
					user.setQuan(rs.getString("quan"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return user;
	}
		
	// 执行指定查询
	public static ResultSet query(String QueryStr) {
		ResultSet set = findForResultSet(QueryStr);
		return set;
	}
	
	// 执行删除
	public static int delete(String sql) {
		return update(sql);
	}
		
	// 添加客户信息的方法
	public static boolean addKeHu(CustomerInfo khinfo) {
		if (khinfo == null)
			return false;
		return insert("insert tb_CustomerInfo values('" + khinfo.getId() + "','"+ khinfo.getKhname() + "','" + khinfo.getJian() + "','"+ khinfo.getAddress() + "','" + khinfo.getBianma() + "','"+ khinfo.getTel() + "','" + khinfo.getFax() + "','"+ khinfo.getLian() + "','" + khinfo.getLtel() + "','"+ khinfo.getMail() + "','" + khinfo.getXinhang() + "','"+ khinfo.getHao() + "')");
	}
		
	// 修改客户信息的方法	
	public static int updateKeHu(CustomerInfo khinfo) {
		return update("update tb_CustomerInfo set jian='" + khinfo.getJian()+ "',address='" + khinfo.getAddress() + "',bianma='"+ khinfo.getBianma() + "',tel='" + khinfo.getTel() + "',fax='"+ khinfo.getFax() + "',lian='" + khinfo.getLian() + "',ltel='"+ khinfo.getLtel() + "',mail='" + khinfo.getMail()+ "',xinhang='" + khinfo.getXinhang() + "',hao='"+ khinfo.getHao() + "' where id='" + khinfo.getId() + "'");
		}
		
	// 修改库存的方法
	public static int updateKucunDj(InventoryInfo kcInfo) {
		return update("update tb_InventoryInfo set dj=" + kcInfo.getDj()+ " where id='" + kcInfo.getId() + "'");
	}
		
	// 修改供应商信息的方法
	public static int updateGys(SupplierInfo gysInfo) {
		return update("update tb_SuplierInfo set jc='" + gysInfo.getJc()+ "',address='" + gysInfo.getAddress() + "',bianma='"+ gysInfo.getBianma() + "',tel='" + gysInfo.getTel()+ "',fax='" + gysInfo.getFax() + "',lian='" + gysInfo.getLian()+ "',ltel='" + gysInfo.getLtel() + "',mail='"+ gysInfo.getMail() + "',yh='" + gysInfo.getYh()+ "' where id='" + gysInfo.getId() + "'");
		}
		
	// 添加供应商信息的方法
	public static boolean addGys(SupplierInfo gysInfo) {
		if (gysInfo == null)
			return false;
		return insert("insert tb_SuplierInfo values('" + gysInfo.getId() + "','"+ gysInfo.getName() + "','" + gysInfo.getJc() + "','"+ gysInfo.getAddress() + "','" + gysInfo.getBianma() + "','"+ gysInfo.getTel() + "','" + gysInfo.getFax() + "','"+ gysInfo.getLian() + "','" + gysInfo.getLtel() + "','"+ gysInfo.getMail() + "','" + gysInfo.getYh() + "')");
	}
		
	// 添加商品
	public static boolean addSp(CommodityInfo spInfo) {
		if (spInfo == null)
			return false;
		return insert("insert tb_CommodityInfo values('" + spInfo.getId() + "','"+ spInfo.getSpname() + "','" + spInfo.getJc() + "','"+ spInfo.getCd() + "','" + spInfo.getDw() + "','"+ spInfo.getGg() + "','" + spInfo.getBz() + "','"+ spInfo.getPh() + "','" + spInfo.getPzwh() + "','"+ spInfo.getMemo() + "','" + spInfo.getGysname() + "')");
	}
		
	// 更新商品
	public static int updateSp(CommodityInfo spInfo) {
		return update("update tb_CommodityInfo set jc='" + spInfo.getJc() + "',cd='"+ spInfo.getCd() + "',dw='" + spInfo.getDw() + "',gg='"+ spInfo.getGg() + "',bz='" + spInfo.getBz() + "',ph='"+ spInfo.getPh() + "',pzwh='" + spInfo.getPzwh() + "',memo='"+ spInfo.getMemo() + "',gysname='" + spInfo.getGysname()+ "' where id='" + spInfo.getId() + "'");
	}
	
	// 读取商品信息
	public static CommodityInfo getSpInfo(Item item) {
		String where = "spname='" + item.getName() + "'";
		if (item.getId() != null)
			where = "id='" + item.getId() + "'";
		ResultSet rs = findForResultSet("select * from tb_CommodityInfo where "+ where);
		CommodityInfo spInfo = new CommodityInfo();
		try {
			if (rs.next()) {
				spInfo.setId(rs.getString("id").trim());
				spInfo.setBz(rs.getString("bz").trim());
				spInfo.setCd(rs.getString("cd").trim());
				spInfo.setDw(rs.getString("dw").trim());
				spInfo.setGg(rs.getString("gg").trim());
				spInfo.setGysname(rs.getString("gysname").trim());
				spInfo.setJc(rs.getString("jc").trim());
				spInfo.setMemo(rs.getString("memo").trim());
				spInfo.setPh(rs.getString("ph").trim());
				spInfo.setPzwh(rs.getString("pzwh").trim());
				spInfo.setSpname(rs.getString("spname").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return spInfo;
	}
		
	// 获取所有商品信息
	public static List getSpInfos() {
		List list = findForList("select * from tb_CommodityInfo");
		return list;
	}
		
	// 获取库存商品信息
	public static InventoryInfo getKucun(Item item) {
		String where = "spname='" + item.getName() + "'";
		if (item.getId() != null)
			where = "id='" + item.getId() + "'";
		ResultSet rs = findForResultSet("select * from tb_InventoryInfo where " + where);
		InventoryInfo kucun = new InventoryInfo();
		try {
			if (rs.next()) {
				kucun.setId(rs.getString("id"));
				kucun.setSpname(rs.getString("spname"));
				kucun.setJc(rs.getString("jc"));
				kucun.setBz(rs.getString("bz"));
				kucun.setCd(rs.getString("cd"));
				kucun.setDj(rs.getDouble("dj"));
				kucun.setDw(rs.getString("dw"));
				kucun.setGg(rs.getString("gg"));
				kucun.setKcsl(rs.getInt("kcsl"));
			}
		} catch (SQLException e) {
				e.printStackTrace();
			}
		return kucun;
	}
		
	// 获取入库单的最大ID，即最大入库票号
	public static String getRuKuMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_ruku_main", "RK", "rkid");
	}
		
	// 在事务中添加入库信息
	public static boolean insertRukuInfo(WarehouseMainInfo ruMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加入库主表记录
			insert("insert into tb_WarehouseMainInfo values('" + ruMain.getRkId()
					+ "','" + ruMain.getPzs() + "'," + ruMain.getJe() + ",'"
					+ ruMain.getYsjl() + "','" + ruMain.getGysname() + "','"
					+ ruMain.getRkdate() + "','" + ruMain.getCzy() + "','"
					+ ruMain.getJsr() + "','" + ruMain.getJsfs() + "')");
			Set<WarehouseDetailInfo> rkDetails = ruMain.getTabRukuDetails();
			for (Iterator<WarehouseDetailInfo> iter = rkDetails.iterator(); iter.hasNext();) {
				WarehouseDetailInfo details = iter.next();
				// 添加入库详细表记录
				insert("insert into tb_WarehouseDetailInfo values('" + ruMain.getRkId()+ "','" + details.getTabSpinfo() + "',"+ details.getDj() + "," + details.getSl() + ")");
				// 添加或修改库存表记录
				Item item = new Item();
				item.setId(details.getTabSpinfo());
				CommodityInfo spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					InventoryInfo kucun = getKucun(item);
				if (kucun.getId() == null || kucun.getId().isEmpty()) {
						insert("insert into tb_InventoryInfo values('" + spInfo.getId()+ "','" + spInfo.getSpname() + "','"+ spInfo.getJc() + "','" + spInfo.getCd()+ "','" + spInfo.getGg() + "','"+ spInfo.getBz() + "','" + spInfo.getDw()+ "'," + details.getDj() + ","+ details.getSl() + ")");
				} 
				else
				{
					int sl = kucun.getKcsl() + details.getSl();
					update("update tb_InventoryInfo set kcsl=" + sl + ",dj="+ details.getDj() + " where id='"+ kucun.getId() + "'");
					}
				}
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
					}
				e.printStackTrace();	
			}
		return true;
	}

	public static ResultSet findForResultSet(String sql) {
		if (conn == null)
			return null;
		long time = System.currentTimeMillis();
		ResultSet rs = null;
		try {
			Statement stmt = null;
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			second = ((System.currentTimeMillis() - time) / 1000d) + "";
		} catch (Exception e) {
			e.printStackTrace();
			}
		return rs;
	}
	
	public static boolean insert(String sql) {
		boolean result = false;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return result;
	}
		
	public static int update(String sql) {
		int result = 0;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return result;
	}
	
	public static List findForList(String sql) {
		List<List> list = new ArrayList<List>();
		ResultSet rs = findForResultSet(sql);
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			while (rs.next()) {
				List<String> row = new ArrayList<String>();
				for (int i = 1; i <= colCount; i++) {
					String str = rs.getString(i);
					if (str != null && !str.isEmpty())
						str = str.trim();
					row.add(str);
				}
				list.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return list;
	}
		
	// 获取退货最大ID
	public static String getRkthMainMaxId(Date date) {	
		return getMainTypeTableMaxId(date, "tb_rkth_mainWarhosRtMainInfo", "RT", "rkthId");
	}
		
	// 在事务中添加入库退货信息
	public static boolean insertRkthInfo(WarhosRtMainInfo rkthMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加入库退货主表记录
			insert("insert into tb_rWarhosRtMainInfo values('" + rkthMain.getRkthId()
					+ "','" + rkthMain.getPzs() + "'," + rkthMain.getJe()
					+ ",'" + rkthMain.getYsjl() + "','" + rkthMain.getGysname()
					+ "','" + rkthMain.getRtdate() + "','" + rkthMain.getCzy()
					+ "','" + rkthMain.getJsr() + "','" + rkthMain.getJsfs()
					+ "')");
			Set<WarhosRtDetailInfo> rkDetails = rkthMain.getTbRkthDetails();
			for (Iterator<WarhosRtDetailInfo> iter = rkDetails.iterator(); iter.hasNext();) {
				WarhosRtDetailInfo details = iter.next();
				// 添加入库详细表记录
				insert("insert into tb_WarhosRtDetailInfo values('"+ rkthMain.getRkthId() + "','" + details.getSpid()+ "'," + details.getDj() + "," + details.getSl() + ")");
				// 添加或修改库存表记录
		        Item item = new Item();
				item.setId(details.getSpid());
				CommodityInfo spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					InventoryInfo kucun = getKucun(item);
					if (kucun.getId() != null && !kucun.getId().isEmpty()) {
						int sl = kucun.getKcsl() - details.getSl();
						update("update tb_InventoryInfo set kcsl=" + sl + " where id='"+ kucun.getId() + "'");
					}
				}
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return true;
	}
		
	// 获取销售主表最大ID
	public static String getSellMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_SaleMainInfo", "XS", "sellID");
	}
	
	// 在事务中添加销售信息
	public static boolean insertSellInfo(SaleMainInfo sellMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加销售主表记录
			insert("insert into tb_SaleMainInfo values('" + sellMain.getSellId()
					+ "','" + sellMain.getPzs() + "'," + sellMain.getJe()
					+ ",'" + sellMain.getYsjl() + "','" + sellMain.getKhname()
					+ "','" + sellMain.getXsdate() + "','" + sellMain.getCzy()
					+ "','" + sellMain.getJsr() + "','" + sellMain.getJsfs()
					+ "')");
			Set<SaleDetailInfo> rkDetails = sellMain.getTbSellDetails();
			for (Iterator<SaleDetailInfo> iter = rkDetails.iterator(); iter.hasNext();) {
				SaleDetailInfo details = iter.next();
				// 添加销售详细表记录
				insert("insert into tb_SaleDetailInfo values('"+ sellMain.getSellId() + "','" + details.getSpid()+ "','" + details.getDj() + "," + details.getSl() + ")");
				// 修改库存表记录
				Item item = new Item();
				item.setId(details.getSpid());
				CommodityInfo spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					InventoryInfo kucun = getKucun(item);
					if (kucun.getId() != null && !kucun.getId().isEmpty()) {
						int sl = kucun.getKcsl() - details.getSl();
						update("update tb_InventoryInfo set kcsl=" + sl + " where id='"+ kucun.getId() + "'");
					}
				}
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return true;
	}
		
	// 获取更类主表最大ID
	private static String getMainTypeTableMaxId(Date date, String table,String idChar, String idName) {
		String dateStr = date.toString().replace("-", "");
		String id = idChar + dateStr;
		String sql = "select max(" + idName + ") from " + table + " where "+ idName + " like '" + id + "%'";
		ResultSet set = query(sql);
		String baseId = null;
		try {
			if (set.next())
				baseId = set.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
			}
		baseId = baseId == null ? "000" : baseId.substring(baseId.length() - 3);
		int idNum = Integer.parseInt(baseId) + 1;
		id += String.format("%03d", idNum);
		return id;
	}
	
	public static String getXsthMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_SaleReturnMainInfo", "XT", "xsthID");
	}
		
	public static List getKucunInfos() {
		List list = findForList("select id,spname,dj,kcsl from tb_kucun");
		return list;
	}
		
	// 在事务中添加销售退货信息
	public static boolean insertXsthInfo(SaleReturnMainInfo xsthMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加销售退货主表记录
			insert("insert into tb_SaleReturnMainInfo values('" + xsthMain.getXsthId()
					+ "','" + xsthMain.getPzs() + "'," + xsthMain.getJe()
					+ ",'" + xsthMain.getYsjl() + "','" + xsthMain.getKhname()
					+ "','" + xsthMain.getThdate() + "','" + xsthMain.getCzy()
					+ "','" + xsthMain.getJsr() + "','" + xsthMain.getJsfs()
					+ "')");
			Set<SaleReturnDetailInfo> xsthDetails = xsthMain.getTbXsthDetails();
			for (Iterator<SaleReturnDetailInfo> iter = xsthDetails.iterator(); iter.hasNext();) {
				SaleReturnDetailInfo details = iter.next();
				// 添加销售退货详细表记录
				insert("insert into tb_SaleRetuenDetailInfo values('"+ xsthMain.getXsthId() + "','" + details.getSpid()+ "'," + details.getDj() + "," + details.getSl() + ")");
				// 修改库存表记录
				Item item = new Item();
				item.setId(details.getSpid());
				CommodityInfo spInfo = getSpInfo(item);
				if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
					InventoryInfo kucun = getKucun(item);
					if (kucun.getId() != null && !kucun.getId().isEmpty()) {
						int sl = kucun.getKcsl() - details.getSl();
						update("update tb_InventoryInfo set kcsl=" + sl + " where id='"+ kucun.getId() + "'");
					}
				}
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return true;
	}
		
	// 添加用户
	public static int addUser(UserInfo ul) {
		return update("insert tb_UserInfo values('" + ul.getUsername() + "','"+ ul.getName() + "','" + ul.getPass() + "','" + ul.getQuan()+ "')");
	}
	
	public static List getUsers() {	
		List list = findForList("select * from tb_UserInfo");
		return list;
	}
		
	// 修改用户方法
	public static int updateUser(UserInfo user) {
		return update("update tb_UserInfo set username='" + user.getUsername()+ "',name='" + user.getName() + "',pass='" + user.getPass()+ "',quan='" + user.getQuan() + "' where name='"+ user.getName() + "'");
	}
		
	// 获取用户对象的方法
	public static UserInfo getUser(Item item) {
		String where = "username='" + item.getName() + "'";
		if (item.getId() != null)
			where = "name='" + item.getId() + "'";
		ResultSet rs = findForResultSet("select * from tb_UserInfo where "+ where);
		UserInfo user = new UserInfo();
		try {
			if (rs.next()) {
				user.setName(rs.getString("name").trim());
				user.setUsername(rs.getString("username").trim());
				user.setPass(rs.getString("pass").trim());
				user.setQuan(rs.getString("quan").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return user;
	}
}
