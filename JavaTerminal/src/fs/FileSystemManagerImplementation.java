package fs;

import exceptions.DirectoryNotFoundError;
import exceptions.FileNotFoundError;
import java.util.List;

/**
 * This class represents the implementation of the FileSystemManager
 * interface
 */
public class FileSystemManagerImplementation implements FileSystemManager {

  /**
   * This is the current directory
   */
  private Directory current;
  /**
   * This is the fileSystem
   */
  private FileSystem fs;

  /**
   * This is the constrcutor of the FileSystemManagerImplementation
   * and it sets the FileSystem and current directory
   */
  public FileSystemManagerImplementation() {
    fs = FileSystem.getInstance();
    current = fs.getRoot();
  }

  /**
   * This create a file inside the current directory
   * @param name is the name of the file
   * @throws DirectoryNotFoundError
   */
  @Override
  public void createFile(String name) throws DirectoryNotFoundError {
    int i = name.lastIndexOf("/");
    if (i == -1) {
      current.add(new File(name));
    } else {
      Directory directory = getDirectoryByPath(name.substring(0, i));
      directory.add(new File(name.substring(i+1)));
    }
  }

  /**
   * This method create a directory in the current directory with
   * specified name
   * @param name is the name of the new directory
   * @throws DirectoryNotFoundError
   */
  @Override
  public void createDirectory(String name) throws DirectoryNotFoundError {
    int i = name.lastIndexOf("/");
    if (i == -1) {
      Directory dir = new Directory(name, current);
      current.add(dir);
    } else {
      Directory directory = getDirectoryByPath(name.substring(0, i));
      Directory dir = new Directory(name.substring(i+1), directory);
      directory.add(dir);
    }
  }

  /**
   * This method delete a file with given name
   * @param fileName is the name of the file to be deleted
   */
  @Override
  public void deleteFile(String fileName) {
    current.delete(fileName);
  }

  /**
   * This method delete a directory with given name
   * @param directoryName is the name of the directory to be deleted
   */
  @Override
  public void deleteDirectory(String directoryName) {
    current.delete(directoryName);
  }

  /**
   * This method change the current directory to the given path
   * @param pathToChange is the path to change to
   * @throws DirectoryNotFoundError
   */
  @Override
  public void changeCurrent(String pathToChange)
      throws DirectoryNotFoundError {
    current = getDirectoryByPath(pathToChange);
  }

  /**
   * This method add extra contents to a file with given name
   * @param filename is the name of file to add content
   * @param extraContent is the extra content to add
   * @throws DirectoryNotFoundError
   * @throws FileNotFoundError
   */
  @Override
  public void addFileContents(String filename, String extraContent)
      throws DirectoryNotFoundError, FileNotFoundError {
    File file = getFile(filename);
    file.addContents(extraContent);
  }

  /**
   * This method reset the content of a file with given name
   * @param filename is the name of the file to reset content
   * @param Content is the new content to reset
   * @throws DirectoryNotFoundError
   * @throws FileNotFoundError
   */
  @Override
  public void setFileContents(String filename, String Content)
      throws DirectoryNotFoundError, FileNotFoundError {
    File file = getFile(filename);
    file.setContents(Content);
  }

  /**
   * this method return the absolute path of the current directory
   * @return the absolute path for the current directory
   */
  @Override
  public String getCurrentPath() {
    return current.getAbsolutePath();
  }

  /**
   * This method change the current directory to root
   */
  @Override
  public void setCurrentAsRoot() {
    this.current = fs.getRoot();
  }

  /**
   * This method return the current directory
   * @return the current directory
   */
  @Override
  public Directory getCurrent() {
    return current;
  }

  /**
   * This method get all the directory type children of current
   * directory
   * @return a list of directory children of the current directory
   */
  @Override
  public List<Directory> getDirectoryList() {
    return current.getDirectoryList();
  }

  /**
   * This method get all the file type children of current
   * directory
   * @return a list of file children of the current directory
   */
  @Override
  public List<File> getFileList() {
    return current.getFiles();
  }

  /**
   * This method return a directory with specified name
   * @param dirName is the name of directory
   * @return the directory with given name
   */
  @Override
  public Directory getDirectory(String dirName) {
    return current.getDirectory(dirName);
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
    if (path.equals("/"))
      return fs.getRoot();
    String[] pathArray = path.split("/");
    Directory curr = current;
    for (int i = 0; i < pathArray.length; i++) {
      if (i == 0 && pathArray[i].isEmpty()) {
        curr = fs.getRoot();
      } else if (pathArray[i].equals("..")) {
        curr = curr.getParent();
      } else if (!pathArray[i].isEmpty() && !pathArray[i].equals(".")) {
        curr = curr.getDirectory(pathArray[i]);
      }
      // if not in the directory, throw the error
      if (curr == null) {
        throw new DirectoryNotFoundError(path + " is not found");
      }
    }

    return curr;
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
    File file;
    int i = path.lastIndexOf("/");
    if (i == -1) {
      file = current.getFile(path);
    } else {
      Directory fileDirectory = getDirectoryByPath(path.substring(0, i));
      file = fileDirectory.getFile(path.substring(i+1));
    }
    if (file == null) {
      throw new FileNotFoundError("File: " + path + " is not found");
    }
    return file;
  }

  /**
   * check whether the name given is a name of a file inside the current
   * directory
   * @param name is the name to check whether it is a file name
   * @return whether it is a file name
   */
  @Override
  public boolean fileCheck(String name) {
    for (int i = 0; i < current.getFiles().size(); i++) {
      if (current.getFiles().get(i).getName().equals(name)) {
        return true;
      }
    }
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
    for (int i=0; i < current.getDirectoryList().size(); i++) {
      if (current.getDirectoryList().get(i).getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

}
