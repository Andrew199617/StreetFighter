package swing;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadAndWriteFileIO {

	StringBuilder stringBuilder = new StringBuilder();
	FileWriter outputStream = null;

	public ReadAndWriteFileIO (){
		try {
			this.outputStream = new FileWriter("Properties.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
/*	public void readFile (){
		FileReader reader = null;
		try {
			reader = new FileReader("Properties.txt");
			for(;;){
				int c = reader.read();
				if(c == -1) break;
				stringBuilder.append((char)c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(toStringStringBuilder());
	}
*/
	public void writeFile(String s){

		try {
			outputStream.write(s+ "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeFile(int s){ //Used for writing an int

		try {
			outputStream.write("" + s + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String toStringStringBuilder(){
		return stringBuilder.toString();
	}

	public void openStream(){
		try {
			this.outputStream = new FileWriter("Properties.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void closeOutPutStream (){
		if(outputStream != null)
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
}
