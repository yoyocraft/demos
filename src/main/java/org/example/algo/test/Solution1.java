package org.example.algo.test;

import java.util.*;


public class Solution1 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 将左表（旧表）和右表（新表）进行差异比对，返回两张表的新增/修改/删除行的信息。 入参包含4个，分别对应左表（旧表）和右表（新表）的主键列和值列，第1个参数是左表的主键列表，第2个参数是左表的值列表，第3个参数是右表的主键列表，第4个参数是右表的值列表。 出参是个双层的列表，内部包含了3个列表，第1个列表是新增行id列表，第2个列表是修改行id列表，第3个列表是删除行id列表，这3个列表均按id升序排列。
     * @param leftIds long长整型ArrayList 左表的id列表，递增
     * @param leftValues string字符串ArrayList 左表的值列表，参数2和参数1列表长度相同，其元素是一一对应关系
     * @param rightIds long长整型ArrayList 右表的id列表，递增
     * @param rightValues string字符串ArrayList 右表的值列表，参数4和参数3列表长度相同，其元素是一一对应关系
     * @return long长整型ArrayList<ArrayList <>>
     */
    public ArrayList<ArrayList<Long>> diff(ArrayList<Long> leftIds, ArrayList<String> leftValues, ArrayList<Long> rightIds, ArrayList<String> rightValues) {
        // write code here
        ArrayList<ArrayList<Long>> ans = new ArrayList<>();
        Set<Long> leftIdSet = new HashSet<>(leftIds);
        Set<Long> rightIdSet = new HashSet<>(rightIds);
        // 新增的
        ArrayList<Long> newIds = new ArrayList<>();
        for (Long id : rightIdSet) {
            if (leftIdSet.contains(id)) {
                continue;
            }
            // right 比 left 多的
            newIds.add(id);
        }
        // 删除的
        ArrayList<Long> deleteIds = new ArrayList<>();
        for (Long id : leftIdSet) {
            if (rightIdSet.contains(id)) {
                continue;
            }
            deleteIds.add(id);
        }

        // 更新的
        ArrayList<Long> updateIds = new ArrayList<>();
        for (Long id : rightIdSet) {
            if (!leftIdSet.contains(id)) {
                continue;
            }
            int idx = Math.toIntExact(id) - 1;
            String leftVal = leftValues.get(idx);
            String rightVal = rightValues.get(idx);
            if (leftVal != null && leftVal.equals(rightVal)) {
                continue;
            }
            updateIds.add(id);
        }
        ans.add(newIds);
        ans.add(updateIds);
        ans.add(deleteIds);
        return ans;
    }
}