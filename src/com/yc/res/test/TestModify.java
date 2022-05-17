package com.yc.res.test;

import com.yc.res.zookeeper.ServerConfig;
import com.yc.res.zookeeper.ZKHelper;
import org.apache.jute.BinaryOutputArchive;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @program: resPro
 * @description:
 * @author: King
 * @create: 2021-07-17 16:35
 */
public class TestModify {


    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper client = new ZKHelper().connect();

        ServerConfig config = new ServerConfig();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BinaryOutputArchive boa = BinaryOutputArchive.getArchive(baos);
        config.setDbPwd("a");
        config.setDbUser("root");
        config.setDbUrl("jdbc:mysql://localhost:3306/res?serverTimeZone=UTC");
        config.serialize(boa,"header");
        client.setData("/res/config", baos.toByteArray(), client.exists("/res/config", false).getVersion());
        client.close();
        System.out.println();
    }


}
