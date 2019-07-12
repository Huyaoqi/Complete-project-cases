<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/admin/css/Style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${pageContext.request.contextPath}/admin/js/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/My97DatePicker/WdatePicker.js"></script>
<!-- <script type="text/javascript">
	function addProduct() {
		window.location.href = "${pageContext.request.contextPath}/admin/products/add.jsp";
	}
</script> -->
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/UserServlet?method=findUserByManyCondition"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
						<strong>查 询 条 件</strong>
					</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									用户名：</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="username" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									状态：</td>
								<td class="ta_01" bgColor="#ffffff">
									<select name="state">
										<option value="" selected="selected">--选择用户状态--</option>
										<option value="1">正常</option>
										<option value="0">注销</option>
									</select>
								</td>
							</tr>

							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									角色：</td>
								<td class="ta_01" bgColor="#ffffff"><select name="role">
										<option value="" selected="selected">--选择角色类别--</option>
										<option value="超级用户">超级用户</option>
										<option value="普通用户">普通用户</option>
								</select></td>
								
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									注册时间：</td>
								<td class="ta_01" bgColor="#ffffff">
									<input id="startTimeStr" style="width:140px" name="startTime" value="${page.startTimeStr }" onfocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly">
										&nbsp; - &nbsp;
									<input id="endTimeStr" style="width:140px" name="endTime" value="${page.endTimeStr }" onfocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly">
								</td>
							</tr>

							<tr>
								<td width="100" height="22" align="center" bgColor="#f5fafe"
									class="ta_01"></td>
								<td class="ta_01" bgColor="#ffffff"><font face="宋体"
									color="red"> &nbsp;</font>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01"><br>
									<br></td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<button type="submit" id="search" name="search"
										value="&#26597;&#35810;" class="button_view" style="CURSOR: hand">
										&#26597;&#35810;</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
									type="reset" name="reset" value="&#37325;&#32622;"
									class="button_view" style="CURSOR: hand" />
								</td>
							</tr>
						</table>
					</td>

				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>用户 列 表</strong>
					</TD>
				</tr>
				<!-- <tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="&#28155;&#21152;"
							class="button_add" onclick="addProduct()">&#28155;&#21152;
						</button>
					</td>
				</tr> -->
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="6%">用户编号</td>
								<td align="center" width="8%">用户名</td>
								<td align="center" width="8%">密码</td>
								<td align="center" width="6%">性别</td>
								<td width="13%" align="center">邮箱</td>
								<td width="8%" align="center">电话号码</td>
								<td width="14%" align="center">自我介绍</td>
								<td align="center" width="9%">激活码</td>
								<td width="5%" align="center">状态</td>
								<td width="8%" align="center">角色</td>
								<td width="25%" align="center">注册时间</td>
								<td width="12%" align="center">编辑</td>
								<td width="12%" align="center">删除</td>
							</tr>

							<c:forEach items="${user}" var="user">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										>${user.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										>${user.username }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										>${user.password }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										>${user.gender }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										>${user.email }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
										${user.telephone}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
										${user.introduce}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
										${user.activeCode}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
										<c:if test="${user.state == 1 }">正常</c:if>
										<c:if test="${user.state == 0 }">注销</c:if>
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
										${user.role}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
										${user.registTime}</td>
									<td align="center" style="HEIGHT: 22px" width="14%"><a
										href="${pageContext.request.contextPath}/UserServlet?method=findUserById&id=${user.id}">
											<img
											src="${pageContext.request.contextPath}/admin/images/i_edit.gif"
											border="0" style="CURSOR: hand"> </a>
									</td>

									<td align="center" style="HEIGHT: 22px" width="14%"><a
										href="${pageContext.request.contextPath}/UserServlet?method=deleteUser&id=${user.id}">
											<img
											src="${pageContext.request.contextPath}/admin/images/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>

