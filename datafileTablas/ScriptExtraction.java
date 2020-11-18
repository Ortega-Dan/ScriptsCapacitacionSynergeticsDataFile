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

public class ScriptExtraction implements IJDomScript {

	@SuppressWarnings({ "unchecked" })
	public Object execute(Document doc, String methodName, String docIdentifier) {

		Exception exception = null;
		try {

			Element batch = doc.getRootElement();
			System.out.println(
					"CORRIENDO SCRIPT DE EXTRACCIÓN PARA LOTE " + batch.getChildText("BatchInstanceIdentifier"));

			List<Element> rows = batch.getChild("Documents").getChild("Document").getChild("DataTables")
					.getChild("DataTable").getChild("Rows").getChildren();

			List<Element> columnasDePrimeraFila = rows.get(0).getChild("Columns").getChildren();

			Element elementoAModificar = columnasDePrimeraFila.get(0).getChild("Value");

			elementoAModificar.setText("99999999");

			System.out.println("TERMINÓ CON EXITO");

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
