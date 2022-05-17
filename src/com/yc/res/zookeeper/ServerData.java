package com.yc.res.zookeeper;



/**
 * @author Exception
 * @create 2021-07-16-11:48
 * @content 服务器基本信息
 */

public class ServerData {
    private String address;
    private Integer id;
    private String name;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
