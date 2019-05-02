package com.parasoft.examples.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcHsqlDbUtil
{
    private JdbcHsqlDbUtil()
    {
        // static util methods only
    }

    public static Connection getConnection()
        throws Exception
    {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:mem:jdbctestdb;DB_CLOSE_DELAY=-1;MODE=Oracle;TRACE_LEVEL_SYSTEM_OUT=2");
    }

    public static void initialize(Connection conn)
        throws Exception
    {
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE BIRDS IF EXISTS");
        stmt.execute("CREATE TABLE BIRDS ("
                + " ID BIGINT generated by default as identity not null,"
                + " TYPE VARCHAR(32),"
                + " COLOR VARCHAR(32),"
                + " WEIGHT DOUBLE,"
                + " CONSTRAINT PK PRIMARY KEY(ID)"
                + ")");
    }
}