package com.ratta.enumeration;

/**
 * 异常枚举枚举类
 *
 * @author 刘明
 */
public enum ErrorCodeEnum {
    E0701("E0701", "操作失败!"),
    E0702("E0702", "删除失败!"),
    E0703("E0703", "请先删除子节点后在删除当前节点!"),
    E0704("E0704", "ID不能为空!"),
    E0705("E0705", "修改失败!"),
    E0706("E0706", "系统错误!"),
    E0707("E0707", "角色下尚有用户不允许删除!"),
    E0708("E0708", "用户名或密码错误!"),
    E0709("E0709", "当前用户处于停用状态,请联系管理员!"),
    E0710("E0710", "用户已锁定,请联系管理员或稍后登录!"),
    E0711("E0711", "用户名或密码错误,剩余登录次数!"),
    E0712("E0712", "您还没有登录或登录已超时,请重新登录!"),
    E0713("E0713", "密码不能与近期相同!"),
    E0714("E0714", "原密码输入错误!"),
    E0715("E0715", "启用失败!"),
    E0716("E0716", "用户不能启用自己!"),
    E0717("E0717", "用户不能停用自己!"),
    E0718("E0718", "停用失败!"),
    E0719("E0719", "该用户已经有操作记录,不可以删除!"),
    E0720("E0720", "用户不可以删除自己!"),
    E0721("E0721", "只解锁锁定用户!"),
    E0722("E0722", "没有该用户信息!"),
    E0723("E0723", "用户不能给自己授权!"),
    E0724("E0724", "授权失败!"),
    E0725("E0725", "定时调度任务停用失败!"),
    E0726("E0726", "定时调度任务启用失败!"),
    E0727("E0727", "任务正在运行!"),
    E0728("E0728", "数据清理异常!"),
    E0729("E0729", "定时任务执行异常,错误!"),
    E0730("E0730", "同一个业务码下不允许存在相同编码！"),
    E0731("E0731", "参数已存在!"),
    E0732("E0732", "正常用户不允许再启用!"),
    E0733("E0733", "用户已经存在!"),
    E0734("E0734", "停用用户不允许再停用!"),
    E0735("E0735", "用户不能解锁自己！"),
    E0736("E0736", "所有数据都是启用状态，请选择未启用的任务！"),
    E0737("E0737", "所有数据都是停用状态，请选择未停用的任务！"),
    E0738("E0738", "请先启用任务！"),
    E0739("E0739", "请求数据为空！"),
    E0740("E0740", "请先删除该角色下的所有子角色!"),
    E0741("E0741", "请先删除该用户下的所有子用户!"),
    E0061("E0061", "账号已经注销!"),
    E0062("E0062", "手机号为空!"),
    E0064("E0064", "发送短信次数过多!"),
    E0065("E0065", "发送短信失败!"),
    E0066("E0066", "手机号格式不对!"),
    E0067("E0067", "上传头像失败!"),
    E0068("E0068", "复制文件超过总个数!"),
    E0069("E0069", "设备不合法!"),
    E0070("E0070", "无需更新!"),
    E0071("E0071", "没有压缩好的包!"),
    E0072("E0072", "supernote目录下不允许任何操作!"),
    E0073("E0073", "昵称不能为空!"),
    E0074("E0074", "昵称已经存在，请重新命名!"),
    E0075("E0075", "设备已经绑定该账号，无需再次绑定!"),
    E0077("E0077", "登录的账号跟设备绑定的账号不是同一个!"),
    E0078("E0078", "已有设备正在同步中,请等待结束后再同步!"),
    E0079("E0079", "正在同步中,请等待结束后再操作!"),
    E0081("E0081", "路径不存在!"),
    E0082("E0082", "有相同md5文件，无需上传!"),
    E0083("E0083", "设备已经绑定其他账号，无法再次绑定新账号!"),
    E0084("E0084", "发布的版本号有误!"),
    E0085("E0085", "token不合法!"),
    E0086("E0086", "国家码为空!"),
    E0087("E0087", "没有最新版本，无需更新。"),
    E0088("E0088", "该资源已有角色使用，不可删除"),
    ;
    private String key;
    private String value;

    private ErrorCodeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

}
