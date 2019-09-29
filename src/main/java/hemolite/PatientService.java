package hemolite;

public class PatientService {

    public PatientService(){
    }

    LoginResponse loginResponse(Patient patient){
        Object patientData = Patient.existingName(patient.getPatientName());
        if(patientData instanceof Patient){
            return new LoginResponse((Patient)patientData, true);
        }
        return new LoginResponse(patient, false);
    }

    SignUpResponse addToDb(Patient patient){
        return new SignUpResponse(Patient.addPatient(patient));
    }

    StringResponse getPatient(int id){
        String patientString = Patient.getPatientData(id);
        StringResponse stringResponse = new StringResponse();
        stringResponse.setString(patientString);
        return stringResponse;
    }
    StringResponse getPatients(){
        String patientsString = Patient.getPatientDatabase();
        StringResponse stringResponse = new StringResponse();
        stringResponse.setString(patientsString);
        return stringResponse;
    }
    Diagnosis getDiagnosis(PatientMeasurement patientMeasurement){
        Patient patient = patientMeasurement.getPatient();
        String diagnosisString = patient.addData(patientMeasurement.getMeasurements());
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagnosisString(diagnosisString);
        return diagnosis;
    }

}