<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath %>">
    <%--<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>--%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        $(function () {
            /*$.getJSON("js/json/class_1.js", function (data) {
                $(data).each(function (i, json) {
                    $("#attr_class_1_select").append("<option value=" + json.id + ">" + json.flmch1 + "</option>");
                });
            });*/
            $('#attr_class_1_select').combobox({
                url:'js/json/class_1.js',
                valueField:'id',
                textField:'flmch1',
                width:200,
                onChange:function get_attr_class_2(){
                    // 获取当前的下拉列表被选中的id
                    var class_1_id = $(this).combobox("getValue");
                    $('#attr_class_2_select').combobox({
                        url:"js/json/class_2_"+class_1_id+".js",
                        valueField:'id',
                        textField:'flmch2',
                        onChange:function (){
                            var flbh2 = $(this).combobox("getValue");
                            get_attr_list_json(flbh2);
                        },
                        width:200
                    });
                }
            });
        });

        function get_attr_class_2(class_1_id) {
            $.getJSON("js/json/class_2_" + class_1_id + ".js", function (data) {
                $("#attr_class_2_select").empty();
                $(data).each(function (i, json) {
                    $("#attr_class_2_select").append("<option value=" + json.id + ">" + json.flmch2 + "</option>");
                });
            });
        }

        function goto_attr_add() {
            // var class_2_id = $("#attr_class_2_select").val();
            // add_tab2('goto_attr_add.do','商品属性管理添加')
            var class_2_id = $("#attr_class_2_select").combobox("getValue")
            add_tab2("goto_attr_add.do?flbh2="+class_2_id,"商品属性管理添加");
            // window.location.href = "goto_attr_add.do?flbh2=" + class_2_id;
        }

        function get_attr_list(flbh2) {
            // 异步查询
            $.post("get_attr_list.do", {flbh2: flbh2}, function (data) {
                $("#attrListInner").html(data);
            });
        }

        function add_tab2(url, title) {
            // add a new tab panel
            var b = $('#tt').tabs('exists', title);
            if (!b) {
                var class_2_id = $("#attr_class_2_select").val();
                $.get(url, function (data) {
                    $('#tt').tabs('add', {
                        title: title,
                        content: data,
                        closable: true,
                        tools: [{
                            iconCls: 'icon-mini-refresh',
                            handler: function () {
                                alert('refresh');
                            }
                        }]
                    });
                });
            } else {
                $('#tt').tabs('select', title);
            }
        }

        /**
         * 获取表格数据
         * @param flbh2
         */
        function get_attr_list_json(flbh2) {
            // 异步查询
            $('#attrListInner').datagrid({
                url: 'get_attr_list_json.do',
                queryParams: {
                    flbh2: flbh2
                },
                columns: [[
                    {field: 'id', title: 'id', width: 100},
                    {field: 'shxm_mch', title: '属性名', width: 100},
                    {
                        field: 'list_value', title: '属性值', width: 300,
                        formatter: function (value, row, index) {
                            var str = "";
                            // 处理字段值的代码
                            $(value).each(function (i, json) {
                                str = str + json.shxzh + json.shxzh_mch + " ";
                            });
                            return str;
                        }
                    },
                    {
                        field: 'chjshj', title: '创建时间', width: 300,
                        formatter: function (value, row, index) {
                            var d = new Date(value);
                            var str = d.toLocaleString();
                            return str;
                        }
                    }
                ]]
            });
        }
    </script>
    <title>硅谷商城</title>
</head>
<body>

    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',split:true" style="height:50px">
            <div style="margin-top:10px;margin-left:10px">
                一级：<select id="attr_class_1_select" data-options="width:200"
                           class="easyui-combobox" onchange="get_attr_class_2(this.value);"></select>
                二级：<select id="attr_class_2_select" data-options="width:200" class="easyui-combobox" onchange="get_attr_list_json(this.value)">
                </select>
                <a href="javascript:goto_attr_add()">添加</a><br>

            </div>
        </div>
        <div data-options="region:'west',split:true" style="width:100px">
            查询<br>
            删除<br>
            编辑<br>
            <hr>
        </div>
        <div data-options="region:'center'">
            <div id="attrListInner" class="easyui-datagrid"></div>
        </div>
    </div>
</body>
</html>