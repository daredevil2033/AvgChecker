package ua.edu.sumdu.j2se.zuliukov.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

public class XMLAPI {
    private static Document document;

    public static void parseSource(String path) throws DocumentException {
        SAXReader reader = new SAXReader();
        reader.setValidation(true);
        document = reader.read(path);
    }

    public static void checkSource() {
        Element root = document.getRootElement();
        for (Iterator<Element> ito = root.elementIterator(); ito.hasNext(); ) {
            Element student = ito.next();
            double sum = 0;
            int count = 0;
            for (Iterator<Element> iti = student.elementIterator("subject"); iti.hasNext(); ) {
                Element subject = iti.next();
                sum += Double.parseDouble(subject.attributeValue("mark"));
                count++;
            }
            double avg = sum / count;
            Node average = student.selectSingleNode("average");
            if (average == null) student.addElement("average").setText(String.format(Locale.ENGLISH, "%.1f", avg));
            else average.setText(String.format(Locale.ENGLISH, "%.1f", avg));
        }
    }

    public static void parseTarget(String path) throws IOException {
        try (FileWriter fileWriter = new FileWriter(path)) {
            XMLWriter writer = new XMLWriter(fileWriter, OutputFormat.createPrettyPrint());
            writer.write(document);
            writer.close();
        }
    }
}
