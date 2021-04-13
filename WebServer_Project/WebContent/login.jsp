<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <% Boolean errorState =
(request.getAttribute("errorState") != null); String username = ""; if
(errorState) { username = request.getParameter("uname"); } %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Login Page</title>
    <link href="Style.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <jsp:include page="navigationBar.jsp" />
    <div class="content-container">
      <div class="question-title-container">Log In</div>

      <form action="Login" method="post">
        <div class="container">
          <% if (errorState) { %>
          <div class="error-message">
            <%=request.getAttribute("errorMessage") %>
          </div>
          <% } %>
          <label for="uname"><b>Username</b></label>
          <input
            type="text"
            placeholder="Enter Username"
            name="uname"
            value="<%=username%>"
            required
          />

          <label for="psw"><b>Password</b></label>
          <input
            type="password"
            placeholder="Enter Password"
            name="psw"
            required
          />

          <div class="btn-group">
            <button style="margin: 5px" type="submit" class="custom-button">
              Login
            </button>
            <button
              style="margin: 5px"
              type="button"
              class="custom-button"
              onclick="window.location.href = 'registration.jsp'"
            >
              Register
            </button>
          </div>
        </div>
      </form>
    </div>
  </body>
</html>