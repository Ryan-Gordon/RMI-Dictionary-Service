<%@ include file="includes/header.jsp" %>	

		<h1>Dictionary</h1>
			<p>Enter a word to get the defintion. Supports all word in Mac OS Dictioanry</p>	
		<form action="dictionaryServlet" method="post">			
			 <h1>Please Enter a Word</h1><br>
			<input type="text" name="word"size="20px">
			<input type="submit" value="submit"><br>			
		</form>	
		<form action="DictionaryServlet" method="get">
			<div class="def"  >
				<h3><%= request.getParameter("result") %></h3>
			</div>
		</form>

<%@ include file="includes/footer.jsp" %>