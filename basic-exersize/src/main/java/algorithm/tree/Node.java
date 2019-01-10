package algorithm.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by zhaixt on 2018/12/18.
 */
@Data
@AllArgsConstructor
public class Node {
    private int data;
    private Node leftNode;
    private Node rightNode;
}
