package cn.edu.gdmec.s07131018.demo_dom;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.renderscript.Element;

public class DOMXMLParser {
	public static List<User> parse(InputStream in){
		List<User> users = null;
		users = new ArrayList<User>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = factory.newDocumentBuilder();
			Document document = documentBuilder.parse(in);
			org.w3c.dom.Element root = document.getDocumentElement();
			NodeList nodeList = root.getElementsByTagName("user");
			for(int i=0;i < nodeList.getLength();i++){
				Node node = nodeList.item(i);
				org.w3c.dom.Element element = (org.w3c.dom.Element) node;
				User user = new User();
				user.setId(Integer.parseInt(((org.w3c.dom.Element) element).getAttribute("id")));
				NodeList childNodes = node.getChildNodes();
				for(int j =0;j < childNodes.getLength();j++){
					Node childNode = childNodes.item(j);
					if(childNode.getNodeType()==Node.ELEMENT_NODE){

						if(childNode.getNodeName().equals("name")){
							user.setName(childNode.getFirstChild().getNodeValue());
						}
						if(childNode.getNodeName().equals("password")){
							user.setPassword(childNode.getFirstChild().getNodeValue());
						}
					}
				}
				users.add(user);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return users;
	}
}
