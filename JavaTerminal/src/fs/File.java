package fs;

/**
 * This represent File.
 */
public class File extends Node {

  /**
   * This is the content of this file
   */
  private String contents;

  /**
   * This is the constructor for the File class and it
   * instantiate its name
   * @param name is the name of the newly created file
   */
  public File(String name) {
    super(name);
  }

  /**
   * This is the constructor for the File class and
   * it instantiate its name and its content
   * @param name is the name of the file
   * @param contents is the content of the file
   */
  public File(String name, String contents) {
    super(name);
    this.contents = contents;
  }

  /**
   * This method returns the name of the file.
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * This method returns the content of the file
   * @return
   */
  public String getContents() {
    return contents;
  }

  /**
   * This method reset the content of the file
   * @param contents is the new content for the file
   */
  public void setContents(String contents) {
    this.contents = contents;
  }

  /**
   * This method add extra content to this file
   * @param additionContents is the extra content
   */
  public void addContents(String additionContents) {
    this.contents += additionContents;
  }


}
