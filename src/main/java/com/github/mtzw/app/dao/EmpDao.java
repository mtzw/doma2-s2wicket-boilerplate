package com.github.mtzw.app.dao;

import java.util.function.Function;
import java.util.stream.Stream;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.Update;

import boilerplate.AppConfig;

import com.github.mtzw.app.entity.Emp;

/**
 */
@Dao(config = AppConfig.class)
public interface EmpDao {
    /**
     * @param id
     * @return the All Emp entity
     */
    @Select(strategy = SelectType.STREAM)
    String selectAll(Function<Stream<Emp>, String> mapper);

    /**
     * @param id
     * @return the Emp entity
     */
    @Select
    Emp selectById(Long id);

    /**
     * @param id
     * @param versionNo
     * @return the Emp entity
     */
    @Select(ensureResult = true)
    Emp selectByIdAndVersion(Long id, Integer versionNo);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(Emp entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(Emp entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(Emp entity);
}