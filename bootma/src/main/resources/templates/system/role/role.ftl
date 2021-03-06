<!DOCTYPE html>
<html lang="zh_CN" xmlns:th="http://www.thymeleaf.org"
	xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
<meta charset="utf-8">
<#include '../../header.ftl'/>
<body class="gray-bg">
	<div class="wrapper wrapper-content ">
		<div class="col-sm-12">
			<div class="ibox">
				<div class="ibox-body">
					<div id="exampleToolbar" role="group">
						<@shiro.hasPermission name="sys:role:add">
								<button  type="button"
									class="btn btn-primary" onclick="add()">
									<i class="fa fa-plus" aria-hidden="true"></i>添加
								</button>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="sys:role:batchRemove">
							<button type="button"
								class="btn btn-danger" onclick="batchRemove()">
								<i class="fa fa-trash" aria-hidden="true"></i>删除
							</button>
						</@shiro.hasPermission>
					</div>
					<table id="exampleTable" data-mobile-responsive="true">
					</table>
				</div>
			</div>
		</div>
		<!--shiro控制bootstraptable行内按钮看见性 来自bootma的创新方案 -->
			<script type="text/javascript">
				var s_edit_h = 'hidden';
				var s_remove_h = 'hidden';
			</script>
		<@shiro.hasPermission name="sys:role:edit">
                <script type="text/javascript">
                    s_edit_h = '';
                </script>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="sys:role:remove">
                <script type="text/javascript">
                    var s_remove_h = '';
                </script>
		</@shiro.hasPermission>

	</div>
    <#include '../../footer.ftl'/>
	<script type="text/javascript" src="/js/appjs/sys/role/role.js">
		
	</script>
</body>

</html>