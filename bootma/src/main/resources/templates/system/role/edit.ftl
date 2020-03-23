<!DOCTYPE html>
<html>
<meta charset="utf-8">
<#include '../../header.ftl'/>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="signupForm">
							<input id="id" name="id" type="hidden"
								value="${role.id}">
                            <input id="menuIds" name="menuIds" type="hidden">
							<div class="form-group">
								<label class="col-sm-3 control-label">角色名：</label>
								<div class="col-sm-8">
									<input id="roleName" name="roleName" class="form-control"
										type="text" value="${role.roleName}">
								</div>
							</div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">标识：</label>
                                <div class="col-sm-8">
                                    <input id="roleSign" name="roleSign" class="form-control"
                                           type="text"  value="${role.roleSign}">
                                </div>
                            </div>
							<div class="form-group">
								<label class="col-sm-3 control-label">备注：</label>
								<div class="col-sm-8">
									<input id="remark" name="remark" class="form-control"
										type="text" value="${role.remark}">
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">菜单权限：</label>
								<div class="col-sm-8">
									<div id="menuTree"></div>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									<button type="submit" class="btn btn-primary">提交</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<#include '../../footer.ftl'/>
	<script src="/js/appjs/sys/role/edit.js"></script>
</body>

</html>
