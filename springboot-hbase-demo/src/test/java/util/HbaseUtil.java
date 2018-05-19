/*
 * FileName: HbaseUtil.java
 * Author:   zzw
 * Date:     2018年05月19日
 * Description:
 */
package util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 〈hbase工具类〉<br>
 * 〈〉
 *
 * @author zzw
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 *
 * 表删除后，再创建同名表，表是disabled的，后无法插入数据，enable之后即可
 * 数据多版本时，获取数据默认根据时间戳从大到小排序
 * 连续插入数据数量超过最大版本数时，默认会以“队列”形式“挤掉”最早版本的数据
 * 插入指定时间戳小于最小版本的时间戳时，数据插入是不成功的
 * 插入比最小版本的时间戳大的时间戳数据，插入是成功的
 * 指定时间戳的数据是可以更新的
 * 可以获取表中指定rowKey某一时间戳或时间戳范围的列数据如get.setTimeStamp get.setTimeRange
 */
public class HbaseUtil {

    /**
     * 创建表
     * @param tableName 表名
     * @param familyNames 列族名
     * */
    public static void createTable(Admin admin,final String tableName, String... familyNames) throws IOException {
        if (admin.tableExists(TableName.valueOf(tableName))) {
            return;
        }
        //通过HTableDescriptor类来描述一个表，HColumnDescriptor描述一个列族
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        for (String familyName : familyNames) {
            tableDescriptor.addFamily(new HColumnDescriptor(familyName));
        }

        admin.createTable(tableDescriptor);
    }

    /**
     * 删除表
     * @param tableName 表名
     * */
    public static void dropTable(Admin admin,String tableName) throws IOException {
        //删除之前要将表disable
        if (!admin.isTableDisabled(TableName.valueOf(tableName))) {
            admin.disableTable(TableName.valueOf(tableName));
        }
        admin.deleteTable(TableName.valueOf(tableName));
    }

