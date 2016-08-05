package SteamVDF.VDF;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VDF extends VDFBaseElement {
	
	public VDF() {
		super();
	}
	
	public VDF(String file) throws IOException {
		this(new File(file));
	}
	
	public VDF(File file) throws IOException {
		this(fileToLine(file));
	}
	
	public VDF(String[] VDFLine) {
		String ElementName = null;
		VDFElement Element = null;
		    
		for (int i = 0; i < VDFLine.length; i++) {
			String[] Line = VDFLine[i].trim().split("\t\t");
		    	
		    if (Line[0].startsWith("\"") && Line[0].endsWith("\"")) {
		    	if (Line.length == 2) {
		    		if (Element == null)
		    			this.addKey(removeQuote(Line[0]), removeQuote(Line[1]));
		    		else
		    			Element.addKey(removeQuote(Line[0]), removeQuote(Line[1]));
		    	} else if(Line.length == 1) {
		    		ElementName = removeQuote(Line[0]);
		    	}
		    } else if (Line[0].contains("{")) {
		    	if (Element == null) {
		    		Element = this.addParent(ElementName, null);
		    	} else {
		    		Element = Element.addParent(ElementName, Element);
		    	}
		    } else if (Line[0].contains("}")) {
		    	if (Element.getBase() == null)
		    		Element = null;
		    	else
		    		Element = Element.getBase();
		    }
		}
	}
	
	private static String[] fileToLine(File file) throws IOException {
		ArrayList<String> BufferLine = new ArrayList<>();
		BufferedReader BufferReader = new BufferedReader(new FileReader(file));
		String LineRead = null;
		while ((LineRead = BufferReader.readLine()) != null) {
			BufferLine.add(LineRead);
		}
		BufferReader.close();
		
		String[] VDFLine = new String[BufferLine.size()];
		
		return BufferLine.toArray(VDFLine);
	}
	
	private static String removeQuote(String str) {
		return str.replace("\"", "");
	}
	
	public void Save(String file) throws IOException {
		this.Save(new File(file));
	}
	
	public void Save(File file) throws IOException {
		BufferedWriter BufferWriter = new BufferedWriter(new FileWriter(file));
		BufferWriter.write(this.toString());
		BufferWriter.close();
	}
}
