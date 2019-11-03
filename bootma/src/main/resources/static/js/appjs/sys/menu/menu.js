var prefix = "/sys/menu"

//模块名称
var module = "菜单"
$(document).ready(function () {
    load();
});
var load = function () {
    $('#exampleTable')
        .bootstrapTreeTable(
            {
                id: 'id',
                code: 'id',
                parentCode: 'parentId',
                type: "GET", // 请求数据的ajax类型
                url: prefix + '/list', // 请求数据的ajax的url
                ajaxParams: {sort:'order_num'}, // 请求数据的ajax的data属性
                expandColumn: '1',// 在哪一列上面显示展开按钮
                striped: true, // 是否各行渐变色
                bordered: true, // 是否显示边框
                expandAll: true, // 是否全部展开
                // toolbar : '#exampleToolbar',
                columns: [
                    {
                        title: '编号',
                        field: 'id',
                        visible: false,
                        align: 'center',
                        valign: 'center',
                        width: '5%'
                    },
                    {
                        title: '名称',
                        valign: 'center',
                        field: 'name',
                        width: '20%'
                    },

                    {
                        title: '图标',
                        field: 'icon',
                        align: 'center',
                        valign: 'center',
                        width : '5%',
                        formatter: function (item, index) {
                            return item.icon == null ? ''
                                : '<i class="' + item.icon
                                + ' fa-lg"></i>';
                        }
                    },
                    {
                        title: '类型',
                        field: 'type',
                        align: 'center',
                        valign: 'center',
                        width : '10%',
                        formatter: function (item, index) {
                            if (item.type === 0) {
                                return '<span class="label label-primary">目录</span>';
                            }
                            if (item.type === 1) {
                                return '<span class="label label-success">菜单</span>';
                            }
                            if (item.type === 2) {
                                return '<span class="label label-warning">按钮</span>';
                            }
                        }
                    },
                    {
                        title: '地址',
                        valign: 'center',
                        width : '20%',
                        field: 'url'
                    },
                    {
                        title: '权限标识',
                        valign: 'center',
                        width : '20%',
                        field: 'perms'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        valign: 'center',
                        formatter: function (item, index) {
                            var e = '<a class="btn btn-primary btn-sm '
                                + s_edit_h
                                + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + item.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var p = '<a class="btn btn-primary btn-sm '
                                + s_add_h
                                + '" href="#" mce_href="#" title="添加下级" onclick="add(\''
                                + item.id
                                + '\')"><i class="fa fa-plus"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm '
                                + s_remove_h
                                + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + item.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return e + d + p;
                        }
                    }]
            });
}

function reLoad() {
    load();
}

function add(pid) {
    openTab('添加'+module,prefix + '/add/'+pid);
}
