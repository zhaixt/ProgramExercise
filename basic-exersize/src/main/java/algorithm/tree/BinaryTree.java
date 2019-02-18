package algorithm.tree;

/**
 * Created by zhaixt on 2018/12/18.
 */
public class BinaryTree {
    /**
     * @author yaobo
     * 二叉树的先序中序后序排序
     */
    public TreeNode init() {//注意必须逆序建立，先建立子节点，再逆序往上建立，因为非叶子结点会使用到下面的节点，而初始化是按顺序初始化的，不逆序建立会报错
        TreeNode J = new TreeNode(8, null, null);
        TreeNode H = new TreeNode(4, null, null);
        TreeNode G = new TreeNode(2, null, null);
        TreeNode F = new TreeNode(7, null, J);
        TreeNode E = new TreeNode(5, H, null);
        TreeNode D = new TreeNode(1, null, G);
        TreeNode C = new TreeNode(9, F, null);
        TreeNode B = new TreeNode(3, D, E);
        TreeNode A = new TreeNode(6, B, C);
        return A;   //返回根节点
    }

    public void printNode(TreeNode node) {
        System.out.println(node.getData());
    }

    public void theFirstTraversal(TreeNode root) {  //先序遍历

        printNode(root);
        if(root.getLeftNode() != null) {    //使用递归进行遍历左孩子
            theFirstTraversal(root.getLeftNode());
        }
        if(root.getRightNode() != null) {    ///使用递归进行遍历又孩子
            theFirstTraversal(root.getRightNode());
        }

    }
    public void theInOrderTraversal(TreeNode root) {  //中序遍历
        if(root.getLeftNode() != null){
            theInOrderTraversal(root.getLeftNode());
        }
        printNode(root);
        if(root.getRightNode() != null){
            theInOrderTraversal(root.getRightNode());
        }
    }
    public void thePostOrderTraversal(TreeNode root){//后序遍历
        if(root.getLeftNode() != null){
            thePostOrderTraversal(root.getLeftNode());
        }
        if(root.getRightNode() != null){
            thePostOrderTraversal(root.getRightNode());
        }
        printNode(root);
    }
    public int getHeight(TreeNode node){ //获取树高度
        if(node == null){
            return 0;
        }
        int leftheight = getHeight(node.getLeftNode());
        int rightHeight = getHeight(node.getRightNode());
        return (leftheight<rightHeight)?rightHeight+1:leftheight+1;
    }
    public int getNum(TreeNode node){ //获取二叉树数目
        if(node == null){
            return 0;
        }
        return  getNum(node.getLeftNode())+getNum(node.getRightNode())+1;//1就是root节点
    }

    //获取最近祖先节点
    private static TreeNode getNearestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
        //发现目标节点则通过返回值标记该子树发现了某个目标结点
        if(root == null || p.getData() == root.getData() || q.getData() == root.getData()){
            return root;
        }
        //查看左子树中是否有目标结点，没有为null
        TreeNode left = getNearestCommonAncestor(root.getLeftNode(),p,q);
        //查看右子树是否有目标节点，没有为null
        TreeNode right = getNearestCommonAncestor(root.getRightNode(),p,q);
        if(left != null && right != null) {
            return root;
        }
        return left == null ? right:left;

    }

    public static void main(String[] args) {
        TreeNode J = new TreeNode(8, null, null);
        TreeNode H = new TreeNode(4, null, null);
        TreeNode G = new TreeNode(2, null, null);
        TreeNode F = new TreeNode(7, null, J);
        TreeNode E = new TreeNode(5, H, null);
        TreeNode D = new TreeNode(1, null, G);
        TreeNode C = new TreeNode(9, F, null);
        TreeNode B = new TreeNode(3, D, E);
        TreeNode A = new TreeNode(6, B, C);


        BinaryTree tree = new BinaryTree();
        TreeNode root = tree.init();
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

        TreeNode ancestor = getNearestCommonAncestor(A,D,E);
        System.out.println("最近共同祖先:");
        System.out.println(ancestor.getData());




    }
}
