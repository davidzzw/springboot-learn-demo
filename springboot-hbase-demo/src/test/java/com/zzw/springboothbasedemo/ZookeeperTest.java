/*
 * FileName: ZookeeperTest.java
 * Author:   zzw
 * Date:     2018年05月18日
 * Description:
 */
package com.zzw.springboothbasedemo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * 〈〉<br>
 * 〈〉
 *
 * @author zzw
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
public class ZookeeperTest {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = new ZooKeeper("172.16.51.87:2181",5000,new ZooKeeperWatcher());
        ZooKeeper.States state = zk.getState();
        System.out.println(state.isAlive());
        List<String> children = zk.getChildren("/hbase", false);
        System.out.println(children);
    }
}
