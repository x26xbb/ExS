package exs.pdf;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import exs.logs.err.Log;
import exs.mod.Estudiante;
import exs.mod.Grupo;
import exs.mod.var.Estudiante_Var;
import exs.mod.var.Matricula_Var;
import java.io.FileOutputStream;

public class PDF_Writer {

    private static String UNA_LOGO = "img/una_logo.png";
    private static String E_LOGO = "img/e_logo.jpg";
    private static String FILE = "ima.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private static Font smallNormal = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);

    public static boolean CreateMatricula(Estudiante estudiante, String curso, String carrera, Grupo grupo, String path) {
        try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            addMetaData(document);
            addTop(document);

            addPersonalInfo(document, estudiante, carrera);
            addTutoriaInfo(document, grupo, curso);

            document.newPage();
            addTop(document);
            addTitle(document);
            addTableContrato(document);
            addContrato(document, estudiante, curso, carrera);

            document.close();
        } catch (Exception e) {
            Log.SendLog(e.getMessage());
            return false;
        }

        return true;
    }

    private static void addTop(Document document)
            throws Exception {
        Image una_image = Image.getInstance(UNA_LOGO);
        Image e_image = Image.getInstance(E_LOGO);
        una_image.setAbsolutePosition(50f, 740f);
        e_image.setAbsolutePosition(475f, 745f);
        document.add(una_image);
        document.add(e_image);
        addEmptyLine(document, 5);
    }

    private static void addTutoriaInfo(Document document, Grupo grupo, String curso)
            throws Exception {
        addEmptyLine(document, 2);
        document.add(new Paragraph("Tutoría Matriculada", smallBold));
        addLine(document);
        document.add(new Paragraph("Curso: " + curso + "\nGrupo: " + grupo.getNum(), smallNormal));
        document.add(new Paragraph("Horario: " + grupo._getHorario(), smallNormal));
        document.add(new Paragraph("Lugar: " + grupo.getLugar(), smallNormal));
        document.add(new Paragraph("Estado: " + grupo.getEstado(), smallNormal));
    }

    private static void addPersonalInfo(Document document, Estudiante estudiante, String carrera)
            throws Exception {

        document.add(new Paragraph("Información Personal", smallBold));
        addLine(document);
        document.add(new Paragraph("Identificación: " + estudiante.getId(), smallNormal));
        document.add(new Paragraph("Nombre: " + estudiante.getFullName(), smallNormal));
        document.add(new Paragraph("Télefono: " + estudiante.getTelefono() + "\t\t\t Celular: " + estudiante.getCelular(), smallNormal));
        document.add(new Paragraph("E-Mail: " + estudiante.getEmail(), smallNormal));
        addEmptyLine(document, 2);
        document.add(new Paragraph("Información Académica", smallBold));
        addLine(document);
        document.add(new Paragraph("Carrera: " + carrera + "\nSede: " + Estudiante_Var.getSede(estudiante.getSede()), smallNormal));
        document.add(new Paragraph("Beca: " + Estudiante_Var.getBeca(estudiante.getBeca()), smallNormal));
    }

    private static void addTitle(Document document) throws Exception {
        Paragraph paragraph = new Paragraph("Contrato de compromiso personal.", smallBold);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        addEmptyLine(document, 2);

    }

    private static void addMetaData(Document document) {
        document.addTitle("Reporte de Matricula");
        document.addSubject("Matricula");
        document.addKeywords("Matricula, ExS, Java");
        document.addAuthor("Kevin Villalobos A.");
        document.addCreator("Kevin Villalobos A.");
    }

    private static void addTableContrato(Document document)
            throws Exception {
        document.add(new Paragraph("Derechos y compromisos por asistir a la tutoría.", smallNormal));
        addEmptyLine(document, 1);
        createTable(document);
    }

    private static void addTitlePage(Document document)
            throws Exception {
        Paragraph preface = new Paragraph();
        addEmptyLine(document, 5);

    }

    private static void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("First Chapter", catFont);
        anchor.setName("First Chapter");

        //paragraph.setAlignment(Element.ALIGN_CENTER);

        //Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Hello"));

        subPara = new Paragraph("Subcategory 2", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Paragraph 1"));
        subCatPart.add(new Paragraph("Paragraph 2"));
        subCatPart.add(new Paragraph("Paragraph 3"));

        // Add a list
        createList(subCatPart);
        Paragraph paragraph = new Paragraph();
        // addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);

        // Add a table
        // createTable(subCatPart);

        // Now add all this to the document
        document.add(catPart);

        // Next section
        anchor = new Anchor("Second Chapter", catFont);
        anchor.setName("Second Chapter");

        // Second parameter is the number of the chapter
        catPart = new Chapter(new Paragraph(anchor), 1);

        subPara = new Paragraph("Subcategory", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("This is a very important message"));

        // Now add all this to the document
        document.add(catPart);

    }

    private static void createTable(Document document)
            throws Exception {
        PdfPTable table = new PdfPTable(2);
        PdfPCell c1 = new PdfPCell(new Phrase("Derechos"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Compromisos"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        table.addCell(Matricula_Var.RESPONSABILIDADES);
        table.addCell(Matricula_Var.COMPROMISOS);
        document.add(table);
    }

    private static void addContrato(Document document, Estudiante estudiante, String curso, String carrera)
            throws Exception {
        addEmptyLine(document, 2);
        String firma = String.format(Matricula_Var.CONTRATO, estudiante.getNombre() + " " + estudiante.getPriApellido() + " " + estudiante.getSegApellido(), estudiante.getId() + "", curso, carrera);
        document.add(new Paragraph(firma, smallNormal));
    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Document document, int number) throws Exception {
        for (int i = 0; i < number; i++) {
            document.add(new Paragraph(" "));
        }
    }

    private static void addLine(Document document) throws Exception {
        document.add(new Paragraph("_________________________________________________", catFont));
    }
}
