package swing;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestReaderAndWriter {

	@Test
	public void testRead() {
		ReadAndWriteFileIO reader = new ReadAndWriteFileIO();
		reader.writeFile("bob");
		reader.readFile();
		assertEquals("bob", reader.toStringStringBuilder());
	}

}
