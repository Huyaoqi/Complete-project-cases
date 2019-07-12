<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/admin/css/Style.css"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/public.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/check.js"></script>

</HEAD>
<script type="text/javascript">
	//设置类别的默认值
	function setInitialValue(s,r,g) {
		var state = document.getElementById("state");
		var role = document.getElementById("role");
		var gender = document.getElementById("gender");
		var state1 = state.options;
		var role1 = role.options;
		var gender1 = gender.options;
		for ( var i = 0; i < state1.length; i++) {
			if (state1[i].value == s) {
				state1[i].selected = true;
			}
		}
		for ( var i = 0; i < role1.length; i++) {
			if (role1[i].value == r) {
				role1[i].selected = true;
			}
		}
		for ( var i = 0; i < gender1.length; i++) {

			if (gender1[i].value == g) {
				gender1[i].selected = true;
				return;
			}
		} 
	};
</script>
<body onload="setInitialValue('${user.state}','${user.role}','${user.gender }')">
	<form id="userAction_save_do" name="Form1"
		action="${pageContext.request.contextPath}/UserServlet?method=editUser" method="post">
		<input type="hidden" name="id" value="${user.id}" /> &nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong><STRONG>编辑用户</STRONG> </strong></td>
			</tr>


			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">用户名：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="username" class="bg" value="${user.username }" /></td>
				<td align="center" bgColor="#f5fafe" class="ta_01">电话号码：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="telephone" class="bg" value="${user.telephone}" /></td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">性别：</td>
				<td class="ta_01" bgColor="#ffffff"><select name="gender"
					id="gender">
						<option value="男">男</option>
						<option value="女">女</option>
				</select></td>
				<td align="center" bgColor="#f5fafe" class="ta_01">邮箱：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="email" class="bg" value="${user.email}" /></td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">用户密码：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="password" class="bg" value="${user.password }" /></td>
				<td align="center" bgColor="#f5fafe" class="ta_01">激活码：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="activeCode" class="bg" value="${user.activeCode}" readonly />&emsp;<span style="color:#F00000">*不可更改*</span></td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">角色：</td>
				<td class="ta_01" bgColor="#ffffff"><select name="role"
					id="role">
						<option value="">--选择用户角色--</option>
						<option value="超级用户">超级用户</option>
						<option value="普通用户">普通用户</option>
				</select></td>
				<td align="center" bgColor="#f5fafe" class="ta_01">注册时间：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="registTime" class="bg" value="${user.registTime}" readonly />&emsp;<span style="color:#F00000">*不可更改*</span></td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">状态：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3"><select name="state"
					id="state">
						<option value="">--选择用户状态--</option>
						<option value="1">正常</option>
						<option value="0">注销</option>
				</select></td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">自我介绍：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3"><textarea
						name="introduce" cols="30" rows="3" style="WIDTH: 96%">${user.introduce}</textarea></td>
			</tr>
			<TR>
				<td align="center" colSpan="4" class="sep1"><img
					src="${pageContext.request.contextPath}/admin/images/shim.gif">
				</td>
			</TR>


			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4"><input type="submit"
					class="button_ok" value="确定"> <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<input type="reset" value="重置" class="button_cancel"> <FONT
					face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT> <INPUT
					class="button_ok" type="button" onclick="history.go(-1)" value="返回" />
					<span id="Label1"> </span></td>
			</tr>
		</table>
	</form>




</body>
</HTML>