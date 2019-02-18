package collections;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
/**
* https://www.cnblogs.com/shaosks/p/8267764.html
 * 优先级队列是不同于先进先出队列的另一种队列。每次从队列中取出的是具有最高优先权的元素。
 * 如果想实现按照自己的意愿进行优先级排列的队列的话，需要实现Comparator接口。
 * 如果不提供Comparator的话，优先队列中元素默认按自然顺序排列，也就是数字默认是小的在队列头，字符串则按字典序排列。
* */
public class PriorityQueueTest2 {
    //二维平面上一个点
    static class point {
        //坐标x
        double x;

        //坐标y
        double y;
        public point(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

     static class PointComparator {
        private   point pointOne;
        private point pointTwo;
        public double distance;
        public PointComparator(point pointOne,point pointTwo)
        {
            this.pointOne = pointOne;
            this.pointTwo = pointTwo;
            computeDistance();
        }
        //计算两点之间距离
        private void computeDistance() {
            double val = Math.pow((this.pointOne.x - this.pointTwo.x),2) +
                    Math.pow((this.pointOne.y - this.pointTwo.y),2);
            this.distance = Math.sqrt(val);
        }


    }

    public static void main(String[] args) {
        Comparator<PointComparator> OrderDistance =  new Comparator<PointComparator>(){
            public int compare(PointComparator one, PointComparator two) {
                if (one.distance < two.distance)
                    return 1;
                else if (one.distance > two.distance)
                    return -1;
                else
                    return 0;
            }
        };

        //定义一个优先队列,用来排序任意两点之间的距离，从大到小排
        Queue<PointComparator> FsQueue = new PriorityQueue<PointComparator>(10,OrderDistance);

        for (int i=0;i<6;i++){

            java.util.Random r= new java.util.Random(10);
            point one =new point(i*2+1,i*3+2);
            point two =new point(i*5+2,i*6+3);
            PointComparator nodecomp = new PointComparator(one,two);

            DecimalFormat df = new DecimalFormat("#.##");
            FsQueue.add(nodecomp);
        }
        DecimalFormat df = new DecimalFormat("#.###");
        for (int i = 0;i<6;i++){
            System.out.println(df.format(FsQueue.poll().distance));
        }
    }

}
