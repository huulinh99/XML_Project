/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import daos.CategoryDAO;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.transform.sax.SAXSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import dtos.CategoryDTO;
import dtos.SubCategoryDTO;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Admin
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String url = "https://www.worldofwallpaper.com/";
        Spider spider = new Spider("Wallpaper", url);
        spider.startExecution();
//        Document document = spider.splitSrcToDOM(
//                "<nav class=\"navigation sw-megamenu \"");
        //spider.saveToFile("test2.html", "<nav class=\"navigation sw-megamenu \"");
        
        String categoriesHTMLSrc = spider.splitSrc("<nav class=\"navigation sw-megamenu \"");
        String categoriesXSLTPath = "web\\WEB-INF\\" + "world_wallpaper_category.xsl";
        String categoriesXMLSrc = new XSLTApplier()
                .applyStylesheet(categoriesXSLTPath, categoriesHTMLSrc);
        
        Document categoriesDom = XMLUtils.parseStringToDom(categoriesXMLSrc);
        XPath xPath = XMLUtils.createXPath();
        NodeList categoriesNodeList = categoriesDom.getElementsByTagName("category");
        CategoryDTO category = new CategoryDTO();
        CategoryDAO categoryDAO = new CategoryDAO();
        SubCategoryDTO subCategory = new SubCategoryDTO();
        for (int i = 0; i < categoriesNodeList.getLength(); i++) {
            String categoryString = categoriesNodeList.item(i).getTextContent();
//            System.out.println("Category: " + categoryString);
            NodeList subNodeList = categoriesNodeList.item(i).getChildNodes();
            for (int j = 0; j < subNodeList.getLength(); j++) {
                Node subNode = subNodeList.item(j);             
                if(subNode.equals("name")){
                    category.setName(subNode.getTextContent());
                    System.out.println("name ne hahaah" + subNode.getTextContent());
                    categoryDAO.insertCategory(category);
                }
                
//                else if(subNode.equals("subcategory")){
//                    NodeList subNodeCateList = categoriesNodeList.item(j).getChildNodes();
//                    for (int k = 0; k < subNodeCateList.getLength(); k++) {
//                        Node subCateNode = subNodeList.item(k);                       
//                        if(subCateNode.equals("name")){
//                            subCategory.setName(subCateNode.getTextContent());
//                        }
//                    }
//                }                           
                if (subNode instanceof Element) {
                    System.out.println(((Element) subNode).getTagName() + subNode.getTextContent());
                }
            }
        }
    }
}
