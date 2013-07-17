package cn.luck.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cn.luck.bean.DoubleBallBean;

public class DoubleBallDAO {

	public int insertDoubleBall(DoubleBallBean bean) throws Exception {
		Connection conn = this.getConnectionByJDBC();
		String sql = "insert into doubleball(id,red1,red2,red3,red4,red5,red6,blue,publishTime) values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, bean.getId());
			statement.setInt(2, bean.getRed1());
			statement.setInt(3, bean.getRed2());
			statement.setInt(4, bean.getRed3());
			statement.setInt(5, bean.getRed4());
			statement.setInt(6, bean.getRed5());
			statement.setInt(7, bean.getRed6());
			statement.setInt(8, bean.getBlue());
			statement.setDate(9, new java.sql.Date(bean.getPublishTime()
					.getTime()));
			return statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			conn.close();
		}
	}
	
	public int selectDoubleBallID(int id) throws Exception {
		Connection conn = this.getConnectionByJDBC();
		String sql = "select id from doubleball where id=?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			int x=0;
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				x=rs.getInt("id");
			}
			return x;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			conn.close();
		}
	}

	public List<DoubleBallBean> findDoubleBalls(Date begin, Date end)
			throws Exception {
		Connection conn = this.getConnectionByJDBC();
		String sql = "select * from doubleball where publishTime>=? and publishTime<=?";
		try {
			PreparedStatement statement = this.getConnectionByJDBC()
					.prepareStatement(sql);
			statement.setDate(1, new java.sql.Date(begin.getTime()));
			statement.setDate(2, new java.sql.Date(end.getTime()));

			List<DoubleBallBean> results = new ArrayList<DoubleBallBean>();
			ResultSet rs = statement.executeQuery();
			DoubleBallBean bean = null;
			while (rs.next()) {
				bean = new DoubleBallBean();
				bean.setId(rs.getInt("id"));
				bean.setRed1(rs.getInt("red1"));
				bean.setRed2(rs.getInt("red2"));
				bean.setRed3(rs.getInt("red3"));
				bean.setRed4(rs.getInt("red4"));
				bean.setRed5(rs.getInt("red5"));
				bean.setRed6(rs.getInt("red6"));
				bean.setBlue(rs.getInt("blue"));
				bean.setPublishTime(rs.getDate("publishTime"));
				results.add(bean);
			}

			return results;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			conn.close();
		}
	}

	public List<DoubleBallBean> findDoubleBalls(Date begin, int number)
			throws Exception {
		Connection conn = this.getConnectionByJDBC();
		String sql = "select * from doubleball where publishTime>=? order by publishTime limit 0,?";
		try {
			PreparedStatement statement = this.getConnectionByJDBC()
					.prepareStatement(sql);
			statement.setDate(1, new java.sql.Date(begin.getTime()));
			statement.setInt(2, number);

			List<DoubleBallBean> results = new ArrayList<DoubleBallBean>();
			ResultSet rs = statement.executeQuery();
			DoubleBallBean bean = null;
			while (rs.next()) {
				bean = new DoubleBallBean();
				bean.setId(rs.getInt("id"));
				bean.setRed1(rs.getInt("red1"));
				bean.setRed2(rs.getInt("red2"));
				bean.setRed3(rs.getInt("red3"));
				bean.setRed4(rs.getInt("red4"));
				bean.setRed5(rs.getInt("red5"));
				bean.setRed6(rs.getInt("red6"));
				bean.setBlue(rs.getInt("blue"));
				bean.setPublishTime(rs.getDate("publishTime"));
				results.add(bean);
			}

			return results;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			conn.close();
		}
	}

	private Connection getConnectionByJDBC() {
		Connection conn=null;
		try {
			// 装载驱动包类
			Class.forName("com.mysql.jdbc.Driver");// 加载驱动
		} catch (ClassNotFoundException e) {
			System.out.println("装载驱动包出现异常!请查正！");
			e.printStackTrace();
		}
		try {
			/**
			 * 建立jdbc连接，但要注意此方法的第一个参数， 如果127.0.0.1出现CommunicationsException异常，
			 * 可能就需要改为localhost才可以
			 **/
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/game", "root", "12345678");
		} catch (SQLException e) {
			System.out.println("链接数据库发生异常!");
			e.printStackTrace();
		}
		return conn;
	}
}
