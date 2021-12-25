package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;


public interface CheckItemDao {
    public void add(CheckItem checkItem);
    public Page<CheckItem> selectByCondition(String queryString);
    void deleteById(int id);

    void edit(CheckItem checkItem);

    long findCountByCheckItemId(Integer id);
}
