package datafileTablas;

import javax.ws.rs.core.MediaType;

// import com.google.gson.Gson;
// import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class SynerAPIComm {

    // constructor
    // JsonExport jsonExport = new JsonExport(jsonDocs, receivedFileName,
    // operatorUserName, "DAVIVIENDA", "15");

    // Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // String toExport = gson.toJson(jsonExport);

    private String postIT(String superString) {

        /* OLD JERSEY 1 */

        Client client = Client.create();

        WebResource webres = null;
        if (System.getenv("SYNERENV").matches("prod")) {

            webres = client.resource("http://172.0.0.6/SolicitudCredito/api/Extract/LoadExtract");

        } else if (System.getenv("SYNERENV").matches("(danvm|danlinux)")) {
            webres = client.resource("http://172.0.0.6/SolicitudCredito/api/Extract/LoadExtract");
            // webres = client.resource("http://192.168.0.100:8023/jaxrs/tester/tester");

            // webres =
            // client.resource("https://iknoline.herokuapp.com/jaxrs/tester/tester");
        } else {
            webres = client.resource("https://www.datafile.com.co/SolicitudCredito/api/Extract/LoadExtract");
        }
        WebResource.Builder buildit = webres.accept(MediaType.APPLICATION_JSON);

        buildit.type(MediaType.APPLICATION_JSON);
        buildit.header("TokenAuth",
                "eyJhbGciOiJBMjU2R0NNS1ciLCJlbmMiOiJBMjU2Q0JDLUhTNTEyIiwiaXYiOiJScHNiX2tpcDh5Y05XZS12IiwidGFnIjoiTzF5eWJRdWxBVktrUHhmTVE3UHZXUSJ9.bCzTVN476JCEbLs3ek-ugfBebumB7xet6lT_0Tz9bSvJflK0uQHoJrkTaRIxmf5lCrRgAcr1jucEr_jWE-RzXA.RTEgwNALwQXoykO_68_uOw.18a3YfrFB9M_H_1bzWjnopNg25T6I-UlJFoYFQrr_s4XAHPQlc2HZnQbxOHqbEe6.lnyL3PE3HqpGxSB-0NuhmtZMD11it1X_Xlqu4J6j-tg");
        buildit.header("X-FINGERPRINT",
                "eyJhbGciOiJBMjU2R0NNS1ciLCJlbmMiOiJBMjU2Q0JDLUhTNTEyIiwiaXYiOiJMczlfYXRscXp0c0YyekI3IiwidGFnIjoiSlZVenFMYnpfam1qbnN6cFFhcHY5ZyJ9.FAvr8FAknxQkeZXWsDmUejfNfft6UWr0zm1_HQwsjtVBe_jorDjYFBCh3ReTkqyjEXaOMzwaPYhcix3q_qLQZA.ygEopczOtsVzhNZ4Lpg-Hg.-RoYyQwxizZS9Me2qg5bfuCjmCjJAohe00iSiT77QnL3eOrFyyMzTv6CDS2JA-x2.2ytqN2iczQ2LTQQ2t7YXlp7-j7Fm2aJum4-PbZ_nC2o");
        String respuesta = buildit.post(String.class, superString);

        return respuesta;
    }

}
