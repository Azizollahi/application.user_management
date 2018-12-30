
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/dashboard/baseline-dashboard-24px.svg" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/materialize/materialize.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css"/>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/lib/materialize/materialize.min.js"></script>
	<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.3.1.min.js"></script>
	<script language="JavaScript" src="${pageContext.request.contextPath}/js/dashboard/sidenav.js"></script>
	<script language="JavaScript" src="${pageContext.request.contextPath}/js/dashboard/dropdown.js"></script>
	<title>Dashboard</title>
</head>
<body>

<header>
<jsp:include page="header.jsp"/>
</header>

<jsp:include page="Content.jsp"/>

<footer>
</footer>

</body>
</html>
