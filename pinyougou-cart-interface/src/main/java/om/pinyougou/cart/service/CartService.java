package om.pinyougou.cart.service;

import com.pinyougou.pojo.group.Cart;

import java.util.List;

/**
 * @program: pinyougou-parent
 * @Date: 2018/11/6
 * @Author: chandler
 * @Description:购物车服务接口
 */
public interface CartService {
    /**
     * 添加商品到购物车
     * @param cartList
     * @param itemId
     * @param num
     * @return
     */
    public List<Cart> addGoodsToCart(List<Cart> cartList,Long itemId,Integer num);

    /**
     * 将购物车保存到redis
     * @param username
     * @param cartList
     */
    public void saveCartListToRedis(String username,List<Cart> cartList);

    /**
     * 从redis中查询购物车
     * @param username
     * @return
     */
    public List<Cart> findCartListFromRedis(String username);
    /**
     * 合并购物车
     * @param cartList1
     * @param cartList2
     * @return
     */
    public List<Cart> mergeCartList(List<Cart> cartList1,List<Cart> cartList2);
}
