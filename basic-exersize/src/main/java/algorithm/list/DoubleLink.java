package algorithm.list;

public class DoubleLink<T> {
    private class Node<T>{

        private T value;
        /**
         * 前一个节点
         */
        private Node<T> prev;
        /**
         * 后一个节点
         */
        private Node<T> prex;

        public Node(T value,Node<T> prev,Node<T> prex){
            this.value = value;
            this.prev = prev;
            this.prex = prex;
        }

    }


    /**
     * 链表长度
     */
    private int size ;
    /**
     * 头节点
     */
    private Node<T> head;
}
