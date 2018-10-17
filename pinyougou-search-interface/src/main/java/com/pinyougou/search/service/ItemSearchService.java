package com.pinyougou.search.service;

import java.util.Map;

/**
 * @program: pinyougou-parent
 * @Date: 2018/10/16
 * @Author: chandler
 * @Description:
 */
public interface ItemSearchService {
    /**
     * 搜索
     * @param searchMap
     * @return
     */
    public Map<String,Object> search(Map searchMap);
}
