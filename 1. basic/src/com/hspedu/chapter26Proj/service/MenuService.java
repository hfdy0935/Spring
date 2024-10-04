package com.hspedu.chapter26Proj.service;

import com.hspedu.chapter26Proj.dao.MenuDAO;
import com.hspedu.chapter26Proj.domain.Menu;

import java.util.List;

public class MenuService {
    private final MenuDAO menuDAO = new MenuDAO();

    /**
     * 获取所有菜品
     *
     * @return
     */
    public List<Menu> getAllMenus() {
        String sql = "select * from menu";
        return menuDAO.queryMulti(sql, Menu.class);
    }

    /**
     * 根据id获取一个菜品
     *
     * @param id
     * @return
     */
    public Menu getMenuById(int id) {
        String sql = "select * from menu where id=?";
        return menuDAO.querySingle(sql, Menu.class, id);
    }

//    public boolean orderMenu(int diningTableId, int id, int num) {
//        String sql = "select * from "
//    }
}
