package hemolite;

public class PatientMeasurement {
    Patient patient;
    Measurements measurements;

    public PatientMeasurement(){

    }

    Patient getPatient(){
        return patient;
    }

    void setPatient(Patient patient){
        this.patient = patient;
    }

    Measurements getMeasurements(){
        return this.measurements;
    }

    void setMeasurements(Measurements measurements){
        this.measurements = measurements;
    }
}
