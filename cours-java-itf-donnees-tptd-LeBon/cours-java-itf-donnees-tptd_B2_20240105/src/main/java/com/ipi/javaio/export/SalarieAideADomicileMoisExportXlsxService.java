package com.ipi.javaio.export;

import com.ipi.javaio.model.SalarieAideADomicile;
import com.ipi.javaio.model.SalarieAideADomicileMois;
import com.ipi.javaio.repository.SalarieAideADomicileMoisRepository;
import com.ipi.javaio.service.SalarieAideADomicileService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class SalarieAideADomicileMoisExportXlsxService {


    @Autowired
    private final SalarieAideADomicileMoisRepository salarieAideADomicileMoisRepository;
    @Autowired
    private final SalarieAideADomicileService salarieAideADomicileService;

    public static final int COL_PREMIER_DU_MOIS = 0;
    public static final int COL_ID_SALARIE = 1;
    public static final int COL_NOM = 2;
    public static final int COL_JOUR_TRAVAILLES_ANNEE_N = 3;
    public static final int COL_CONGES_PAYES_ACQUIS_ANNEE_N = 4;
    public static final int COL_ANCIENNETE = 5;


    public SalarieAideADomicileMoisExportXlsxService(
            SalarieAideADomicileMoisRepository salarieAideADomicileMoisRepository,
            SalarieAideADomicileService salarieAideADomicileService) {
        this.salarieAideADomicileMoisRepository = salarieAideADomicileMoisRepository;
        this.salarieAideADomicileService = salarieAideADomicileService;
    }

    public void export(ServletOutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        //Apache POI
        Sheet sheet = workbook.createSheet("Mois des salariés");
        Row headerRow = sheet.createRow(0);

        CellStyle styleHeader = styleColor(workbook);

        Cell cellHeaderPremierDuMois = headerRow.createCell(COL_PREMIER_DU_MOIS); // 0
        cellHeaderPremierDuMois.setCellValue("Premier du mois");
        cellHeaderPremierDuMois.setCellStyle(styleHeader);

        // TODO [TD]


        Cell cellHeaderIDSalarie = headerRow.createCell(COL_ID_SALARIE);
        cellHeaderIDSalarie.setCellValue("ID Salarié");
        cellHeaderIDSalarie.setCellStyle(styleHeader);

        Cell cellHeaderNom = headerRow.createCell(COL_NOM);
        cellHeaderNom.setCellValue("Nom");
        cellHeaderNom.setCellStyle(styleHeader);

        Cell cellHeaderJourTravaillesAnneeN = headerRow.createCell(COL_JOUR_TRAVAILLES_ANNEE_N);
        cellHeaderJourTravaillesAnneeN.setCellValue("JourTravaillesAnneeN");
        cellHeaderJourTravaillesAnneeN.setCellStyle(styleHeader);

        Cell cellHeaderCongesPayesAcquisAnneeN = headerRow.createCell(COL_CONGES_PAYES_ACQUIS_ANNEE_N);
        cellHeaderCongesPayesAcquisAnneeN.setCellValue("CongesPayesAcquisAnneeN");
        cellHeaderCongesPayesAcquisAnneeN.setCellStyle(styleHeader);

        Cell cellHeaderAnciennete = headerRow.createCell(COL_ANCIENNETE);
        cellHeaderAnciennete.setCellValue("Anciennete");
        cellHeaderAnciennete.setCellStyle(styleHeader);


        CellStyle cellStyleBorder = newStyleBorder(workbook);

        Iterable<SalarieAideADomicileMois> allMois = salarieAideADomicileMoisRepository.findAll();
        int rowIndex = 1;
        for (SalarieAideADomicileMois mois : allMois) {
            SalarieAideADomicile salarie = mois.getSalarieAideADomicile();
            Row row = sheet.createRow(rowIndex++);

            // TODO [TD]

            Cell cellPremierDuMois = row.createCell(COL_PREMIER_DU_MOIS); // 0
            cellPremierDuMois.setCellValue(mois.getPremierDuMois().toString());
            cellPremierDuMois.setCellStyle(styleHeader);

            Cell cellIDSalarie = row.createCell(COL_ID_SALARIE);
            cellIDSalarie.setCellValue(salarie.getId().toString());
            cellIDSalarie.setCellStyle(styleHeader);

            Cell cellNom = row.createCell(COL_NOM);
            cellNom.setCellValue(salarie.getNom());
            cellNom.setCellStyle(styleHeader);

            Cell cellJourTravailleAnneeN = row.createCell(COL_JOUR_TRAVAILLES_ANNEE_N);
            cellJourTravailleAnneeN.setCellValue(salarie.getJoursTravaillesAnneeN());
            cellJourTravailleAnneeN.setCellStyle(styleHeader);

            Cell cellCongesPayesAcquisAnneeN = row.createCell(COL_CONGES_PAYES_ACQUIS_ANNEE_N);
            cellCongesPayesAcquisAnneeN.setCellValue(salarie.getCongesPayesAcquisAnneeN());
            cellCongesPayesAcquisAnneeN.setCellStyle(styleHeader);

            Cell cellAnciennete = row.createCell(COL_ANCIENNETE);
            cellAnciennete.setCellValue(salarie.getMoisDebutContrat().toString());
            cellAnciennete.setCellStyle(styleHeader);



        }

        Sheet sheetSalarieJean = workbook.createSheet("Jean");
        Row entete = sheetSalarieJean.createRow(0);

        Cell enteteIDSalarie = entete.createCell(0);
        enteteIDSalarie.setCellValue("ID Salarié");
        enteteIDSalarie.setCellStyle(styleHeader);

        Cell enteteNom = entete.createCell(1); // 0
        enteteNom.setCellValue("Nom");
        enteteNom.setCellStyle(styleHeader);

        Cell enteteMoisDebutContrat = entete.createCell(2); // 0
        enteteMoisDebutContrat.setCellValue("Mois debut de contrat");
        enteteMoisDebutContrat.setCellStyle(styleHeader);

        Cell enteteMoisEnCours = entete.createCell(3); // 0
        enteteMoisEnCours.setCellValue("Mois en cours");
        enteteMoisEnCours.setCellStyle(styleHeader);


        Cell entetejoursTravaillesAnneeN = entete.createCell(4); // 0
        entetejoursTravaillesAnneeN.setCellValue("JoursTravaillesAnneeN");
        entetejoursTravaillesAnneeN.setCellStyle(styleHeader);




        Sheet sheetSalarieJeannette = workbook.createSheet("Jeannette");
        Row enteteJeannette = sheetSalarieJeannette.createRow(0);

        Cell enteteJeannetteID = enteteJeannette.createCell(0);
        enteteJeannetteID.setCellValue("ID Salarié");
        enteteJeannetteID.setCellStyle(styleHeader);

        Cell enteteJeannetteNom = enteteJeannette.createCell(1); // 0
        enteteJeannetteNom.setCellValue("Nom");
        enteteJeannetteNom.setCellStyle(styleHeader);

        Cell enteteJeannetteMDC = enteteJeannette.createCell(2); // 0
        enteteJeannetteMDC.setCellValue("Mois debut de contrat");
        enteteJeannetteMDC.setCellStyle(styleHeader);

        Cell enteteJeannetteMoisEnCours = enteteJeannette.createCell(3); // 0
        enteteJeannetteMoisEnCours.setCellValue("Mois en cours");
        enteteJeannetteMoisEnCours.setCellStyle(styleHeader);


        Cell enteteJeannetteJoursTravailles = enteteJeannette.createCell(4); // 0
        enteteJeannetteJoursTravailles.setCellValue("JoursTravaillesAnneeN");
        enteteJeannetteJoursTravailles.setCellStyle(styleHeader);

        int lineIndex = 1;
        Iterable<SalarieAideADomicile> salaries = salarieAideADomicileService.getSalaries();
        for (SalarieAideADomicile salarie : salaries ) {


            long id = salarie.getId() ;
            // TODO [TD]
            if ( id == 1) {
                Row line = sheetSalarieJean.createRow(lineIndex++);


                Cell cellIDSalarie = line.createCell(0);
                cellIDSalarie.setCellValue(salarie.getId());
                cellIDSalarie.setCellStyle(styleHeader);

                Cell cellNom = line.createCell(1);
                cellNom.setCellValue(salarie.getNom());
                cellNom.setCellStyle(styleHeader);

                Cell cellAnciennete = line.createCell(2);
                cellAnciennete.setCellValue(salarie.getMoisDebutContrat().toString());
                cellAnciennete.setCellStyle(styleHeader);

                Cell cellMoisEnCours = line.createCell(3);
                cellMoisEnCours.setCellValue(salarie.getMoisEnCours().toString());
                cellMoisEnCours.setCellStyle(styleHeader);

                Cell cellJoursTravaillesAnneeN = line.createCell(4);
                cellJoursTravaillesAnneeN.setCellValue(salarie.getJoursTravaillesAnneeN());
                cellJoursTravaillesAnneeN.setCellStyle(styleHeader);
            } else if (id == 8) {
                Row line = sheetSalarieJeannette.createRow(lineIndex=1);


                Cell cellIDSalarie = line.createCell(0);
                cellIDSalarie.setCellValue(salarie.getId());
                cellIDSalarie.setCellStyle(styleHeader);

                Cell cellNom = line.createCell(1);
                cellNom.setCellValue(salarie.getNom());
                cellNom.setCellStyle(styleHeader);

                Cell cellAnciennete = line.createCell(2);
                cellAnciennete.setCellValue(salarie.getMoisDebutContrat().toString());
                cellAnciennete.setCellStyle(styleHeader);

                Cell cellMoisEnCours = line.createCell(3);
                cellMoisEnCours.setCellValue(salarie.getMoisEnCours().toString());
                cellMoisEnCours.setCellStyle(styleHeader);

                Cell cellJoursTravaillesAnneeN = line.createCell(4);
                cellJoursTravaillesAnneeN.setCellValue(salarie.getJoursTravaillesAnneeN());
                cellJoursTravaillesAnneeN.setCellStyle(styleHeader);

            }
        }



        workbook.write(outputStream);
        workbook.close();

    }

    private CellStyle styleColor(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        setBorderColor(style);

        Font font = workbook.createFont();
        font.setColor(IndexedColors.GREEN.getIndex());
        style.setFont(font);
        return style;
    }

    private CellStyle newStyleBorder(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        setBorderColor(style);
        return style;
    }

    private void setBorderColor(CellStyle style) {
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBottomBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderTop(BorderStyle.MEDIUM);
        style.setTopBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setLeftBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderRight(BorderStyle.MEDIUM);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
    }


}
