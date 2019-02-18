import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataStructure {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hehe");
        list.add("haha");
        list.add("lala");
        System.out.println(list.get(0));
        list.remove(1);
        System.out.println(list.get(1));


//        LinkedHashMap可以认为是HashMap+LinkedList，即它既使用HashMap操作数据结构，又使用LinkedList维护插入元素的先后顺序
        Map<String,String> map = new LinkedHashMap<>();
    }
}
