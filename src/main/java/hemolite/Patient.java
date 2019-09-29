package hemolite;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class Patient {

    private int patientID;
    private static int numPatients = 1;
    private String name;
    private boolean anemic = false;
    private double standardResistance;
    private double hemoglobin;
    private String hemorrhageStatus;
    private static DecimalFormat df = new DecimalFormat("#.##");
    private ArrayList<Measurements> postOpData;
    private static ArrayList<Patient> patients = new ArrayList <Patient>();

    public Patient(){
        postOpData = new ArrayList<Measurements> ();
    }
    public Patient (String name, double hematocritValue, double resistance) {
        this.name = name;
        standardResistance = resistance;
        hemoglobin = hematocritValue/3.0;
        if (hemoglobin <= 7.0) {
            anemic = true;
        }
        postOpData = new ArrayList<Measurements> ();
        patientID = numPatients;
    }

    public static boolean addPatient(Patient patient){
        try{
            int tempLength = patients.size();
            patient.patientID = tempLength+1;
            patients.add(patient);
            if (tempLength == patients.size()){
                return false;
            }
            else{
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
    }
    public double getStandardResistance() {
        return standardResistance;
    }

    public String getAnemic() {
        if (anemic) {
            return "Anemic";
        }else {
            return "Not anemic";
        }
    }

    public int getPatientID() {
        return patientID;
    }

    public String getPatientName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHemorrhageStatus() {
        return hemorrhageStatus;
    }

    private double getHemoglobin() {
        return hemoglobin;
    }

    public static Object existingName (String name) {
        for (Patient p: patients) {
            if (p.getPatientName().equals(name)) {
                return p;
            }
        }
        return new Object();
    }
    private double calcScaleFactor() {
        if (standardResistance > hemoglobin) {
            return standardResistance/hemoglobin;
        }else if (hemoglobin > standardResistance) {
            return hemoglobin/standardResistance;
        }else {
            return 1.0;
        }
    }

    public String addData (Measurements measurements) {
        postOpData.add(measurements);
        return diagnose();
    }

    public double calcDifference() {
        double resistDiff = java.lang.Math.abs(postOpData.get(postOpData.size()-1).getResistance() - standardResistance);
        if (standardResistance > hemoglobin) {
            return resistDiff/calcScaleFactor();
        }else if (hemoglobin > standardResistance) {
            return resistDiff * calcScaleFactor();
        }else {
            return resistDiff;
        }
    }

    public String diagnose() {
        calcDifference();
        if (anemic) {
            hemorrhageStatus = "HIGH";
        }else if (hemoglobin > 7.0 && hemoglobin <=10.0) {
            if (calcDifference() >= 3.0) {
                hemorrhageStatus = "HIGH";
            }else {
                hemorrhageStatus = "LOW";
            }
        }else {
            if (calcDifference() >= 4.0) {
                hemorrhageStatus = "HIGH";
            }else {
                hemorrhageStatus = "LOW";
            }
        }
        return recommendation();
    }

    public String recommendation () {
        String answer = "";
        if (hemorrhageStatus == "HIGH") {
            answer += "This patient is at high risk for hemorrhage:";
            answer += "\n     Consider: IV fluids, O2 administration, blood transfusion, and/or resuscitation";
            return answer;
        }else {
            answer += "This patient is at low risk for hemorrhage";
            answer += "\n     Continue to monitor patient's vitals closely";
            return answer;
        }
    }

    public Iterator<String> iterator(){
        return new Iterator <String>() {
            int pos = 0;
            public boolean hasNext() {
                return pos <=postOpData.size()-1;
            }
            public String next() {
                return postOpData.get(pos++).toString();
            }
        };
    }

    public static String getPatientDatabase() {
        String answer = "";
        if (patients.size() == 0){
            return "No patients yet.";
        }
        for (Patient p: patients) {
            answer += "\nID: " + p.getPatientID() + ", Name: " + p.getPatientName() + ", Anemic: " + p.getAnemic() +
                    ", Hemorrhage Risk: " + p.getHemorrhageStatus();
        }
        return answer;
    }

    public static String getPatientData (int ID) {
        for (Patient p: patients){
            if (p.getPatientID()==ID){
                String answer = "Name: " + p.getPatientName();
                answer += "\nStandard: " + df.format(p.getStandardResistance()) + " Hemoglobin: " + df.format(p.getHemoglobin());
                Iterator <String> iter = p.iterator();
                while (iter.hasNext()) {
                    answer += "\n" + iter.next();
                }

                return answer;
            }
        }
        return "No patient found";

    }

}
