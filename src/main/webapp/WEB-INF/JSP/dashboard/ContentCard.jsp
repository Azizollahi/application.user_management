<jsp:useBean id="cardsInfo" scope="request" type="domain.management.CardsInfo"/>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<div class="section"></div>
	<div class="row">
		<c:forEach var="i" begin="0" end="${cardsInfo.cardInfo.size() -1}">
			<div class="col s12 m6">
				<div class="card z-depth-1 hoverable waves-effect waves-light teal">
					<a href="${cardsInfo.cardInfo[i].link}">
						<div class="card-content">
							<p style="color: black">${cardsInfo.cardInfo[i].description}</p>
						</div>
					</a>
				</div>
			</div> <!-- col m6 -->
		</c:forEach>
	</div> <!-- row -->

</div>