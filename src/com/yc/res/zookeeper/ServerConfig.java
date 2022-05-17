package com.yc.res.zookeeper;

import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;

import java.io.IOException;

/**
 * @author Exception
 * @create 2021-07-16-11:45
 * @content 微服务的配置信息, 要存到zk中.
 */

public class ServerConfig implements Record {
    private String dbUrl;
    private String dbPwd;
    private String dbUser;

    public ServerConfig() {
    }

    public ServerConfig(String dbUrl, String dbPwd, String dbUser) {
        this.dbUrl = dbUrl;
        this.dbPwd = dbPwd;
        this.dbUser = dbUser;
    }

    @Override
    public void serialize(OutputArchive archive, String tag) throws IOException {
        archive.startRecord(this, tag);
        archive.writeString(dbUrl, "dbUrl");
        archive.writeString(dbPwd, "dbPwd");
        archive.writeString(dbUser, "dbUser");
        archive.endRecord(this, tag);
    }

    @Override
    public void deserialize(InputArchive archive, String tag) throws IOException {
        archive.startRecord(tag);
        this.dbUrl = archive.readString("dbUrl");
        this.dbPwd = archive.readString("dbPwd");
        this.dbUser = archive.readString("dbUser");
        archive.endRecord(tag);
    }

    @Override
    public String toString() {
        return "ServerConfig{" +
                "dbUrl='" + dbUrl + '\'' +
                ", dbPwd='" + dbPwd + '\'' +
                ", dbUser='" + dbUser + '\'' +
                '}';
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }
}
