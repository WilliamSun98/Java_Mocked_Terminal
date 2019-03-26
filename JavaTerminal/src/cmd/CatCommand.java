package cmd;

import exceptions.DirectoryNotFoundError;
import exceptions.FileNotFoundError;
import fs.FileSystemManager;
import fs.File;

import java.util.List;

/**
 * This represents the CatCommand, this command will show the content of
 * the given file(s)
 */
public class CatCommand extends FileSystemCommand {

  /**
   * This is the FileSystemManager to control the FileSystem
   */
  private FileSystemManager fsm;
  /**
   * This is the list of Files which needs to show the content
   */
  private List<String> listOfFiles;
  /**
   * This is the valid property to check whether the given parameter
   * is valid for CatCommand
   */
  private boolean valid;

  /**
   * This is the constructor of the CatCommand, it instantiate the fsm
   * parameter inside its parent.
   *
   * @param fsm This is parameter used to instantiate the FileSystemManager
   * inside its parent class
   */
  public CatCommand(FileSystemManager fsm) {
    this.fsm = fsm;
  }

  /**
   * This method is called when user type cat
   */
  @Override
  public void execute() {
    if (valid) {
      File file;
      for (String fileName : this.listOfFiles) {
        try {
          file = this.fsm.getFile(fileName);
          System.out.println(file.getContents());
        } catch (FileNotFoundError | DirectoryNotFoundError e) {
          System.err.println(e.getMessage());
        }
      }
    }
  }

  /**
   * This method is used to set up the parameters for cat command store the
   * list of filenames inside the listOfFiles parameter
   *
   * it will also check whether the parameter is more than one, because cat
   * command takes at least one parameter
   *
   * @param args this is the parameters for the CatCommand typed by user
   */
  @Override
  public void setUp(List<String> args) {
    if (args == null || args.size() == 0) {
      System.out.println("cat: at least has one parameter");
      valid = false;
    } else {
      valid = true;
      this.listOfFiles = args;
    }
  }

  /**
   * This method is used to print the documentation of the CatCommand
   */
  public void getUsage() {
    System.out.println();
    System.out.println("getUsage: cat CMD");
    System.out.println("\tPrint Documentation for CMD");
    System.out.println("\tDisplay the contents of FILE1 and"
        + " other files (i.e. File2 ....) concatenated in the shell.");
    System.out.println("\tYou may want to use three line breaks to"
        + " separate the contents of one file from the other file.");
    System.out.println("\n\techo STRING [> OUTFILE]");
    System.out.println("\tIf OUTFILE is not provided,"
        + " print STRING on the shell.");
    System.out.println("\tOtherwise, put STRING into file OUTFILE. ");
    System.out.println("\tSTRING is a string of characters surrounded"
        + " by double quotation marks.");
    System.out.println("\tThis creates a new file if OUTFILE does not"
        + " exists and erases the old contents if OUTFILE already exists.");
    System.out.println("\tIn either case, the only thing"
        + " in OUTFILE should be STRING.");
    System.out.println("\n\techo STRING >> OUTFILE");
    System.out.println("\tLike the previous command,"
        + " but appends instead of overwrites.");
    System.out.println();
  }
}
