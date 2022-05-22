package com.meowu.plugins.mybatis.mysql.page;

import com.meowu.commons.security.page.PageRequest;

public class MysqlPageRequest<T> extends PageRequest{

    public MysqlPageRequest(){
        super();
    }

    public MysqlPageRequest(Long pageNumber, Long pageSize){
        super(pageNumber, pageSize);
    }

    @Override
    public Long getOffset(){
        return (getPageNumber() - 1) * getPageSize();
    }
}
