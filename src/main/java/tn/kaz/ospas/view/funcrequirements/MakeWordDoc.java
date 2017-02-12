package tn.kaz.ospas.view.funcrequirements;

import com.vaadin.server.StreamResource;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontSize;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import tn.kaz.ospas.model.Config;
import tn.kaz.ospas.model.funcrequirement.FuncRequirement;

import java.io.*;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

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
        //Создание документа
        final XWPFDocument doc = new XWPFDocument();
        // зададим Franklin Gothic Book на весь документ
        XWPFStyles styles = doc.createStyles();
        CTFonts fonts = CTFonts.Factory.newInstance();
        fonts.setEastAsia("Franklin Gothic Book");
        fonts.setHAnsi("Franklin Gothic Book");
        fonts.setAscii("Franklin Gothic Book");



        //Подумать как это использовать
        CTFontSize fontSize = CTFontSize.Factory.newInstance();
        fontSize.setVal(12);
        styles.setDefaultFonts(fonts);

        //Определение полей документа
        CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
        CTPageMar pageMar = sectPr.addNewPgMar();
        pageMar.setLeft(BigInteger.valueOf(Config.cmToTwips(2.2)));
        pageMar.setRight(BigInteger.valueOf(Config.cmToTwips(1.3)));
        pageMar.setTop(BigInteger.valueOf(Config.cmToTwips(1)));
        pageMar.setBottom(BigInteger.valueOf(Config.cmToTwips(1.3)));

        //Таблица с прозрачными полями
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

        XWPFTableRow row1 = table.getRow(0);
        XWPFParagraph cell1 = row1.getCell(0).getParagraphArray(0);

        XWPFRun runcell = cell1.createRun();
        setParagraphOneAndHalfSpacing(cell1);
        runcell.setFontSize(12);
        runcell.setText("СОГЛАСОВАНО");
        runcell.addBreak();
        runcell.setText("Начальник О АСУТП");
        runcell.addBreak();
        runcell.setText("АО \"Транснефть - Прикамье\"");
        runcell.addBreak();
        runcell.setText(fillString("А.Н. Низамутдинов",true, 33));
        runcell.addBreak();
        runcell.setText("\"___\"___________2017г.");

        XWPFParagraph cell2 = row1.createCell().getParagraphArray(0);
        // если в данной ячейке выравнивание нужно сделать по правому краю
        // cell2.setAlignment(ParagraphAlignment.RIGHT);
        setParagraphOneAndHalfSpacing(cell2);
        runcell = cell2.createRun();
        runcell.setFontSize(12);
        runcell.setText("УТВЕРЖДАЮ");
        runcell.addBreak();
        runcell.setText("Заместитель главного инженера по АСУ");
        runcell.addBreak();
        runcell.setText("АО \"Транснефть - Прикамье\"");
        runcell.addBreak();
        runcell.setText(fillString("И.Ф. Гибаев",true, 33));
        runcell.addBreak();
        runcell.setText("\"___\"___________2017г.");


        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setSpacingBeforeLines(350);

        setParagraphOneAndHalfSpacing(paragraph);

        XWPFRun run = paragraph.createRun();
        //Добавление разных стилей
        run.setFontSize(12);
        //run.setBold(true);
        //run.setItalic(true);
        //run.addBreak();
        //run.setTextPosition(100);
        //run.setStrikeThrough(true);

        run.setText("ФУНКЦИОНАЛЬНОЕ ТРЕБОВАНИЕ");
        run.addBreak();
        run.setText("ФТ - АО Транснефть – Прикамье - Ромашкинское РНУ – 10-41-05–2016 - сентябрь – 222");
        run.addBreak();
        run.setText("на программно-аппаратную доработку микропроцессорной системы автоматики");
        run.addBreak();
        run.setText("НПС \"Елизаветинка-4\"");

        XWPFParagraph para1 = doc.createParagraph();
        setParagraphOneAndHalfSpacing(para1);

        para1.setSpacingBeforeLines(200);

        run = para1.createRun();
        run.setFontSize(12);
        run.setText("1. Основание");
        run.addBreak();
        run.setText("1.1 Письмо №АК-15-01-03/35855 от 04.07.2016");
        run.addBreak();
        run.setText("1.2 Мероприятия по корректировке проектных алгоритмов, доработке СА и СДКУ для недопущения превышения допустимого рабочего давления в линейной части трубопроводов АО \"Транснефть-Прикамье\" от 21.07.2016.");
        XWPFParagraph para2 = doc.createParagraph();
        setParagraphOneAndHalfSpacing(para2);
        para2.setSpacingBeforeLines(200);


        run = para2.createRun();
        run.setFontSize(12);
        run.setText("2. Описание доработки");
        run.addBreak();
        run.setText("Доработать ПО СУ, ВУ МПСА НПС «Елизаветинка-4» с учетом выполнения следующих тре-бований:");
        run.addBreak();
        run.setText("2.1 Доработать алгоритм формирования защит в МПСА НПС «Елизаветинка-4»:");
        run.addBreak();
        run.setText("- Верхний аварийный уровень в резервуаре №1 (Аварийный максимальный уровень в емкости РГС-200 №1);");
        run.addBreak();
        run.setText("- Верхний аварийный уровень в резервуаре №2 (Аварийный максимальный уровень в емкости РГС-200 №2);");
        run.addBreak();
        run.setText("- Верхний аварийный уровень в резервуаре №3 (Аварийный максимальный уровень в емкости РГС-200 №3);");
        run.addBreak();
        run.setText("- Верхний аварийный уровень в резервуаре №4 (Аварийный максимальный уровень в емкости РГС-200 №4);");
        run.addBreak();
        run.setText("алгоритмами данных защит предусмотреть:");
        run.addBreak();
        run.setText("- закрытие задвижки на входе в НПС «Елизаветинка-4» № 169, 170*, 150, 148 только по-сле получения сигнала (ТУ) технологический участок МН «Байтуган - Елизаветинка» Ду150/200 остановлен.");
        run.addBreak();

        //СПИСКИ
