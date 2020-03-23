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
							<input id="id" name="id" value="${dept.id}"
								class="hidden">
							<div class="form-group">
								<label class="col-sm-3 control-label ">上级部门：</label>
								<div class="col-sm-8">
								<input value="${parentDeptName}"
										class="form-control" type="text" readonly="true" />
									<input class="form-control hidden" type="text" id="parentId" name="parentId" value="${dept.parentId}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">部门名称：</label>
								<div class="col-sm-8">
									<input id="name" name="name" value="${dept.name}"
										class="form-control" type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">排序：</label>
								<div class="col-sm-8">
									<input id="orderNum" name="orderNum" value="${dept.orderNum!}"
										class="form-control" type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">状态：</label>
								<div class="col-sm-8">
									<input id="delFlag" name="delFlag" value="${dept.delFlag!}"
										class="form-control" type="text">
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
	<script type="text/javascript" src="/js/appjs/sys/dept/edit.js">
	</script>
</body>
</html>
