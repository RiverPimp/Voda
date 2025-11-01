/*
 * Developed By Andre Potgieter
 * ajpotgieter1@gmail.com
 *
 * */
package ReadExcelFileDemo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class WriteFile {

    public void writer(ArrayList duration, ArrayList costs,ArrayList callingNo, ArrayList noCalled, ArrayList callDate,ArrayList callDef, ArrayList callTime, String excelPath) {


        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Costs");




        // Style the cell with borders all around.

        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setBold(true);


        XSSFFont redFont = workbook.createFont();
        redFont.setColor(new XSSFColor(new java.awt.Color(255, 0, 0)));


        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THICK);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THICK);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THICK);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.MEDIUM_DASHED);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(font);

        CellStyle styleValues = workbook.createCellStyle();
        styleValues.setBorderBottom(BorderStyle.THIN);
        styleValues.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styleValues.setBorderLeft(BorderStyle.THIN);
        styleValues.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        styleValues.setBorderRight(BorderStyle.THIN);
        styleValues.setRightBorderColor(IndexedColors.BLACK.getIndex());
        styleValues.setBorderTop(BorderStyle.THIN);
        styleValues.setTopBorderColor(IndexedColors.BLACK.getIndex());

        CellStyle styleCosts = workbook.createCellStyle();
        styleCosts.setBorderBottom(BorderStyle.THIN);
        styleCosts.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styleCosts.setBorderLeft(BorderStyle.THIN);
        styleCosts.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        styleCosts.setBorderRight(BorderStyle.THIN);
        styleCosts.setRightBorderColor(IndexedColors.BLACK.getIndex());
        styleCosts.setBorderTop(BorderStyle.THIN);
        styleCosts.setTopBorderColor(IndexedColors.BLACK.getIndex());
        styleCosts.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("R#,##0.00"));

        CellStyle styleDate = workbook.createCellStyle();
        styleDate.setBorderBottom(BorderStyle.THIN);
        styleDate.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styleDate.setBorderLeft(BorderStyle.THIN);
        styleDate.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        styleDate.setBorderRight(BorderStyle.THIN);
        styleDate.setRightBorderColor(IndexedColors.BLACK.getIndex());
        styleDate.setBorderTop(BorderStyle.THIN);
        styleDate.setTopBorderColor(IndexedColors.BLACK.getIndex());
        styleDate.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("d/m/yy"));

        CellStyle styleSeperatedNum = workbook.createCellStyle();
        styleSeperatedNum.setBorderBottom(BorderStyle.THIN);
        styleSeperatedNum.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styleSeperatedNum.setBorderLeft(BorderStyle.THIN);
        styleSeperatedNum.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        styleSeperatedNum.setBorderRight(BorderStyle.THIN);
        styleSeperatedNum.setRightBorderColor(IndexedColors.BLACK.getIndex());
        styleSeperatedNum.setBorderTop(BorderStyle.THIN);
        styleSeperatedNum.setTopBorderColor(IndexedColors.BLACK.getIndex());
        styleSeperatedNum.setFont(redFont);


//Writing duration to Excel file

        Row headerRow = sheet.createRow(0);
//For Creates headers
        for(int col =0;col<8;col++) {

            Cell headerCell = headerRow.createCell(col);//column num


            switch (headerCell.getColumnIndex()){

                case 0:
                    headerCell.setCellValue("Calling Number");
                    headerCell.setCellStyle(style);
                    break;

                case 1:
                    headerCell.setCellValue("Number Called");
                    headerCell.setCellStyle(style);
                    break;

                case 2:
                    headerCell.setCellValue("Call Definition");
                    headerCell.setCellStyle(style);
                    break;

                case 3:
                    headerCell.setCellValue("Call Date");
                    headerCell.setCellStyle(style);
                    break;

                case 4:
                    headerCell.setCellValue("Call Time");
                    headerCell.setCellStyle(style);
                    break;

                case 5:
                    headerCell.setCellValue("Duration");
                    headerCell.setCellStyle(style);
                    break;

                case 6:
                    headerCell.setCellValue("Call Costs");
                    headerCell.setCellStyle(style);
                    break;

                case 7:
                    headerCell.setCellValue("Segregated Numbers");
                    headerCell.setCellStyle(style);
                    break;
            }
//retrieves cell value's length to determine size of cell
            // source https://stackoverflow.com/questions/18983203/how-to-speed-up-autosizing-columns-in-apache-poi
            // changed 1.14 to 1.54
            int width = ((int)(headerCell.getStringCellValue().length() * 1.54388)) * 256;
            sheet.setColumnWidth(col, width);
        }

//adds values to cells
        String callingCellNo ="" /*(String) callingNo.get(0)*/;

        for(int x =0;x<duration.size();x++){

            Row row = sheet.createRow(x+1);







            Cell cellCallingNo = row.createCell(0);//column num
            cellCallingNo.setCellValue((String) callingNo.get(x));
            cellCallingNo.setCellStyle(styleValues);

            Cell cellNoCalled = row.createCell(1);//column num
            cellNoCalled.setCellValue((String) noCalled.get(x));
            cellNoCalled.setCellStyle(styleValues);
//new
            Cell callDefinition = row.createCell(2);//column num
            callDefinition.setCellValue((String) callDef.get(x));
            callDefinition.setCellStyle(styleDate);

            Cell callD = row.createCell(3);//column num
            callD.setCellValue((Date) callDate.get(x));
            callD.setCellStyle(styleDate);

            Cell callT = row.createCell(4);//column num
            callT.setCellValue((String) callTime.get(x));
            callT.setCellStyle(styleValues);

            Cell cellDur = row.createCell(5);//column num
            cellDur.setCellValue((Double) duration.get(x));//reading null values as 0,dont
            cellDur.setCellStyle(styleValues);

            Cell cellCost = row.createCell(6);//column num
            cellCost.setCellValue((Double) costs.get(x));
            cellCost.setCellStyle(styleCosts);


//tries to seperate diff calling numbers with a blank row
                if (!(callingCellNo.equals(callingNo.get(x)))) {

//                    Row rowBlank = sheet.createRow(x);
                    Cell cellBlank = row.createCell(7);
                    cellBlank.setCellValue((String) callingNo.get(x));
                    cellBlank.setCellStyle(styleSeperatedNum);
                }


            callingCellNo= (String) callingNo.get(x);
        }
        try {
            FileOutputStream fout = new FileOutputStream(excelPath);

            workbook.write(fout);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}