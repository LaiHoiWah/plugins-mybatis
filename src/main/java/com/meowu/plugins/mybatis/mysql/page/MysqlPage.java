package com.meowu.plugins.mybatis.mysql.page;

import com.meowu.commons.security.page.Page;
import com.meowu.commons.security.page.PageRequest;

import java.util.List;

public class MysqlPage<T> extends Page{

    public MysqlPage(List<T> content, Long total, PageRequest pageRequest){
        super(content, total, pageRequest);
    }

    @Override
    public boolean hasNext(){
        return getPageRequest().getOffset() + getContent().size() < getTotal();
    }
}
