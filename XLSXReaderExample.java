/*
 * Developed By Andre Potgieter
 * ajpotgieter1@gmail.com
 *
 * */
package ReadExcelFileDemo;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

public class XLSXReaderExample {


    static double   freeCall = 0.00;
    //Patch 1.0 Starts here to add a condition for certain called numbers to have 0 rates applied to them.
    boolean zeroRateNum = false;

    public XLSXReaderExample(String excelPath, String exportPath, double vans, double vodacomMobile,
                             double mntMobile, double cellCMobile, double telkomMobile, double internationalCalls,
                             double telkomFixed, double liquidTelecoms) {

        WriteFile fileWriter = new WriteFile();


        ArrayList<Double> duration = new ArrayList<Double>(10);
        ArrayList<String> callDef = new ArrayList<String>(10);
        ArrayList<Double> costs = new ArrayList<Double>(10);
        ArrayList<Double> newCosts = new ArrayList<Double>(10);
        ArrayList<String> callingNo = new ArrayList<String>(10);
        ArrayList<String> noCalled = new ArrayList<String>(10);
        ArrayList<Date> callDate = new ArrayList<Date>(10);
        ArrayList<String> callTime = new ArrayList<String>(10);



        String writtenExcelPath;

        try {

            File file = new File(excelPath);   //creating a new file instance
//TO loop through directory for xlsx

            File[] directoryListing = file.listFiles();

            if (directoryListing != null) {
                for (File child : directoryListing) {

                    //GUI has to set filepath and new name
                    writtenExcelPath =exportPath+"\\"+" Edited"+child.getName();


            FileInputStream fis = new FileInputStream(child);   //obtaining bytes from the file
//creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object

            CollectionUtils.addIgnoreNull(duration, null);
            CollectionUtils.addIgnoreNull(callDef, null);

            int firstRow = 9;
            int lastRow = sheet.getLastRowNum();


            for (int i = firstRow; i <= lastRow; i++) {


                Row row = sheet.getRow(i);

                if (sheet.getRow(i) != null) {


                    for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {


                        Cell cell = row.getCell(j);


                        switch (cell.getColumnIndex()) {

                            case 2:    //points to column
                                if (cell.getStringCellValue() != null && cell.getStringCellValue().length() != 0)
                                    callingNo.add(cell.getStringCellValue().trim());

                                break;
                            case 3:    //points to column
                                if (cell.getStringCellValue() != null && cell.getStringCellValue().length() != 0){
                                    noCalled.add(cell.getStringCellValue().trim());
                                }

                                try {
                                    //Patch 1.0
                                    if(cell.getStringCellValue().startsWith("27800")){
                                        zeroRateNum =true;
                                    }
                                }catch (NullPointerException e){
                                    e.printStackTrace();
                                }


                                break;
                            case 4:    //points to column

                                if (cell.getStringCellValue() != null && cell.getStringCellValue().length() != 0){

                                    if(zeroRateNum == false){
                                        callDef.add(cell.getStringCellValue().trim());
                                    }else{
                                        callDef.add("Toll Free");
                                        zeroRateNum =false;
                                    }
                                }



                                break;
//new
                            case 5:    //points to column

                                if (cell.getDateCellValue() != null)

                                    callDate.add(cell.getDateCellValue());
                                break;

                            case 6:    //points to column

                                if (cell.getStringCellValue() != null && cell.getStringCellValue().length() != 0)

                                    callTime.add(cell.getStringCellValue().trim());
                                break;

                            case 7:
                                duration.add(cell.getNumericCellValue()); //add to arraylist
                                break;

                            case 8:
                                costs.add(cell.getNumericCellValue()); //add to arraylist
                                break;

                        }
                    }
                }
            }
                    //ensures that interational's calculations ignore empty cells fetched from excel sheet.
                    for(int j = 0;j<callDef.size();j++) {
                        if (duration.get(j) == 0 && costs.get(j) == 0) {
                            duration.remove(j);
                            costs.remove(j);
                        }
                    }

                        //removes 0.0 ie null
            //do not remove
            do {
                duration.remove(0.0);
            } while (duration.contains(0.0));

            double formulaDuration;
            String caseCallDef;
            double costsForIcalc; //International's calculations
            int x = 0;



                do {

                    formulaDuration = duration.get(x);
                    caseCallDef = callDef.get(x);
                    costsForIcalc = costs.get(x);



                    switch (caseCallDef) { //switch filters by call deff to determine rates and then adds them to newCosts arraylist.

                        case "VoIP to Vodacom":

                            newCosts.add((formulaDuration / 60) * vodacomMobile);
                            break;

                        case "VoIP to MTN":
                            newCosts.add((formulaDuration / 60) * mntMobile);
                            break;

                        case "VoIP Free Call":
                            newCosts.add((formulaDuration / 60) * freeCall);
                            break;

                        case "Toll Free":
                            newCosts.add((formulaDuration / 60) * freeCall);
                            break;

                        case "VoIP to VANs":
                            newCosts.add((formulaDuration / 60) * vans);
                            break;

                        case "VoIP to Cell C":
                            newCosts.add((formulaDuration / 60) * cellCMobile);
                            break;

                        case "VoIP to Telkom":
                            newCosts.add((formulaDuration / 60) * telkomFixed);
                            break;

                        case "VoIP to Telkom Mobile":
                            newCosts.add((formulaDuration / 60) * telkomMobile);
                            break;

                        case "VoIP to Liquid Telecoms":
                            newCosts.add((formulaDuration / 60) * liquidTelecoms);
                            break;

                        case "VoIP International Calls":
                            newCosts.add(costsForIcalc * internationalCalls);
                            break;

                    }


                    x++;
                } while (x < callDef.size());




            //call exporter
            fileWriter.writer(duration, newCosts, callingNo, noCalled, callDate,callDef, callTime, writtenExcelPath);

            //ZERO arraylists for the next use on a separate file

                    duration.clear();
                    callDef.clear();
                    costs.clear();
                    newCosts.clear();
                    callingNo.clear();
                    noCalled.clear();
                    callDate.clear();
                    callTime.clear();
                    //clear path
                    writtenExcelPath ="";

        } //for's
        } //if's
                } catch(Exception e){
                    e.printStackTrace();
                }


            }


}