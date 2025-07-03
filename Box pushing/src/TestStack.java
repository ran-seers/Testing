import javax.swing.*;

//public class TestStack {
//    public static void main(String[] args){
//        //创建一个JFrame窗口
//    JFrame frame = new JFrame("简单的wing示例");
//
//    //窗口默认关闭操作
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    //设置窗口大小
//    frame.setSize(500,600);
//
//    //创建一个JLable组件
//    JLabel lable = new JLabel("你好，SIEWING!",SwingConstants.CENTER);
//
//    //将JLable 添加到窗口的内容面板
//    frame.getContentPane().add(lable);
//
//    //设置窗口可见
//    frame.setVisible(true);
//    }
//}

//import java.util.HashMap;
//import java.util.Map;
//
//public class TestStack{
//    public static void main(String[] args){
//        //创建一个 HashMap 来存储键值对
//        Map<String,String>map = new HashMap<>();
//
//        //向map里添加一些条目
//        map.put("key1","value1");
//        map.put("key2","value2");
//
//        //根据键来检索并打印值
//        System.out.println(map.get("key1"));
//        System.out.println(map.get("key2"));
//    }
//}

//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//
//public class LabelInFrame {
//    public static void main(String[] args) {
//        // 创建一个窗口（JFrame）
//        JFrame frame = new JFrame("JFrame 中的 JLabel 示例");
//
//        // 创建一个 JLabel 标签
//        JLabel label = new JLabel("你好，这是一个 JLabel 标签！");
//
//        // 设置窗口大小
//        frame.setSize(400, 200);
//
//        // 添加标签到窗口
//        frame.add(label);
//
//        // 设置关闭操作（点击关闭按钮时退出程序）
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // 设置窗口可见
//        frame.setVisible(true);
//    }
//}
//
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//
//public class ButtonInFrame {
//    public static void main(String[] args) {
//        // 创建窗口
//        JFrame frame = new JFrame("按钮示例");
//
//        // 创建按钮组件
//        JButton button = new JButton("点击我");
//
//        // 设置按钮位置和大小（如果使用 null 布局）
//        frame.setLayout(null); // 关闭默认布局，允许手动定位
//        button.setBounds(130, 80, 100, 40); // x, y, width, height
//
//        // 将按钮添加到窗口中
//        frame.add(button);
//
//        // 设置窗口基本属性
//        frame.setSize(400, 300);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
//}
