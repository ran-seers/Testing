import java.util.HashMap;
import java.util.Map;

/**
 * 地图
 */
public class GameMap {
    public static int MAX_LEVEL= 2;//初始最大关卡
    private static final String KEY = "key-";//标记关卡
    private int level,boyX,boyY;//关卡，人物的位置
    private int[][] runMap = new int[10][10];//当前呈现的地图
    private Map<String,int[][]> maps;//关卡地图集合

    public GameMap(int level){
        initMap();
        this.level = level;
        runMap = maps.get(KEY + level);
        for(int i = 0; i < runMap.length; i++){
            for(int j = 0; j < runMap[i].length; j++){
                //确定人物的位置（获取人物初始化位置）
                //问题？j与i的位置 j在前与i在前有什么区别或者是否一致
                if(runMap[j][i] == 5){
                    boyX = i;
                    boyY = j;
                }
            }
        }
    }
    //应该再别的类会使用 小人的位置 所以这个需要生成 get方法
    public int getBoyX() {return boyX;}

    public int getBoyY() {return boyY;}

    public int[][] getRunMap() {return runMap;}

    /**
     * 初始化地图
     * 0:背景图片，1：墙，2：路，3：箱子，4：待入库（灰箱子），5：小人下，6：小人左，7：小人右，8：小人上，9：箱子入库完成（红箱子）
     */
    private void initMap() {
        maps = new HashMap<String,int[][]>();
        int[][] map1 = {
                {0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                {1, 1, 1, 2, 5, 2, 1, 0, 0, 0},
                {1, 2, 2, 3, 4, 2, 1, 1, 0, 0},
                {1, 2, 2, 4, 3, 4, 2, 1, 0, 0},
                {1, 1, 1, 2, 2, 3, 2, 1, 0, 0},
                {0, 0, 1, 2, 2, 2, 2, 1, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] map2 = {
                {1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {1, 2, 2, 2, 2, 2, 1, 0, 0, 0},
                {1, 5, 3, 3, 3, 3, 1, 1, 0, 0},
                {1, 1, 1, 4, 4, 4, 4, 1, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] map3 = {
                {0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 1, 4, 4, 1, 0, 0, 0, 0},
                {0, 1, 1, 2, 4, 1, 1, 0, 0, 0},
                {0, 1, 2, 2, 3, 4, 1, 0, 0, 0},
                {1, 1, 2, 3, 2, 2, 1, 1, 0, 0},
                {1, 2, 2, 1, 3, 3, 2, 1, 0, 0},
                {1, 2, 2, 5, 2, 2, 2, 1, 0, 0},
                {1, 2, 2, 2, 2, 2, 2, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] map4 = {
                {1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {1, 2, 4, 2, 4, 2, 1, 0, 0, 0},
                {1, 2, 2, 2, 2, 2, 1, 0, 0, 0},
                {1, 2, 2, 2, 1, 1, 1, 0, 0, 0},
                {1, 1, 1, 3, 3, 5, 1, 0, 0, 0},
                {0, 0, 1, 2, 2, 2, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] map5 = {
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                {1, 2, 2, 2, 1, 0, 0, 0, 0, 0},
                {1, 2, 2, 2, 1, 0, 0, 0, 0, 0},
                {1, 4, 3, 4, 1, 0, 0, 0, 0, 0},
                {1, 3, 4, 3, 1, 0, 0, 0, 0, 0},
                {1, 2, 5, 2, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        maps.put(KEY + 1,map1);
        maps.put(KEY + 2,map2);
        maps.put(KEY + 3,map3);
        maps.put(KEY + 4,map4);
        maps.put(KEY + 5,map5);


        MAX_LEVEL = maps.size();
    }
}
