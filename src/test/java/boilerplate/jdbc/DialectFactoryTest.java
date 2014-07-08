package boilerplate.jdbc;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.OracleDialect;
import org.seasar.doma.jdbc.dialect.StandardDialect;

public class DialectFactoryTest {

    @Test
    public void testGetDialectByDefaultMethod() {
        DialectFactory notImplements = new DialectFactory() {
        };
        assertThat(notImplements.getDialect(),
                is(instanceOf(StandardDialect.class)));
    }

    @Test
    public void testGetDialect() {
        MyDialectFactory factory = new MyDialectFactory(new OracleDialect());
        assertThat(factory.getDialect(), is(instanceOf(OracleDialect.class)));
    }

    static class MyDialectFactory implements DialectFactory {
        private Dialect dialect;

        public MyDialectFactory(Dialect dialect) {
            this.dialect = dialect;
        }

        @Override
        public Dialect getDialect() {
            return this.dialect;
        }
    }

}