//        XWPFNumbering numbering = null;
//        XWPFNum num = null;
//        List<XWPFParagraph> paraList = null;
//        Iterator<XWPFParagraph> paraIter = null;
//        BigInteger numID = null;
//        int numberingID = -1;
//
//        numbering = doc.getNumbering();
//
//        for(int i = 0; i < 5; i++) {
//            XWPFParagraph p1 = doc.createParagraph();
//            XWPFRun r1 = p1.createRun();
//            r1.setText("text for iteration " + i);
//            paraList.add(p1);
//        }
//
//        paraIter = paraList.iterator();
//        while(paraIter.hasNext()) {
//            XWPFParagraph p1 = paraIter.next();
//            numID = p1.getNumID();
//            if(numID != null) {
//                if(numID.intValue() != numberingID) {
//                    num = numbering.getNum(numID);
//                    numberingID = numID.intValue();
//                } else {
//
//                }
//            }
//        }









//        XWPFNumbering num1 =  doc.createNumbering();
//        CTAbstractNum abstractNum = CTAbstractNum.Factory.newInstance();
//        XWPFAbstractNum abs = new XWPFAbstractNum(abstractNum, num1);
//        BigInteger id = BigInteger.valueOf(0);
//        boolean found = false;
//        while(!found)
//        {
//            Object o = num1.getAbstractNum(id);
//            found = (o == null);
//            if (!found) id = id.add(BigInteger.ONE);
//
//        }
//
//        abs.getAbstractNum().setAbstractNumId(id);
//
//        id = num1.addAbstractNum(abs);
//        doc.getNumbering().addNum(id);


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

        final FileOutputStream out = new FileOutputStream("E:/TEMP/simple.docx");
        doc.write(out);
        out.close();
        doc.close();
    }



    private static void setParagraphOneAndHalfSpacing(XWPFParagraph paragraph) {
        CTPPr cell1ppr = paragraph.getCTP().getPPr();
        if (cell1ppr == null) cell1ppr = paragraph.getCTP().addNewPPr();
        CTSpacing spacing = cell1ppr.isSetSpacing() ? cell1ppr.getSpacing() : cell1ppr.addNewSpacing();
        spacing.setAfter(BigInteger.valueOf(0));
        spacing.setBefore(BigInteger.valueOf(0));
        spacing.setLineRule(STLineSpacingRule.AUTO);
        spacing.setLine(BigInteger.valueOf(360));
    }

    private static String fillString(String base, boolean left, int size) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < (size - base.length()); i++) {
            result.append('_');
        }
        if(left) {
            return result.toString() + base;
        } else {
            return base + result.toString();
        }
    }


}
