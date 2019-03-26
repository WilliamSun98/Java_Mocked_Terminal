package fs;

import exceptions.DirectoryNotFoundError;
import exceptions.FileNotFoundError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileSystemManagerImplementationTest {

  private FileSystem fs = FileSystem.getInstance();
  private Directory root = fs.getRoot();
  private FileSystemManager fsm;

  @Before
  public void setUp() {
    fsm = new FileSystemManagerImplementation();
  }

  @Test
  public void getCurrentPathTest() {
    Assert.assertEquals("/", fsm.getCurrentPath());
  }

  @Test
  public void getCurrentPathComplexPathTest() throws DirectoryNotFoundError {
    Directory pathOne = new Directory("pathOne", null);
    Directory pathTwo = new Directory("pathTwo", null);
    pathOne.add(pathTwo);
    root.add(pathOne);
    fsm.changeCurrent("pathOne/pathTwo");
    Assert.assertEquals("/pathOne/pathTwo/", fsm.getCurrentPath());
  }

  @Test
  public void createFileInCurrentPathTest() throws DirectoryNotFoundError {
    fsm.createFile("testFile");
    Assert.assertNotNull(root.get("testFile"));
  }

  @Test
  public void createDirectoryInCurrentPathTest() throws DirectoryNotFoundError {
    fsm.createDirectory("someDir");
    Assert.assertNotNull(root.get("someDir"));
  }

  @Test
  public void getCurrentTest() {
    Assert.assertEquals(root, fsm.getCurrent());
  }

  @Test
  public void getFileTest() throws DirectoryNotFoundError, FileNotFoundError {
    File file = new File("testingFile");
    root.add(file);
    Assert.assertEquals(file, fsm.getFile("testingFile"));
  }

  @Test
  public void fileCheckTest() {
    root.add(new File("testFile"));
    Assert.assertTrue(fsm.fileCheck("testFile"));
  }

  @Test
  public void directoryCheckTest() {
    root.add(new Directory("testDirectory", null));
    Assert.assertTrue(fsm.directoryCheck("testDirectory"));
  }

}