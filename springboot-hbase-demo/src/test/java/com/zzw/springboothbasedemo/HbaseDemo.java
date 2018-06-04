/*
 * FileName: HbaseDemo.java
 * Author:   zzw
 * Date:     2018年05月18日
 * Description:
 */
package com.zzw.springboothbasedemo;

import com.zzw.springboothbasedemo.model.UserVo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.codehaus.jackson.map.ObjectMapper;
import com.zzw.springboothbasedemo.util.HbaseUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈〉<br>
 * 〈〉
 *
 * @author zzw
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
public class HbaseDemo {

    public static void main(String[] args) throws Exception {

        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "escnode0");
        Connection conn = ConnectionFactory.createConnection(conf);
        //HbaseUtil.listCells(conn,"trace");
        HbaseUtil.createTable(conn.getAdmin(),"trace","rpc");
        //Result result = HbaseUtil.listCells(conn, "trace", "0afe4ccb15277680093381009d000e");
        //System.out.println(result.listCells().size());
        //String s = "{\"span\" : \"12312\", \"startTime\" : \"121312313\", \"traceName\" : \"eqwe\", \"rpcId\" : \"1.0.0\", \"appname\" : \"test\", \"resultCode\":\"200\", \"rpcType\" : \"0\", \"traceId\" : \"123123123\"} ";
       // HbaseUtil.putData(conn,"123123123","trace",Bytes.toBytes("rpc"),Bytes.toBytes("1.0.0"),Bytes.toBytes(s));
        //HbaseUtil.deleteColumn(conn,"trace","123123123",Bytes.toBytes("rpc"),Bytes.toBytes("1.0.0.10"));
        //HbaseUtil.deleteColumn(conn,"trace","123123123",Bytes.toBytes("rpc"),Bytes.toBytes("1.0.0"));
        //HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();

      /*  ObjectMapper objectMapper = new ObjectMapper();
        UserVo userVo = new UserVo();
        userVo.setUserName("hello");
        userVo.setPwd("123456");
        String s = objectMapper.writeValueAsString(userVo);
        System.out.println(s);*/
       /* Table table = conn.getTable(TableName.valueOf("test"));
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        for (Result result:scanner){
            System.out.println("Scan: " + result);
        }
        scanner.close();*/
        /*Map<String,String> map = new HashMap<>();
        map.put("7","7");
        map.put("8","8");
        map.put("9","9");
        HbaseUtil.putDatas(conn,"1", "csf",Bytes.toBytes("rpc"),map);*/
       /* HbaseUtil.createTable(conn.getAdmin(),"csf2","rpc");
        List<Put> list = new ArrayList<>();
        Put put = new Put(Bytes.toBytes("1"));
        for (int j = 1000;j < 1002; j++){
            put.addColumn("rpc".getBytes(),(j + "").getBytes(),s.getBytes());
            list.add(put);
        }

        HbaseUtil.putByHTable(conn,"csf2",list);*/
    }

    private static void modifyColumn(Connection conn,String webtable, String visitflow, int maxValue) throws Exception {
        HbaseUtil.modifyColumn(conn,webtable,visitflow,maxValue);
    }

    private static void modifyTableAddFamily(Connection conn,String webtable, String visitflow, String description, String value) throws Exception {
        HbaseUtil.modifyTableAddFamily(conn,webtable,visitflow,description,value);
    }


    public static void latestCell(Connection conn,String tableName, String rowKey,byte[] family, byte[] qualifier) throws IOException {
        HbaseUtil.latestCell(conn,tableName,rowKey,family,qualifier);
    }

    public static void listCells2(Connection conn,String tableName, String rowKey,byte[] family, byte[] qualifier) throws IOException {
        HbaseUtil.listCells(conn,tableName,rowKey,family,qualifier);
    }

    public static void listCells(Connection conn,String tableName, String rowKey) throws IOException {
        HbaseUtil.listCells(conn,tableName,rowKey);
    }

    public static void push(Connection conn) throws IOException {
        HbaseUtil.putData(conn,"1", "csf", Bytes.toBytes("rpc"), Bytes.toBytes("1"), Bytes.toBytes("1"));
        HbaseUtil.putData(conn,"1", "csf", Bytes.toBytes("rpc"), Bytes.toBytes("2"), Bytes.toBytes("2"));
        HbaseUtil.putData(conn,"1", "csf", Bytes.toBytes("rpc"), Bytes.toBytes("3"), Bytes.toBytes("3"));

        HbaseUtil.putData(conn,"1", "csf", Bytes.toBytes("rpc"), Bytes.toBytes("4"), Bytes.toBytes("4"));
        HbaseUtil.putData(conn,"1", "csf", Bytes.toBytes("rpc"), Bytes.toBytes("5"), Bytes.toBytes("5"));

        HbaseUtil.putData(conn,"1", "csf", Bytes.toBytes("rpc"), Bytes.toBytes("6"), Bytes.toBytes("6"));
    }
}
