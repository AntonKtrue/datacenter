package tn.kaz.ospas.view.funcrequirements;

import org.apache.xpath.SourceTree;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.dom.OdfContentDom;
import org.odftoolkit.odfdom.dom.element.text.TextListElement;
import org.odftoolkit.odfdom.dom.element.text.TextListItemElement;
import org.odftoolkit.odfdom.dom.style.props.OdfTableCellProperties;
import org.odftoolkit.odfdom.incubator.doc.text.OdfTextList;
import org.xml.sax.SAXException;

import java.util.List;

/**
 * Created by Anton on 26.01.2017.
 */
public class MakeOdfDocument {

    public static void main(String[] args) throws Exception {

//        OdfTextDocument doc = OdfTextDocument.loadDocument("E:/TEMP/FT_TEMPLATE.odt");
//        OdfContentDom dom = doc.getContentDom();
//        List<OdfTable> tableList = doc.getTableList();

        System.out.println(1);
        OdfTextDocument doc = OdfTextDocument.newTextDocument();
        OdfTable table = OdfTable.newTable(doc,1,2);
        OdfTableCell cell = table.getCellByPosition(0,0);

        table.getRowByIndex(0).getCellByIndex(0).setStringValue("Привет!");
        table.getRowByIndex(0).getCellByIndex(1).setStringValue("Как дела?");

        OdfTextList list = new OdfTextList(doc.getContentDom());
        for(int i = 1; i < 10 ; i++) {
            TextListItemElement li = list.newTextListItemElement();
            li.newTextListElement ().setTextContent(String.valueOf(i));
        }
        doc.getContentRoot().appendChild(list);
        doc.save("E:/TEMP/testdoc1.odt");

    }
}
