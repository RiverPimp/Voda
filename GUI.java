/*
 * Developed By Andre Potgieter
 * ajpotgieter1@gmail.com
 *
 * */
package ReadExcelFileDemo;

import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class GUI extends JFrame implements ActionListener{


    ArrayList<String> comboBoxValues = new ArrayList<>(10);

    JFrame f;
    JDialog jDialogPath;
    JButton btnChooseImport, btnExecute, btnChooseExport, btnSaveRates, btnRestart;
    JLabel fPathImportLable,fPathExportLable,rateTypePickerLable,
            vansLable,vodaMobileLable,mtnMobileLable,cellCMobileLable,
            telkomMobileLable,internCallLable,telkomFixedLable,liquidTelecomsLable,
            rateNameLable;
    //free calls need to be static value
    JTextField jTextFilePathImport,jTextFilePathExport,
                jTextVans,jTextVodaMobile,jTextMtnMobile,jTextCellCMobile,jTextTelkomMobile,
                jTextInternCall,jTextTelkomFixed,jTextLiquidTelecoms,
                jTextRateName;
    JComboBox ratePicker;
    JPanel panel;
    String dir;
    String pickedRate = "Default";
    callRates defaultRate = new callRates();
    HashMap<String, Double> newHashMap = null;




    public GUI() {
        dir = System.getProperty("user.dir");

        // directory from where the program was launched
        // e.g /home/mkyong/projects/core-java/java-io
        System.out.println(dir);

        Path path = Paths.get("\\V1.2Auto\\RatesDropDown");

        System.out.println( path.toAbsolutePath());




        //first combo entry
        comboBoxValues.add("Default");
        File fileFolder =new File(dir+"\\RatesDropDown");
        File[] listOfFiles = fileFolder.listFiles();

        for(int x =0;x<listOfFiles.length;x++) {//consider if
            comboBoxValues.add(listOfFiles[x].getName());
        }

        f = new JFrame("Costs Automater");
        f.setSize(350,570);
        f.setLocation(500,50);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        panel = new JPanel();
        panel.setLayout(null);
        f.add(panel);

        //used for testing GUI
//        jDialogPath = new JDialog(f,"Check");
//        JLabel test = new JLabel(dir);
//        jDialogPath.add(test);
//        jDialogPath.setSize(100,50);
//        jDialogPath.setVisible(true);

        fPathImportLable = new JLabel("Specify Folder:");
        fPathImportLable.setBounds(20,20,100,25);
        panel.add(fPathImportLable);

        jTextFilePathImport =new JTextField(50);
        jTextFilePathImport.setBounds(150,20,165,25);
        panel.add(jTextFilePathImport);

        btnChooseImport = new JButton("Choose Folder");
        btnChooseImport.setBounds(30,50,150,25);
        btnChooseImport.addActionListener(this);
        panel.add(btnChooseImport);

        fPathExportLable = new JLabel("Specify Destination:");
        fPathExportLable.setBounds(20,80,130,25);
        panel.add(fPathExportLable);

        jTextFilePathExport =new JTextField(50);
        jTextFilePathExport.setBounds(150,80,165,25);
        panel.add(jTextFilePathExport);

        btnChooseExport = new JButton("Choose Destination");
        btnChooseExport.setBounds(30,110,150,25);
        btnChooseExport.addActionListener(this);
        panel.add(btnChooseExport);

        rateTypePickerLable = new JLabel("Specify Rate type:");
        rateTypePickerLable.setBounds(20,140,170,25);
        panel.add(rateTypePickerLable);

        ratePicker =new JComboBox(comboBoxValues.toArray());
        ratePicker.setBounds(150,140,165,25);
        ratePicker.addActionListener(this);
        panel.add(ratePicker);

        vansLable = new JLabel("VANS Rate:");
        vansLable.setBounds(20,180,100,25);
        panel.add(vansLable);

        jTextVans =new JTextField(50);
        jTextVans.setBounds(220,180,50,25);
        panel.add(jTextVans);

        vodaMobileLable = new JLabel("Vodacom Mobile Rate:");
        vodaMobileLable.setBounds(20,210,150,25);
        panel.add(vodaMobileLable);

        jTextVodaMobile =new JTextField(50);
        jTextVodaMobile.setBounds(220,210,50,25);
        panel.add(jTextVodaMobile);

        mtnMobileLable = new JLabel("MTN Mobile Rate:");
        mtnMobileLable.setBounds(20,240,150,25);
        panel.add(mtnMobileLable);

        jTextMtnMobile =new JTextField(50);
        jTextMtnMobile.setBounds(220,240,50,25);
        panel.add(jTextMtnMobile);

        cellCMobileLable = new JLabel("Cell C Mobile Rate:");
        cellCMobileLable.setBounds(20,270,150,25);
        panel.add(cellCMobileLable);

        jTextCellCMobile =new JTextField(50);
        jTextCellCMobile.setBounds(220,270,50,25);
        panel.add(jTextCellCMobile);

        telkomMobileLable = new JLabel("Telkom Mobile Rate:");
        telkomMobileLable.setBounds(20,300,150,25);
        panel.add(telkomMobileLable);

        jTextTelkomMobile =new JTextField(50);
        jTextTelkomMobile.setBounds(220,300,50,25);
        panel.add(jTextTelkomMobile);

        internCallLable = new JLabel("International Call Rate:");
        internCallLable.setBounds(20,330,150,25);
        panel.add(internCallLable);

        jTextInternCall =new JTextField(50);
        jTextInternCall.setBounds(220,330,50,25);
        panel.add(jTextInternCall);

        telkomFixedLable = new JLabel("Telkom Fixed Rate:");
        telkomFixedLable.setBounds(20,360,150,25);
        panel.add(telkomFixedLable);

        jTextTelkomFixed =new JTextField(50);
        jTextTelkomFixed.setBounds(220,360,50,25);
        panel.add(jTextTelkomFixed);

        liquidTelecomsLable = new JLabel("Liquid Telecoms Rate:");
        liquidTelecomsLable.setBounds(20,390,150,25);
        panel.add(liquidTelecomsLable);

        jTextLiquidTelecoms =new JTextField(50);
        jTextLiquidTelecoms.setBounds(220,390,50,25);
        panel.add(jTextLiquidTelecoms);

        rateTypePickerLable = new JLabel("New rate name:");
        rateTypePickerLable.setBounds(20,420,150,25);
        panel.add(rateTypePickerLable);

        jTextRateName =new JTextField(50);
        jTextRateName.setBounds(150,420,165,25);
        jTextRateName.setToolTipText("Issue a name for the new rate you wish to create.");
        panel.add(jTextRateName);

        btnExecute = new JButton("Run");
        btnExecute.setBounds(190,450,100,25);
        btnExecute.addActionListener(this);
        panel.add(btnExecute);

        btnSaveRates = new JButton("Save Rates");
        btnSaveRates.setBounds(40,450,100,25);
        btnSaveRates.addActionListener(this);
        btnSaveRates.setToolTipText("Add rates and the name given to the Dropdown");
        panel.add(btnSaveRates);

        btnRestart = new JButton("Refresh");
        btnRestart.setBounds(40,480,100,25);
        btnRestart.addActionListener(this);
        btnRestart.setToolTipText("Press to Refresh dropdown");
        panel.add(btnRestart);

        //set defaults on app launch
        jTextVans.setText(String.valueOf(defaultRate.getVans()));
        jTextVodaMobile.setText(String.valueOf(defaultRate.getVodacomMobile()));
        jTextMtnMobile.setText(String.valueOf(defaultRate.getMntMobile()));
        jTextCellCMobile.setText(String.valueOf(defaultRate.getCellCMoblie()));
        jTextTelkomMobile.setText(String.valueOf(defaultRate.getTelkomMobile()));
        jTextInternCall.setText(String.valueOf(defaultRate.getInternationalCalls()));
        jTextTelkomFixed.setText(String.valueOf(defaultRate.getTelkomFixed()));
        jTextLiquidTelecoms.setText(String.valueOf(defaultRate.getLiquidTelecoms()));


        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource()== btnChooseImport) {
            JFileChooser chooseImport = new JFileChooser();
            chooseImport.setDialogTitle("Pick the Directory that contains the CDRs");
            chooseImport.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int i = chooseImport.showOpenDialog(this);
            if (i == JFileChooser.APPROVE_OPTION) {
                File importf = chooseImport.getSelectedFile();
                String importFilepath = importf.getPath();
                jTextFilePathImport.setText(importFilepath);
            }
        }else if(e.getSource()== btnChooseExport){
                JFileChooser chooseExport=new JFileChooser();
                chooseExport.setDialogTitle("Pick the Directory to save Edits to");
                chooseExport.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int j=chooseExport.showOpenDialog(this);
                if(j==JFileChooser.APPROVE_OPTION){
                    File exportf=chooseExport.getSelectedFile();
                    String exportFilepath=exportf.getPath();
                    jTextFilePathExport.setText(exportFilepath);
                }

                //passes values to callRates to serialize data
        }else if (e.getSource() == btnSaveRates){
            callRates saveRate = new callRates(dir, jTextRateName.getText(),Double.parseDouble(jTextVans.getText()),Double.parseDouble(jTextVodaMobile.getText()),
                    Double.parseDouble(jTextMtnMobile.getText()),Double.parseDouble(jTextCellCMobile.getText()),Double.parseDouble(jTextTelkomMobile.getText()),
                    Double.parseDouble(jTextInternCall.getText()),Double.parseDouble(jTextTelkomFixed.getText()),Double.parseDouble(jTextLiquidTelecoms.getText()));




//                callRates saveRate = new callRates();
        }else if (e.getSource() == ratePicker){
           pickedRate = (String) ratePicker.getSelectedItem();



               try {

                   if (pickedRate.equals("Default")) {
                       //set default
                       jTextVans.setText(String.valueOf(defaultRate.getVans()));
                       jTextVodaMobile.setText(String.valueOf(defaultRate.getVodacomMobile()));
                       jTextMtnMobile.setText(String.valueOf(defaultRate.getMntMobile()));
                       jTextCellCMobile.setText(String.valueOf(defaultRate.getCellCMoblie()));
                       jTextTelkomMobile.setText(String.valueOf(defaultRate.getTelkomMobile()));
                       jTextInternCall.setText(String.valueOf(defaultRate.getInternationalCalls()));
                       jTextTelkomFixed.setText(String.valueOf(defaultRate.getTelkomFixed()));
                       jTextLiquidTelecoms.setText(String.valueOf(defaultRate.getLiquidTelecoms()));

                   } else {

                       FileInputStream fileIn = new FileInputStream(dir+"\\RatesDropDown\\" + pickedRate);
                       ObjectInputStream in = new ObjectInputStream(fileIn);
                       newHashMap = (HashMap) in.readObject();


                       //fetch serialized values from file
                       jTextVans.setText(String.valueOf(newHashMap.get(FilenameUtils.removeExtension(pickedRate) + "VANS")));
                       jTextVodaMobile.setText(String.valueOf(newHashMap.get(FilenameUtils.removeExtension(pickedRate) + "VM")));
                       jTextMtnMobile.setText(String.valueOf(newHashMap.get(FilenameUtils.removeExtension(pickedRate) + "MNTM")));
                       jTextCellCMobile.setText(String.valueOf(newHashMap.get(FilenameUtils.removeExtension(pickedRate) + "CCM")));
                       jTextTelkomMobile.setText(String.valueOf(newHashMap.get(FilenameUtils.removeExtension(pickedRate) + "TM")));
                       jTextInternCall.setText(String.valueOf(newHashMap.get(FilenameUtils.removeExtension(pickedRate) + "IC")));
                       jTextTelkomFixed.setText(String.valueOf(newHashMap.get(FilenameUtils.removeExtension(pickedRate) + "TF")));
                       jTextLiquidTelecoms.setText(String.valueOf(newHashMap.get(FilenameUtils.removeExtension(pickedRate) + "LT")));


                       in.close();
                       fileIn.close();
                   }//else brac
                   } catch(IOException i){
                       i.printStackTrace();
                       return;
                   } catch(ClassNotFoundException c){
                       System.out.println("Rate class not found");
                       c.printStackTrace();
                       return;
                   }


        }else if (e.getSource()==btnExecute){

            XLSXReaderExample read = new XLSXReaderExample(jTextFilePathImport.getText(), jTextFilePathExport.getText(),Double.parseDouble(jTextVans.getText()),
                    Double.parseDouble(jTextVodaMobile.getText()),Double.parseDouble(jTextMtnMobile.getText()),Double.parseDouble(jTextCellCMobile.getText()),
                    Double.parseDouble(jTextTelkomMobile.getText()),Double.parseDouble(jTextInternCall.getText()),
                    Double.parseDouble(jTextTelkomFixed.getText()),Double.parseDouble(jTextLiquidTelecoms.getText()));

        }
        else if(e.getSource()==btnRestart){
            try {
                restartApplication();
            } catch (URISyntaxException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }

        }
    }
    public void restartApplication() throws URISyntaxException {
        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        final File currentJar = new File(GUI.class.getProtectionDomain().getCodeSource().getLocation().toURI());

//         is it a jar file?
        if(!currentJar.getName().endsWith(".jar"))
            return;

       //  Build command: java -jar application.jar
        final ArrayList<String> command = new ArrayList<String>();
        command.add(javaBin);
        command.add("-jar");
        command.add(currentJar.getPath());

        final ProcessBuilder builder = new ProcessBuilder(command);
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args)  {



        GUI gui = new GUI();//????



    }
}