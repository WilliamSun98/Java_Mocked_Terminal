package fs;

import exceptions.DirectoryNotFoundError;
import exceptions.FileNotFoundError;
import java.util.ArrayList;
import java.util.List;

/**
 * This represents the FileSystemManager interface
 */
public interface FileSystemManager {

  /**
   * This create a file inside the current directory
   * @param name is the name of the file
   * @throws DirectoryNotFoundError
   */
  void createFile(String name) throws DirectoryNotFoundError;

  /**
   * This method create a directory in the current directory with
   * specified name
   * @param name is the name of the new directory
   * @throws DirectoryNotFoundError
   */
  void createDirectory(String name) throws DirectoryNotFoundError;

  /**
   * This method delete a file with given name
   * @param fileName is the name of the file to be deleted
   */
  void deleteFile(String fileName);

  /**
   * This method delete a directory with given name
   * @param directoryName is the name of the directory to be deleted
   */
  void deleteDirectory(String directoryName);

  /**
   * This method change the current directory to the given path
   * @param pathToChange is the path to change to
   * @throws DirectoryNotFoundError
   */
  void changeCurrent(String pathToChange) throws DirectoryNotFoundError;

  /**
   * This method add extra contents to a file with given name
   * @param filename is the name of file to add content
   * @param extraContent is the extra content to add
   * @throws DirectoryNotFoundError
   * @throws FileNotFoundError
   */
  void addFileContents(String filename, String extraContent)
      throws DirectoryNotFoundError, FileNotFoundError;

  /**
   * This method reset the content of a file with given name
   * @param filename is the name of the file to reset content
   * @param Content is the new content to reset
   * @throws DirectoryNotFoundError
   * @throws FileNotFoundError
   */
  void setFileContents(String filename, String Content)
      throws DirectoryNotFoundError, FileNotFoundError;

  /**
   * this method return the absolute path of the current directory
   * @return the absolute path for the current directory
   */
  String getCurrentPath();

  /**
   * This method change the current directory to root
   */
  void setCurrentAsRoot();

  /**
   * This method return the current directory
   * @return the current directory
   */
  Directory getCurrent();

  /**
   * This method get all the directory type children of current
   * directory
   * @return a list of directory children of the current directory
   */
  List<Directory> getDirectoryList();

  /**
   * This method get all the file type children of current
   * directory
   * @return a list of file children of the current directory
   */
  List<File> getFileList();

  /**
   * This method return a directory with specified name
   * @param dirName is the name of directory
   * @return the directory with given name
   */
  Directory getDirectory(String dirName);

  /**
   * This method return a directory of the location specified by path
   * @param path is the path to look for this directory
   * @return the directory of the location specified by path
   * @throws DirectoryNotFoundError
   */
  Directory getDirectoryByPath(String path)
      throws DirectoryNotFoundError;

  /**
   * This method returns the file on the location specified by path
   * @param path is the path to look for the fiel
   * @return the a file on the location specified by path
   * @throws DirectoryNotFoundError
   * @throws FileNotFoundError
   */
  File getFile(String path)
      throws DirectoryNotFoundError, FileNotFoundError;

  /**
   * check whether the name given is a name of a file inside the current
   * directory
   * @param name is the name to check whether it is a file name
   * @return whether it is a file name
   */
  boolean fileCheck(String name);

  /**
   * check whether the name given is a name of a directory inside the
   * current directory
   * @param name is the name to check whether it is a directory name
   * @return whether it is a directory name
   */
  boolean directoryCheck(String name);
}
