package datafileTablas;

import java.io.File;
import java.io.PrintWriter;

import com.ephesoft.dcma.script.IJDomScript;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class ScriptFieldValueChange implements IJDomScript {

    public Object execute(Document document, String fieldName, String docIdentifier) {
        Exception exception = null;
        try {

            System.out.println(System.getenv("SYNERENV"));

            System.out.println(document + " - " + docIdentifier + " - " + fieldName);

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
            ScriptFieldValueChange se = new ScriptFieldValueChange();

            // Calling
            se.execute(doc, "DOC0X", "ElNombreDelCampo");

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
