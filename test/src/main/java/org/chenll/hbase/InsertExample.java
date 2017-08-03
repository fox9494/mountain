package org.chenll.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.chenll.util.IDGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsertExample {

    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        insert(connection);
    }

    private static String getFixLenthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }

    public static void insert(Connection connection) throws IOException {
        Table table = connection.getTable(TableName.valueOf(Example.TABLE_NAME));
        try{
            List<Put> list = new ArrayList<Put>();
            for (int i = 0; i < 20; i++) {
                String rowkey = "key"+i+"-"+ getFixLenthString(5);
                Put put = new Put(Bytes.toBytes(rowkey));
                put.addColumn(Bytes.toBytes(Example.FAMILY1), Bytes.toBytes("1_coloumn1"),
                        Bytes.toBytes("1_myvalue"+i));

                put.addColumn(Bytes.toBytes(Example.FAMILY1), Bytes.toBytes("1_coloumn2"),
                        Bytes.toBytes("1_value3"+i));

                put.addColumn(Bytes.toBytes(Example.FAMILY2), Bytes.toBytes("2_coloumn1"),
                        Bytes.toBytes("2_myvalue"+i));

                put.addColumn(Bytes.toBytes(Example.FAMILY2), Bytes.toBytes("2_coloumn2"),
                        Bytes.toBytes("2_value3"+i));

                list.add(put);
            }



            table.put(list);

            System.out.println("insert finished ");
        }

        finally {
            if (table != null) table.close();
        }
    }
}
