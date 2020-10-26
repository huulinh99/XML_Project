/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import java.io.File;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Admin
 */
public class XJCGeneratorJavaObj {

    public static void main(String[] args) {
        try {
            String output = "src/java/";
            SchemaCompiler sc = XJC.createSchemaCompiler();
            sc.setErrorListener(new ErrorListener() {
                @Override
                public void error(SAXParseException saxpe) {
                    System.out.println("error" + saxpe.getMessage());
                }

                @Override
                public void fatalError(SAXParseException saxpe) {
                    System.out.println("fatal" + saxpe.getMessage());
                }

                @Override
                public void warning(SAXParseException saxpe) {
                    System.out.println("warning" + saxpe.getMessage());
                }

                @Override
                public void info(SAXParseException saxpe) {
                    System.out.println("info" + saxpe.getMessage());
                }
            });
            sc.forcePackageName("dtos");
//            File schema = new File("customer.xsd");
            File schema = new File("web/WEB-INF/categories.xsd");
            InputSource is = new InputSource(schema.toURI().toString());
            sc.parseSchema(is);
            // schema to java Obj
            S2JJAXBModel model = sc.bind();
            JCodeModel code = model.generateCode(null, null);
            code.build(new File(output));
            System.out.println("Finished");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
