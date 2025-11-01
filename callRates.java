/*
* Developed By Andre Potgieter
* ajpotgieter1@gmail.com
*
* */
package ReadExcelFileDemo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class callRates {
    static double vans,vodacomMobile,mntMobile,
            cellCMoblie,freeCall,telkomMobile,
            internationalCalls,telkomFixed,
            liquidTelecoms;
    static String rateName;

    static String stringVans = "VoIP to VANs", stringVodacomM = "VoIP to Vodacom", stringMntM = "VoIP to MTN",
            stringCellCM = "VoIP to Cell C" , stringTelkomM = "VoIP to Telkom Mobile",
            stringinternational = "VoIP International Calls", stringTelkomF = "VoIP to Telkom",
            stringLiquidT = "VoIP to Liquid Telecoms", stringFreeCall = "VoIP Free Call";



    public  callRates(){

                rateName = "Default";
                vans = 0.29;
                vodacomMobile = 0.33;
                mntMobile = 0.33;
                cellCMoblie = 0.38;
                freeCall = 0.00;//constant 0
                telkomMobile = 0.38;
                internationalCalls = 1.3;///130% of cost given
                telkomFixed = 0.28;
                liquidTelecoms = 0.29;

    }

    public static double getVans() {
        return vans;
    }

    public static double getVodacomMobile() {
        return vodacomMobile;
    }

    public static double getMntMobile() {
        return mntMobile;
    }

    public static double getCellCMoblie() {
        return cellCMoblie;
    }

    public static double getFreeCall() {
        return freeCall;
    }

    public static double getTelkomMobile() {
        return telkomMobile;
    }

    public static double getInternationalCalls() {
        return internationalCalls;
    }

    public static double getTelkomFixed() {
        return telkomFixed;
    }

    public static double getLiquidTelecoms() {
        return liquidTelecoms;
    }

    public callRates(String dir, String iRateName, Double iVans, Double iVodacomMobile, Double iMntMobile, Double iCellCMobile,
                     Double iTelkomMobile, Double iInternationalCalls, Double iTelkomFixed, Double iLiquidTelecoms){

        rateName = iRateName;
        vans = iVans;
        vodacomMobile = iVodacomMobile;
        mntMobile = iMntMobile;
        cellCMoblie = iCellCMobile;
        freeCall = 0.00;//constant 0
        telkomMobile = iTelkomMobile;
        internationalCalls = iInternationalCalls;
        telkomFixed = iTelkomFixed;
        liquidTelecoms = iLiquidTelecoms;


        Map<String, Double> ratesPerType = new HashMap<>();
            ratesPerType.put(rateName+"VANS", vans);
            ratesPerType.put(rateName+"VM", vodacomMobile);
            ratesPerType.put(rateName+"MNTM", mntMobile);
            ratesPerType.put(rateName+"CCM", cellCMoblie);
            ratesPerType.put(rateName+"FC", freeCall);
            ratesPerType.put(rateName+"TM", telkomMobile);
            ratesPerType.put(rateName+"IC", internationalCalls);
            ratesPerType.put(rateName+"TF", telkomFixed);
            ratesPerType.put(rateName+"LT", liquidTelecoms);

        try {



                FileOutputStream fileOut = new FileOutputStream(dir+"\\RatesDropDown\\"+rateName+".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(ratesPerType);
                out.close();
                fileOut.close();
                System.out.printf("Serialized data is saved in V1.2Auto");


        } catch (IOException i) {
            i.printStackTrace();
        }
    }


}
