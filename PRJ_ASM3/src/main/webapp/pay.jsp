<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/pay/styles.css">
</head>
<body>
	<div class="container">
  <div class="title">
      <h2>Product Order Form</h2>
  </div>
<div class="d-flex">
  <form action="PayController" method="post">
  <%
	String error = (String)session.getAttribute("error");
		if(error!= null){
		 	out.println(error);
		 }	
	%>
    <label for="username">
      <span class="fname">First Name <span >*</span></span>
      <input type="text" name="username" id="username">
    </label>
    <label for="address">
      <span class="fname">Address <span >*</span></span>
      <input type="text" name="address" id="address">
    </label>
    <label for="discount">
      <span>Discount</span>
      <input type="text" name="discount" id="discount">
    </label>
    <button type="submit">Place Order</button>
  </form>
  <div class="Yorder">
    <table>
      <tr>
        <th colspan="2">Your order</th>
      </tr>
      <tr>
        <td>Subtotal</td>
        <td></td>
      </tr>
      <tr>
        <td>Shipping</td>
        <td>Free shipping</td>
      </tr>
    </table><br>
    <div>
      <input type="radio" name="dbt" value="dbt" checked> Direct Bank Transfer
    </div>
    <p>
        Make your payment directly into our bank account. Please use your Order ID as the payment reference. Your order will not be shipped until the funds have cleared in our account.
    </p>
    <div>
      <input type="radio" name="dbt" value="cd"> Cash on Delivery
    </div>
    
  </div><!-- Yorder -->
 </div>
</div>
</body>
</html>