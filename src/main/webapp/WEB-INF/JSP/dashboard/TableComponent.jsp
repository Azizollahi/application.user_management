<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="tableComponent" scope="request" type="application.management.models.TableComponentModel"/>

<div class="row" style="border-top: 2px solid teal">
	<table class="responsive-table striped centered" >
		<thead>
			<tr>
				<c:if test="${tableComponent.headerNames != null && tableComponent.headerNames.size() > 0}">
					<c:forEach var = "i" begin = "0" end = "${tableComponent.headerNames.size()-1}">
						<th>${tableComponent.headerNames.get(i)}</th>
					</c:forEach>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:if test="${tableComponent.tableValues != null && tableComponent.tableValues.size() > 0}">
				<c:forEach var="i" begin="0" end="${tableComponent.tableValues.size()-1}">
					<tr>
						<c:forEach var="j" begin="0" end="${tableComponent.tableValues.get(i).size() -1}">
							<td>${tableComponent.tableValues.get(i).get(j)}</td>
						</c:forEach>
						<td><div class="row">
							<a href="#editModal"
							   onclick="openEditModal(this, '${tableComponent.tableValues.get(i).get(0)}',
								   '${tableComponent.accountCallbackUrl}?accountName=${tableComponent.tableValues.get(i).get(0)}')"
							   class="col m-1 modal-trigger">
								<i class="material-icons left">edit</i>
							</a>
							<a href="${tableComponent.exportCallbackUrl}?exportId=${tableComponent.tableValues.get(i).get(0)}"
							   target="_blank"
							   class="col m-1">
								<i class="material-icons left">import_export</i>
							</a>
							<a href="#" onclick="deleteItem(this, '${tableComponent.tableValues.get(i).get(0)}', '${tableComponent.deleteCallbackUrl}')"
							   class="col m-1">
								<i class="material-icons left">delete</i>
							</a>
						</div></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>

<div id="editModal" class="modal modal-fixed-footer">
	<form action="${tableComponent.editSaveCallbackUrl}" method="post">
		<div class="modal-content">
			<table id="editorModal" class="responsive-table">
			</table>
		</div>
		<div class="modal-footer">
			<button type = "submit" class="waves-effect waves-light btn"><i class="material-icons left">send</i>Save</button>
		</div>
	</form>
</div>

