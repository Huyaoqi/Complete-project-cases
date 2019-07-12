package cn.itcast.bookStore.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.security.auth.login.LoginException;

import cn.itcast.bookStore.dao.UserDao;
import cn.itcast.bookStore.domain.PageBean;
import cn.itcast.bookStore.domain.User;
import cn.itcast.bookStore.exception.ActiveUserException;
import cn.itcast.bookStore.exception.AddProductException;
import cn.itcast.bookStore.exception.FindProductByIdException;
import cn.itcast.bookStore.exception.ListUserException;
import cn.itcast.bookStore.exception.RegisterException;
import cn.itcast.bookStore.utils.MailUtils;

public class UserService {
	private UserDao dao = new UserDao();

	// 注册操作
	public void register(User user) throws RegisterException {
		// 调用dao完成注册操作
		try {
			dao.addUser(user);

			// 发送激活邮件
			String emailMsg = "感谢您注册网上书城，点击<a href='http://localhost:8080/bookstore/activeUser?activeCode="
					+ user.getActiveCode() + "'>&nbsp;激活&nbsp;</a>后使用。<br>为保障您的账户安全，请在24小时内完成激活操作";
			MailUtils.sendMail(user.getEmail(), emailMsg);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException("注冊失败");
		}
	}

	// 激活用户
	public void activeUser(String activeCode) throws ActiveUserException {

		try {
			// 根据激活码查找用户
			User user = dao.findUserByActiveCode(activeCode);
			if (user == null) {
				throw new ActiveUserException("激活用户失败");
			}

			// 判断激活码是否过期 24小时内激活有效.
			// 1.得到注册时间
			Date registTime = user.getRegistTime();
			// 2.判断是否超时
			long time = System.currentTimeMillis() - registTime.getTime();
			if (time / 1000 / 60 / 60 > 24) {
				throw new ActiveUserException("激活码过期");
			}

			// 激活用户，就是修改用户的state状态
			dao.activeUser(activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ActiveUserException("激活用户失败");
		}
	}

	// 登录操作
	public User login(String username, String password) throws LoginException {
		try {
			//根据登录时表单输入的用户名和密码，查找用户
			User user = dao.findUserByUsernameAndPassword(username, password);
			//如果找到，还需要确定用户是否为激活用户
			if (user != null) {
				// 只有是激活才能登录成功，否则提示“用户未激活”
				if (user.getState() == 1) {
					return user;
				}
				throw new LoginException("用户未激活");
			}
			throw new LoginException("用户名或密码错误");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("登录失败");
		}

	}
	//查看所有用户
	public List<User> findAllUser() throws ListUserException{
		try {
			return dao.findAllUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ListUserException("查询用户失败");
		}
	}
	
	// 添加用户
	public void addUser(User user) throws AddProductException {

			try {
				dao.addUser(user);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AddProductException("添加商品失败");
			}
		}
	
	//获取当前页数据
	public PageBean findUserByPage(int currentPage, int currentCount) {
		PageBean bean = new PageBean();
		// 封装每页显示数据条数
		bean.setCurrentCount(currentCount);
		// 封装当前页码
		bean.setCurrentPage(currentPage);

		// 封装当前查找类别
		//bean.setCategory(category);

		try {
			// 获取总条数
			int totalCount = dao.findAllCount();
			bean.setTotalCount(totalCount);

			// 获取总页数
			int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
			bean.setTotalPage(totalPage);

			// 获取当前页数据
			List<User> user = dao.findByPage(currentPage, currentCount);
			bean.setUser(user);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}
	
	// 根据id查找商品
	public User findUserById(int id) throws FindProductByIdException {
			try {
				return dao.findUserbyId(id);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new FindProductByIdException("根据ID查找用户失败");
			}
		}
	
	// 修改用户信息
	public void editUser(User user) {
			try {
				dao.editUser(user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	//删除用户
	public void deleteUser(int id) {
		try {
			dao.deleteUser(id);
		} catch (SQLException e) {
			throw new RuntimeException("后台系统根据id删除用户信息失败！");
		}
	}
	
	//多条件查找用户
	public List<User> findUserByManyCondition(String username,String state,String role,String startTime,String endTime){
		List<User> user = null;
		try {
			user = dao.findUserByManyCondition(username, state, role, startTime, endTime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
} 
