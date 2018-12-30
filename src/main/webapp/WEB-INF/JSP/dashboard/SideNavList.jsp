<jsp:useBean id="userInfo" scope="request" type="domain.management.DashboardUserInfo"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<li><div class="user-view">
	<div class="background">
		<img src="${pageContext.request.contextPath}/img/dashboard/office.jpg">
	</div>
	<a href="#"><img class="circle" src=${userInfo.accountPicUrl}></a>
	<a href="#"><span class="white-text name">${userInfo.userFirstName} ${userInfo.userLastName}</span></a>
	<a href="#"><span class="white-text email">${userInfo.email}</span></a>
</div></li>
<li><a href="#"><i class="material-icons">cloud</i><span>User management</span></a></li>
<li><a href="#"><i class="material-icons">cloud</i><span>Account management</span></a></li>
<li><a href="#"><i class="material-icons">cloud</i><span>Role management</span></a></li>
<li><a href="#"><i class="material-icons">cloud</i><span>Permission management</span></a></li>
<li><div class="divider"></div></li>
<li><a class="subheader">Settings</a></li>
<li><a class="waves-effect" href="#">Logout</a></li>

