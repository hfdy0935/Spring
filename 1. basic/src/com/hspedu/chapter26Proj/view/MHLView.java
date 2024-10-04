package com.hspedu.chapter26Proj.view;

import com.hspedu.chapter26Proj.domain.*;
import com.hspedu.chapter26Proj.service.BillService;
import com.hspedu.chapter26Proj.service.DiningTableService;
import com.hspedu.chapter26Proj.service.EmployeeService;
import com.hspedu.chapter26Proj.service.MenuService;
import com.hspedu.chapter26Proj.utils.Utility;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class MHLView {
    private boolean loop = true; // 是否退出菜单
    private boolean isMainMenuShow = true;
    private String key = ""; // 用户选择
    private Employee employee = null; //当前用户
    private List<Bill> billList = null; // 当前用户的账单
    private final EmployeeService employeeService = new EmployeeService();
    private final DiningTableService diningTableService = new DiningTableService();
    private final MenuService menuService = new MenuService();
    private final BillService billService = new BillService();


    public static void main(String[] args) {
        new MHLView().showSetupMenu();
        System.out.println("已退出满汉楼系统");
    }

    /**
     * 初始主界面
     */
    public void showSetupMenu() {
        while (loop) {
            printTitle("满汉楼");
            System.out.println("\t\t 1 登录满汉楼");
            System.out.println("\t\t 2 退出满汉楼");
            System.out.println("输入选项：");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    showLoginMenu();
                    break;
                case "2":
                    loop = false;
                    break;
            }
        }
    }

    /**
     * 登录界面
     */
    private void showLoginMenu() {
        System.out.println("输入员工号：");
        String empId = Utility.readString(50);
        System.out.println("输入密码：");
        String password = Utility.readString(50);
        // 数据库查询判断
        employee = employeeService.getEmployeeByIdAndPassword(empId, password);
        if (employee != null) {
            System.out.println("登录成功，欢迎 " + employee.getName());
            showMainMenu();
        } else {
            System.out.println("账号或密码错误，请重新输入");
        }
    }

    /**
     * 显示登录后的菜单
     */
    private void showMainMenu() {
        isMainMenuShow = true;
        while (isMainMenuShow) {
            printTitle("满汉楼（二级菜单）");
            System.out.println("\t\t 1 显示餐桌状态");
            System.out.println("\t\t 2 预定餐桌");
            System.out.println("\t\t 3 显示所有菜品");
            System.out.println("\t\t 4 点餐服务");
            System.out.println("\t\t 5 查看我的账单");
            System.out.println("\t\t 6 结账");
            System.out.println("\t\t 8 退出登录");
            System.out.println("\t\t 9 退出满汉楼");
            System.out.println("输入选项：");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    showAllDiningTables();
                    break;
                case "2":
                    orderDiningTable();
                    break;
                case "3":
                    showAllMenus();
                    break;
                case "4":
                    orderMenu();
                    break;
                case "5":
                    showMyBills();
                    break;
                case "6":
                    payBill();
                    break;
                case "8":
                    logout();
                    break;
                case "9":
                    logout();
                    loop = false;
                    break;
                default:
                    System.out.println("输入有误，请重新输入：");
            }
        }
    }

    private void printTitle(String title) {
        System.out.println("==========" + title + "==========");
    }

    /**
     * 退出登录
     */
    private void logout() {
        employee = null;
        isMainMenuShow = false;
    }

    /**
     * 主菜单选1，查看所有餐桌状态
     */
    private void showAllDiningTables() {
        printTitle("餐桌状态");
        List<DiningTable> diningTableList = diningTableService.getAllDiningTables();
        System.out.println("id\t\t状态\t\t预订人姓名\t\t预订人电话");
        for (DiningTable dt : diningTableList) {
            System.out.print(dt);
            // 判断已被预定的餐桌是不是当前用户预定的
            if (dt.getStatus() == 0) {
                System.out.println("\t\t\t" + ((dt.getEmpId().equals(employee.getEmpId())) ? "我" : ""));
            } else {
                System.out.println();
            }
        }
    }

    /**
     * 预定餐桌
     */
    private void orderDiningTable() {
        printTitle("预定餐桌");
        System.out.println("请输入要预定的餐桌编号（-1退出）：");
        int id = Utility.readInt(1);
        if (id == -1) {
            printTitle("已取消预订");
            return;
        }
        // 确认是否预定
        char c = Utility.readConfirmSelection();
        if (c == 'N') {
            printTitle("已取消预订");
            return;
        }
        // 查找对应餐桌
        DiningTable dt = diningTableService.getDiningTableById(id);
        if (dt == null) {
            printTitle("该餐桌不存在");
            return;
        }
        if (dt.getStatus() == 0) {
            System.out.println("该餐桌已被预订，请重新选择");
            return;
        }
        // 可预订，输入信息
        System.out.println("输入预订人姓名：");
        String orderName = Utility.readString(50);
        System.out.println("输入预订人电话：");
        String orderTel = Utility.readString(20);
        if (diningTableService.orderDiningTable(id, employee.getEmpId(), orderName, orderTel)) {
            printTitle("预订成功");
        } else {
            printTitle("预定失败，请检查餐桌id、预订人姓名和电话");
        }
    }

    /**
     * 显示所有菜品
     */
    private void showAllMenus() {
        printTitle("所有菜品");
        List<Menu> menuList = menuService.getAllMenus();
        System.out.println("编号\t\t菜名\t\t分类\t\t价格");
        for (Menu m : menuList) {
            System.out.println(m);
        }
    }


    /**
     * 点餐服务
     */
    private void orderMenu() {
        printTitle("点餐服务");
        int diningTableId, menuId, menuNum;
        Menu menu = null;
        // 选择餐桌
        while (true) {
            System.out.println("输入餐桌id（-1）退出：");
            diningTableId = Utility.readInt();
            if (diningTableId == -1) {
                return;
            }
            // 判断该餐桌的状态
            DiningTable dt = diningTableService.getDiningTableById(diningTableId);
            if (dt == null) System.out.println("餐桌不存在，请重新输入");
            else if (dt.getStatus() == 1) {
                System.out.println("该餐桌还未预定，请先预定");
            } else if (dt.getStatus() == 0 && !dt.getEmpId().equals(employee.getEmpId()))
                // 预定了，但不是当前用户预定的
                System.out.println("该餐桌已被别人预定，请给自己餐桌点餐");
            else break;
        }
        // 选择菜品
        while (true) {
            System.out.println("输入菜品id（-1退出）：");
            menuId = Utility.readInt();
            if (menuId == -1) return;
            // 查看是否有该菜品
            menu = menuService.getMenuById(menuId);
            if (menu == null) System.out.println("菜品不存在，请重新输入");
            else break;
        }
        // 输入数量
        while (true) {
            System.out.println("输入预订数量（-1退出）：");
            menuNum = Utility.readInt();
            if (menuNum == -1) return;
            else if (menuNum == 0) System.out.println("数量不能为0，请重新输入");
            else break;
        }
        String billId = UUID.randomUUID().toString(); // 随机uuid账单号
        // 订单创建时间
        String[] stringDateTime = LocalDateTime.now().toString().split("\\.");
        String billDateTime = stringDateTime[0].replace('T', ' ');
        double money = menuNum * menu.getPrice();
        boolean result = billService.addBill(billId, employee.getId(), diningTableId, menuId, menuNum, billDateTime, money);
        if (result) System.out.println("订单创建成功，请耐心等待出餐");
        else System.out.println("订单创建失败，请重试");
    }

    /**
     * 查看所有账单
     */
    private void showMyBills() {
        billList = billService.getAllBills(employee.getId());
        System.out.println("\t序号\t\t\t\t\t\t\t账单号\t\t\t\t\t 用户id\t  餐桌id\t  菜品id\t  菜品数量\t\t  创建时间\t\t\t \t总金额\t 订单状态");
        if (billList.isEmpty()) System.out.println("账单为空");
        else {
            int idx = 0;
            for (Bill b : billList) {
                System.out.print("\t" + (++idx));
                System.out.println(b);
            }
        }
    }

    /**
     * 结账
     */
    private void payBill() {
        // 只查找未支付的账单
        billList = billService.getAllUnPaidBills(employee.getId());
        if (billList.isEmpty()) {
            System.out.println("你还未创建订单，请先创建订单");
            return;
        }
        System.out.println("\t序号\t\t\t\t\t\t\t账单号\t\t\t\t\t 用户id\t  餐桌id\t  菜品id\t  菜品数量\t\t  创建时间\t\t\t \t总金额\t 订单状态");
        int idx = 0;
        for (Bill b : billList) {
            System.out.print("\t" + (++idx));
            System.out.println(b);
        }
        System.out.println("选择要支付订单的序号");
        int toPayNum = Utility.readInt();
        if (!(toPayNum >= 1 && toPayNum <= billList.size())) {
            System.out.println("订单序号应在 " + 1 + " ~ " + billList.size() + " 之间");
            return;
        }
        // 获得该订单，List下标从0开始，序号需要-1获得正确的索引
        Bill bill = billList.get(toPayNum - 1);
        // 判断该订单是否已支付
        if (bill.getStatus() == 1) {
            System.out.println("该订单已支付");
            return;
        }
        // 支付
        boolean result = billService.payBillById(bill.getId());
        if (result) {
            System.out.println("支付成功");
            // 修改餐桌状态、预订人姓名、电话
            diningTableService.initDiningTableByIdl(bill.getDiningTableId());
        } else System.out.println("支付失败");
    }

    /**
     * 多表查询
     */
    @Test
    public void test() {
        List<MultiTableBill> multiTableBillsList = billService.getAllMultiTableBills();
        System.out.println("\t序号\t\t\t\t\t\t\t账单号\t\t\t\t\t  用户id\t\t用户名 餐桌id\t  菜品id\t   菜品名\t  菜品数量\t\t  创建时间\t\t\t\t总金额\t\t订单状态");
        int idx = 0;
        for (MultiTableBill mtb : multiTableBillsList) {
            System.out.print("\t" + (++idx));
            System.out.println(mtb);
        }
    }
}
