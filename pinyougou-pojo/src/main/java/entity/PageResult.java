package entity;

import java.io.Serializable;
import java.util.List;

/**
 * @program: pinyougou-parent
 * @Date: 2018/9/17
 * @Author: chandler
 * @Description:分页结果封装对象
 */
public class PageResult implements Serializable {
    private int total;//总记录数
    private List rows;//当前页结果

    public PageResult(int total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
