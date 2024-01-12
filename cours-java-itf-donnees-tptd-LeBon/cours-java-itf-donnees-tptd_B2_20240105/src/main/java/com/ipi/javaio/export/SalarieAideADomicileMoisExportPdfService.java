package com.ipi.javaio.export;

import com.ipi.javaio.model.SalarieAideADomicile;
import com.ipi.javaio.model.SalarieAideADomicileMois;
import com.ipi.javaio.repository.SalarieAideADomicileMoisRepository;
import com.ipi.javaio.service.SalarieAideADomicileService;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import org.apache.poi.xddf.usermodel.text.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class SalarieAideADomicileMoisExportPdfService {

    DateTimeFormatter frenchDateTimeFormatter = DateTimeFormatter.ofPattern("'MMM' yyyy");

    @Autowired
    private final SalarieAideADomicileMoisRepository salarieAideADomicileMoisRepository;
    @Autowired
    private final SalarieAideADomicileService salarieAideADomicileService;

    public SalarieAideADomicileMoisExportPdfService(
            SalarieAideADomicileMoisRepository salarieAideADomicileMoisRepository,
            SalarieAideADomicileService salarieAideADomicileService) {
        this.salarieAideADomicileMoisRepository = salarieAideADomicileMoisRepository;
        this.salarieAideADomicileService = salarieAideADomicileService;
    }

    public void export(OutputStream outputStream) throws IOException {
        Iterable<SalarieAideADomicileMois> allMois = salarieAideADomicileMoisRepository.findAll();
        exportBase(outputStream, allMois);
    }

    public void export(OutputStream outputStream, Long salarieId/*, LocalDate premierDuMois*/) throws IOException {
        SalarieAideADomicile salarieAideADomicile = salarieAideADomicileService.getSalarie(salarieId);
        //SalarieAideADomicileMois mois = salarieAideADomicileMoisRepository
        //        .findBySalarieAideADomicileAndMois(salarieAideADomicile, premierDuMois).get();
        List<SalarieAideADomicileMois> allMois = salarieAideADomicileMoisRepository
                .findBySalarieAideADomicile(salarieAideADomicile);
        exportBase(outputStream, allMois);
    }

    public void exportBase(OutputStream outputStream, Iterable<SalarieAideADomicileMois> allMois) throws IOException {

        // Création d'un document
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdfDoc);

        Paragraph title = new Paragraph("Fiche de paie");
        document.add(title);


        // création d'un tableau
        Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();

        // remplissage des cellules du tableau
        table.addCell("Premier du mois");
        // TODO
        table.addCell("Nom");
        for (SalarieAideADomicileMois mois : allMois) {
            table.addCell(mois.getPremierDuMois().toString());

            // TODO
        }
        Cell cellTotalLibelle = new Cell(1, 2);
        cellTotalLibelle.add(new Paragraph("fefez"));
        table.addCell(cellTotalLibelle);

        table.addCell(""); // TODO ?

        document.add(table);

        document.close();

    }

}
