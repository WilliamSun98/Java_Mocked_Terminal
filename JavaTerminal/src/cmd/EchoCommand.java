package cmd;

import exceptions.DirectoryNotFoundError;
import exceptions.FileNotFoundError;
import fs.FileSystemManager;

import java.util.List;

/**
 * This represents the EchoCommand.
 *
 * This command can print some content typed by user.
 *
 * It can also replace the content of a specified file with new content
 * typed by the user.
 *
 * It can also append some new content typed by the user to the end of a
 * specified file.
 */
public class EchoCommand extends FileSystemCommand {

  /**
   * This is the content to print
   */
  private String text;
  /**
   * This is the symbol ">" or ">>"
   */
  private String symbol;
  /**
   * This is the name of the file
   */
  private String filename;

  /**
   * This is the constructor of the EchoCommand and it instantiate the
   * property of FileSystemManager to control the FileSystem.
   *
   * @param fsm is used to make changes to the FileSystem.
   */
  public EchoCommand(FileSystemManager fsm) {
    super(fsm);
  }

  /**
   * This execute method is used to finish the functionality of the echo
   * command, by using the parameters set up by the setUp methods.
   */
  @Override
  public void execute() {
    // if there is a symbol and file name
    if (this.symbol != null && this.filename != null) {
      try {
        fsm.createFile(filename);
        // judge by the symbol
        switch (this.symbol) {
          // if the symbol is >, then replace the content
          case ">":
            if (!fsm.fileCheck(filename) && !fsm.directoryCheck(filename)) {
              fsm.createFile(filename);
              fsm.setFileContents(filename, text);
            } else if (fsm.directoryCheck(filename)){
              System.err.println("a Directory with same name has exist!");
            } else {
              fsm.setFileContents(filename, text);
            }
            break;
          // if the symbol is >>, then add the content
          case ">>":
            if (!fsm.fileCheck(filename) && !fsm.directoryCheck(filename)) {
              fsm.createFile(filename);
              fsm.addFileContents(filename, text);
            } else if (fsm.directoryCheck(filename)){
              System.err.println("a Directory with same name has exist!");
            } else {
              fsm.addFileContents(filename, text);
            }
            break;
          default:
            System.err.println("error in echo command parameters");
            break;
        }
      } catch (DirectoryNotFoundError | FileNotFoundError e) {
        System.err.println(e.getMessage());
      }
    // if there is no symbol, then just print the content
    } else if (this.text != null) {
      System.out.println(this.text);
    }
  }

  /**
   * This setUp method is used to setup the parameter which will be used by
   * execute method later.
   *
   * @param args is all the parameter given by the user for EchoCommand.
   */
  @Override
  public void setUp(List<String> args) {
    if (args.size() != 1 && args.size() != 3) {
      System.err.println("error in echo command parameters");
    } else if (args.size() == 3) {
      this.filename = args.get(2);
      this.symbol = args.get(1);
      this.text = args.get(0);
      if (text.matches("\".+?\"")) {
        text = text.substring(1, text.length() - 1);
      }
      if (this.filename.matches("\".+?\"")) {
        filename = filename.substring(1, filename.length() - 1);
      }
    } else {
      this.text = args.get(0);
    }
  }

  /**
   * This method prints the documentation of the EchoCommand.
   */
  @Override
  public void getUsage() {
    super.getUsage();
    System.out.println();
    System.out.println("usage: EchoCommand");
    System.out.println("\tCreate a file if file doesn't exists");
    System.out.println("If file exists and using '>', then replace"
        + "\t the file's content with new content");
    System.out.println("If file exists and using '>>',"
        + "\t then add new content to the end of the file's content");
    System.out.println();

  }

}
