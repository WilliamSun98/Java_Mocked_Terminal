package fs;

import org.junit.Assert;
import org.junit.Test;

public class FileTest {

  @Test
  public void getNameTest() {
    File file = new File("someName");
    Assert.assertEquals(file.getName(), "someName");
  }

  @Test
  public void getContentsTest() {
    File file = new File("someName", "someContents");
    Assert.assertEquals(file.getContents(), "someContents");
  }

  @Test
  public void setContentsTest() {
    File file = new File("someName", "someContents");
    file.setContents("hello, world!");
    Assert.assertEquals(file.getContents(), "hello, world!");
  }

  @Test
  public void equalsTrueTest() {
    File firstFile = new File("myNameIsHere");
    File secondFile = new File("myNameIsHere");
    Assert.assertTrue(firstFile.equals(secondFile));
  }

  @Test
  public void equalsFalseTest() {
    File firstFile = new File("myNameIsHere");
    File secondFile = new File("myNameIsTHere");
    Assert.assertFalse(firstFile.equals(secondFile));
  }

  @Test
  public void addContentsTest() {
    File file = new File("someName", "someContents");
    file.addContents("hello, world!");
    Assert.assertEquals(file.getContents(), "someContentshello, world!");
  }
}