////游戏面板容器
//
////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.Toolkit;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.io.PrintStream;
//import java.util.Stack;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//public class GamePanel extends JPanel implements KeyListener {
//    private int[][] map;
//    private int[][] backupMap;
//    private Image[] images;
//    private int len = 45;
//    public int level = 1;
//    private int boyX;
//    private int boyY;
//    private Stack stack = new Stack();
//
//    public GamePanel() {
//        this.map = (new GameMap(this.level)).getRunMap();
//        this.setBounds(125, 60, 450, 450);
//        this.images = new Image[10];
//
//        for(int i = 0; i < 10; ++i) {
//            this.images[i] = Toolkit.getDefaultToolkit().getImage("src\\images\\" + i + ".png");
//        }
//
//        this.addKeyListener(this);
//    }
//
//    void doPushBox(int level) {
//        GameMap levelMap = new GameMap(level);
//        this.map = levelMap.getRunMap();
//        this.boyX = levelMap.getBoyX();
//        this.boyY = levelMap.getBoyY();
//        this.backupMap = (new GameMap(level)).getRunMap();
//        this.repaint();
//    }
//
//    public void paint(Graphics g) {
//        for(int i = 0; i < 10; ++i) {
//            for(int j = 0; j < 10; ++j) {
//                if (g == null || this.images == null || this.map == null) {
//                    PrintStream var10000 = System.out;
//                    String var10001 = String.valueOf(g);
//                    var10000.println(var10001 + "----" + String.valueOf(this.images) + "----" + String.valueOf(this.map));
//                }
//
//                g.drawImage(this.images[this.map[j][i]], i * this.len, j * this.len, this);
//            }
//        }
//
//        g.setColor(new Color(255, 0, 0, 255));
//        g.setFont(new Font("楷体", 1, 20));
//        g.drawString("第", 185, 20);
//        g.drawString(String.valueOf(this.level), 225, 20);
//        g.drawString("层", 265, 20);
//    }
//
//    public void keyPressed(KeyEvent e) {
//        if (38 == e.getKeyCode()) {
//            this.moveUp();
//        }
//
//        if (40 == e.getKeyCode()) {
//            this.moveDown();
//        }
//
//        if (37 == e.getKeyCode()) {
//            this.moveLeft();
//        }
//
//        if (39 == e.getKeyCode()) {
//            this.moveRight();
//        }
//
//        if (this.win()) {
//            if (this.level == GameMap.MAX_LEVEL) {
//                JOptionPane.showMessageDialog(this, "你真棒，已经是最后一关了！！！");
//            } else {
//                String msg = "恭喜你通过第" + this.level + "关!\n是否要进入下一关?";
//                int choice = JOptionPane.showConfirmDialog((Component)null, msg, "恭喜过关", 0);
//                if (choice == 1) {
//                    System.exit(0);
//                } else if (choice == 0) {
//                    ++this.level;
//                    this.doPushBox(this.level);
//                }
//            }
//        }
//
//    }
//
//    private void moveUp() {
//        if (this.map[this.boyY - 1][this.boyX] != 1 && (this.map[this.boyY - 1][this.boyX] != 3 && this.map[this.boyY - 1][this.boyX] != 9 || this.map[this.boyY - 2][this.boyX] != 1 && this.map[this.boyY - 2][this.boyX] != 3 && this.map[this.boyY - 2][this.boyX] != 9)) {
//            if (this.map[this.boyY - 1][this.boyX] == 3) {
//                if (this.map[this.boyY - 2][this.boyX] == 4) {
//                    this.map[this.boyY - 2][this.boyX] = 9;
//                } else {
//                    this.map[this.boyY - 2][this.boyX] = 3;
//                }
//
//                this.map[this.boyY - 1][this.boyX] = 8;
//                this.map[this.boyY][this.boyX] = 2;
//                this.repaint();
//                --this.boyY;
//            } else {
//                this.map[this.boyY - 1][this.boyX] = 8;
//                this.map[this.boyY][this.boyX] = 2;
//                this.repaint();
//                --this.boyY;
//            }
//
//        } else {
//            System.out.println("向上小人无法推动箱子");
//        }
//    }
//
//    private void moveDown() {
//        if (this.map[this.boyY + 1][this.boyX] != 1 && (this.map[this.boyY + 1][this.boyX] != 3 && this.map[this.boyY + 1][this.boyX] != 9 || this.map[this.boyY + 2][this.boyX] != 1 && this.map[this.boyY + 2][this.boyX] != 3 && this.map[this.boyY + 2][this.boyX] != 9)) {
//            if (this.map[this.boyY + 1][this.boyX] == 3) {
//                if (this.map[this.boyY + 2][this.boyX] == 4) {
//                    this.map[this.boyY + 2][this.boyX] = 9;
//                } else {
//                    this.map[this.boyY + 2][this.boyX] = 3;
//                }
//
//                this.map[this.boyY + 1][this.boyX] = 5;
//                this.map[this.boyY][this.boyX] = 2;
//                this.repaint();
//                ++this.boyY;
//            } else {
//                this.map[this.boyY + 1][this.boyX] = 5;
//                this.map[this.boyY][this.boyX] = 2;
//                this.repaint();
//                ++this.boyY;
//            }
//
//        } else {
//            System.out.println("向下小人无法推动箱子");
//        }
//    }
//
//    private void moveLeft() {
//        if (this.map[this.boyY][this.boyX - 1] != 1 && (this.map[this.boyY][this.boyX - 1] != 3 && this.map[this.boyY][this.boyX - 1] != 9 || this.map[this.boyY][this.boyX - 2] != 1 && this.map[this.boyY][this.boyX - 2] != 3 && this.map[this.boyY][this.boyX - 2] != 9)) {
//            if (this.map[this.boyY][this.boyX - 1] == 3) {
//                if (this.map[this.boyY][this.boyX - 2] == 4) {
//                    this.map[this.boyY][this.boyX - 2] = 9;
//                } else {
//                    this.map[this.boyY][this.boyX - 2] = 3;
//                }
//
//                this.map[this.boyY][this.boyX - 1] = 6;
//                this.map[this.boyY][this.boyX] = 2;
//                this.repaint();
//                --this.boyX;
//            } else {
//                this.map[this.boyY][this.boyX - 1] = 6;
//                this.map[this.boyY][this.boyX] = 2;
//                this.repaint();
//                --this.boyX;
//            }
//
//        } else {
//            System.out.println("向左小人无法推动箱子");
//        }
//    }
//
//    private void moveRight() {
//        if (this.map[this.boyY][this.boyX + 1] != 1 && (this.map[this.boyY][this.boyX + 1] != 3 && this.map[this.boyY][this.boyX + 1] != 9 || this.map[this.boyY][this.boyX + 2] != 1 && this.map[this.boyY][this.boyX + 2] != 3 && this.map[this.boyY][this.boyX + 2] != 9)) {
//            if (this.map[this.boyY][this.boyX + 1] == 3) {
//                if (this.map[this.boyY][this.boyX + 2] == 4) {
//                    this.map[this.boyY][this.boyX + 2] = 9;
//                } else {
//                    this.map[this.boyY][this.boyX + 2] = 3;
//                }
//
//                this.map[this.boyY][this.boyX + 1] = 7;
//                this.map[this.boyY][this.boyX] = 2;
//                this.repaint();
//                ++this.boyX;
//            } else {
//                this.map[this.boyY][this.boyX + 1] = 7;
//                this.map[this.boyY][this.boyX] = 2;
//                this.repaint();
//                ++this.boyX;
//            }
//
//        } else {
//            System.out.println("向左小人无法推动箱子");
//        }
//    }
//
//    boolean win() {
//        for(int i = 0; i < 10; ++i) {
//            for(int j = 0; j < 10; ++j) {
//                if (this.map[i][j] == 4 || this.map[i][j] == 3) {
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }
//
//    public void keyTyped(KeyEvent e) {
//    }
//
//    public void keyReleased(KeyEvent e) {
//    }
//}
