package cmd;

import exceptions.CommandError;
import java.util.List;

/**
 * This is the Command interface, it has three basic methods
 *
 * execute method is to do the work of a command
 *
 * getUsage prints the documentation of a command in the shell
 *
 * setUp method setup the parameter for a command to execute
 */
public interface Command {

  /**
   * This method is uses the parameters set up by setUp methods to finish
   * the functionality of one command
   */
  void execute();

  /**
   * This method is used to print the documentation of a command
   */
  void getUsage();

  /**
   * This method is used to setup the parameter of a command
   *
   * @param args this is the parameters got from user for command
   */
  void setUp(List<String> args) throws CommandError;
}
