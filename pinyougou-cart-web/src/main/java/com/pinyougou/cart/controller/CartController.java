package com.pinyougou.cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinyougou.pojo.group.Cart;
import om.pinyougou.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;

import entity.Result;
import util.CookieUtil;

@RestController
@RequestMapping("/cart")
public class CartController {


    @Reference
    private CartService cartService;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    /**
     * 购物车列表
     * @return
     */
    @RequestMapping("/findCartList")
    public List<Cart> findCartList(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("当前登录人："+username);
        String cartListString = CookieUtil.getCookieValue(request,"cartList","UTF-8");
        if (cartListString == null || cartListString.equals("")) {
            cartListString="[]";
        }

        List cartList_cookie = JSON.parseArray(cartListString,Cart.class);

        if (username.equals("anonymousUser")) {//如果未登录
            System.out.println("从cookie中提取购物车");
            //从cookie中取出购物车
            return cartList_cookie;
        } else {//如果已登录
            //从redis获取购物车
            List<Cart> cartList_redis =cartService.findCartListFromRedis(username);
            if (cartList_cookie.size()>0){//判断当本地购物车中存在数据
                //合并购物车
                List<Cart> cartList = cartService.mergeCartList(cartList_cookie,cartList_redis);
                //将合并后的购物车存入redis
                cartService.saveCartListToRedis(username,cartList);
                //本地购物车清除
                CookieUtil.deleteCookie(request,response,"cartList");
                System.out.println("执行了合并购物车的逻辑");
                return cartList;
            }
            return cartList_redis;
        }
    }

    /**
     * 添加商品到购物车
     * @param itemId
     * @param num
     * @return
     */
    @RequestMapping("/addGoodsToCartList")
    public Result addGoodsToCartList(Long itemId,Integer num){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("当前登录人："+name);
        try{
            //提取购物车
            List<Cart> cartList = findCartList();
            cartList = cartService.addGoodsToCart(cartList,itemId,num);
            if (name.equals("anonymousUser")) {//如果未登录
                //将新的购物车存入cookie
                String cartListString = JSON.toJSONString(cartList);
                CookieUtil.setCookie(request,response,"cartList",JSON.toJSONString(cartList),3600*24,"UTF-8");
                System.out.println("向cookie存储购物车");
            } else {
                //如果登录
                cartService.saveCartListToRedis(name,cartList);
            }
            return new Result(true,"存入购物车成功");
        } catch (Exception e){
            e.printStackTrace();
            return new Result(false,"存入购物车失败");
        }
    }
}
