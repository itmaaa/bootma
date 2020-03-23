<!DOCTYPE html>
<html>
<meta charset="utf-8">
<#include '../../header.ftl'/>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="signupForm">
							<input id="parentId" name="parentId" type="hidden"
								value="${pId}">
							<div class="form-group">
								<label class="col-sm-3 control-label">上级菜单：</label>
								<div class="col-sm-8">
									<input id="" name="" class="form-control" type="text"
										value="${pName}" readonly>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">菜单类型：</label>
								<div class="col-sm-8">
									<label class="radio-inline"> <input type="radio"
										name="type" value="0" /> 目录
									</label> <label class="radio-inline"> <input type="radio"
										name="type" value="1" checked /> 菜单
									</label> <label class="radio-inline"> <input type="radio"
										name="type" value="2" /> 按钮
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">菜单名称：</label>
								<div class="col-sm-8">
									<input id="name" name="name" class="form-control" type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">链接地址：</label>
								<div class="col-sm-8">
									<input id="url" name="url" class="form-control" type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">权限标识：</label>
								<div class="col-sm-8">
									<input id="perms" name="perms" class="form-control" type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">排序号：</label>
								<div class="col-sm-8">
									<input id="orderNum" name="orderNum" class="form-control"
										type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">图标：</label>
								<div class="col-sm-6">
									<input id="icon" name="icon" class="form-control" type="text"
										placeholder="例如：fa fa-circle-o">
								</div>
								<input id="ico-btn" class="btn btn-warning" type="button" value="选择图标">
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
	<script src="/js/appjs/sys/menu/add.js"></script>
</body>

</html>
