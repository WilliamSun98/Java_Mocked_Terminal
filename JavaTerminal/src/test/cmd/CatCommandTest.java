package cmd;

import exceptions.CommandError;
import fs.FileSystemManager;
import fs.MockedFileSystemManager;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CatCommandTest {

  private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errStream = new ByteArrayOutputStream();
  private final PrintStream stdout = System.out;
  private final PrintStream stderr = System.err;
  private Command command;

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outStream));
    System.setErr(new PrintStream(errStream));
    FileSystemManager fsm = new MockedFileSystemManager();
    command = new CatCommand(fsm);
  }

  @Test
  public void notExistFileTest() throws CommandError {
    List<String> args = new ArrayList<>();
    args.add("NotExistFile");
    command.setUp(args);
    command.execute();
    Assert.assertEquals("NotExistFile\n", errStream.toString());
  }

  @Test
  public void notExistDirectoryTest() throws CommandError {
    List<String> args = new ArrayList<>();
    args.add("somethignelse");
    command.setUp(args);
    command.execute();
    Assert.assertEquals("NotExistDirectory\n", errStream.toString());
  }

  @Test
  public void catSingleFileTest() throws CommandError {
    List<String> args = new ArrayList<>();
    args.add("testfile1");
    command.setUp(args);
    command.execute();
    Assert.assertEquals("mocked-contents-one\n", outStream.toString());
  }

  @Test
  public void catMultipleFilesTest() throws CommandError {
    List<String> args = new ArrayList<>();
    args.add("testfile1");
    args.add("testfile2");
    args.add("testfile3");
    command.setUp(args);
    command.execute();
    String res =
        "mocked-contents-one\nmocked-contents-two\nmocked-contents-three\n";
    Assert.assertEquals(res, outStream.toString());
  }

  @After
  public void restore() {
    System.setOut(stdout);
    System.setErr(stderr);
  }

}
