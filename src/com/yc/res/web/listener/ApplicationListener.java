package com.yc.res.web.listener;

import com.yc.res.zookeeper.ServerConfig;
import com.yc.res.zookeeper.ZKHelper;
import org.apache.jute.BinaryInputArchive;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.ByteBufferInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @program: resPro
 * @description:
 * @author: King
 * @create: 2021-07-17 16:00
 */
public class ApplicationListener implements
        ServletContextListener {
    private ZooKeeper client;

    private Watcher watcher;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("start ....");
        ZKHelper zkHelper = new ZKHelper();
        ServletContext servletContext = sce.getServletContext();

        watcher = new Watcher() {

            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                        Stat stat = new Stat();
                        byte[] result = client.getData("/res/config", watcher, stat);
                        ByteBuffer bb = ByteBuffer.wrap(result);
                        ByteBufferInputStream bbis = new ByteBufferInputStream(bb);
                        BinaryInputArchive bia = BinaryInputArchive.getArchive(bbis);
                        ServerConfig config = new ServerConfig();
                        config.deserialize(bia, "header");
                        System.out.println(config);
                        servletContext.setAttribute("config", config);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        client = zkHelper.connect();
        try {
            if (client.exists("/res", false) == null) {
                client.create("/res", null, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT);
            }
            Stat stat = new Stat();
            byte[] result = client.getData("/res/config", watcher, stat);
            ByteBuffer bb = ByteBuffer.wrap(result);
            ByteBufferInputStream bbis = new ByteBufferInputStream(bb);
            ;
            BinaryInputArchive bia = BinaryInputArchive.getArchive(bbis);
            ServerConfig config = new ServerConfig();
            config.deserialize(bia, "header");
            System.out.println(config);
            servletContext.setAttribute("config", config);
        } catch (KeeperException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("stop ...");
        try {
            client.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
