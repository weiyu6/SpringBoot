package com.wytest.springboot.bean;

import java.io.Serializable;

/**
 * TODO
 * @author weiyu
 * @date 2021/1/26
 */

public class User  implements Serializable {

    private static final long serialVersionUID = 8264335866053098806L;
    private String id;
    private String name;
    private String pwd;

    /**
     * 获取
     *
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return pwd
     */
    public String getPwd() {
        return this.pwd;
    }

    /**
     * 设置
     *
     * @param pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
