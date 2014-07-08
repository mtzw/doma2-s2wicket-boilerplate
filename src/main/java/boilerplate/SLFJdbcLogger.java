/*
 * Copyright 2004-2013 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package boilerplate;

import static org.seasar.doma.message.Message.*;

import java.sql.SQLException;

import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.Sql;
import org.seasar.doma.jdbc.SqlExecutionSkipCause;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLFJdbcLogger implements JdbcLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger("jdbc");

    @Override
    public void logDaoMethodEntering(String callerClassName,
            String callerMethodName, Object... args) {
        LOGGER.debug("ENTRY");
    }

    @Override
    public void logDaoMethodExiting(String callerClassName,
            String callerMethodName, Object result) {
        LOGGER.debug(String.format("RETURN %s", result));
    }

    @Override
    public void logDaoMethodThrowing(String callerClassName,
            String callerMethodName, RuntimeException e) {
        LOGGER.error(String.format("THROW %s", e.toString()), e);
    }

    @Override
    public void logSqlExecutionSkipping(String callerClassName,
            String callerMethodName, SqlExecutionSkipCause cause) {
        LOGGER.debug(cause.name());
    }

    @Override
    public void logSql(String callerClassName, String callerMethodName,
            Sql<?> sql) {
        LOGGER.debug(DOMA2076.getMessage(sql.getSqlFilePath(),
                sql.getFormattedSql()));
    }

    @Override
    public void logTransactionBegun(String callerClassName,
            String callerMethodName, String transactionId) {
        LOGGER.debug(DOMA2063.getMessage(transactionId));
    }

    @Override
    public void logTransactionEnded(String callerClassName,
            String callerMethodName, String transactionId) {
        LOGGER.debug(DOMA2064.getMessage(transactionId));
    }

    @Override
    public void logTransactionSavepointCreated(String callerClassName,
            String callerMethodName, String transactionId, String savepointName) {
        LOGGER.debug(DOMA2065.getMessage(transactionId, savepointName));
    }

    @Override
    public void logTransactionCommitted(String callerClassName,
            String callerMethodName, String transactionId) {
        LOGGER.debug(DOMA2067.getMessage(transactionId));
    }

    @Override
    public void logTransactionRolledback(String callerClassName,
            String callerMethodName, String transactionId) {
        LOGGER.debug(DOMA2068.getMessage(transactionId));
    }

    @Override
    public void logTransactionSavepointRolledback(String callerClassName,
            String callerMethodName, String transactionId, String savepointName) {
        LOGGER.debug(DOMA2069.getMessage(transactionId, savepointName));

    }

    @Override
    public void logTransactionRollbackFailure(String callerClassName,
            String callerMethodName, String transactionId, SQLException e) {
        LOGGER.error(DOMA2070.getMessage(transactionId));
    }

    @Override
    public void logAutoCommitEnablingFailure(String callerClassName,
            String callerMethodName, SQLException e) {
        LOGGER.debug(DOMA2071.getMessage(), e);
    }

    @Override
    public void logTransactionIsolationSettingFailuer(String callerClassName,
            String callerMethodName, int transactionIsolationLevel,
            SQLException e) {
        LOGGER.error(DOMA2072.getMessage(transactionIsolationLevel), e);
    }

    @Override
    public void logConnectionClosingFailure(String callerClassName,
            String callerMethodName, SQLException e) {
        LOGGER.error(DOMA2073.getMessage(), e);
    }

    @Override
    public void logStatementClosingFailure(String callerClassName,
            String callerMethodName, SQLException e) {
        LOGGER.error(DOMA2074.getMessage(), e);
    }

    @Override
    public void logResultSetClosingFailure(String callerClassName,
            String callerMethodName, SQLException e) {
        LOGGER.error(DOMA2075.getMessage(), e);
    }

}
