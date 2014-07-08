package boilerplate.jdbc;

import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.StandardDialect;

/**
 * Dialect Factory
 */
public interface DialectFactory {

    /**
     * Get instance of dialect.
     *
     * @return {@link Dialect}
     */
    default Dialect getDialect() {
        return new StandardDialect();
    }

}
