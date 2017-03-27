package org.chenll.dao.sencond;

import org.apache.ibatis.annotations.Mapper;
import org.chenll.entity.TPerson;
import org.chenll.entity.TStudent;

/**
 * Created by chenlile on 17-3-17.
 */
@Mapper
public interface TStudentMapper {


    public void insert(TStudent person);
}
