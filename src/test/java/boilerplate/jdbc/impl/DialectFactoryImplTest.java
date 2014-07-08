package boilerplate.jdbc.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.seasar.doma.jdbc.dialect.PostgresDialect;
import org.seasar.doma.jdbc.dialect.StandardDialect;

public class DialectFactoryImplTest {

    @Test
    public void testDialectFactoryImpl() {
        DialectFactoryImpl impl = new DialectFactoryImpl();

        assertThat(impl, is(notNullValue()));
        assertThat(impl.getDialect(), is(instanceOf(StandardDialect.class)));
    }

    @Test
    public void testDialectFactoryImplDialect() {
        DialectFactoryImpl impl = new DialectFactoryImpl(new H2Dialect());

        assertThat(impl, is(notNullValue()));
        assertThat(impl.getDialect(), is(instanceOf(H2Dialect.class)));

        impl = new DialectFactoryImpl(new PostgresDialect());

        assertThat(impl, is(notNullValue()));
        assertThat(impl.getDialect(), is(instanceOf(PostgresDialect.class)));
    }

    @Test
    public void testDialectFactoryImplString() {
        DialectFactoryImpl impl = new DialectFactoryImpl("mysql");

        assertThat(impl, is(notNullValue()));
        assertThat(impl.getDialect(), is(instanceOf(MysqlDialect.class)));

        impl = new DialectFactoryImpl("MYSQLdialect");
        assertThat(impl, is(notNullValue()));
        assertThat(impl.getDialect(), is(instanceOf(MysqlDialect.class)));

        impl = new DialectFactoryImpl("hogehoge");
        assertThat(impl, is(notNullValue()));
        assertThat(impl.getDialect(), is(instanceOf(StandardDialect.class)));
    }

}
