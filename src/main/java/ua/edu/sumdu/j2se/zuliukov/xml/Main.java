package ua.edu.sumdu.j2se.zuliukov.xml;

import org.dom4j.DocumentException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, DocumentException {
        XMLAPI.parseSource(args[0]);
        XMLAPI.checkSource();
        XMLAPI.parseTarget(args[1]);
    }
}
