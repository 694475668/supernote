package com.ratta.enumeration;


/**
 * @author yll
 * 错误码
 */
public enum ErrorCodeEnum {
//	E1401("E1401","已有产品banner图处于上线状态，请下线后再上线新的banner图"),
	E1402("E1402","该序号已经存在，请重新设置序号"),
	E0730("E0730", "同一个业务码下不允许存在相同编码！"),
	E0701("E0701", "操作失败!"),
	E1421("E1421", "操作错误"),
	E0705("E0705", "修改失败!"),
	E0702("E0702", "删除失败!"),
	E1441("E1441","数据不存在"),
	E1403("E1403","该页面已有SEO!"),
	E1404("E1404","该标题下存在标题内容，请先删除标题内容再删除标题!"),
	E1470("E1470","系列编号已存在!"),
	E1471("E1471","产品编号已存在!"),
	E1472("E1472","推荐类型和推荐缩略图个数不相等!"),
	E1473("E1473","其他位置有相同的配件序列号!");
	
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
