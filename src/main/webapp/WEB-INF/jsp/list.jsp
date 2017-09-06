<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="common/head.jsp"%>
    <title>9.12秒杀商品列表</title>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center" style="background-size: cover;background-image: url('../../images/head_bg.jpg');height: 280px"></div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>秒杀商品</th>
                        <th>秒杀价格</th>
                        <th>商品库存</th>
                        <th>开启时间</th>
                        <th>结束时间</th>
                        <th>创建时间</th>
                        <th>点击进入</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="seckill" items="${list}">
                        <tr>
                            <td>${seckill.name}</td>
                            <td>
                                    <fmt:formatNumber value="${seckill.seckillPrice}" pattern="0.00" maxFractionDigits="2"/>
                            </td>
                            <td>${seckill.number}</td>
                            <td>
                                <fmt:formatDate value="${seckill.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${seckill.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${seckill.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <a class="btn btn-default" href="${pageContext.request.contextPath}/seckill/${seckill.seckillId}/detail" target="_blank">Link</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>
        <div class="panel-footer">版权所有: @ kynezhk@gmail.com</div>
    </div>
</div>
</body>
</html>
