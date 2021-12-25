package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

public interface CheckItemService {
    public void add(CheckItem checkItem);

    PageResult pageQuery(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    void edit(CheckItem checkItem);

    boolean isCheckItemUsedInGroup(Integer id);
}
