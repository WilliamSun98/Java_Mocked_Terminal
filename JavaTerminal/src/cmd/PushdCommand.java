package cmd;

import containers.DirectoryStack;
import exceptions.CommandError;
import exceptions.DirectoryNotFoundError;
import fs.FileSystemManager;
import java.util.List;

/**
 * This represents the PushdCommand class and it push a directory specified
 * by user into the directory stack.
 */
public class PushdCommand extends DirectoryStackCommand {

  /**
   * This is the name of the directory to push into the stack
   */
  private String directoryName;
  /**
   * This is the parameter that shows whether the parameter is valid
   */
  private boolean valid;

  /**
   * This is the constructor for PushCommand and it get a stack to store
   * directories and a FileSystemManager to take control of FileSystem.
   *
   * @param stackd is a stack used for storing directories.
   * @param fsm is used to change FileSystem.
   */
  public PushdCommand(DirectoryStack stackd, FileSystemManager fsm) {
    super(stackd, fsm);
    valid = false;
  }

  /**
   * This methods set up the directory to be pushed into directory stack.
   *
   * @param args this is the parameters got from user for PushdCommand.
   * @throws CommandError when input parameters are invalid.
   */
  @Override
  public void setUp(List<String> args) throws CommandError {
    if (args.size() != 1) {
      valid = false;
      throw new CommandError("Please indicate a valid path for pushd");
    }
    this.directoryName = args.get(0);
    valid = true;
  }

  /**
   * This method is used to push a specified directory into the directory
   * stack.
   */
  @Override
  public void execute() {
    if (!valid) {
      return;
    }
    try {
      stackd.push(fsm.getDirectoryByPath(directoryName));
    } catch (DirectoryNotFoundError e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * this method is used to print the documentation of the PushdCommand.
   */
  @Override
  public void getUsage() {
    System.out.println();
    System.out.println("usage: PushdCommand");
    System.out.println("\tPush a Directory into the stack");
    System.out.println();
  }

}
