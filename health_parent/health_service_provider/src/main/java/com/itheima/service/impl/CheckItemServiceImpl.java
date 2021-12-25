package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    public boolean isCheckItemUsedInGroup(Integer id) {
//        checkItemDao.isisCheckItemUsedInGroup(id);
        //暂时写死
        return false;
    }

    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    public void deleteById(Integer id) {
        //判断当前检查项是否已经关联到检查组
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            //当前检查项已经被关联到检查组，不允许删除
            throw new RuntimeException("当前检查项已经被关联到检查组，不允许删除");
        }
        checkItemDao.deleteById(id);
    }

    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //based on mybatis  and PageHelper
        if (queryString != null && queryString.length()>0) {
            currentPage=1;
        }
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page=checkItemDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();
        return new PageResult(total,rows);
    }

    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }
}
