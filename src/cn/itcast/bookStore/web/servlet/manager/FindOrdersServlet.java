package cn.itcast.bookStore.web.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookStore.domain.Order;
import cn.itcast.bookStore.service.OrderService;
@WebServlet("/findOrders")
//查找所有订单
public class FindOrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		OrderService service = new OrderService();

		List<Order> orders = service.findAllOrder();

		request.setAttribute("orders", orders);
		
		request.getRequestDispatcher("/admin/orders/list.jsp").forward(request, response);
	}

}
