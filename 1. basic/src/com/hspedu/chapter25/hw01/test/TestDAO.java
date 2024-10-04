package com.hspedu.chapter25.hw01.test;

import com.hspedu.chapter25.JDBCUtils.JDBCUtils;
import com.hspedu.chapter25.hw01.dao.GoodsDAO;
import com.hspedu.chapter25.hw01.domain.Goods;
import com.hspedu.chapter25.p6.domain.Actor;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TestDAO {
    Connection conn = JDBCUtils.getConnection();
    GoodsDAO goodsDAO = new GoodsDAO();

    private List<Goods> getGoodsList() {
        List<Goods> list = new ArrayList<>();
        list.add(new Goods(10, "华为", 2000.0));
        list.add(new Goods(20, "苹果", 3000.0));
        list.add(new Goods(30, "小米", 2000.0));
        list.add(new Goods(40, "VIVO", null));
        list.add(new Goods(50, "三星", 2300.0));
        list.add(new Goods(60, "海尔", 1800.0));
        list.add(new Goods(70, "IBM", 5000.0));
        list.add(new Goods(80, "格力", null));
        list.add(new Goods(80, "格力", null));
        return list;
    }

    @Test
    public void createAndInitTable() {
        // 创建表
        String sql1 = "create table if not exists goods(" +
                "id int(11)," +
                "goods_name varchar(10)," +
                "price double)";
        goodsDAO.execute(sql1);
    }

    @Test
    public void test() {
        // 1. 增
        String sql1 = "insert into goods values(?,?,?)";
        for (Goods goods : getGoodsList()) {
            goodsDAO.update(sql1, goods.getId(), goods.getGoods_name(), goods.getPrice());
        }
        System.out.println("插入成功");
        // 2. 删除id=30的
        String sql2 = "delete from goods where id=?";
        goodsDAO.update(sql2, 30);
        System.out.println("删除成功");
        // 3. 修改价格>=3000的手机：手机名+"手机"，价格都+200
        String sql3 = "update goods set goods_name=concat(goods_name,'手机'),price=if(price,price+200,null) where price>=?";
        goodsDAO.update(sql3, 3000);
        System.out.println("修改成功");
        // 4. 查询单行
        Goods goods = goodsDAO.querySingle("select * from goods where id=?", Goods.class, 10);
        System.out.println("单行查询结果：" + goods);
        // 5. 多行查询
        List<Goods> goodsList = goodsDAO.queryMulti("select * from goods where price>?", Goods.class, 2000.0);
        System.out.println("多行查询结果：");
        for (Goods good : goodsList) {
            System.out.println(good);
        }
        // 6. 单行单列查询
        String name = goodsDAO.queryScalar("select goods_name from goods where id=?",String.class, 20);
        System.out.println("单行单列查询结果：" + name);
    }
}
