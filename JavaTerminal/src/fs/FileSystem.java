package fs;

/**
 * This is the FileSystem. It stores a root
 * Directory that can links to every file and directory
 */
public class FileSystem {

  /**
   * This is the root directory
   */
  private static Directory root;

  /**
   * This is the private constructor for the FileSystem
   */
  private FileSystem() {
    root = new Directory("root", null);
    root.setParent(root);
  }

  /**
   * This is a static method that create a static FileSystem
   */
  private static class FileSystemHolder {
    private static FileSystem fileSystem = new FileSystem();
  }

  /**
   * This method return a instance of FileSystem
   * @return a instance a FileSystem
   */
  public static FileSystem getInstance() {
    return FileSystemHolder.fileSystem;
  }


  /**
   * This method return the root directory
   * @return the root directory
   */
  public Directory getRoot() {
    return root;
  }


}
