package cmd;

import java.util.List;

/**
 * This is the abstract class for all classes that deal with with the
 * documentation of commands.
 */
public abstract class UsageCommand implements Command {

  /**
   * this method is used for finishing functionality of class that is a
   * UsageCommand.
   */
  @Override
  public void execute() {

  }

  /**
   * This method is used to print the documentation of a class that is a
   * UsageCommand.
   */
  @Override
  public void getUsage() {
  }

  /**
   * This method is used to setup parameters given by user for class that
   * is a UsageCommand.
   *
   * @param args this is the parameters got from user for UsageCommand.
   */
  @Override
  public void setUp(List<String> args) {

  }

}
