<!DOCTYPE html>
<meta charset="utf-8">

<#include "./include.ftl"/>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="ibox">
                <div class="ibox-title" style="padding-bottom: 0;">
                    <h3 class="text-center">个人资料中心</h3>
                    <div class="gg-nav">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#basic_info" data-toggle="tab">基本资料</a></li>
                            <li><a href="#photo_info" data-toggle="tab">头像修改</a></li>
                            <li><a href="#pwd_info" data-toggle="tab">修改密码</a></li>
                        </ul>
                    </div>
                </div>
                <div class="tab-content">
                    <!--basic info-->
                    <div class="ibox-content tab-pane fade in active"  id="basic_info">
                        <form class="gg-form" role="form" id="basicInfoForm">
                            <input name="id" type="hidden" value="${user.id!}"/>
                            <div class="gg-formGroup">
                                <div class="gg-formTitle">
                                    <em class="gg-star">*</em>
                                    <span>姓名:</span>
                                </div>
                                <div class="gg-formDetail">
                                    <input type="text" class="form-control" id="userName" name="name" value="${user.name!}"  placeholder="请输入姓名" />
                                </div>
                            </div>
                            <div class="gg-formGroup">
                                <div class="gg-formTitle">
                                    <em class="gg-star">*</em>
                                    <span>性别:</span>
                                </div>
                                <div class="gg-formDetail">
                                    <div class="radio i-checks">
                                       <#-- <label class="radio-inline" each="sex:${sexList!}"> <input
                                                field="*{user.sex}" type="radio" name="sex" value="${sex.id!}" text="${sex.name!}"/>
                                        </label>-->
                                       <#list sexList as sex>
                                            <label class="radio-inline">
                                                <input name="sex" type="radio"  value="${sex.id!}" <#if  user.sex == sex.id > checked </#if>> ${sex.name!}</input>
                                            </label>
                                        </#list>
                                    </div>
                                </div>
                            </div>
                            <div class="gg-formGroup">
                                <div class="gg-formTitle">
                                    <em class="gg-star">*</em>
                                    <span>出生年月:</span>
                                </div>
                                <div class="gg-formDetail">
                                  <input type="text" class="laydate-icon layer-date" id="birth" name="birth" value="${user.birth?string('yyyy-MM-dd')}" placeholder="请选择出生年月"
                                           onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" style="background-color: #fff;" readonly="readonly"/>
                                </div>
                            </div>
                            <div class="gg-formGroup">
                                <div class="gg-formTitle">
                                    <em class="gg-star">*</em>
                                    <span>手机:</span>
                                </div>
                                <div class="gg-formDetail">
                                    <input type="text" class="form-control" id="phone" name="mobile"  value="${user.mobile!}" placeholder="请输入手机号" />
                                </div>
                            </div>
                            <div class="gg-formGroup">
                                <div class="gg-formTitle">
                                    <em class="gg-star">*</em>
                                    <span>邮箱:</span>
                                </div>
                                <div class="gg-formDetail">
                                    <input type="text" class="form-control" id="email" name="email" value="${user.email!}" placeholder="请输入邮箱" />
                                </div>
                            </div>
                            <div class="gg-formGroup">
                                <div class="gg-formTitle">
                                    <em class="gg-star">*</em>
                                    <span>居住地:</span>
                                </div>
                                <div class="gg-formDetail gg-font0" data-toggle="distpicker"  attr="data-province=${user.province!},data-city=${user.city!},data-district=${user.district!}">
                                    <div class="gg-area">
                                        <select class="form-control" id="province" name="province">
                                        </select>
                                    </div>
                                    <div class="gg-area">
                                        <select class="form-control" id="city" name="city">
                                        </select>
                                    </div>
                                    <div class="gg-area">
                                        <select class="form-control" id="district" name="district">
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="gg-formGroup">
                                <div class="gg-formTitle">
                                    <em class="gg-star">*</em>
                                    <span>联系地址:</span>
                                </div>
                                <div class="gg-formDetail">
                                    <input type="text" class="form-control" id="address" name="liveAddress" value="${user.liveAddress!}" placeholder="请输入居住地址" />
                                </div>
                            </div>
                            <div class="gg-formGroup">
                                <div class="gg-formTitle">
                                    <em class="gg-star">*</em>
                                    <span>爱好:</span>
                                </div>
                                <div class="gg-formDetail">
                                    <div class="checxbox i-checks" >
                                        <input type="hidden" name="hobby" id="hobby"/>
                                          <#list hobbyList as hobby>
                                            <label>
                                                <input class="hobby"   type="checkbox" value="${hobby.id!}"  <#if  (hobby.remarks)! = "true" > checked </#if> >${hobby.name!}</input>
                                            </label>
                                          </#list>
                                      <#--  <label each="hobby : ${hobbyList}">
                                            <input type="checkbox" class="hobby"  value="${hobby.id}" text="${hobby.name}" checked="${hobby.remarks}=='true'"/>
                                        </label>-->
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="gg-btnGroup">
                            <button type="button" class="btn btn-sm btn-primary" id="base_save">保存</button>
                        </div>
                    </div>
                    <!--photo_info-->
                    <div class="ibox-content tab-pane fade gg" id="photo_info">
                        <div class="ggcontainer" id="crop-avatar">
                            <form class="avatar-form" action="/sys/user/uploadImg" enctype="multipart/form-data" method="post">
                                <div class="avatar-body">
                                    <div class="avatar-upload">
                                        <input class="avatar-src" name="avatar_src" type="hidden">
                                        <input class="avatar-data" name="avatar_data" type="hidden">
                                        <label for="avatarInput">选取文件</label>
                                        <input class="avatar-input" id="avatarInput" name="avatar_file" type="file">
                                    </div>
                                    <!-- Crop and preview -->
                                    <div class="row">
                                        <div class="col-md-9">
                                            <div class="avatar-wrapper"></div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="avatar-preview preview-lg"></div>
                                            <div class="avatar-preview preview-md"></div>
                                            <div class="avatar-preview preview-sm"></div>
                                        </div>
                                    </div>

                                    <div class="row avatar-btns">
                                        <div class="col-md-9">
                                            <div class="btn-group">
                                                <button class="btn btn-primary" data-method="rotate" data-option="-90" type="button" title="Rotate -90 degrees">左旋转</button>
                                                <button class="btn btn-primary" data-method="rotate" data-option="-15" type="button">-15°</button>
                                                <button class="btn btn-primary" data-method="rotate" data-option="-30" type="button">-30°</button>
                                                <button class="btn btn-primary" data-method="rotate" data-option="-45" type="button">-45°</button>
                                            </div>
                                            <div class="btn-group">
                                                <button class="btn btn-primary" data-method="rotate" data-option="90" type="button" title="Rotate 90 degrees">右旋转</button>
                                                <button class="btn btn-primary" data-method="rotate" data-option="15" type="button">15°</button>
                                                <button class="btn btn-primary" data-method="rotate" data-option="30" type="button">30°</button>
                                                <button class="btn btn-primary" data-method="rotate" data-option="45" type="button">45°</button>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <button class="btn btn-primary btn-block avatar-save" type="submit">完成裁剪</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <!-- Loading state -->
                            <div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
                        </div>
                    </div>
                    <!--pwd_info-->
                    <div class="ibox-content tab-pane fade" id="pwd_info">
                        <form class="gg-form" role="form" id="modifyPwd">
                            <a id="logout" class="hidden" href="/logout"></a>
                            <input type="hidden" name ="user.id" value="${user.id!}"/>
                            <div class="gg-formGroup">
                                <div class="gg-formTitle">
                                    <em class="gg-star">*</em>
                                    <span>旧密码:</span>
                                </div>
                                <div class="gg-formDetail gg-dashed">
                                    <input type="password" class="form-control gg-border0" id="pwdOld" name="pwdOld" placeholder="请输入旧密码" />
                                    <span class="fa fa-eye gg-faeye" title="鼠标移入显示内容"><span>
                                </div>
                            </div>
                            <div class="gg-formGroup">
                                <div class="gg-formTitle">
                                    <em class="gg-star">*</em>
                                    <span>新密码:</span>
                                </div>
                                <div class="gg-formDetail gg-dashed">
                                    <input type="password" class="form-control gg-border0" id="pwdNew" name="pwdNew" placeholder="请输入新密码" />
                                    <span class="fa fa-eye gg-faeye" title="鼠标移入显示内容"></span>
                                </div>
                            </div>
                            <div class="gg-formGroup">
                                <div class="gg-formTitle">
                                    <em class="gg-star">*</em>
                                    <span>确认密码:</span>
                                </div>
                                <div class="gg-formDetail gg-dashed">
                                    <input type="password" class="form-control gg-border0" id="confirm_password" name="confirm_password" placeholder="请确认密码" />
                                    <span class="fa fa-eye gg-faeye" title="鼠标移入显示内容"></span>
                                </div>
                            </div>
                        </form>
                        <div class="gg-btnGroup">
                            <button type="button" class="btn btn-sm btn-primary" id="pwd_save">保存</button>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>
<#--<#include "./include.ftl"/>-->
<script type="text/javascript" src="/js/appjs/sys/user/personal.js"></script>
</body>
</html>
