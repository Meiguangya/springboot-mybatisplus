package javaee;

//假设菜单树形数据在MySQL中是以记录pid(父级菜单主键)形式存储, 请实现getMenuTree方法。

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class MenuTree {
    private int id; // 主键，菜单id
    private String name; // 菜单名称
    private int pid; // 菜单父id，根节点pid=0
    private List<MenuTree> children; // 下级菜单

    public MenuTree(int id,String name,int pid){
        this.id = id;
        this.name = name;
        this.pid = pid;
    }

    public static MenuTree getMenuTree(List<MenuTree> menuList) {
        MenuTree rootMenu = null;
        java.util.Map<Integer, List<MenuTree>> map = new HashMap<>();
        menuList.forEach(m -> {
            if (map.containsKey(m.getPid())) {
                map.get(m.getPid()).add(m);
            } else {
                List<MenuTree> list = new ArrayList<>();
                list.add(m);
                map.put(m.getPid(), list);
            }
        });

        for (int i = 0; i < menuList.size(); i++) {
            MenuTree menuTree = menuList.get(i);
            if(0 == menuTree.getPid()){
                rootMenu = menuTree;
            }
            menuTree.setChildren(map.get(menuTree.getId()));
        }

        return rootMenu;
    }


    /**
     * 测试数据
     * 主键  菜单名称  菜单父ID
     * 1    主菜单     0
     * 2    用户管理   1
     * 3    商品管理   1
     * 4    密码管理   2
     * 5    用户名管理 2
     * 6    订单管理   3
     */

    public static void main(String[] args) {
        MenuTree m1 = new MenuTree(1,"主菜单",0);
        MenuTree m2 = new MenuTree(2,"用户管理",1);
        MenuTree m3 = new MenuTree(3,"商品管理",1);
        MenuTree m4 = new MenuTree(4,"密码管理",2);
        MenuTree m5 = new MenuTree(5,"用户名管理",2);
        MenuTree m6 = new MenuTree(6,"订单管理",3);

        List<MenuTree> list = new ArrayList<>();
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        list.add(m5);
        list.add(m6);

        MenuTree root = getMenuTree(list);
        System.out.println(root);
    }
}
