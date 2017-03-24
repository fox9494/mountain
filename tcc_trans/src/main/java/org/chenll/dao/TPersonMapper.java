package org.chenll.dao;

import org.apache.ibatis.annotations.Mapper;
import org.chenll.entity.TPerson;

/**
 * Created by chenlile on 17-3-17.
 */
@Mapper
public interface TPersonMapper {


    public void insert(TPerson person);
}
