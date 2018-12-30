<%@ page contentType="text/html;charset=UTF-8" %>
<script language="JavaScript" src="${pageContext.request.contextPath}/js/dashboard/sidenav.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath}/js/dashboard/dropdown.js"></script>
<jsp:useBean id="headerInfo" scope="request" type="domain.management.HeaderInfo"/>
<jsp:useBean id="userInfo" scope="request" type="domain.management.DashboardUserInfo"/>

<nav style="background-color: #42a5f5">
	<div class="nav-wrapper">
		<div class="container">
			<a href="#" data-target="slide-out" class="sidenav-trigger" >
				<img src="${pageContext.request.contextPath}/img/dashboard/nav_side.png"
					 style="max-width: 9vw; max-height: 9vh;" class="vertical-center" />
			</a>
			<div class="brand-logo">
				<span>${headerInfo.headerTitle}</span>
			</div>
		</div>
		<div class="container">
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><span class="white-text name">${userInfo.userFirstName} ${userInfo.userLastName}</span></li>
				<li >
					<a class="dropdown-trigger" data-target='dropdown-user-profile' href="#" >
						<img class="vertical-center" style="width: 2%; height: 50%;" src=${userInfo.accountPicUrl} />
					</a>
				</li>
			</ul>
		</div>

	</div> <!-- nav-wrapper -->
</nav>
<ul id="slide-out" class="sidenav">
	<jsp:include page="SideNavList.jsp" />
</ul>

<ul id="dropdown-user-profile" class="dropdown-content" style="min-width: 14vw">
	<jsp:include page="Dropdown_List.jsp" />
</ul>

