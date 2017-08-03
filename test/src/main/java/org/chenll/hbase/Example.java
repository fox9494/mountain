package org.chenll.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class Example {
    public static final String TABLE_NAME = "mytable";
    public static final String FAMILY1 = "family1";
    public static final String FAMILY2 = "family2";

    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        createTable(connection);
//        modifySchema(connection);

    }

    public static void createTable(Connection connection) throws IOException {
        Admin admin = connection.getAdmin();

        HTableDescriptor table = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
        table.addFamily(new HColumnDescriptor(FAMILY1).setCompressionType(Compression.Algorithm.NONE));
        table.addFamily(new HColumnDescriptor(FAMILY2).setCompressionType(Compression.Algorithm.NONE));

        System.out.print("Creating table. ");
        createOrOverwrite(admin, table);
        System.out.println(" Done.");

    }

    public static void createOrOverwrite(Admin admin, HTableDescriptor table) throws IOException {
        if (admin.tableExists(table.getTableName())) {
            admin.disableTable(table.getTableName());
            admin.deleteTable(table.getTableName());
        }
        admin.createTable(table);
    }




    public static void modifySchema (Connection connection) throws IOException {
        Admin admin = connection.getAdmin();

        TableName tableName = TableName.valueOf(TABLE_NAME);
        if (!admin.tableExists(tableName)) {
            System.out.println("Table does not exist.");
            System.exit(-1);
        }

        HTableDescriptor table = admin.getTableDescriptor(tableName);

        // Update existing table
        HColumnDescriptor newColumn = new HColumnDescriptor("NEWCF");
        newColumn.setCompactionCompressionType(Compression.Algorithm.GZ);
        newColumn.setMaxVersions(HConstants.ALL_VERSIONS);
        admin.addColumn(tableName, newColumn);

        // Update existing column family
//        HColumnDescriptor existingColumn = new HColumnDescriptor(CF_DEFAULT);
//        existingColumn.setCompactionCompressionType(Compression.Algorithm.GZ);
//        existingColumn.setMaxVersions(HConstants.ALL_VERSIONS);
//        table.modifyFamily(existingColumn);
//        admin.modifyTable(tableName, table);

//             Disable an existing table
//        admin.disableTable(tableName);

        // Delete an existing column family
//        admin.deleteColumn(tableName, CF_DEFAULT.getBytes("UTF-8"));

        // Delete a table (Need to be disabled first)
//        admin.deleteTable(tableName);
    }


}
