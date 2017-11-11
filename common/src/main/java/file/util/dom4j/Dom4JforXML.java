package file.util.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class Dom4JforXML {

	@Test
	public void test() throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("F:\\workspace\\filesearch\\common\\src\\main\\resources\\test\\s.xml"));
		Element root = document.getRootElement();
		listNodes(root);
	}

	public void listNodes(Element node){
		System.out.println("节点名称" + node.getName());

		List<Attribute> list = node.attributes();

		for(Attribute attribute : list){
			System.out.println("属性"+attribute.getName() +":" + attribute.getValue());
		}

		if(!(node.getTextTrim().equals(""))){
			 System.out.println( node.getName() + "文本内容" + node.getText());
		}

		Iterator<Element> iterator = node.elementIterator();
		while(iterator.hasNext()){
			Element e = iterator.next();
			listNodes(e);
		}
	}

	@Test
	public void test2()throws Exception{

		SAXReader reader = new SAXReader();

		Document document = reader.read(new File("F:\\workspace\\filesearch\\common\\src\\main\\resources\\test\\s.xml"));

		Element root = document.getRootElement();

		System.out.println("-------节点信息------");

		Element student1Element = root.element("student1");

		listNodes(student1Element);

		Attribute idAttribute = student1Element.attribute("id");

		student1Element.remove(idAttribute);

		student1Element.addAttribute("name", "测试文件");
		System.out.println("-------文件添加------");
		listNodes(student1Element);
	}

	@Test
	public void test3()throws Exception{

		SAXReader reader = new SAXReader();

		Document document = reader.read(new File("F:\\workspace\\filesearch\\common\\src\\main\\resources\\test\\s.xml"));

		Element root = document.getRootElement();
		System.out.println("-------student1------");

		Element student1Element = root.element("student1");

		listNodes(student1Element);

		Element phoneElement = student1Element.addElement("phone");

		phoneElement.setText("137xxxxxxxx");
		System.out.println("-------信息展示------");
		listNodes(student1Element);
	}

	@Test
	public void test4()throws Exception{

		SAXReader reader = new SAXReader();

		Document document = reader.read(new File("F:\\workspace\\filesearch\\common\\src\\main\\resources\\test\\s.xml"));

		Element root = document.getRootElement();
		System.out.println("-------student1------");

		Element student1Element = root.element("student1");

		listNodes(student1Element);

		Element phoneElement = student1Element.addElement("phone");

		phoneElement.setText("137xxxxxxxx");
		System.out.println("-------student1展示------");
		listNodes(student1Element);
		writerDocumentToNewFile(document);
		System.out.println("---文件写入----");
	}

	public void writerDocumentToNewFile(Document document)throws Exception{
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(new File("F:\\workspace\\filesearch\\common\\src\\main\\resources\\test\\s1.xml")),"UTF-8"), format);
		writer.write(document);
		writer.flush();
		writer.close();
	}
}
