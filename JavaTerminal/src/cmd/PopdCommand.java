package cmd;

import containers.DirectoryStack;
import exceptions.DirectoryNotFoundError;
import exceptions.EmptyContainerError;
import fs.Directory;
import fs.FileSystemManager;

/**
 * This represents the PopdCommand that get the most recently saved
 * directory out of stack.
 */
public class PopdCommand extends DirectoryStackCommand {

  /**
   * This is the constructor of PopdCommand that instantiate the stack that
   * stores directories and FileSystemManager to control FileSystem.
   *
   * @param stackd is the stack that store directories.
   * @param fsm is the FileSystemManager to control the FileSystem.
   */
  public PopdCommand(DirectoryStack stackd, FileSystemManager fsm) {
    super(stackd, fsm);
  }

  /**
   * This method pop out the most recently saved directory from the
   * directory stack.
   */
  @Override
  public void execute() {
    try {
      Directory directory = stackd.pop();
      fsm.changeCurrent(directory.getAbsolutePath());
    } catch (EmptyContainerError |
        DirectoryNotFoundError e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * This method prints the documentation for the PopdCommand
   */
  @Override
  public void getUsage() {
    System.out.println();
    System.out.println("usage: PopdCommand");
    System.out.println("\tPop the last directory that pushed "
        + "into the directory stack");
    System.out.println();
  }

}
