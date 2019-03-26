package cmd;

import java.util.List;

/**
 * This represents the ExitCommand, when user type in exit in shell. Then
 * the shell program will be exterminated.
 */
public class ExitCommand implements Command {

  /**
   * This is the parameter that shows whether the parameter is valid
   */
  private boolean valid;

  /**
   * This is the constructor of the ExitCommand which set valid to be false
   * at first, because it haven't been validated yet.
   */
  public ExitCommand() {
    valid = false;
  }

  /**
   * This is the execute method used to finish the functionality of the
   * ExitCommand class.
   */
  @Override
  public void execute() {
    if (!valid) {
      return;
    }
    System.exit(0);
  }

  /**
   * This method is used to print the documentation for the ExitCommand.
   */
  @Override
  public void getUsage() {
    System.out.println();
    System.out.println("exit");
    System.out.println("\tQuit the program");
    System.out.println();
  }

  /**
   * This method is used to set up the parameters typed in by user that
   * will be used by the execute method later.
   *
   * @param args this is the parameters got from user for ExitCommand.
   */
  @Override
  public void setUp(List<String> args) {
    if (args.size() != 0) {
      System.err.println("exit: too many arguments");
    } else {
      valid = true;
    }
  }
}
