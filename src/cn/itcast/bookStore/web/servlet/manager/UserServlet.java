package cn.itcast.bookStore.web.servlet.manager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookStore.domain.User;
import cn.itcast.bookStore.exception.FindProductByIdException;
import cn.itcast.bookStore.exception.ListUserException;
import cn.itcast.bookStore.service.UserService;

/**
 * Servlet implementation class ListUserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private  UserService service = new UserService();  
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String method = request.getParameter("method");
		if("ListUser".equals(method)){
			ListUser(request,response);
		}else if("deleteUser".equals(method)){
			deleteUser(request,response);
		}else if("findUserById".equals(method)){
			findUserById(request,response);
		}else if("editUser".equals(method)){
				editUser(request,response);
		}else if("findUserByManyCondition".equals(method)){
			findUserByManyCondition(request,response);
		}
		
		
		
	}
	//遍历所有用户
	public void ListUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用service层用于查询所有商品的方法
			List<User> user = service.findAllUser();
			// 将查询出的所有商品放进request域中
			request.setAttribute("user", user);
			// 重定向到list.jsp页面
			request.getRequestDispatcher("/admin/users/list.jsp").forward(request, response);
		} catch (ListUserException e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}
	}
	
	//删除用户
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int userId = 0;
		if(id != null && !"".equals(id)){
		userId = Integer.parseInt(id);
		}
		try {
		service.deleteUser(userId);
		response.sendRedirect(request.getContextPath() + "/UserServlet?		method=ListUser");
		} catch (Exception e) {
		e.printStackTrace();
		response.getWriter().write(e.getMessage());
		}
	}
	//编辑用户
	public void findUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			User user = service.findUserById(Integer.parseInt(id));
			request.setAttribute("user", user);
			// 重定向到list.jsp页面
			request.getRequestDispatcher("/admin/users/edit.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FindProductByIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String introduce = request.getParameter("introduce");
		String state = request.getParameter("state");
		String role = request.getParameter("role");
		int userId = 0;
		if(id != null && !"".equals(id)){
			userId = Integer.parseInt(id);
		}
		User user = new User();
		user.setId(userId);
		user.setUsername(username);
		user.setPassword(password);
		user.setGender(gender);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setIntroduce(introduce);
		user.setState(Integer.parseInt(state));
		user.setRole(role);
		
		service.editUser(user);
		response.sendRedirect(request.getContextPath() + "/UserServlet?method=ListUser");
		
	}
	
	//多条件查询
	public void findUserByManyCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String state = request.getParameter("state");
		String role = request.getParameter("role");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		List<User> user = service.findUserByManyCondition(username, state, role, startTime, endTime);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/admin/users/list.jsp").forward(
				request, response);
	}
	
	
	
}
