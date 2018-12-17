package java8;


import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhaixt on 2018/4/22.
 *  作者：kexue
 链接：https://www.jianshu.com/p/9101b2ef96d8
 來源：简书
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LambdaTest {
    public static void main(String[] args){
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1, 10, Transaction.Type.GEOCERY));
        transactionList.add(new Transaction(3, 30, Transaction.Type.GEOCERY));
        transactionList.add(new Transaction(6,null, Transaction.Type.GEOCERY));
        transactionList.add(new Transaction(5, 50, Transaction.Type.GEOCERY));
        transactionList.add(new Transaction(2, 20, Transaction.Type.A));
        transactionList.add(new Transaction(4, 40, Transaction.Type.C));
        if(CollectionUtils.isNotEmpty(transactionList)) {
            List<Transaction> ids = Stream.of(transactionList).filter(Objects::nonNull)
                    .flatMap(Collection::stream).collect(Collectors.toList());
            //or
//          List<Transaction> ids = Stream.of(transactionList).filter(Objects::nonNull)
//              .flatMap(childList->childList.stream()).collect(Collectors.toList());
            System.out.println("++ print ids:");
            ids.forEach(e->{
                System.out.print("ids:"+e.getId()+" ");
            });
        }


        // 1. Individual values 单独值
        System.out.println("");
        System.out.println("-- print streams elements:");

        Stream stream = Stream.of("a1", "b1", "c1");
        stream.forEach(System.out::print);
        // 2. Arrays 数组
        System.out.println("");
        System.out.println("== print streams arrays:");
        String[] strArray = new String[] {"a2", "b2", "c2"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        System.out.println(stream.collect(Collectors.joining(",")).toString());//打印 a2,b2,c2

        // 3. Collections 集合
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        // JDK 8 如果发现type为grocery的所有交易, 然后返回以交易值降序排序的交易ID集合
        List<Integer> idList = transactionList.stream()
                .filter(t->t.getType().equals(Transaction.Type.GEOCERY))
                .sorted(Comparator.comparing(Transaction::getId).reversed()).
                map(Transaction::getId).collect(Collectors.toList());
        System.out.println("idList:"+idList.toString());

        Transaction maxTransaction = transactionList.stream()
                .filter(t -> t.getValue() != null)
                .max(Comparator.comparing(Transaction::getValue))
                .orElse(null);

        System.out.println("-- flatmap: --");
        //flatmap
        Stream<List<Integer>> inputStream =
                Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        Stream<Integer> outputStream = inputStream.flatMap(childList -> childList.stream());
//        System.out.println("flatmap list1:"+outputStream.collect(Collectors.toList()).toString());// [1, 2, 3, 4, 5, 6]
        //or
        List<Integer> flatmaplist = outputStream.collect(Collectors.toList());
        System.out.println("flatmap list2:"+flatmaplist.toString());


        //map_reduce
        int result = 0;
        /*
        * 可以看到reduce方法接受一个函数，这个函数有两个参数.
        * 第一个参数是上次函数执行的返回值（也称为中间结果），第二个参数是stream中的元素，
        * 这个函数把这两个值相加，得到的和会被赋值给下次执行这个函数的第一个参数。
        * 第一次执行的时候第一个参数的值是Stream的第一个元素，第二个参数是Stream的第二个元素**。
        * */
        result = transactionList.stream()
                .map(t -> t.getId()).reduce(0, (sum,item)->sum+item);
        System.out.println("mapreduce result:"+result);

        Stream<Transaction>  testStream = transactionList.stream().filter(t->t.getType().equals(Transaction.Type.B));

        int res =  (int) testStream.count();
        // Function<T, R> -T作为输入，返回的R作为输出

        Function<String,String> function = (x) -> {System.out.print(x+": ");return "Function";};

        System.out.println(function.apply("hello world"));



        Function<Integer,Boolean> isAdult = a -> a>=18;

        /*
        * List合并
        * */


    }
}
