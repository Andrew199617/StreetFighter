package swing;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


/** Assumes UTF-8 encoding. JDK 7+. */
public class ReadWithScanner {
	String  [] values = new String[6];
	static int [] intValues = new int [6];
	int position = 0;

  public static int giveValues(int i) throws IOException {
    ReadWithScanner parser = new ReadWithScanner("Properties.txt");
    parser.processLineByLine();
    int stat = intValues[i];
	return stat;
  }
  public ReadWithScanner(String aFileName){
    fFilePath = Paths.get(aFileName);
  }
  public final void processLineByLine() throws IOException {
    try (Scanner scanner =  new Scanner(fFilePath, ENCODING.name())){
      while (scanner.hasNextLine()){
        processLine(scanner.nextLine());
        position++;
      }
      for(int i = 0; i<6; i++){
    	//  System.out.println(values[i]);
    	  intValues[i] = Integer.parseInt(values[i]);
      }
    }
  }
  

  
  
  protected void processLine(String aLine){
    //use a second Scanner to parse the content of each line 
    Scanner scanner = new Scanner(aLine);
    scanner.useDelimiter(":");
    if (scanner.hasNext()){
      //assumes the line has a certain structure
      String name = scanner.next();
      String value = scanner.next();
      String number = value.trim();
     // System.out.println("Name is : " + quote(name.trim()) + ", and Value is : " + quote(value.trim()));
      values [position]=number;
      
    }
    else {
      System.out.println("Empty or invalid line. Unable to process.");
    }
  }
  
  // PRIVATE 
  private final Path fFilePath;
  private final static Charset ENCODING = StandardCharsets.UTF_8;  
  
  private static void log(Object aObject){
  //  System.out.println(String.valueOf(aObject));
  }
  
  private String quote(String aText){
    String QUOTE = "'";
    return QUOTE + aText + QUOTE;
  }
} 