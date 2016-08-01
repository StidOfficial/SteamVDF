package SteamVDF.VDF;

import java.util.HashMap;
import java.util.Map;

public class VDFBaseElement {
	
	private Map<String, VDFElement> MapParent = new HashMap<>();
	private Map<String, String> MapKey = new HashMap<>();
	
	public void addKey(String Name, String Value) {
		this.MapKey.put(Name, Value);
	}
	
	public VDFKey getKey(String Name) {
		return new VDFKey(this.MapKey.get(Name));
	}
	
	public String[] getKeys() {
		String[] Keys = new String[MapKey.size()];
		
		int i = 0;
		for (String Key: this.MapKey.keySet()) {
			Keys[i] = Key;
			i++;
		}
		
		return Keys;
	}
	
	public VDFElement addParent(String Name) {
		return this.addParent(Name, null);
	}
	
	public VDFElement addParent(String Name, VDFElement Parent) {
		VDFElement Element = new VDFElement(Name, Parent);
		this.MapParent.put(Name, Element);
		
		return Element;
	}
	
	public VDFElement getParent(String Name) {
		return this.MapParent.get(Name);
	}
	
	public VDFElement[] getParents() {
		VDFElement[] Parents = new VDFElement[MapParent.size()];
		
		int i = 0;
		for (String Key: this.MapParent.keySet()) {
			Parents[i] = this.MapParent.get(Key);
			i++;
		}
		return Parents;
	}
	
	private static String generateTabs(int number) {
		String Tabs = "";
		for (int t = 0; t < number; t++) {
			Tabs = Tabs + "\t";				
		}
		return Tabs;
	}
	
	private static String keysToString(VDFBaseElement Element, int tabNumber) {
		String OutputKeys = "";
		for (int i = 0; i < Element.getKeys().length; i++) {
			String Key = Element.getKeys()[i];
			OutputKeys = OutputKeys + generateTabs(tabNumber) + "\"" + Key + "\"\t\t\"" + Element.getKey(Key) + "\"\r\n";
		}
		return OutputKeys;
	}
	
	private static String parentsToString(VDFBaseElement Element, int tabNumber) {
		String OutputParents = "";
		for (int i = 0; i < Element.getParents().length; i++) {
			VDFElement Parent = Element.getParents()[i];
			OutputParents = OutputParents + generateTabs(tabNumber) + "\"" + Parent.getName() + "\"\r\n" + generateTabs(tabNumber) + "{" + generateTabs(tabNumber) + "\r\n" + Parent.toString(tabNumber+1) + generateTabs(tabNumber) + "}\r\n";
		}
		return OutputParents;
	}
	
	protected String toString(int tabNumber) {
		String OutputVDF = keysToString(this, tabNumber);
		OutputVDF = OutputVDF + parentsToString(this, tabNumber);
		return OutputVDF;
	}
	
	public String toString() {
		return this.toString(0);
	}
}
