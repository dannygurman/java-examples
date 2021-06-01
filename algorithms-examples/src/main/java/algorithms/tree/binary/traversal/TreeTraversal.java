package algorithms.tree.binary.traversal;

import algorithms.tree.binary.common.model.BinaryTree;
import algorithms.tree.binary.common.utils.BinaryTreeUtils;
import algorithms.tree.binary.common.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TreeTraversal {

    public List <Integer> travereseTree(BinaryTree tree){
        List<Node> orderedNode =  traverseTree( tree);
        return orderedNode.stream()
                .map(n -> Integer.valueOf(n.value))
                .collect(Collectors.toList());
    }



    protected List <Node> traverseTree(BinaryTree tree){
        List <Node> list = new ArrayList();
        traverseTreeInternal(list , tree.root);
        return list;
    }

    protected abstract  void traverseTreeInternal(List <Node> list, Node node);




}
