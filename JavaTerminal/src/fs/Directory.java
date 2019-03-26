package fs;

import java.util.ArrayList;
import java.util.List;

/**
 * This represent Directory
 */
public class Directory extends Node {

  /**
   * This is the parent directory of this directory
   */
  private Directory parent;
  /**
   * This is a list of children for this directory
   */
  private List<Node> children;

  /**
   * This is the constructor of the Directory class which instantiate
   * its name and parent directory
   * @param name is the name of this newly created directory
   * @param parent is the parent directory of this newly created directory
   */
  public Directory(String name, Directory parent) {
    super(name);
    this.parent = parent;
    this.children = new ArrayList<>();
    if (this.parent != null) {
      this.parent.add(this);
    }
  }

  /**
   * This method add a new Node to the children of this directory
   * @param node is the node to add as a child
   */
  public void add(Node node) {
    for (Node nodeInChildren : this.children) {
      if (nodeInChildren.equals(node)) {
        return;
      }
    }
    this.children.add(node);
    if (node instanceof Directory) {
      ((Directory) node).setParent(this);
    }
  }

  /**
   * This method is used to get the name of this directory
   * @return String name of this directory
   */
  public String getName() {
    return this.name;
  }

  /**
   * This method reset the parent directory of this directory
   * @param parent is the new parent directory
   */
  public void setParent(Directory parent) {
    this.parent = parent;
  }

  /**
   * This method returns the parent directory
   * @return Directory parent directory
   */
  public Directory getParent() {
    return parent;
  }


  /**
   * This method delete a child specified by name
   * @param name is the name of the child
   */
  public void delete(String name) {
    for (int i = 0; i < children.size(); i++) {
      if (children.get(i).getName().equals(name)) {
        children.remove(i);
        break;
      }
    }
  }

  /**
   * This method find and return a child specified by name
   * @param name is the name of the child
   * @return a Node child with the given name
   */
  public Node get(String name) {
    for (Node node : children) {
      if (node.getName().equals(name)) {
        return node;
      }
    }
    return null;
  }

  /**
   * This method find and return a directory specified by name
   * @param dirName is the name of the directory
   * @return one directory with the same name given
   */
  public Directory getDirectory(String dirName) {
    Node node = get(dirName);
    if (node instanceof File) {
      return null;
    }
    return (Directory) node;
  }

  /**
   * This method find and return a file specified by name
   * @param fileName is the name of the file
   * @return one file with the same given name
   */
  public File getFile(String fileName) {
    Node node = get(fileName);
    if (node instanceof Directory) {
      return null;
    }
    return (File) node;
  }

  /**
   * This method get the absolute path for the current directory
   * @return the absolute path for the current directory
   */
  public String getAbsolutePath() {
    String path;
    if (this.getParent().equals(this)) {
      path =  "/";
    } else {
      path = this.parent.getAbsolutePath() + this.name + "/";
    }
    return path;
  }


  /**
   * This method returns the all the children that are directories
   * @return a list of all the directories children for the current
   * Directory
   */
  public List<Directory> getDirectoryList() {
    List<Directory> directories = new ArrayList<>();
    for (Node node : children) {
      if (node instanceof Directory) {
        directories.add((Directory) node);
      }
    }
    return directories;
  }

  /**
   * This method returns the all the children that are files
   * @return a list of all the file type children for the current Directory
   */
  public List<File> getFiles() {
    List<File> files = new ArrayList<>();
    for (Node node : children) {
      if (node instanceof File) {
        files.add((File) node);
      }
    }
    return files;
  }
}
