<!DOCTYPE html>
<html>
<meta charset="utf-8">
<#include '../../header.ftl'/>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="col-sm-12">
			<div class="ibox">
				<div class="ibox-body">
					<div class="fixed-table-toolbar">
						<div class="columns pull-left">
							<button type="button" class="btn  btn-danger"
								onclick="batchRemove()">
								<i class="fa fa-trash" aria-hidden="true"></i>删除
							</button>
						</div>
						<div class="columns pull-right" role="group">
							<button class="btn btn-success" onclick="reLoad()">
								<i class="fa fa-search" aria-hidden="true"></i>查询
							</button>
						</div>
						<div class="pull-right search col-md-2">
							<input id="searchOperation" type="text" class="form-control"
								placeholder="操作">
						</div>
						<div class="pull-right search col-md-2 nopadding">
							<input id="searchUsername" type="text" class="form-control"
								placeholder="用户">
						</div>
					</div>
					<table id="exampleTable" data-mobile-responsive="true">
					</table>
				</div>
			</div>
		</div>
	</div>
    <#include '../../footer.ftl'/>
	<script type="text/javascript" src="/js/appjs/common/log/log.js"></script>
</body>
</html>