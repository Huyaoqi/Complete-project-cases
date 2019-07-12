package cn.itcast.bookStore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.bookStore.domain.User;
import cn.itcast.bookStore.utils.DataSourceUtils;

public class UserDao {
	// 添加用户
	public void addUser(User user) throws SQLException {
		String sql = "insert into user(username,password,gender,email,telephone,introduce,activecode,registTime) values(?,?,?,?,?,?,?,NOW())";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, user.getUsername(), user.getPassword(),
				user.getGender(), user.getEmail(), user.getTelephone(),
				user.getIntroduce(), user.getActiveCode());
		if (row == 0) {
			throw new RuntimeException();
		}
	}

	// 根据激活码查找用户
	public User findUserByActiveCode(String activeCode) throws SQLException {
		String sql = "select * from user where activecode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), activeCode);

	}

	// 激活用戶
	public void activeUser(String activeCode) throws SQLException {
		String sql = "update user set state=? where activecode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, 1, activeCode);
	}
	
	//根据用户名与密码查找用户
	public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
		String sql="select * from user where username=? and password=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class),username,password);
	}
	
	//查看所有用户
	public List<User> findAllUser() throws SQLException{
		String sql="select * from user";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<User>(User.class));
	}
	//通过userId查找用户
	public User findUserbyId(int id) throws SQLException{
		String sql="select * from user where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class),id);
	}
	//删除用户
	public void deleteUser(int id) throws SQLException {
		String sql = "DELETE FROM user WHERE id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}
	//编辑用户信息
	public void editUser(User user) throws SQLException {

		List<Object> obj = new ArrayList<Object>();
		obj.add(user.getUsername());
		obj.add(user.getPassword());
		obj.add(user.getGender());
		obj.add(user.getEmail());
		obj.add(user.getTelephone());
		obj.add(user.getIntroduce());
		obj.add(user.getRole());
		obj.add(user.getState());
		String sql  = "update user set  username=?,password=? ,gender=?,email=?,telephone=?,introduce=?,role=?,state=? ";
		sql += " where id=?";
		obj.add(user.getId());
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, obj.toArray());

	}
	
	//获取总的用户数
	public int findAllCount() throws SQLException{
		String sql = "select count(*) from user";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Long count = (Long) runner.query(sql, new ScalarHandler());
		return count.intValue();
	}
	//获取当前页用户
	public List<User> findByPage(int currentPage, int currentCount) throws SQLException {
		// 要执行的sql语句
		String sql = null;
		// 参数
		Object[] obj = null;
		
		sql = "select * from user  limit ?,?";
		obj = new Object[] { (currentPage - 1) * currentCount,
					currentCount, };
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<User>(User.class),
				obj);
	}
	
	//多条件查找用户
	public List<User> findUserByManyCondition(String username,String state,String role,String startTime,String endTime) throws SQLException{
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from user where 1=1 ";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if (username != null && username.trim().length() > 0) {
			sql += " and username=?";
			list.add(username);
		}
		if (state != null && !"".equals(state)) {
			sql += " and state=?";
			list.add(state);
		}
		if (role != null && !"".equals(role)) {
			sql += " and role=?";
			list.add(role);
		}
		//开始时间和结束时间都不为空
		if((startTime != null && !"".equals(startTime)) && (endTime != null && !"".equals(endTime))){
			sql += " and registTime between date_format(?,'%Y-%c-%d %h:%i:%s') and date_format(?,'%Y-%c-%d %h:%i:%s')";
			list.add(startTime);
			list.add(endTime);
		}
		//开始时间不为空，结束时间为空
		if((startTime != null && !"".equals(startTime)) && (endTime == null || "".equals(endTime))){
			sql += " and registTime >= date_format(?,'%Y-%c-%d %h:%i:%s')";
			list.add(startTime);
		}
		//开始时间为空，结束时间不为空
		if((startTime == null || "".equals(startTime)) && (endTime != null && !"".equals(endTime))){
			sql += " and registTime <= date_format(?,'%Y-%c-%d %h:%i:%s')";
			list.add(endTime);
		}
		
		Object[] params = list.toArray();/*
		System.out.println(sql);
		System.out.println(params.toString());*/
		return runner.query(sql, new BeanListHandler<User>(User.class),
				params);
		
	}
	
	
}
