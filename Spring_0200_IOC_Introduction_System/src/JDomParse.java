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
        //����xml�ļ�·��
        String xmlpath = "D:\\Myeclipse\\workspace\\Spring_0100_AbstractOrientedPragraming\\src\\test.xml";
        SAXBuilder builder = new SAXBuilder(false);
        try {
            Document doc = builder.build(xmlpath);
            // �õ���Ԫ��
            Element books = doc.getRootElement();
            // �õ���books��Ԫ�ص���������Ϊ��book����Ԫ�أ�������ЩԪ�ض��ŵ�һ��List������
            List booklist = books.getChildren("book");
            /**
             * ��ѭList����,����1��
             */
            for (Iterator iter = booklist.iterator(); iter.hasNext();) {
                Element book = (Element) iter.next();
                String email = book.getAttributeValue("email");// ��ȡemailԪ�ص�ֵ
                System.out.println(email);
                String name = book.getChildTextTrim("name");
                System.out.println(name);
                // �޸�nameԪ�ص�ֵ��ע����ǣ�����ȷ��bookԪ�ص���Ϊ��name������Ԫ��ֻ��һ����
                book.getChild("name").setText("edit-jdom");
            }

            /**
             * ��ѭList����,����2��
             */
//            for (int i = 0; i < booklist.size(); i++) {
//                Element book = (Element) booklist.get(i);
//                String email = book.getAttributeValue("email");// ��ȡemailԪ�ص�ֵ
//                System.out.println(email);
//                String name = book.getChildTextTrim("name");
//                System.out.println(name);
//// �޸�nameԪ�ص�ֵ��ע����ǣ�����ȷ��bookԪ�ص���Ϊ��name������Ԫ��ֻ��һ����
//                book.getChild("name").setText("edit-jdom");
//            }

            // ʹ��XMLOutputter�࣬���Ѿ��޸��˵�Document�����XML�ĵ��С�
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
