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
                    <div class="columns pull-left">
                        <button shiro:hasPermission="system:sysDept:add" type="button" class="btn  btn-primary"
                                onclick="add(0)">
                            <i class="fa fa-plus hidden" aria-hidden="true"></i>添加
                        </button>
                    </div>
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
<!--shiro控制bootstraptable行内按钮看见性 来自bootdo的创新方案 -->
<div>
    <script type="text/javascript">
        var s_add_h = 'hidden';
        var s_edit_h = 'hidden';
        var s_remove_h = 'hidden';
        var s_resetPwd_h = 'hidden';
    </script>
</div>
<div shiro:hasPermission="system:sysDept:edit">
    <script type="text/javascript">
        s_edit_h = '';
    </script>
</div>
<div shiro:hasPermission="system:sysDept:add">
    <script type="text/javascript">
        s_add_h = '';
    </script>
</div>
<div shiro:hasPermission="system:sysDept:remove">
    <script type="text/javascript">
        var s_remove_h = '';
    </script>
</div>
<div shiro:hasPermission="system:sysDept:resetPwd">
    <script type="text/javascript">
        var s_resetPwd_h = '';
    </script>
</div>
</div>
<#include '../../footer.ftl'/>
<script type="text/javascript" src="/js/appjs/sys/dept/dept.js"></script>
</body>
</html>