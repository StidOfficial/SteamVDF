package SteamVDF.VDF;

public class VDFElement extends VDFBaseElement {
	
	private String ElementName;
	private VDFElement BaseElement;
	
	public VDFElement(String Name, VDFElement BaseElement) {
		this.ElementName = Name;
		this.BaseElement = BaseElement;
	}
	
	public String getName() {
		return this.ElementName;
	}	
	
	public VDFElement getBase() {
		return BaseElement;
	}
}
