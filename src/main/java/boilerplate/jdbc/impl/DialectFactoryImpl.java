package boilerplate.jdbc.impl;

import org.seasar.doma.jdbc.dialect.Db2Dialect;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H212126Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.dialect.HsqldbDialect;
import org.seasar.doma.jdbc.dialect.Mssql2008Dialect;
import org.seasar.doma.jdbc.dialect.MssqlDialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.seasar.doma.jdbc.dialect.OracleDialect;
import org.seasar.doma.jdbc.dialect.PostgresDialect;
import org.seasar.doma.jdbc.dialect.SqliteDialect;
import org.seasar.doma.jdbc.dialect.StandardDialect;

import boilerplate.jdbc.DialectFactory;

/**
 * DialectfactoryImpl
 *
 * how to use.<br/>
 *
 * <pre>
 * &ltcomponent name="dialectFactory" class="boilerplate.jdbc.impl.DialectFactoryImpl"&gt
 *   &ltarg&gt
 *     &ltcomponent name="h2Dialect" class="org.seasar.doma.jdbc.dialect.H2Dialect" /&gt
 *   &lt/arg&gt
 * &lt/component&gt
 * </pre>
 *
 * either-or...<br/>
 *
 * <pre>
 * &ltcomponent name="dialectFactory" class="boilerplate.jdbc.impl.DialectFactoryImpl"&gt
 *   &ltarg&gt"h2Dialect"&lt/arg&gt
 * &lt/component&gt
 * </pre>
 */
public class DialectFactoryImpl implements DialectFactory {

    /**
     * Default Constructor
     */
    public DialectFactoryImpl() {
        this.dialect = new StandardDialect();
    }

    /**
     * Constructor
     *
     * @param dialect
     *            A instance of {@link Dialect}.
     */
    public DialectFactoryImpl(Dialect dialect) {
        this.dialect = dialect;
    }

    /**
     * Constructor
     *
     * @param dialectName
     *            A name of dialect.
     */
    public DialectFactoryImpl(String dialectName) {
        this.dialect = createDialectInstanceForName(dialectName);
    }

    private final Dialect dialect;

    /**
     * Get dialect instance.
     */
    @Override
    public Dialect getDialect() {
        return dialect;
    }

    private Dialect createDialectInstanceForName(String dialectName) {
        String refactorName = dialectName.toLowerCase();
        refactorName = refactorName.endsWith("dialect") ? refactorName
                .substring(0, refactorName.lastIndexOf("dialect"))
                : refactorName;
        switch (refactorName) {
        case "db2":
            return new Db2Dialect();
        case "h212126":
            return new H212126Dialect();
        case "h2":
            return new H2Dialect();
        case "hsqldb":
            return new HsqldbDialect();
        case "mssql2008":
            return new Mssql2008Dialect();
        case "mssql":
            return new MssqlDialect();
        case "mysql":
            return new MysqlDialect();
        case "oracle":
            return new OracleDialect();
        case "postgres":
            return new PostgresDialect();
        case "sqlite":
            return new SqliteDialect();
        default:
            return new StandardDialect();
        }
    }

}
