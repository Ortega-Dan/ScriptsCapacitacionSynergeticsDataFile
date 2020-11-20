package datafileTablas;

import java.lang.reflect.Method;

import com.ephesoft.dcma.script.IJDomScript;

import org.jdom.Document;

public class ScriptFunctionKey implements IJDomScript {

    public Object execute(Document document, String methodName, String docIdentifier) {
        Exception exception = null;
        try {
            Class<ScriptFunctionKey> c = ScriptFunctionKey.class;
            Method meth = c.getMethod(methodName, String.class, Document.class);
            ScriptFunctionKey scriptFunctionKey = new ScriptFunctionKey();
            meth.invoke(scriptFunctionKey, docIdentifier, document);
        } catch (Exception e) {
            e.printStackTrace();
            exception = e;
        }
        return exception;

    }

    public void datafileMetodo1(String docIdentifier, Document document) {

        System.out.print("Hola Tabla 1 - ");
        System.out.println(document + " - " + docIdentifier);

    }

    public void datafileMetodo2(String docIdentifier, Document document) {

        System.out.print("Hola Tabla 2 - ");
        System.out.println(document + " - " + docIdentifier);

    }
}
