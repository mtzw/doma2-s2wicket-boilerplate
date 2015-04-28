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

package boilerplate.app.web;

import java.util.stream.Collectors;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.seasar.doma.jdbc.tx.TransactionManager;

import boilerplate.AppConfig;
import boilerplate.app.dao.EmpDao;
import boilerplate.app.dao.impl.EmpDaoImpl;

/**
 * Homepage
 */
public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
        EmpDao dao = new EmpDaoImpl();
        TransactionManager ltm = AppConfig.singleton().getTransactionManager();
        ltm.required(() -> {
            String message = dao.selectAll(stream -> {
                return stream.map(s -> s.getEmpName()).collect(
                        Collectors.joining("#"));
            });
            add(new Label("message", message));
        });
    }
}
