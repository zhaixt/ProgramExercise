package algorithm.tree;

/**
 * Created by zhaixt on 2018/12/18.
 */
public class BinaryTree {
    /**
     * @author yaobo
     * 二叉树的先序中序后序排序
     */
    public Node init() {//注意必须逆序建立，先建立子节点，再逆序往上建立，因为非叶子结点会使用到下面的节点，而初始化是按顺序初始化的，不逆序建立会报错
        Node J = new Node(8, null, null);
        Node H = new Node(4, null, null);
        Node G = new Node(2, null, null);
        Node F = new Node(7, null, J);
        Node E = new Node(5, H, null);
        Node D = new Node(1, null, G);
        Node C = new Node(9, F, null);
        Node B = new Node(3, D, E);
        Node A = new Node(6, B, C);
        return A;   //返回根节点
    }

    public void printNode(Node node) {
        System.out.println(node.getData());
    }

    public void theFirstTraversal(Node root) {  //先序遍历

        printNode(root);
        if(root.getLeftNode() != null) {    //使用递归进行遍历左孩子
            theFirstTraversal(root.getLeftNode());
        }
        if(root.getRightNode() != null) {    ///使用递归进行遍历又孩子
            theFirstTraversal(root.getRightNode());
        }

    }
    public void theInOrderTraversal(Node root) {  //中序遍历
        if(root.getLeftNode() != null){
            theInOrderTraversal(root.getLeftNode());
        }
        printNode(root);
        if(root.getRightNode() != null){
            theInOrderTraversal(root.getRightNode());
        }
    }
    public void thePostOrderTraversal(Node root){//后序遍历
        if(root.getLeftNode() != null){
            thePostOrderTraversal(root.getLeftNode());
        }
        if(root.getRightNode() != null){
            thePostOrderTraversal(root.getRightNode());
        }
        printNode(root);
    }
    public int getHeight(Node node){ //获取树高度
        if(node == null){
            return 0;
        }
        int leftheight = getHeight(node.getLeftNode());
        int rightHeight = getHeight(node.getRightNode());
        return (leftheight<rightHeight)?rightHeight+1:leftheight+1;
    }
    public int getNum(Node node){ //获取二叉树数目
        if(node == null){
            return 0;
        }
        return  getNum(node.getLeftNode())+getNum(node.getRightNode())+1;//1就是root节点
    }
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Node root = tree.init();
        System.out.println("先序遍历");
        tree.theFirstTraversal(root);
        System.out.println("");
        System.out.println("中序遍历");
        tree.theInOrderTraversal(root);
        System.out.println("");
        System.out.println("后序遍历");
        tree.thePostOrderTraversal(root);
        System.out.println("树高度1:");
        int height1 = tree.getHeight(root);
        System.out.println(height1);
        System.out.println("左子树高度2:");
        int height2 = tree.getHeight(root.getLeftNode());
        System.out.println(height2);

        System.out.println("树数目1:");
        int num = tree.getNum(root);
        System.out.println(num);
        System.out.println("左子树数目2:");
        int num2 = tree.getNum(root.getLeftNode());
        System.out.println(num2);
    }
}
