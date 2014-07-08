package com.github.mtzw.app.dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

import junit.framework.TestCase;

import org.seasar.doma.jdbc.NoCacheSqlFileRepository;
import org.seasar.doma.jdbc.SqlFile;
import org.seasar.doma.jdbc.SqlFileRepository;
import org.seasar.doma.jdbc.dialect.Dialect;

/**
 * 
 */
public class EmpDaoTest extends TestCase {

    /** */
    protected SqlFileRepository repository;

    /** */
    protected Dialect dialect;

    /** */
    protected Driver driver;

    /** */
    protected String url;

    /** */
    protected String user;

    /** */
    protected String password;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repository = new NoCacheSqlFileRepository();
        dialect = new org.seasar.doma.jdbc.dialect.H2Dialect();
        @SuppressWarnings("unchecked")
        Class<Driver> driverClass = (Class<Driver>) Class.forName("org.h2.Driver");
        driver = driverClass.newInstance();
        DriverManager.registerDriver(driver);
        url = "jdbc:h2:file:demo";
        user = "sa";
        password = "";
    }

    @Override
    protected void tearDown() throws Exception {
        DriverManager.deregisterDriver(driver);
        super.tearDown();
    }

    /**
     * 
     * @param sqlFile
     * @throws Exception
     */
    protected void execute(SqlFile sqlFile) throws Exception {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            try {
                statement.execute(sqlFile.getSql());
            } finally {
                statement.close();
            }
        } finally {
            try {
                connection.rollback();
            } finally {
                connection.close();
            }
        }
    }

    /**
     * 
     * @return
     * @throws Exception
     */
    protected Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 
     * @return method
     * @throws Exception
     */
    protected Method getMethod() throws Exception {
        return getClass().getMethod(getName(), new Class[] {});
    }

    /**
     * 
     * @throws Exception
     */
    public void testSelectById() throws Exception {
        SqlFile sqlFile = repository.getSqlFile(getMethod(), "META-INF/com/github/mtzw/app/dao/EmpDao/selectById.sql", dialect);
        execute(sqlFile);
    }

    /**
     * 
     * @throws Exception
     */
    public void testSelectByIdAndVersion() throws Exception {
        SqlFile sqlFile = repository.getSqlFile(getMethod(), "META-INF/com/github/mtzw/app/dao/EmpDao/selectByIdAndVersion.sql", dialect);
        execute(sqlFile);
    }

    /**
     * 
     * @throws Exception
     */
    public void testSelectAll() throws Exception {
        SqlFile sqlFile = repository.getSqlFile(getMethod(), "META-INF/com/github/mtzw/app/dao/EmpDao/selectAll.sql", dialect);
        execute(sqlFile);
    }

}