package cmd;

import exceptions.DirectoryNotFoundError;
import fs.Directory;
import fs.File;
import fs.FileSystemManager;

import java.util.List;

/**
 * This represents the LsCommand which can show the files and directories
 * inside a specified directory.
 */
public class LsCommand extends FileSystemCommand {

  /**
   * This is the path to the directory
   */
  private List<String> path;
  /**
   * This is the list of directories of the current directory
   */
  private List<Directory> DirectoryList;
  /**
   * This is the list of files of the current directory
   */
  private List<File> FileList;

  /**
   * this is the constructor for the LsCommand that instantiate the fsm
   * property.
   *
   * @param fsm is the FileSystemManager used to control the FileSystem.
   */
  public LsCommand(FileSystemManager fsm) {
    super(fsm);
  }

  /**
   * This is the method 1. change the current directory to another
   * directory by using the specified path. 2. get the files and
   * directories inside that directory.
   *
   * @param path is the path to the target directory.
   */
  private void changePathAndGetDirectoryAndFiles(String path) {
    if (path != null) {
      try {
        fsm.changeCurrent(path);
      } catch (DirectoryNotFoundError e) {
        System.err.println(e.getMessage());
      }
    }
    this.DirectoryList = fsm.getDirectoryList();
    this.FileList = fsm.getFileList();
  }

  /**
   * This method finish all the functionality of the LsCommand eg. if the
   * path given is a file, then print the filename if the path given
   * directs to a directory, then print the content of the directory if no
   * parameter is given, then print the content of the current directory.
   */
  @Override
  public void execute() {

    // if no parameter is given, print the content of the current directory
    if (path.size() == 0) {
      this.changePathAndGetDirectoryAndFiles(null);
      for (Directory dir : DirectoryList) {
        System.out.println(dir.getName());
      }
      for (File file : FileList) {
        System.out.println(file.getName());
      }
      // if there are parameters
    } else {

      // loop through each path
      for (String name : path) {
        // check whether the path is a filename
        if (fsm.fileCheck(name)) {
          System.out.println(name);
          // if the name is a directory name
        } else {
          String currentPath = fsm.getCurrent().getAbsolutePath();
          this.changePathAndGetDirectoryAndFiles(name);

          System.out.print(name + ": ");
          for (Directory dir : DirectoryList) {
            System.out.print(dir.getName() + " ");
          }
          for (File file : FileList) {
            System.out.print(file.getName() + " ");
          }

          try {
            fsm.changeCurrent(currentPath);
          } catch (DirectoryNotFoundError e) {
            System.err.println(e.getMessage());
          }
          System.out.println();
        }
      }
    }
  }

  /**
   * This is the method used to set up parameters f9r LsCommand.
   *
   * @param args this is the parameters got from user for LsCommand.
   */
  @Override
  public void setUp(List<String> args) {
    this.path = args;
  }

  /**
   * This method prints the documentation for the LsCommand.
   */
  @Override
  public void getUsage() {
    super.getUsage();
    System.out.println();
    System.out.println("usage: LsCommand");
    System.out.println("Call FileSystemManager to get the list of Files"
        + "\tand Directories inside the specified Directory");
    System.out.println();
  }
}
