//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class GameFrame extends JFrame implements ActionListener{
//    //开发按钮功能
//    JButton back = new JButton("悔一步");
//    JButton reopen = new JButton("重玩");
//    JButton last = new JButton("上一关");
//    JButton next = new JButton("下一关");
//    GamePanel panel = new GamePanel();
//
//    public GameFrame(){
//        super("推箱子游戏");//设置窗口的标题
//        setSize(700,700);//设置窗口的大小
//        setVisible(true);//窗口是否显示，显示
//        setResizable(false);//设置窗口是否能够调整大小，不能调整
//        setLocation(600,150);//设置窗口再桌面的位置
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭窗口按钮
//
//        //获取内容容器面板
//        Container cont = getContentPane();
//        cont.setLayout(null);//设置样式,不设置
//
//        //添加组件到容器里面
//        add(reopen);
//        add(back);
//        add(last);
//        add(next);
//        //设置按钮组件再窗口里面的位置
//        //窗口大小700*700 这里设置按钮位置150*620 按钮大小设置为80*30
//        reopen.setBounds(150,620,80,30);
//        //设置按钮组件的事件，需要GameFrame实现actionListener接口
//        reopen.addActionListener(this);
//
//        back.setBounds(250,620,80,30);
//        back.addActionListener(this);
//
//        last.setBounds(350,620,80,30);
//        last.addActionListener(this);
//
//        next.setBounds(450,620,80,30);
//        next.addActionListener(this);
//
//        add(panel);
//        panel.doPushBox(panel.level);
//        panel.requestFocus();//获取焦点
//        //验证各个组件是否有问题
//        validate();
//    }
//
//    //按钮点击事件
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(back == e.getSource()){ //悔一步操作
//            if(panel.getStack().size() == 0){
//                JOptionPane.showMessageDialog(this,"你还没有移动，不能悔一步！");
//            }else{
//                System.out.println("悔一步");
//                System.out.println("悔一步前的stack的值为"+panel.getStack());
//                //获得上一步，即栈的顶元素，并从栈中移除
//                String lastStep = (String) panel.getStack().pop();
//                if(lastStep.startsWith("up")){
//                    System.out.println("悔向上移动的一步");
//                    panel.backUp(lastStep);
//                }else if(lastStep.startsWith("down")){
//                    System.out.println("悔向下移动的一步");
//                    panel.backDown(lastStep);
//                }else if(lastStep.startsWith("left")){
//                    System.out.println("悔向左移动的一步");
//                    panel.backLeft(lastStep);
//                }else if(lastStep.startsWith("right")){
//                    System.out.println("悔向右移动的一步");
//                    panel.backRight(lastStep);
//                }
//                System.out.println("悔一步后的stack的值为"+panel.getStack());
//            }
//        } else if(last == e.getSource()){
//            System.out.println("点击上一关");
//            panel.level--;
//            if(panel.level < 1){
//                panel.level++;
//                JOptionPane.showMessageDialog(this,"这已经是第一关了！！！");
//                panel.requestFocus();
//            }else{
//                panel.doPushBox(panel.level);
//                panel.requestFocus();
//            }
//
//            panel.clearStack();
//        } else if (next == e.getSource()){
//            System.out.println("点击下一关");
//            panel.level++;
//            if (panel.level > GameMap.MAX_LEVEL){
//                panel.level--;
//                JOptionPane.showMessageDialog(this,"这已经是最后一关了！！！");
//                panel.requestFocus();
//            }else{
//                panel.doPushBox(panel.level);
//                panel.requestFocus();
//            }
//            panel.clearStack();
//        }else if(reopen == e.getSource()){
//            System.out.println("重玩");
//            panel.doPushBox(panel.level);
//            panel.requestFocus();
//            panel.clearStack();
//        }
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener{
    // 开发按钮功能
    JButton back = new JButton("悔一步");
    JButton reopen = new JButton("重玩");
    JButton last = new JButton("上一关");
    JButton next = new JButton("下一关");
    GamePanel panel = new GamePanel();

    public GameFrame(){
        super("推箱子游戏");
        setSize(700, 700);
        setVisible(true);
        setResizable(false);
        setLocation(600, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 获取内容容器面板
        Container cont = getContentPane();
        cont.setLayout(new BorderLayout()); // 使用 BorderLayout 来管理布局

        // 设置按钮面板，并使用 FlowLayout 管理按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 按钮居中
        buttonPanel.add(reopen);
        buttonPanel.add(back);
        buttonPanel.add(last);
        buttonPanel.add(next);

        // 添加按钮面板到窗口的南边
        cont.add(buttonPanel, BorderLayout.SOUTH);

        // 设置按钮的事件监听
        reopen.addActionListener(this);
        back.addActionListener(this);
        last.addActionListener(this);
        next.addActionListener(this);

        // 添加游戏面板到窗口的中心
        cont.add(panel, BorderLayout.CENTER);

        panel.doPushBox(panel.level); // 初始化地图
        panel.requestFocus(); // 获取焦点

        // 验证界面组件是否有问题
        validate();
    }

    // 按钮点击事件
    @Override
    public void actionPerformed(ActionEvent e) {
        if(back == e.getSource()) { // 悔一步操作
            if(panel.getStack().size() == 0) {
                JOptionPane.showMessageDialog(this, "你还没有移动，不能悔一步！");
            } else {
                String lastStep = (String) panel.getStack().pop(); // 获取上一步操作
                undoMove(lastStep);  // 调用统一的撤销移动函数
            }
        } else if(last == e.getSource()) {
            switchLevel(-1);  // 调用切换关卡的函数，向上一关
        } else if (next == e.getSource()) {
            switchLevel(1);   // 调用切换关卡的函数，向下一关
        } else if(reopen == e.getSource()) {
            panel.doPushBox(panel.level); // 重新开始当前关卡
            panel.requestFocus();
            panel.clearStack();
        }
    }

    // 统一撤销移动的函数
    private void undoMove(String lastStep) {
        if(lastStep.startsWith("up")) {
            panel.backUp(lastStep);
        } else if(lastStep.startsWith("down")) {
            panel.backDown(lastStep);
        } else if(lastStep.startsWith("left")) {
            panel.backLeft(lastStep);
        } else if(lastStep.startsWith("right")) {
            panel.backRight(lastStep);
        }
    }

    // 切换关卡的函数，支持上一关和下一关
    private void switchLevel(int direction) {
        panel.level += direction;
        if(panel.level < 1) {
            panel.level = 1; // 如果已经是第一关，不能再往前切换
            JOptionPane.showMessageDialog(this, "这已经是第一关了！");
        } else if(panel.level > GameMap.MAX_LEVEL) {
            panel.level = GameMap.MAX_LEVEL; // 如果已经是最后一关，不能再往后切换
            JOptionPane.showMessageDialog(this, "这已经是最后一关了！");
        } else {
            panel.doPushBox(panel.level);  // 更新关卡
        }
        panel.clearStack();
        panel.requestFocus();
    }
}
