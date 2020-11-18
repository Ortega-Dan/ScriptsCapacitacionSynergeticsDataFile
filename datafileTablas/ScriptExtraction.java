package datafileTablas;

import java.io.File;
import java.io.PrintWriter;

import com.ephesoft.dcma.script.IJDomScript;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class ScriptExtraction implements IJDomScript {


	public Object execute(Document documentFile, String methodName, String docIdentifier) {
		
		Exception exception = null;
		try {



			System.out.println("Hola Mundo Datafile");



		} catch (Exception e) {
			System.out.println(e.getMessage());
			exception = e;
		}
		return exception;
	}



	public static void main(String[] args) {

		String filePath = "testingXMLs/BI2BAA_batch.xml";
		try {
			// Reading
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(filePath);

			// CAMBIAR POR LA CLASE EN LA QUE SE ESTÉ TRABAJANDO
			ScriptExtraction se = new ScriptExtraction();

			// Calling
			se.execute(doc, "", "");

			// Writing back
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			File outfile = new File(filePath);
			PrintWriter printWriter = new PrintWriter(outfile, "UTF-8");
			xmlOutput.output(doc, printWriter);

		} catch (Exception x) {
			x.printStackTrace(System.out);
		}
	}


}
