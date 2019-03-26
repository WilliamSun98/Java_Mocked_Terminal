package cmd;

import fs.FileSystemManager;
import java.util.List;

/**
 * This represent the PwdCommand, this command will show path of current
 * directory when user type pwd.
 */
public class PwdCommand extends FileSystemCommand {

  /**
   * This is the parameter that shows whether the parameter is valid
   */
  private boolean valid;

  /**
   * This is the constructor of the PwdCommand, it instantiate the fsm
   * parameter inside its parent.
   *
   * @param fsm This is parameter used to instantiate the FileSystemManager.
   * inside its parent class.
   */
  public PwdCommand(FileSystemManager fsm) {
    super(fsm);
    valid = false;
  }

  /**
   * This method is called when user types pwd, it will print the path of
   * the current directory.
   */
  @Override
  public void execute() {
    if (!valid) {
      return;
    }
    String path = fsm.getCurrentPath();
    System.out.println(path);
  }

  /**
   * This method is used to check whether there are extra parameters for
   * pwd. Because there is no arguments required for pwd. So print error
   * messages when there is any extra parameter.
   */
  @Override
  public void setUp(List<String> args) {
    if (args == null || args.size() > 0) {
      valid = false;
      System.err.println("no arguments needed for pwd");
    } else {
      valid = true;
    }
  }

  /**
   * This method print the documentation for this command.
   */
  @Override
  public void getUsage() {
    System.out.println();
    System.out.println("usage: PwdCommand");
    System.out.println("\tCall the FileSystemManager to get the "
        + "absolute path of the current Directory");
    System.out.println();
  }
}
