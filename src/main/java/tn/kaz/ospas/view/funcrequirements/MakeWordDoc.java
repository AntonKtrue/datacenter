package tn.kaz.ospas.view.funcrequirements;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;

import java.io.*;
import java.math.BigInteger;

/**
 * Created by Anton on 25.01.2017.
 */
public class MakeWordDoc {
    private FuncRequirement funcRequirement;

    public static void main(String[] args) throws IOException {
        //readDoc();
        makeDoc();
    }

//    public MakeWordDoc(FuncRequirement funcRequirement) throws IOException {
//        this.funcRequirement = funcRequirement;
//       // makeDoc();
//        readDoc();
//    }

    private static void readDoc() {
        try {
            FileInputStream fis = new FileInputStream("E:/TEMP/ft.docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            FileOutputStream out = new FileOutputStream("E:/TEMP/simple.docx");

            xdoc.write(out);
            out.close();
            xdoc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void makeDoc() throws IOException {
        XWPFDocument doc = new XWPFDocument();


        //paragraph.setStyle("bold");

        //XWPFParagraph headerP = doc.createParagraph();
        XWPFTable table = doc.createTable();
        CTTbl tab1 = table.getCTTbl();
        CTTblPr pr = tab1.getTblPr();
        CTTblWidth tblW = pr.getTblW();
        tblW.setW(BigInteger.valueOf(5000));
        tblW.setType(STTblWidth.PCT);
        pr.setTblW(tblW);
        pr.unsetTblBorders();
        tab1.setTblPr(pr);
        CTJc jc = pr.addNewJc();
        jc.setVal(STJc.RIGHT);
        pr.setJc(jc);

//        table.setRowBandSize(1);
//        table.setColBandSize(1);
//        table.setWidth(1);
//        table.setCellMargins(1,1,100,30 );
//        table.setStyleID("finest");

        XWPFTableRow row1 = table.getRow(0);

        row1.getCell(0).setText("СОГЛАСОВАНО");
        row1.addNewTableCell().setText("УТВЕРЖДАЮ");
       // headTable.bor
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();

        run.setBold(true);
        run.setItalic(true);
        run.addBreak();
        run.setTextPosition(100);
        run.setStrikeThrough(true);
        run.setText("ФУНКЦИОНАЛЬНОЕ ТРЕБОВАНИЕ");
        run.addTab();
        run.setText("на программно-аппаратную доработку микпропроцессорной системы автоматики");


        XWPFNumbering num1 =  doc.createNumbering();
        CTAbstractNum abstractNum = CTAbstractNum.Factory.newInstance();
        XWPFAbstractNum abs = new XWPFAbstractNum(abstractNum, num1);
        BigInteger id = BigInteger.valueOf(0);
        boolean found = false;
        while(!found)
        {
            Object o = num1.getAbstractNum(id);
            found = (o == null);
            if (!found) id = id.add(BigInteger.ONE);

        }
// assign id
        abs.getAbstractNum().setAbstractNumId(id);
        // add to numbering, should get back same id
        id = num1.addAbstractNum(abs);
        // add to num list, result is numid
        doc.getNumbering().addNum(id);


//
//        XWPFParagraph p1 = doc.createParagraph();
//        p1.setAlignment(ParagraphAlignment.CENTER);
//
//        XWPFRun r1 = p1.createRun();
//        r1.setBold(true);
//        r1.setText(funcRequirement.getStructure().toString());
//        r1.setBold(true);
//        r1.setFontFamily("Courier");
//        r1.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
//        r1.setTextPosition(100);
//
//
//        XWPFParagraph p2 = doc.createParagraph();
//        p2.setAlignment(ParagraphAlignment.RIGHT);
//
//        //BORDERS
//        p2.setBorderBottom(Borders.DOUBLE);
//        p2.setBorderTop(Borders.DOUBLE);
//        p2.setBorderRight(Borders.DOUBLE);
//        p2.setBorderLeft(Borders.DOUBLE);
//        p2.setBorderBetween(Borders.SINGLE);
//
//        XWPFRun r2 = p2.createRun();
//        r2.setText("jumped over the lazy dog");
//        r2.setStrikeThrough(true);
//        r2.setFontSize(20);
//
//        XWPFRun r3 = p2.createRun();
//        r3.setText("and went away");
//        r3.setStrikeThrough(true);
//        r3.setFontSize(20);
//        r3.setSubscript(VerticalAlign.SUPERSCRIPT);
//
//
//        XWPFParagraph p3 = doc.createParagraph();
//        p3.setWordWrapped(true);
//        p3.setPageBreak(true);
//
//        p3.setAlignment(ParagraphAlignment.DISTRIBUTE);
//        p3.setAlignment(ParagraphAlignment.BOTH);
//        //p3.setS(15, LineSpacingRule.EXACT);
//
//        p3.setIndentationFirstLine(600);
//
//
//        XWPFRun r4 = p3.createRun();
//        r4.setTextPosition(20);
//        r4.setText("To be, or not to be: that is the question: "
//                + "Whether 'tis nobler in the mind to suffer "
//                + "The slings and arrows of outrageous fortune, "
//                + "Or to take arms against a sea of troubles, "
//                + "And by opposing end them? To die: to sleep; ");
//        r4.addBreak(BreakType.PAGE);
//        r4.setText("No more; and by a sleep to say we end "
//                + "The heart-ache and the thousand natural shocks "
//                + "That flesh is heir to, 'tis a consummation "
//                + "Devoutly to be wish'd. To die, to sleep; "
//                + "To sleep: perchance to dream: ay, there's the rub; "
//                + ".......");
//        r4.setItalic(true);
////This would imply that this break shall be treated as a simple line break, and break the line after that word:
//
//        XWPFRun r5 = p3.createRun();
//        r5.setTextPosition(-10);
//        r5.setText("For in that sleep of death what dreams may come");
//        r5.addCarriageReturn();
//        r5.setText("When we have shuffled off this mortal coil,"
//                + "Must give us pause: there's the respect"
//                + "That makes calamity of so long life;");
//        r5.addBreak();
//        r5.setText("For who would bear the whips and scorns of time,"
//                + "The oppressor's wrong, the proud man's contumely,");
//
//        r5.addBreak(BreakClear.ALL);
//        r5.setText("The pangs of despised love, the law's delay,"
//                + "The insolence of office and the spurns" + ".......");

        FileOutputStream out = new FileOutputStream("/tmp/simple.docx");
        doc.write(out);
        out.close();
        doc.close();


    }
}
