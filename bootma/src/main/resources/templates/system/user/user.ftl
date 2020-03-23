<!DOCTYPE html>
<html lang="zh_CN" xmlns:th="http://www.thymeleaf.org"
	xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
<meta charset="utf-8">
<#include '../../header.ftl'/>
<body class="gray-bg">
	<div class="wrapper wrapper-content ">
		<div class="row">
			<div class="col-sm-3">
				<div class="ibox ibox-body">
					<div class="ibox-title">
						<h5>选择部门</h5>
					</div>
					<div class="ibox-content">
						<div id="jstree"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="ibox">
					<div class="ibox-body">
						<div class="fixed-table-toolbar">
							<div class="columns pull-left">
								<@shiro.hasPermission name="sys:user:add">
									<button type="button"
										class="btn  btn-primary" onclick="add()">
										<i class="fa fa-plus" aria-hidden="true"></i>添加
									</button>
								</@shiro.hasPermission>
								<@shiro.hasPermission name="sys:user:batchRemove">
									<button type="button"
										class="btn  btn-danger" onclick="batchRemove()">
										<i class="fa fa-trash" aria-hidden="true"></i>删除
									</button>
								</@shiro.hasPermission>
							</div>
							<div class="columns pull-right">
								<button class="btn btn-success" onclick="reLoad()">查询</button>
							</div>

							<div class="columns pull-right col-md-2 nopadding">
								<input id="searchName" type="text" class="form-control"
									placeholder="姓名">
							</div>
						</div>
						<table id="exampleTable" data-mobile-responsive="true">
						</table>
					</div>
				</div>
			</div>
		</div>

        <script type="text/javascript">
            var s_edit_h = 'hidden';
            var s_remove_h = 'hidden';
            var s_resetPwd_h = 'hidden';
        </script>
		<@shiro.hasPermission name="sys:user:edit">
                <script type="text/javascript">
                    s_edit_h = '';
                </script>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="sys:user:remove">
                <script type="text/javascript">
                    var s_remove_h = '';
                </script>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="sys:user:adminResetPwd">
				<script type="text/javascript">
					s_resetPwd_h = '';
				</script>
		</@shiro.hasPermission>
    </div>

	<#include '../../footer.ftl'/>
	<script type="text/javascript" src="/js/appjs/sys/user/user.js"></script>


</body>
</html>