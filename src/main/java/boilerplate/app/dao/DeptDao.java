package boilerplate.app.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import boilerplate.AppConfig;
import boilerplate.app.entity.Dept;

/**
 */
@Dao(config = AppConfig.class)
public interface DeptDao {

    /**
     * @param id
     * @return the Dept entity
     */
    @Select
    Dept selectById(Long id);

    /**
     * @param id
     * @param versionNo
     * @return the Dept entity
     */
    @Select(ensureResult = true)
    Dept selectByIdAndVersion(Long id, Integer versionNo);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(Dept entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(Dept entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(Dept entity);
}