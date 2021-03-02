package algorithm.list;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        System.out.println("Hello World!");
        Node node1 = new Node("a");
        Node node2 = new Node("b");
        node1.setNext(node2);
        Node node3 = new Node("c");
        node2.setNext(node3);



        Node node11 = new Node("e");
        Node node12 = new Node("b");
        node11.setNext(node12);
        Node node13 = new Node("f");
        node12.setNext(node13);


        getFirstNode(node1,node11);
    }

    private static Node getFirstNode(Node temp1,Node temp2){
        Integer temp1Index = 0;
        Map<String,Integer> map = new HashMap<>();
        while(temp1.getNext() != null){
            if(!map.containsKey(temp1.data)){
                map.put(temp1.data,temp1Index);
            }
            temp1 = temp1.getNext();
            temp1Index ++;
        }
        while(temp2.getNext() != null){
            if(map.containsKey(temp2.data)){
                System.out.println(temp2.data);
                return temp2;
            }
        }
        return null;
    }
}

class Node{
    String data;
    Node next;
    Node(String data){
        this.data = data;
    }
    public void setData(String data){
        this.data = data;
    }
    public String getData(){
        return this.data;
    }
    public void setNext(Node next){
        this.next = next;
    }
    public Node getNext(){
        return this.next;
    }
}
