package org.chenll.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

import static org.apache.hadoop.hbase.CellUtil.*;

public class QueryExample {

    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
//        get(connection);
        scan(connection);
    }


    public static void get(Connection connection) throws IOException {

        Get get = new Get(Bytes.toBytes("key8-01353"));
        RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.LESS,
                new BinaryComparator(Bytes.toBytes("key8")));
        get.setFilter(rowFilter);
        Table table = connection.getTable(TableName.valueOf(Example.TABLE_NAME));
        Result result = table.get(get);
        List<Cell> cs = result.listCells();
        if (cs==null){
            System.out.println("没有数据");
            return;
        }
        for (Cell cell : cs) {
            System.out.println("result = " +Bytes.toString(CellUtil.cloneRow(cell))+"-"+Bytes.toString(CellUtil.cloneQualifier(cell))+"-"+Bytes.toString(CellUtil.cloneValue(cell)) );
        }

    }

    public static void scan(Connection connection) throws IOException {
        Scan scan = new Scan();
        RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL,
                new RegexStringComparator("key0*"));
        scan.setFilter(rowFilter);
        Table table = connection.getTable(TableName.valueOf(Example.TABLE_NAME));
        ResultScanner scanResult = table.getScanner(scan);
        for (Result result : scanResult) {
            List<Cell> cs = result.listCells();
            if (cs==null){
                System.out.println("没有数据");
                return;
            }
            for (Cell cell : cs) {
                System.out.println("result = " +Bytes.toString(CellUtil.cloneRow(cell))+"-"+Bytes.toString(CellUtil.cloneQualifier(cell))+"-"+Bytes.toString(CellUtil.cloneValue(cell)) );
            }
        }


    }
}
