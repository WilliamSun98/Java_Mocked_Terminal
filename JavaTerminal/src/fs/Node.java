package fs;

/**
 * This class represent a Node, a Node can both be a File or
 * a Directory
 */
public abstract class Node {

  /**
   * This is the name of the Node
   */
  String name;

  /**
   * This is the constructor of the node and it takes a name parameter
   * @param name is the name of the Node
   */
  public Node(String name) {
    this.name = name;
  }

  /**
   * This method returns the name of the node
   * @return the name of the Node
   */
  public String getName() {
    return name;
  }

  /**
   * This method return whether two nodes are equal
   * @param node is the node to compare with this node
   * @return a boolean to represent whether they are equal
   */
  public boolean equals(Node node) {
    return node != null && name.equals(node.getName());
  }
}
