package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @program: com.pinyougou
 * @Date: 2018/9/16
 * @Author: chandler
 * @Description:
 */
public interface BrandService {

    public List<TbBrand> findAll();

    /**
     * 返回分页列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findPage(int pageNum,int pageSize);

    /**
     * 增加
     * @param tbBrand
     */
    public void add(TbBrand tbBrand);

    /**
     * 修改
     * @param tbBrand
     */
    public void update(TbBrand tbBrand);

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    public TbBrand findOne(Long id);

    /**
     * 删除
     * @param ids
     */
    public void delete(Long[] ids);

    /**
     * 条件查询
     * @param tbBrand
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findPage(TbBrand tbBrand,int pageNum,int pageSize);
    /**
     * 品牌下拉框数据
     */
    List<Map> selectOptionList();
}
