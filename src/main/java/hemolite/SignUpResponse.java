package hemolite;

public class SignUpResponse {
    boolean succes;

    public SignUpResponse(boolean succes){
        this.succes = succes;
    }

    public void setSuccess(boolean succes) {
        this.succes = succes;
    }

    public boolean getSuccess(){
        return succes;
    }
}
