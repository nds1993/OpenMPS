package nds.frm.util;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlUtil {
    /**
     * Element의 Root정보와 tagName을 입력받아 해당 값으로 Node를 생성해주는 메소드
     *
     * @param root      Root Element
     * @param tagName   Root Element의 하위 Element리스트의 검색 대상명
     * @return Node     입력받은 정보로 구성한 Node
     */
    public static Node getSubNode(Element root, String tagName){

        NodeList list = root.getElementsByTagName(tagName);

        for(int loop = 0; loop < list.getLength(); loop++){
            Node node = list.item(loop);
            if(node != null)
                return node;
        }
        return null;
    }

    /**
     * 특정 root의 태그이름의 하위 태그이름에 해당하는 Node를 리턴
     *
     * @param root          Root Element
     * @param tagName       Root Element의 하위 Element리스트의 검색 대상명
     * @param subTagName    생성될 하위 Node명
     * @return Node         특정 root의 태그이름의 하위 태그이름에 해당하는 Node
     */
    public static Node getSubNode(Element root, String tagName, String subTagName){

        NodeList list = root.getElementsByTagName(tagName);

        for(int loop = 0; loop < list.getLength(); loop++){

            Node node = list.item(loop);

            if(node == null)
                continue;

            Node child = getSubNode(node, subTagName);

            if(child != null)
                return child;
        }
        return null;
    }

    /**
     * 특정 Node의 하위 Node를 리턴
     *
     * @param node      Node
     * @param tagName   하위 Node 검색 대상명
     * @return Node     특정 Node의 하위 Node
     */
    public static Node getSubNode(Node node, String tagName){
        if(node == null)
            return null;

        NodeList children = node.getChildNodes();

        for(int innerLoop = 0; innerLoop < children.getLength(); innerLoop++){
            Node child = children.item(innerLoop);
            if( (child != null) && (child.getNodeName() != null) &&
               child.getNodeName().equals(tagName)){
                Node grandChild = child.getFirstChild();
                if(grandChild != null && grandChild.getNodeValue() != null)
                    return grandChild;
            }
        }
        return null;
    }

    /**
     * 특정 root의 해당 Node의 하위Tag의 속성값을 리턴
     *
     * @param root          Root Element
     * @param tagName       Root Element의 하위 Element리스트의 검색 대상명
     * @param subTagName    특정 root의 해당 Node의 하위Tag명
     * @param attribute     검색 대상 속성값
     * @return String       특정 root의 해당 Node의 하위Tag의 속성값
     */
    public static String getSubTagAttribute(Element root, String tagName,
                                            String subTagName, String attribute){
        String returnString = "";
        NodeList list = root.getElementsByTagName(tagName);
        for(int loop = 0; loop < list.getLength(); loop++){
            Node node = list.item(loop);
            if(node != null){
                NodeList children = node.getChildNodes();
                for(int innerLoop = 0; innerLoop < children.getLength(); innerLoop++){
                    Node child = children.item(innerLoop);
                    if( (child != null) && (child.getNodeName() != null) && child.getNodeName().equals(subTagName)){
                        if(child instanceof Element)
                            return( (Element) child).getAttribute(attribute);
                    }
                }
            }
        }
        return returnString;
    }

    /**
     * 특정 root의 해당 Node의 하위Tag의 값을 리턴
     *
     * @param root          Root Element
     * @param tagName       Root Element의 하위 Element리스트의 검색 대상명
     * @param subTagName    생성될 하위 Node명
     * @return String       특정 root의 태그이름의 하위 태그이름에 해당하는 Node의 value값
     */
    public static String getSubTagValue(Element root, String tagName, String subTagName){
        Node rnode = getSubNode(root, tagName, subTagName);
        if(rnode == null)
            return "";
        return rnode.getNodeValue();
    }

    /**
     * 해당 Node의 하위Tag의 값을 리턴
     *
     * @param node          검색대상 Node
     * @param subTagName    Node의 하위 tag명
     * @return String       해당 Node의 하위Tag의 값
     */
    public static String getSubTagValue(Node node, String subTagName){
        Node rnode = getSubNode(node, subTagName);
        if(rnode == null)
            return "";
        return rnode.getNodeValue();
    }

    /**
     * 특정 root의 해당 Tag의 값을 리턴
     *
     * @param root      Root Element명
     * @param tagName   특정 root의 해당 Tag명
     * @return String   특정 root의 해당 Tag의 값
     */
    public static String getTagValue(Element root, String tagName){
        String returnString = "";
        NodeList list = root.getElementsByTagName(tagName);
        for(int loop = 0; loop < list.getLength(); loop++){
            Node node = list.item(loop);
            if(node != null){
                Node child = node.getFirstChild();
                if( (child != null) && child.getNodeValue() != null)
                    return child.getNodeValue();
            }
        }
        return returnString;
    }

    /**
     * xml 문서로 부터 Document객체를 생성하고 해당 노드를 초기화 한다.
     *
     * @param location          XML파일의 이름
     * @param inputStream       XML파일의 위치 경로
     * @return Element          초기화된 Element
     * @throws java.lang.Exception
     */
    public static Element loadDocument(String location, InputStream inputStream)
    throws Exception{

        try{
            InputSource xmlInp = new InputSource(inputStream);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlInp);
            Element root = doc.getDocumentElement();
            root.normalize();
            return root;
        } catch(SAXParseException spe){
            throw spe;
        } catch(SAXException se){
            throw se;
        } catch(java.net.MalformedURLException mux){
            throw mux;
        } catch(java.io.IOException ioe){
            throw ioe;
        } catch(Exception e){
            throw e;
        }
    }
}
