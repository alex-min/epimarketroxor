<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="menu.jsp" />

<p> Test de page d'inscription </p>

<form:form modelAttribute="commandForm"></form:form>

<div>
	<form action="/epimarket/app/registration" method="POST">
		<p>
			<label for="LOGIN">Login : </label>
			<input type="text" name="LOGIN" id="LOGIN"/>
		</p>
		<p>
			<label for="PASSWORD">Password : </label>
			<input type="password" name="PASSWORD" id="PASSWORD"/>
		</p>
		<p>
			<label for="EMAIL">Email : </label>
			<input type="text" name="EMAIL" id="EMAIL"/>
		</p>
		<p>
			<label for="AGE">Age : </label>
			<input type="text" name="AGE" id="AGE"/>
		</p>
		<p>
			<?php echo "Sex : " ?>
			
			<input type="radio" name="SEXE" value="Homme" id="HOMME"/>
			<label for="HOMME">Homme</label>
			
			<input type="radio" name="SEXE" value="Femme" id="FEMME"/>
			<label for="FEMME">Femme</label>
		</p>
		<p>
			<input class="submitform" type="submit" value="Inscription">
		</p>
	</form>
</div>
