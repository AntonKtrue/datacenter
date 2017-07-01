package tn.kaz.ospas;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Anton on 14.04.2017.
 */
public class XmlToDb {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, SQLException {

        Connection connection;
        DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test_op?user=root&password=90559067&characterEncoding=UTF-8");
        connection.createStatement().execute("TRUNCATE TABLE `answers`");
        connection.createStatement().execute("TRUNCATE TABLE `questions`");


        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse("src/main/resources/ans_ques.xml");
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        XPathExpression questionsExpression = xPath.compile("/test/questions/question");
        XPathExpression answersExpression = xPath.compile("/test/answers/answer");
        NodeList questions = (NodeList) questionsExpression.evaluate(document, XPathConstants.NODESET);
        NodeList answers = (NodeList) answersExpression.evaluate(document, XPathConstants.NODESET);
        PreparedStatement questionStatement = connection.prepareStatement(
                "INSERT INTO questions (id, name, cost) VALUES (?, ?, ?)"
        );
        PreparedStatement answersStatement = connection.prepareStatement(
          "INSERT INTO answers (ID_N, VAL, NAME) VALUES (?, ?, ?)"
        );
        for(int i = 0; i < questions.getLength(); i++ ) {
            Node node = questions.item(i);
            Attr qnum = (Attr) node.getAttributes().getNamedItem("qnum");
            String text = node.getTextContent();
            System.out.println(
                    "Number:" + qnum + "; Question: " + text
            );
            questionStatement.setInt(1, Integer.valueOf(qnum.getValue()));
            questionStatement.setString(2, text);
            questionStatement.setInt(3, 1);
            questionStatement.executeUpdate();
        }

        for(int i = 0; i < answers.getLength(); i++ ) {
            Node node = answers.item(i);
            Attr qnum = (Attr) node.getAttributes().getNamedItem("qnum");
            Attr trueVal = (Attr) node.getAttributes().getNamedItem("true");
            String text = node.getTextContent();
            System.out.println(
                    "Number:" + qnum + "; Answer: " + text +
                            ((trueVal != null) ? "true" : "")

            );
            answersStatement.setInt(1, Integer.valueOf(qnum.getValue()));
            answersStatement.setInt(2, (trueVal != null ? 1 : 0));
            answersStatement.setString(3, text);
            answersStatement.executeUpdate();
        }



    }
}
