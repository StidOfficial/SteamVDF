package SteamVDF.VDF;

public class VDFKey {
	
	private String Value;
	
	public VDFKey(String Value) {
		this.Value = Value;
	}
	
	@Override
	public String toString() {
		return this.Value;
	}
	
	public boolean toBoolean() { 
		return (this.Value == null || this.Value.equals("0")) ? false : true;
	}
	
	public int toInt() {
		return Integer.parseInt(this.Value);
	}
	
	public long toLong() {
		return Long.parseLong(this.Value);
	}
}
