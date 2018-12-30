<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="LoginHeader.jsp"/>
<div class="section"></div>
<main>
	<div style="text-align: center;">

		<div class="container"> <!-- body -->
		<h5 class="indigo-text">Please, login into your account</h5>
		<div class="section"></div>

			<div class="center-align z-depth-1 grey lighten-4 row" style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;"> <!-- form border -->
				<form class="col s12" method="post" action="/login" id="loginFormId">
					<div class='row'>
						<div class='col s12'>
						</div>
					</div>

					<div class='row'>
						<div class='input-field col s12'>
							<input class='validate' type='email' name='userName' id='userName' />
							<label for='userName'>Enter your email</label>
						</div>
					</div>

					<div class='row'>
						<div class='input-field col s12'>
							<input class='validate' type='password' name='password' id='password' />
							<label for='password'>Enter your password</label>
						</div>
					</div>

					<br />
					<div style="text-align: center;">
						<div class='row'>
							<button type='submit' name='btn_login' class='col s12 btn btn-large waves-effect indigo'>Login</button>
						</div>
					</div>
				</form>
			</div> <!-- form border -->
		</div> <!-- body -->
	</div>
	<div class="section"></div>
	<div class="section"></div>
</main>
<jsp:include page="LoginFooter.jsp"/>
