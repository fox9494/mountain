package org.chenll.util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenlile on 17-5-8.
 */
public class XMLUtils {

    /**
     * XML格式字符串转换为Map
     *只支持简单的一维，不支持嵌套
     * @param strXML XML字符串
     * @return XML数据转换后的Map
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
        InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
        org.w3c.dom.Document doc = documentBuilder.parse(stream);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int idx=0; idx<nodeList.getLength(); ++idx) {
            Node node = nodeList.item(idx);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                data.put(element.getNodeName(), element.getTextContent());
            }
        }
        try {
            stream.close();
        }
        catch (Exception ex) {

        }
        return data;
    }

    /**
     * 将Map转换为XML格式的字符串
     *（支持简单的字符串转，一维的，不支持嵌套）
     * @param data Map类型数据
     * @return XML格式的字符串
     * @throws Exception
     */
    public static String mapToXml(Map<String, String> data) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
        org.w3c.dom.Document document = documentBuilder.newDocument();
        org.w3c.dom.Element root = document.createElement("xml");
        document.appendChild(root);
        for (String key: data.keySet()) {
            String value = data.get(key);
            if (value == null) {
                value = "";
            }
            value = value.trim();
            org.w3c.dom.Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
        try {
            writer.close();
        }
        catch (Exception ex) {
        }
        return output;
    }


    public static void main(String[] args) throws Exception {
        String xml="<xml>\n" +
                "  <appid><![CDATA[wx947e188ed993ecca]]></appid>\n" +
                "  <attach><![CDATA[{\"payType\":\"DEPOSIT\",\"uuid\":\"BLTKZ0x5Wfz/5+1cCDj+yg\\u003d\\u003d\"}]]></attach>\n" +
                "  <bank_type><![CDATA[CFT]]></bank_type>\n" +
                "  <cash_fee><![CDATA[1]]></cash_fee>\n" +
                "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
                "  <is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                "  <mch_id><![CDATA[1459909802]]></mch_id>\n" +
                "  <nonce_str><![CDATA[9ecbc25d51954220af045917764a72bf]]></nonce_str>\n" +
                "  <openid><![CDATA[oRx_H068oimYQ9Cb3A8XX7DtuyiA]]></openid>\n" +
                "  <out_trade_no><![CDATA[309738353920708608]]></out_trade_no>\n" +
                "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <sign>\n" +
                "\t<ab>myk</ab>\n" +
                "\t<cd>myk</cd>\n" +
                "  </sign>\n" +
                "  <time_end><![CDATA[20170504170952]]></time_end>\n" +
                "  <total_fee>1</total_fee>\n" +
                "  <trade_type><![CDATA[APP]]></trade_type>\n" +
                "  <transaction_id><![CDATA[4000122001201705049536239744]]></transaction_id>\n" +
                "</xml>";
        Map<String, String> map = XMLUtils.xmlToMap(xml);
        System.out.println("map = " + map);
    }
}
