package datafileTablas;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import com.ephesoft.dcma.script.IJDomScript;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


/* class represents the script export structure. Writer of scripts plug-in should implement this
 * IScript interface to execute it from the scripting plug-in. Via implementing this interface writer can change its java file at run
 * time. Before the actual call of the java Scripting plug-in will compile the java and run the new class file.
 * 
 * @author Ephesoft
 * @version 1.0
 */
public class ScriptValidation implements IJDomScript {

	
	/**
	 * The <code>execute</code> method will execute the script written by the writer at run time with new compilation of java file. It
	 * will execute the java file dynamically after new compilation.
	 * 
	 * @param document {@link Document}
	 */
	@SuppressWarnings({ "unchecked" })
	public Object execute(Document document, String methodName, String documentIdentifier) {
		Exception exception = null;
		try {

			Element batch = document.getRootElement();
			System.out.println(
					"CORRIENDO SCRIPT DE EXTRACCIÓN PARA LOTE " + batch.getChildText("BatchInstanceIdentifier"));

			List<Element> rows = batch.getChild("Documents").getChild("Document").getChild("DataTables")
					.getChild("DataTable").getChild("Rows").getChildren();

			List<Element> columnasDePrimeraFila = rows.get(0).getChild("Columns").getChildren();
			List<Element> columnasDeSegundaFila = rows.get(1).getChild("Columns").getChildren();

			String elementoACompararF1 = columnasDePrimeraFila.get(0).getChildText("Value");
			String elementoACompararF2 = columnasDeSegundaFila.get(0).getChildText("Value");

			if(!elementoACompararF1.equals(elementoACompararF2))
			{
				batch.getChild("Documents").getChild("Document").getChild("Valid").setText("false");
				batch.getChild("Documents").getChild("Document").getChild("ErrorMessage").setText("Validacion por script errada!!!");
				
				columnasDePrimeraFila.get(0).getChild("Valid").setText("false");
				columnasDeSegundaFila.get(0).getChild("Valid").setText("false");
	
			}


			
		} catch (Exception e) {
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
			ScriptValidation se = new ScriptValidation();

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
