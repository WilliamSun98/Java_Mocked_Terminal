package cmd;

import exceptions.DirectoryNotFoundError;
import fs.FileSystemManager;

import java.util.List;

/**
 * This represents the MkdirCommand which used to create a new directory in
 * the place specified by user.
 */
public class MkdirCommand extends FileSystemCommand {

  public List<String> directories;

  /**
   * This is the constructor for MkdirCommand and it instantiate the
   * FileSystemManager that will be used to change the FileSystem.
   *
   * @param fsm is the FileSystemManager which helps add new directory.
   */
  public MkdirCommand(FileSystemManager fsm) {
    super(fsm);
  }

  /**
   * This is the method that will be used to make a new directory.
   */
  @Override
  public void execute() {
    if (directories.size() == 0) {
      System.err.println(
          "please enter the directory name you want to create");
      return;
    }
    for (String dirName : directories) {
      try {
        this.fsm.createDirectory(dirName);
      } catch (DirectoryNotFoundError e) {
        System.err.println(e.getMessage());
      }
    }
  }

  /**
   * This is the method that will be used to set up the directory list.
   *
   * @param args this is the list of directory names got from user.
   */
  @Override
  public void setUp(List<String> args) {
    this.directories = args;
  }

  /**
   * This is the method that uses to print the documentation of
   * MkdirCommand.
   */
  @Override
  public void getUsage() {
    System.out.println();
    System.out.println("usage: MkdirCommand");
    System.out.println("\tCall FileSystemManager to create a new "
        + "Directory in place get from User");
    System.out.println();
  }
}
