package algorithms.tree.binary.traversal;

import algorithms.tree.binary.BinaryTree;
import algorithms.tree.binary.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class TraversalExample {


    protected void testTreeTraverese(){
        BinaryTree tree = BinaryTree.buildExampleBinaryTree();
        List<Node> orderedNode =  traverseTree( tree);
        orderedNode.stream().map(n -> n.value).forEach(System.out::println);
    }



    protected  List traverseTree(BinaryTree tree){
        List <Node> list = new ArrayList();
        traverseTreeInternal(list , tree.root);
        return list;
    }

    protected abstract  void traverseTreeInternal(List <Node> list, Node node);




}
