<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="searchComponent" scope="request" type="application.management.models.SearchComponentModel"/>

<div class="row">
	<form action="${searchComponent.searchCallBackUrl}" method="post">
		<div class="col m6 input-field valign-wrapper">
			<input id="searchName" type="text" class="validate" name="searchName"/>
			<label for="searchName">Search Name</label>
		</div>
		<div class=" col m2" style="margin-top: 2%">
			<button type = "submit" class="waves-effect waves-light btn"><i class="material-icons left">search</i>Search</button>
		</div>
	</form>
	<div class="col m2" style="margin-top: 2%">
		<a id = "searchExportId" href="#"
		   onclick="exportByFindingName(this, '${searchComponent.exportCallbackUrl}')"
		   class="waves-effect waves-light btn"><i class="material-icons left">import_export</i>Export</a>
	</div>
	<div class="col m2" style="margin-top: 2%">
		<a href="#addModal"
		   onclick="openAddModal(this, '${searchComponent.accountInfoCallbackUrl}')" class="waves-effect waves-light btn"><i class="material-icons left">add_circle_outline</i>Add</a>
	</div>
</div>

<div>
	<div id="addModal" class="modal modal-fixed-footer">
		<form action="${searchComponent.addSaveCallbackUrl}" method="post">
			<div class="modal-content">
				<table id="addModalTable" class="responsive-table">
				</table>
			</div>
			<div class="modal-footer">
				<button type = "submit" class="waves-effect waves-light btn"><i class="material-icons left">send</i>Save</button>
			</div>
		</form>
	</div>
</div>
