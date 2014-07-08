package boilerplate;

import javax.sql.DataSource;

import org.seasar.doma.SingletonConfig;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;
import org.seasar.extension.datasource.DataSourceFactory;
import org.seasar.extension.datasource.impl.SelectableDataSourceProxy;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import boilerplate.jdbc.DialectFactory;
import boilerplate.jdbc.impl.DialectFactoryImpl;

@SingletonConfig
public class AppConfig implements Config {

    private static final AppConfig CONFIG = new AppConfig();

    private final LocalTransactionManager transactionManager;

    private AppConfig() {
        transactionManager = new LocalTransactionManager(
                new LocalTransactionDataSource(getDataSource())
                        .getLocalTransaction(getJdbcLogger()));
    }

    @Override
    public Dialect getDialect() {
        return getDialectInternal();
    }

    @Override
    public DataSource getDataSource() {
        return getDataSourceInternal();
    }

    @Override
    public String getDataSourceName() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        if (container.hasComponentDef(DataSourceFactory.class)) {
            DataSourceFactory dataSourceFactory = (DataSourceFactory) container
                    .getComponent(DataSourceFactory.class);
            if (getDataSourceInternal() instanceof SelectableDataSourceProxy) {
                return dataSourceFactory.getSelectableDataSourceName();
            }
        }
        return getClass().getName();
    }

    @Override
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    @Override
    public JdbcLogger getJdbcLogger() {
        return new SLFJdbcLogger();
    }

    protected Dialect getDialectInternal() {
        DialectFactory factory = null;
        try {
            factory = SingletonS2Container.getComponent(DialectFactory.class);
        } catch (ComponentNotFoundRuntimeException e) {
            factory = new DialectFactoryImpl();
        }
        return factory.getDialect();
    }

    protected DataSource getDataSourceInternal() {
        return SingletonS2Container.getComponent(DataSource.class);
    }

    public static AppConfig singleton() {
        return CONFIG;
    }
}
