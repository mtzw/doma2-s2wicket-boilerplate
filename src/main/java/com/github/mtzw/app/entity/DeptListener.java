package com.github.mtzw.app.entity;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

/**
 * 
 */
public class DeptListener implements EntityListener<Dept> {

    @Override
    public void preInsert(Dept entity, PreInsertContext<Dept> context) {
    }

    @Override
    public void preUpdate(Dept entity, PreUpdateContext<Dept> context) {
    }

    @Override
    public void preDelete(Dept entity, PreDeleteContext<Dept> context) {
    }

    @Override
    public void postInsert(Dept entity, PostInsertContext<Dept> context) {
    }

    @Override
    public void postUpdate(Dept entity, PostUpdateContext<Dept> context) {
    }

    @Override
    public void postDelete(Dept entity, PostDeleteContext<Dept> context) {
    }
}