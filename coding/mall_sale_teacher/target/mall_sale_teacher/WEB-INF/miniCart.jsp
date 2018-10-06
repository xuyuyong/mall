<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2018/10/6
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
        function show_cart() {
            $.get("miniCart.do", function (data) {
                $("#cart_list").html(data);
            });
            $("#cart_list").show();
        }

        function hide_cart() {
            $("#cart_list").hide();
        }
    </script>
    <title>硅谷商城</title>
</head>
<body>
<div class="card">
    <a href="goto_cart_list.do" onmouseout="hide_cart()" onmouseover="show_cart()">购物车
        <div class="num">0</div>
    </a>

    <!--购物车商品-->
    <div id="cart_list" class="cart_pro" style="display:none;">
        <jsp:include page="miniCartList.jsp"></jsp:include>
    </div>
</div>

</body>
</html>
