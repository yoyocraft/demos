package org.example.eat;

import java.util.HashMap;
import java.util.Map;

/**
 * 随机生成要吃饭的点（推荐）
 *
 * @author yoyocraft
 * @date 2023/06/22
 */
public class Eat {

    public static final String[] EAT_PLACES = {
            "河二楼",
            "民族餐厅",
            "重庆小面",
            "鸡米饭",
            "沙县小吃",
            "卷饼",
            "海苑水饺",
            "螺蛳粉",
            "披萨店",
            "川味小吃"
    };

    public static void main(String[] args) {
        if (args[0].isEmpty()) {
            System.err.println("start.sh ${num}");
            return;
        }
        int board = EAT_PLACES.length;
        Map<String, Integer> countMap = new HashMap<>();

        int count = Integer.parseInt(args[0]);
        for (int i = 0; i < count; i++) {
            // 随机生成一个下标
            int index = (int) (Math.random() * board);
            countMap.put(EAT_PLACES[index], countMap.getOrDefault(EAT_PLACES[index], 0) + 1);
        }

        String mostSelectedRestaurant = null;
        int maxSelections = 0;

        System.out.println("地点 -- 推荐次数");

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + " -- " + entry.getValue());
            if (entry.getValue() > maxSelections) {
                mostSelectedRestaurant = entry.getKey();
                maxSelections = entry.getValue();
            }
        }

        System.out.println("\n\n======>推荐去吃: " + mostSelectedRestaurant);
    }
}