    /**
     * 指定行/列中插入数据
     * @param tableName 表名
     * @param rowKey 主键rowkey
     * @param family 列族
     * @param column 列
     * @param value 值
     */
    public static void insert(Connection connection,String tableName, String rowKey, String family, String column, String value) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(value));
        table.put(put);
    }


    /**
     * 删除表中的指定行
     * @param tableName 表名
     * @param rowKey rowkey
     */
    public static void delete(Connection connection,String tableName, String rowKey) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
    }

    /**
     * 插入(更新)数据
     *
     * @param rowKey
     * @param tableName
     * @param family
     * @param qualifier
     * @param value
     * @throws IOException
     */
    public static void putData(Connection conn,String rowKey, String tableName, byte[] family, byte[] qualifier, byte[] value) throws IOException {
        if (StringUtils.isEmpty(rowKey) || StringUtils.isEmpty(tableName)) {
            return;
        }

        if (ArrayUtils.isEmpty(family) || ArrayUtils.isEmpty(qualifier)) {
            return;
        }

        Put put = new Put(Bytes.toBytes(rowKey));
        HTable table = (HTable) conn.getTable(TableName.valueOf(tableName));
        put.addColumn(family, qualifier, value);
        table.put(put);
        System.out.println("add data Success!");
        table.close();
    }

    /**
     * 插入(更新)数据
     *
     * @param rowKey
     * @param tableName
     * @param family
     * @param map
     * @throws IOException
     */
    public static void putDatas(Connection conn,String rowKey, String tableName, byte[] family, Map<String,String> map) throws IOException {
        if (StringUtils.isEmpty(rowKey) || StringUtils.isEmpty(tableName)) {
            return;
        }
        Put put = new Put(Bytes.toBytes(rowKey));
        HTable table = (HTable) conn.getTable(TableName.valueOf(tableName));

        for (Map.Entry<String,String> entry:map.entrySet()){
            put.addColumn(family, entry.getKey().getBytes(), entry.getValue().getBytes());
        }

        table.put(put);
        System.out.println("add data Success!");
        table.close();
    }

    /*
     * 批量插入数据
     */
    public static void putByHTable(Connection conn,String tableName,List<Put> puts) throws IOException {
        HTable htable = (HTable) conn.getTable(TableName.valueOf(tableName));
        try {
            htable.put((List<Put>)puts);
        } finally {
            htable.close();
            conn.close();
        }
        return ;
    }

    /**
     * 遍历指定rowKey的数据
     *
     * @param tableName
     * @param rowKey
     * @return Result 可自定义某种数据封装
     * @throws IOException
     */
    public static Result listCells(Connection conn,String tableName, String rowKey) throws IOException {
        Get get = new Get(Bytes.toBytes(rowKey));
        HTable table = (HTable) conn.getTable(TableName.valueOf(tableName));
        Result result = table.get(get);

        System.out.println("result.current(): " + result.current());

        CellScanner cellScanner = result.cellScanner();

        System.out.println("cellScanner: " + cellScanner.toString());
        System.out.println("cellScanner.current(): " + cellScanner.current());

        System.out.println("---------------------------------------------");

        for (Cell cell : result.listCells()) {
            System.out.println("cell: " + cell.toString());
            System.out.println("family: " + Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()));
            System.out.println("qualifier: " + Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()));
            System.out.println("value: " + Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
            System.out.println("timestamp: " + cell.getTimestamp());
            System.out.println("---------------------------------------------");
        }
        return result;
    }

    /**
     * 遍历指定rowKey和列的数据
     *
     * @param tableName
     * @param rowKey
     * @return Result 可自定义某种数据封装
     * @throws IOException
     */
    public static Result listCells(Connection conn,String tableName, String rowKey, byte[] family, byte[] qualifier) throws IOException {
        Get get = new Get(Bytes.toBytes(rowKey));
        get.setMaxVersions(Integer.MAX_VALUE);
        HTable table = (HTable) conn.getTable(TableName.valueOf(tableName));
        Result result = table.get(get);

        for (Cell cell : result.getColumnCells(family, qualifier)) {
            System.out.println("cell: " + cell.toString());
            System.out.println("family: " + Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()));
            System.out.println("qualifier: " + Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()));
            // value要根据实际类型转换toString等
            System.out.println("value: " + Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
            System.out.println("timestamp: " + cell.getTimestamp());
            System.out.println("***************************************");
        }
        return result;
    }

    /**
     * 获取指定rowKey和列最新版本的数据
     *
     * @param tableName
     * @param rowKey
     * @return Result 可自定义某种数据封装
     * @throws IOException
     */
    public static Result latestCell(Connection conn,String tableName, String rowKey, byte[] family, byte[] qualifier) throws IOException {
        Get get = new Get(Bytes.toBytes(rowKey));
        HTable table = (HTable) conn.getTable(TableName.valueOf(tableName));
        Result result = table.get(get);

        Cell cell = result.getColumnLatestCell(family, qualifier);
        System.out.println("cell: " + cell.toString());
        System.out.println("family: " + Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()));
        System.out.println("qualifier: " + Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()));
        System.out.println("value: " + Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
        System.out.println("timestamp: " + cell.getTimestamp());

        return result;
    }

    /**
     * 修改表结构-替换该表的所有列，相当于重建表
     *
     * @param tableName
     * @param family
     * @param key
     * @param value
     * @throws Exception
     */
    public static void modifyTableAddFamily(Connection conn,String tableName, String family, String key, String value) throws Exception {
        if (StringUtils.isEmpty(tableName) || StringUtils.isEmpty(family) || StringUtils.isEmpty(key)) {
            return;
        }

        TableName tn = TableName.valueOf(tableName);

        HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();
        HTableDescriptor desc = new HTableDescriptor(tn);
        desc.addFamily(new HColumnDescriptor(family));
        desc.setValue(key, value);
        // 要先disable
        admin.disableTable(tn);
        admin.modifyTable(tn, desc);
        // 再enable
        admin.enableTable(tn);
        admin.close();
    }

    /**
     * 修改列属性[也可在创建时设置]
     *
     * @param tableName
     * @param family
     * @param maxVersions
     * @throws Exception
     */
    public static void modifyColumn(Connection conn,String tableName, String family, int maxVersions) throws Exception {
        if (StringUtils.isEmpty(tableName) || StringUtils.isEmpty(family)) {
            return;
        }

        TableName tn = TableName.valueOf(tableName);

        HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();

        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(family);
        hColumnDescriptor.setMaxVersions(maxVersions);
        admin.disableTable(tn);
        admin.modifyColumn(tn, hColumnDescriptor);
        admin.enableTable(tn);
        admin.close();
    }

    /**
     * 添加列
     *
     * @param tableName
     * @param family
     * @param maxVersions
     * @throws Exception
     */
    public static void addColumn(Connection conn,String tableName, String family, int maxVersions) throws Exception {
        if (StringUtils.isEmpty(tableName) || StringUtils.isEmpty(family)) {
            return;
        }

        TableName tn = TableName.valueOf(tableName);

        HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();

        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(family);
        hColumnDescriptor.setMaxVersions(maxVersions);
        admin.disableTable(tn);
        admin.addColumn(tn, hColumnDescriptor);
        admin.enableTable(tn);
        admin.close();
    }

    /**
     * 遍历查询表中全部数据
     *
     * @param tableName
     * @return ResultScanner
     * @throws IOException
     */
    public static ResultScanner listCells(Connection conn,String tableName) throws IOException {
        Scan scan = new Scan();
        // 返回版本数据
        scan.setMaxVersions(Integer.MAX_VALUE);

        HTable table = (HTable) conn.getTable(TableName.valueOf(tableName));
        ResultScanner resultScanner = table.getScanner(scan);

        Iterator iterator = resultScanner.iterator();

        while (iterator.hasNext()) {
            Result result = (Result) iterator.next();
            for (Cell cell : result.rawCells()) {
                System.out.println("cell: " + cell.toString());
                System.out.println("family: " + Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()));
                System.out.println("qualifier: " + Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()));
                System.out.println("value: " + Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
                System.out.println("timestamp: " + cell.getTimestamp());
                System.out.println("***************************************");
            }
        }

        return resultScanner;
    }

    /**
     * 删除列
     *
     * @param tableName
     * @param rowKey
     * @param family
     * @param qualifier
     * @throws IOException
     */
    public static void deleteColumn(Connection conn,String tableName, String rowKey, byte[] family, byte[] qualifier) throws IOException {
        HTable table = (HTable) conn.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        // 删除指定列
        delete.addColumn(family, qualifier);
        table.delete(delete);
    }

    /**
     * 删除表
     *
     * @param tableName
     * @throws IOException
     */
    public static void deteleTable(Connection conn,String tableName) throws IOException {
        HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();
        admin.disableTable(TableName.valueOf(tableName));
        admin.deleteTable(TableName.valueOf(tableName));
        admin.close();
    }
}
