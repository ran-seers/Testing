import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

/**
 * 游戏面板容器
 */
public class GamePanel extends JPanel implements KeyListener{
    private int[][] map;//当前关卡地图
    private int[][] backupMap;//备份地图
    private Image[] images;//缓存的图片
    private int len = 45;//图片大小
    public int level = 1;//初始化关卡，第一关
    private int boyX,boyY;//小人位置
    private Stack stack = new Stack();//定义一个栈类型的变量，用于保存移动轨迹

    public GamePanel(){
        //初始化默认第一关
        map = new GameMap(level).getRunMap();
        //设置面板的位置和大小 45*10 = 450  125=(700-450)/2
        setBounds(125,60,450,450);
        images = new Image[10];
        for(int i = 0;i < 10;i++){
            images[i] =Toolkit.getDefaultToolkit().getImage("src\\images\\"+ i +".png");
        }
        //添加键盘的监听事件
        addKeyListener(this);
    }
    //执行推箱子游戏
    void doPushBox(int level){
        //根据关卡获取地图
        GameMap levelMap = new GameMap(level);
        map = levelMap.getRunMap();
        boyX = levelMap.getBoyX();
        boyY = levelMap.getBoyY();
        backupMap = new GameMap(level).getRunMap();
        repaint(); //调用绘图函数
    }
    /**
     * 绘图函数
     * @param g  the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics g){
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                if(g==null || images ==null || map==null){
                    System.out.println(g+"----" + images + "----"+map);
                }
                //images[0]
                g.drawImage(images[map[j][i]],i*len,j*len,this);
            }
        }
        //设置关卡信息
        g.setColor(new Color(255, 0, 0,255));
        g.setFont(new Font("楷体",Font.BOLD,20));
        g.drawString("第",185,20);
        g.drawString(String.valueOf(level),225,20);
        g.drawString("层",265,20);

    }

    //按下键盘的时候调用的函数
    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.VK_UP == e.getKeyCode()){
            //向上移动
            moveUp();
        }
        if(KeyEvent.VK_DOWN == e.getKeyCode()){
            //向下移动
            moveDown();
        }
        if(KeyEvent.VK_LEFT == e.getKeyCode()){
            //向左移动
            moveLeft();
        }
        if(KeyEvent.VK_RIGHT == e.getKeyCode()){
            //向右移动
            moveRight();
        }

        if(win()){
            //如果胜利了，进入下一关，或者通关
            if(level == GameMap.MAX_LEVEL){
                JOptionPane.showMessageDialog(this,"你真棒，已经是最后一关了！！！");
            }else{
                String msg = "恭喜你通过第"+level+"关!\n是否要进入下一关?";
                int choice = JOptionPane.showConfirmDialog(null,msg,"恭喜过关",JOptionPane.YES_NO_OPTION);
                if(choice == 1){
                    System.exit(0);//退出程序
                }else if(choice == 0){
                    level++;
                    doPushBox(level);
                }
                clearStack();
            }
        }
    }
    //向上移动
    /**
     * 分析什么情况下小人不能移动
     * 1、小人上面是墙的时候
     * 2、小人上面是箱子/红箱子 并且其上面 是墙/箱子/红箱子
     */
    private void moveUp() {
        //小人不能移动
        if(map[boyY - 1][boyX] == 1 ||
                ((map[boyY - 1][boyX] == 3 || map[boyY - 1][boyX] == 9) &&
                        (map[boyY - 2][boyX] == 1 || map[boyY - 2][boyX] == 3 || map[boyY - 2][boyX] == 9))){
            System.out.println("向上小人无法推动箱子");
            return;
        }
        //如果小人上面是箱子，那么移动时箱子也会向上移动一格
        //如果是红箱子移动时箱子也会向上移动一格
        if(map[boyY - 1][boyX] == 3 || map[boyY - 1][boyX] == 9){
            if(map[boyY - 2][boyX] == 4){
                map[boyY - 2][boyX] = 9; //变为完成入库
            }else {
                map[boyY - 2][boyX] = 3; //箱子向上移动一格
            }
            map[boyY - 1][boyX] = 8;
            map[boyY][boyX] = showFootprint();//这里是移动后的位置设置为路
            repaint();//重绘地图
            boyY--;
            stack.push("up+box");//保存向上有推动箱子移动
        }else{
            map[boyY - 1][boyX] = 8;
            map[boyY][boyX] = showFootprint();//这里是移动后的位置设置为路
            repaint();//重绘地图
            boyY--;
            stack.push("up");//保存向上没有推动箱子移动
        }
    }
    //向下移动
    private void moveDown() {
        //小人不能移动
        if(map[boyY + 1][boyX] == 1 ||
                ((map[boyY + 1][boyX] == 3 || map[boyY + 1][boyX] == 9) &&
                        (map[boyY + 2][boyX] == 1 || map[boyY + 2][boyX] == 3 || map[boyY + 2][boyX] == 9))){
            System.out.println("向下小人无法推动箱子");
            return;
        }
        if(map[boyY + 1][boyX] == 3 || map[boyY + 1][boyX] == 9){

            if(map[boyY + 2][boyX] == 4){
                map[boyY + 2][boyX] = 9;
            }else {
                map[boyY + 2][boyX] = 3;
            }
            map[boyY + 1][boyX] = 5;
            map[boyY][boyX] = showFootprint();
            repaint();
            boyY++;
            stack.push("down+box");
        }else{
            map[boyY + 1][boyX] = 5;
            map[boyY][boyX] = showFootprint();
            repaint();
            boyY++;
            stack.push("down");
        }

    }
    //向左移动
    private void moveLeft() {
        //小人不能移动
        if(map[boyY][boyX - 1] == 1 ||
                ((map[boyY][boyX - 1] == 3 || map[boyY][boyX - 1] == 9) &&
                        (map[boyY][boyX - 2] == 1 || map[boyY][boyX - 2] == 3 || map[boyY][boyX - 2] == 9))){
            System.out.println("向左小人无法推动箱子");
            return;
        }

        if(map[boyY][boyX - 1] == 3 || map[boyY][boyX - 1] == 9){
            if(map[boyY][boyX - 2] == 4){
                map[boyY][boyX - 2] = 9;
            }else {
                map[boyY][boyX - 2] = 3;
            }
            map[boyY][boyX - 1] = 6;
            map[boyY][boyX] = showFootprint();//这里是移动后的位置设置为路
            repaint();//重绘地图
            boyX--;
            stack.push("left+box");
        }else{
            map[boyY][boyX - 1] = 6;
            map[boyY][boyX] = showFootprint();//这里是移动后的位置设置为路
            repaint();//重绘地图
            boyX--;
            stack.push("left");
        }

    }
    //向右移动
    private void moveRight() {
        //小人不能移动
        if(map[boyY][boyX + 1] == 1 ||
                ((map[boyY][boyX + 1] == 3 || map[boyY][boyX + 1] == 9) &&
                        (map[boyY][boyX + 2] == 1 || map[boyY][boyX + 2] == 3 || map[boyY][boyX + 2] == 9))){
            System.out.println("向左小人无法推动箱子");
            return;
        }
        if(map[boyY][boyX + 1] == 3 || map[boyY][boyX + 1] == 9){

            if(map[boyY][boyX + 2] == 4){
                map[boyY][boyX + 2] = 9;
            }else {
                map[boyY][boyX + 2] = 3;
            }
            map[boyY][boyX + 1] = 7;
            map[boyY][boyX] = showFootprint();//这里是移动后的位置设置为路
            repaint();//重绘地图
            boyX++;
            stack.push("right+box");
        }else{
            map[boyY][boyX + 1] = 7;
            map[boyY][boyX] = showFootprint();//这里是移动后的位置设置为路
            repaint();//重绘地图
            boyX++;
            stack.push("right");
        }

    }

