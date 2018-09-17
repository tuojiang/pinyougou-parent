package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: com.pinyougou
 * @Date: 2018/9/16
 * @Author: chandler
 * @Description:
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }

    @RequestMapping("/findPage")
    public PageResult findPage(int page, int size) {
        return brandService.findPage(page, size);
    }

    //增加
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand tbBrand) {
        try {
            brandService.add(tbBrand);
            return new Result(true, "增加品牌成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加品牌失败");
        }
    }
    //查找
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id) {
        return brandService.findOne(id);
    }
    //修改
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand tbBrand) {
        try {
            brandService.update(tbBrand);
            return new Result(true, "修改品牌成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改品牌失败");
        }
    }
    //删除
    @RequestMapping("/delete")
    public void delete(Long[] ids) {
        brandService.delete(ids);
    }

    //条件查找
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand tbBrand,int page,int size) {
        return brandService.findPage(tbBrand,page,size);
    }
}
