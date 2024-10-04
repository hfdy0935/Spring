package com.hspedu.chapter25.p6.test;

import com.hspedu.chapter25.p6.dao.ActorDAO;
import com.hspedu.chapter25.p6.domain.Actor;
import com.hspedu.chapter25.p6.utilds.JDBCUtilsByDruid;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"all"})
public class TestDAO {
    private static final ActorDAO actorDAO = new ActorDAO();

    private static List<Actor> getActorArr() {
        List<Actor> list = new ArrayList<Actor>();
        list.add(new Actor(null, "张三", "男", "2020-11-11"));
        list.add(new Actor(null, "李四", "男", "2021-11-11"));
        list.add(new Actor(null, "王五", "女", "2021-11-11"));
        list.add(new Actor(null, "赵六", "男", "2021-11-11"));
        list.add(new Actor(null, "田七", "女", "2021-11-11"));
        list.add(new Actor(null, "钱八", "男", "2021-11-11"));
        return list;
    }

    @Test
    public void createAndInitTable() {
        String sql1 = "create table if not exists actor(" +
                "id int primary key auto_increment," +
                "`name` varchar(30) not null," +
                "gender varchar(5) not null," +
                "birthday date not null)";
        actorDAO.execute(sql1);
        // 初始化
        String sql2 = "insert into actor values(?,?,?,?)";
        for (Actor actor : getActorArr()) {
            int resRows = actorDAO.update(sql2, actor.getId(), actor.getName(), actor.getGender(), actor.getBirthday());
            if (resRows > 0) {
                System.out.println("插入成功，影响" + resRows + "行");
            }
        }
    }

    @Test
    public void testActorDAO() {
        // 1. 查询
        List<Actor> actors = actorDAO.queryMulti("select * from actor where id>=?", Actor.class, 1);
        System.out.println("=====查询结果=====");
        for (Actor actor : actors) {
            System.out.println(actor);
        }
        // 2. 查询单行
        Actor actor = actorDAO.querySingle("select * from actor where id=?", Actor.class, 6);
        System.out.println("=====单行查询结果=====");
        System.out.println(actor);
        // 3. 查询单行单列
        String p = actorDAO.queryScalar("select name from actor where id=?", String.class, 6);
        System.out.println("=====查询单行单列值=====");
        System.out.println(p);
        // 4. dml操作
        int update = actorDAO.update("insert into actor values(?,?,?,?)", null, "张无忌", "男", "2021-11-11");
        System.out.println(update > 0 ? "执行成功" : "执行没有影响表");
    }
}
