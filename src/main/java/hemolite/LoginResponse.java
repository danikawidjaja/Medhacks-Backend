package hemolite;

public class LoginResponse {
    boolean exist;
    Patient patient;

    public LoginResponse(Patient patient, boolean exist){
        this.exist = exist;
        this.patient = patient;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public boolean getExist(){
        return exist;
    }

    public void setPatient(Patient patient){
        this.patient = patient;
    }

    public Patient getPatient(){
        return patient;
    }
}
