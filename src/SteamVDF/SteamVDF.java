package SteamVDF;

import java.io.File;
import java.io.IOException;

import SteamVDF.VDF.VDF;
import SteamVDF.VDF.VDFElement;

public class SteamVDF {

	public static void main(String[] args) {
		File InputVDFFile = new File("input.vdf");
		try {
			VDF InputVDF = new VDF(InputVDFFile);
			System.out.println(InputVDF.getKey("Key1"));
			System.out.println(InputVDF.getParent("Parent1").getKey("Parent1Key1"));
			System.out.println(InputVDF.getParent("Parent1").getParent("Parent1Parent1").getKey("Parent1Parent1Key1"));
			System.out.println(InputVDF.getParent("Parent1").getKey("Parent1Key2"));
			System.out.println(InputVDF.getParent("Parent1").getParent("Parent1Parent2").getKey("Parent1Parent2Key1"));
			System.out.println(InputVDF.getParent("Parent1").getKey("Parent1Key3"));
			System.out.println(InputVDF.getKey("Key2"));
			System.out.println(InputVDF.getParent("Parent2").getKey("Parent2Key1"));
			System.out.println(InputVDF.getParent("Parent2").getParent("Parent2Parent1").getKey("Parent2Parent1Key1"));
			System.out.println(InputVDF.getParent("Parent2").getKey("Parent2Key2"));
			System.out.println(InputVDF.getParent("Parent2").getParent("Parent2Parent2").getKey("Parent2Parent2Key1"));
			System.out.println(InputVDF.getParent("Parent2").getKey("Parent2Key3"));
			System.out.println(InputVDF.getKey("Key3"));
			System.out.println(InputVDF.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		VDF OutputVDF = new VDF();
		OutputVDF.addKey("Test", "Test2");
		VDFElement Test = OutputVDF.addParent("test");
		Test.addKey("test2", "test2");
		VDFElement SubTest = Test.addParent("SubTest");
		SubTest.addKey("eee", "e");
		
		File OutputFile = new File("output.vdf");
		try {
			if (OutputFile.exists())
				OutputFile.createNewFile();
				
			OutputVDF.Save(OutputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}