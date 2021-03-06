package algorithm.sort;

/**
 * @author zhaixt
 * @date 2020/12/12 23:32
 * 冒泡排序
 */
public class BubbleSort {
    private static int[] bubbleSort(int[] arr){
        for (int i =0;i<arr.length-1;i++) {
            for(int j = 0;j<arr.length-1-i;j++){
                if(arr[j] > arr[j+1]){
                    int temp;
                    temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,6,9,4,2,6,5,1,8,3,7};
        int[] res = bubbleSort(arr);
        System.out.println(res.toString());
    }
}