    /**
     * 判断是否获胜
     * 地图上还有灰箱子 或者 箱子表示没有胜利，否则胜利
     * true表示胜利  false表示没有胜利
     */
    boolean win(){
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                if(map[i][j] == 4 || map[i][j] == 3){
                    return false; //表示没有胜利
                }
            }
        }
        return true;
    };

    //显示足迹
    private int showFootprint(){
        int result = 0;
        //如果原来是路、小人、箱子，那么移动之后变成路
        if(backupMap[boyY][boyX] == 2 || backupMap[boyY][boyX] == 5
                || backupMap[boyY][boyX] == 3){
            result = 2;
            //如果原来是灰箱子或者红箱子，那么移动之后变成灰箱子
        }else if(backupMap[boyY][boyX] == 4 || backupMap[boyY][boyX] == 9){
            result = 4;
        }
        return result;
    };

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public Stack getStack() {
        return stack;
    }

    /**
     * 悔一步功能分析：
     * 1、我们需要把小人移动步骤记录下来，当我们悔一步的时候，需要把悔了的这一步记录去掉
     * 2、地图需要跟随变化
     * 我们需要把步骤记录到栈中进行保存（先进后出）
     */
    //悔向上移动的一步
    public void backUp(String step){
        if(step.endsWith("box")){
            //前面的如果原来是小人那么我们要设置成路
            if (backupMap[boyY - 1][boyX] == 5){
                map[boyY - 1][boyX] = 2;
            }else{
                //前面的还原为原来的
                map[boyY - 1][boyX] = backupMap[boyY - 1][boyX];
            }
            //如果原来是灰箱子，那么变成红箱子
            if(backupMap[boyY][boyX] == 4){
                map[boyY][boyX] = 9;
            }else {
                //其它情况还原成箱子
                map[boyY][boyX] = 3;
            }
        }else{
            if(stack.size()>0 && backupMap[boyY][boyX] == 5){
                //如果移动位置了，但是再小人位置上，那么小人位置还是要还原成路
                map[boyY][boyX] = 2;
            }else if (stack.size()>0 && backupMap[boyY][boyX] == 3){
                map[boyY][boyX] = 2;
            }else{
                map[boyY][boyX] = backupMap[boyY][boyX];//直接还原
            }
        }
        //后面的设置为小人
        map[boyY + 1][boyX] = stack.size() == 0 ? 5 : 8;
        repaint();
        boyY++;
    }
    //悔向下移动的一步
    public void backDown(String step){
        if(step.endsWith("box")){
            //前面的如果原来是小人那么我们要设置成路
            if (backupMap[boyY + 1][boyX] == 5){
                map[boyY + 1][boyX] = 2;
            }else{
                //前面的还原为原来的
                map[boyY + 1][boyX] = backupMap[boyY + 1][boyX];
            }
            //如果原来是灰箱子，那么变成红箱子
            if(backupMap[boyY][boyX] == 4){
                map[boyY][boyX] = 9;
            }else {
                //其它情况还原成箱子
                map[boyY][boyX] = 3;
            }
        }else{
            if(stack.size()>0 && backupMap[boyY][boyX] == 5){
                //如果移动位置了，但是再小人位置上，那么小人位置还是要还原成路
                map[boyY][boyX] = 2;
            }else if (stack.size()>0 && backupMap[boyY][boyX] == 3){
                map[boyY][boyX] = 2;
            }else{
                map[boyY][boyX] = backupMap[boyY][boyX];//直接还原
            }
        }
        //后面的设置为小人
        map[boyY - 1][boyX] = stack.size() == 0 ? 5 : 5;
        repaint();
        boyY--;
    }
    //悔向左移动的一步
    public void backLeft(String step){
        if(step.endsWith("box")){
            //前面的如果原来是小人那么我们要设置成路
            if (backupMap[boyY][boyX - 1] == 5){
                map[boyY][boyX - 1] = 2;
            }else{
                //前面的还原为原来的
                map[boyY][boyX - 1] = backupMap[boyY][boyX - 1];
            }
            //如果原来是灰箱子，那么变成红箱子
            if(backupMap[boyY][boyX] == 4){
                map[boyY][boyX] = 9;
            }else {
                //其它情况还原成箱子
                map[boyY][boyX] = 3;
            }
        }else{
            if(stack.size()>0 && backupMap[boyY][boyX] == 5){
                //如果移动位置了，但是再小人位置上，那么小人位置还是要还原成路
                map[boyY][boyX] = 2;
            }else if (stack.size()>0 && backupMap[boyY][boyX] == 3){
                map[boyY][boyX] = 2;
            }else{
                map[boyY][boyX] = backupMap[boyY][boyX];//直接还原
            }
        }
        //后面的设置为小人
        map[boyY][boyX+ 1] = stack.size() == 0 ? 5 : 6;
        repaint();
        boyX++;

    }
    //悔向右移动的一步
    public void backRight(String step){
        if(step.endsWith("box")){
            //前面的如果原来是小人那么我们要设置成路
            if (backupMap[boyY][boyX + 1] == 5){
                map[boyY][boyX + 1] = 2;
            }else{
                //前面的还原为原来的
                map[boyY][boyX + 1] = backupMap[boyY][boyX + 1];
            }
            //如果原来是灰箱子，那么变成红箱子
            if(backupMap[boyY][boyX] == 4){
                map[boyY][boyX] = 9;
            }else {
                //其它情况还原成箱子
                map[boyY][boyX] = 3;
            }
        }else{
            if(stack.size()>0 && backupMap[boyY][boyX] == 5){
                //如果移动位置了，但是再小人位置上，那么小人位置还是要还原成路
                map[boyY][boyX] = 2;
            }else if (stack.size()>0 && backupMap[boyY][boyX] == 3){
                map[boyY][boyX] = 2;
            }else{
                map[boyY][boyX] = backupMap[boyY][boyX];//直接还原
            }
        }
        //后面的设置为小人
        map[boyY][boyX - 1] = stack.size() == 0 ? 5 : 7;
        repaint();
        boyX--;

    }

    //清空栈的内容（清空悔一步 记录的步骤数据）
    public void clearStack(){
        this.stack.removeAllElements();
    }

    //敲击键盘的时候调用
    @Override
    public void keyTyped(KeyEvent e) {

    }
    //松开键盘的时候调用
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
