import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class JDomParse {
	public JDomParse() {
        //定义xml文件路径
        String xmlpath = "D:\\Myeclipse\\workspace\\Spring_0100_AbstractOrientedPragraming\\src\\test.xml";
        SAXBuilder builder = new SAXBuilder(false);
        try {
            Document doc = builder.build(xmlpath);
            // 得到根元素
            Element books = doc.getRootElement();
            // 得到“books”元素的所在名称为“book”的元素，并把这些元素都放到一个List集合中
            List booklist = books.getChildren("book");
            /**
             * 轮循List集合,方法1：
             */
            for (Iterator iter = booklist.iterator(); iter.hasNext();) {
                Element book = (Element) iter.next();
                String email = book.getAttributeValue("email");// 获取email元素的值
                System.out.println(email);
                String name = book.getChildTextTrim("name");
                System.out.println(name);
                // 修改name元素的值，注意的是，必须确定book元素的名为“name”的子元素只有一个。
                book.getChild("name").setText("edit-jdom");
            }

            /**
             * 轮循List集合,方法2：
             */
//            for (int i = 0; i < booklist.size(); i++) {
//                Element book = (Element) booklist.get(i);
//                String email = book.getAttributeValue("email");// 获取email元素的值
//                System.out.println(email);
//                String name = book.getChildTextTrim("name");
//                System.out.println(name);
//// 修改name元素的值，注意的是，必须确定book元素的名为“name”的子元素只有一个。
//                book.getChild("name").setText("edit-jdom");
//            }

            // 使用XMLOutputter类，把已经修改了的Document保存进XML文档中。
            XMLOutputter outputter = new XMLOutputter();
            outputter.output(doc, new FileOutputStream(xmlpath));
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JDomParse();
    }
}
