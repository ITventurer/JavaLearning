package code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(-1);
        list.add(0);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
