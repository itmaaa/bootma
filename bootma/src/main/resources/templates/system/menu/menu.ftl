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
					<div id="exampleToolbar" role="group" class="t-bar">
						<@shiro.hasPermission name="sys:menu:add">
							<button  type="button"
								class="btn btn-primary" title="在根节点下添加菜单" onclick="add('0')">
								<i class="fa fa-plus" aria-hidden="true"></i>添加
							</button>
						</@shiro.hasPermission>
						<#--<button shiro:hasPermission="sys:menu:batchRemove" type="button"
							class="btn btn-danger" onclick="batchRemove()">
							<i class="fa fa-trash" aria-hidden="true"></i>删除
						</button>-->
					</div>
					<table id="exampleTable" data-mobile-responsive="true">
					</table>
				</div>
			</div>
		</div>
		<!--shiro控制bootstraptable行内按钮看见性 来自bootdo的创新方案 -->
			<script type="text/javascript">
				var s_add_h = 'hidden';
				var s_edit_h = 'hidden';
				var s_remove_h = 'hidden';
			</script>
		<@shiro.hasPermission name="sys:menu:add">
				<script type="text/javascript">
					s_add_h = '';
				</script>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="sys:menu:edit">
				<script type="text/javascript">
					s_edit_h = '';
				</script>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="sys:menu:remove">
				<script type="text/javascript">
					var s_remove_h = '';
				</script>
		</@shiro.hasPermission>
	</div>
	<#include '../../footer.ftl'/>
	<script src="/js/appjs/sys/menu/menu.js"></script>
</body>

</html>