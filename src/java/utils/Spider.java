/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import utils.FileUtil;
import utils.GetHTML;
import utils.Internet;
import utils.XMLUtils;

/**
 *
 * @author Admin
 */
public class Spider {

    public String name;
    public String startURL;
    public String src;

    public Spider(String name, String startURL) {
        this.name = name;
        this.startURL = startURL;
    }

    public void startExecution() throws IOException, Exception {
        String rawHTML = GetHTML.getHTMLToString(startURL);
        this.src = rawHTML;
    }

    public void saveToFile(String filePath) {
        FileUtil.saveSrcToFile(filePath, src);
    }

    public void saveToFile(String filePath, String containerTag) throws IOException {
        String splitted = XMLUtils.splitSection(src, containerTag);
        FileUtil.saveSrcToFile(filePath, splitted);
    }

    public void saveToFile(String filePath, String[] containerTag, String[] beforeEndTag) throws IOException {
        String splitted = XMLUtils.splitSection(src, containerTag, beforeEndTag);
        FileUtil.saveSrcToFile(filePath, splitted);
    }

    public Document splitSrcToDOM(String containerTag) throws IOException,
            ParserConfigurationException, SAXException {
        String splitedSrc = XMLUtils.splitSection(src, containerTag);
        return XMLUtils.parseStringToDom(splitedSrc);
    }
    
    public String splitSrc(String containerTag) throws IOException,
            ParserConfigurationException, SAXException {
        return XMLUtils.splitSection(src, containerTag);
    }
//
//    public XMLEventReader splitSrcToXML(String containerTag) throws IOException,
//            FileNotFoundException, UnsupportedEncodingException, XMLStreamException {
//        String splitedSrc = XMLUtils.splitSection(src, containerTag);
//        System.out.println("SPLITED: ");
//        System.out.println(splitedSrc);
//        return XMLUtils.parseStringToXMLEvent(splitedSrc);
//    }
//
//    public XMLEventReader splitSrcToXML(String[] containerTag, String[] beforeEndTag) throws IOException,
//            FileNotFoundException, XMLStreamException, UnsupportedEncodingException {
//        String splitedSrc = XMLUtils.splitSection(src, containerTag, beforeEndTag);
//        System.out.println("SPLITED: ");
//        System.out.println(splitedSrc);
//        return XMLUtils.parseStringToXMLEvent(splitedSrc);
//    }

}
