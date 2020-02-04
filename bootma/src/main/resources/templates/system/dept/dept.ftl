<!DOCTYPE html>
<html>
<meta charset="utf-8">
<#include '../../header.ftl'/>
<body class="gray-bg">
<div class="wrapper wrapper-content ">
    <div class="col-sm-12">
        <div class="ibox">
            <div class="ibox-body">
                <div class="fixed-table-toolbar">
                        <@shiro.hasPermission name="sys:dept:add">
                            <div class="columns pull-left">
                                <button  type="button" class="btn  btn-primary"
                                        onclick="add(0)">
                                    <i class="fa fa-plus" aria-hidden="true"></i>添加
                                </button>
                            </div>
                        </@shiro.hasPermission>
                <#-- <div class="columns pull-right">
                     <button class="btn btn-success" onclick="reLoad()">查询</button>
                 </div>

                 <div class="columns pull-right col-md-2 nopadding">
                     <input id="searchName" type="text" class="form-control"
                            placeholder="">
                 </div>-->
                </div>
                <table id="exampleTable" data-mobile-responsive="true">
                </table>
            </div>
        </div>
    </div>
</div>
<!--shiro控制bootstraptable行内按钮看见性 来自bootma的创新方案 -->
    <script type="text/javascript">
        var s_add_h = 'hidden';
        var s_edit_h = 'hidden';
        var s_remove_h = 'hidden';
    </script>
<@shiro.hasPermission name="sys:dept:add">
        <script type="text/javascript">
            s_add_h = '';
        </script>
</@shiro.hasPermission>
<@shiro.hasPermission name="sys:dept:edit">
        <script type="text/javascript">
            s_edit_h = '';
        </script>
</@shiro.hasPermission>
<@shiro.hasPermission name="sys:dept:remove">
        <script type="text/javascript">
            var s_remove_h = '';
        </script>
</@shiro.hasPermission>
</div>
<#include '../../footer.ftl'/>
<script type="text/javascript" src="/js/appjs/sys/dept/dept.js"></script>
</body>
</html>