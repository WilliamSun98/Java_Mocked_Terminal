package fs;

import exceptions.DirectoryNotFoundError;
import exceptions.FileNotFoundError;
import java.util.List;

/**
 * This represents the MockedFileSystemManager
 */
public class MockedFileSystemManager implements FileSystemManager {

  /**
   * This is the path property, first it is set as root
   */
  private String path = "/";

  /**
   * This method create a file in current directory
   * @param name is the name of the file
   * @throws DirectoryNotFoundError
   */
  @Override
  public void createFile(String name) throws DirectoryNotFoundError {

  }

  /**
   * This method create a directory in the current directory
   * @param name is the name of the new directory
   * @throws DirectoryNotFoundError
   */
  @Override
  public void createDirectory(String name) throws DirectoryNotFoundError {

  }

  /**
   * This method delete a file with specified name in the current directory
   * @param fileName is the name of the file to be deleted
   */
  @Override
  public void deleteFile(String fileName) {

  }

  /**
   * The method delete a directory with specified name in the current
   * directory
   * @param directoryName is the name of the directory to be deleted
   */
  @Override
  public void deleteDirectory(String directoryName) {

  }

  /**
   * The method change the current directory by the path given
   * @param pathToChange is the path to change to
   * @throws DirectoryNotFoundError
   */
  @Override
  public void changeCurrent(String pathToChange) throws DirectoryNotFoundError {
    if (pathToChange.startsWith("/")) {
      path = pathToChange;
    } else {
      path = path + pathToChange;
    }
  }

  /**
   * This method add new content to the file with given name
   * @param filename is the name of file to add content
   * @param extraContent is the extra content to add
   * @throws DirectoryNotFoundError
   * @throws FileNotFoundError
   */
  @Override
  public void addFileContents(String filename, String extraContent)
      throws DirectoryNotFoundError, FileNotFoundError {

  }

  /**
   * This method reset the content of the file with given name
   * @param filename is the name of the file to reset content
   * @param Content is the new content to reset
   * @throws DirectoryNotFoundError
   * @throws FileNotFoundError
   */
  @Override
  public void setFileContents(String filename, String Content)
      throws DirectoryNotFoundError, FileNotFoundError {

  }

  /**
   * This method get the absolute path of the current directory
   * @return the path of the current directory
   */
  @Override
  public String getCurrentPath() {
    return path;
  }

  /**
   * This method reset the current directory as root
   */
  @Override
  public void setCurrentAsRoot() {

  }

  /**
   * This method return the current directory
   * @return the current directory
   */
  @Override
  public Directory getCurrent() {
    return null;
  }

  /**
   * This method return a list of all directory type children of the
   * current directory
   * @return a list of all directory type children of the current
   * directory
   */
  @Override
  public List<Directory> getDirectoryList() {
    return null;
  }

  /**
   * This method return a list of all file type children of the
   * current directory
   * @return a list of all file type children of the current
   * directory
   */
  @Override
  public List<File> getFileList() {
    return null;
  }

  /**
   * This method find and return directory with specified name
   * @param dirName is the name of directory
   * @return a directory with specified name
   */
  @Override
  public Directory getDirectory(String dirName) {
    return null;
  }

  /**
   * This method return a directory of the location specified by path
   * @param path is the path to look for this directory
   * @return the directory of the location specified by path
   * @throws DirectoryNotFoundError
   */
  @Override
  public Directory getDirectoryByPath(String path)
      throws DirectoryNotFoundError {
    return null;
  }

  /**
   * This method returns the file on the location specified by path
   * @param path is the path to look for the fiel
   * @return the a file on the location specified by path
   * @throws DirectoryNotFoundError
   * @throws FileNotFoundError
   */
  @Override
  public File getFile(String path)
      throws DirectoryNotFoundError, FileNotFoundError {
    switch (path) {
      case "testfile1":
        return new File("test-file-one", "mocked-contents-one");
      case "testfile2":
        return new File("test-file-two", "mocked-contents-two");
      case "testfile3":
        return new File("test-file-three", "mocked-contents-three");
      case "NotExistFile":
        throw new FileNotFoundError("NotExistFile");
      default:
        throw new DirectoryNotFoundError("NotExistDirectory");
    }
  }

  /**
   * check whether the name given is a name of a file inside the current
   * directory
   * @param name is the name to check whether it is a file name
   * @return whether it is a file name
   */
  @Override
  public boolean fileCheck(String name) {
    return false;
  }

  /**
   * check whether the name given is a name of a directory inside the
   * current directory
   * @param name is the name to check whether it is a directory name
   * @return whether it is a directory name
   */
  @Override
  public boolean directoryCheck(String name) {
    return false;
  }
}
