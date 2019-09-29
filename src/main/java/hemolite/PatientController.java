package hemolite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PatientController {
    @RequestMapping(value="/login", method= RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<LoginResponse> loginPatient(@RequestBody Patient patient){
        PatientService patientService = new PatientService();
        LoginResponse loginResponse = patientService.loginResponse(patient);
        return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
    }

    @RequestMapping(value="/signup", method= RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> signupPatient(@RequestBody Patient patient){
        PatientService patientService = new PatientService();
        SignUpResponse signUpResponse = patientService.addToDb(patient);
        return new ResponseEntity<>(signUpResponse, HttpStatus.OK);
    }

    @RequestMapping(value="/measurements", method= RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Diagnosis> postMeasurements(@RequestBody PatientMeasurement patientMeasurement){
        PatientService patientService = new PatientService();
        Diagnosis diagnosis = patientService.getDiagnosis(patientMeasurement);
        return new ResponseEntity<Diagnosis>(diagnosis, HttpStatus.OK);
    }

    @RequestMapping(value="/patient/{id}", method= RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<StringResponse> getPatientHistory(@PathVariable("id") int id){
        PatientService patientService = new PatientService();
        StringResponse stringResponse = patientService.getPatient(id);
        return new ResponseEntity<StringResponse>(stringResponse, HttpStatus.OK);
    }

    @RequestMapping(value="/patients", method= RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<StringResponse> getPatientsHistory(){
        PatientService patientService = new PatientService();
        StringResponse stringResponse = patientService.getPatients();
        return new ResponseEntity<StringResponse>(stringResponse, HttpStatus.OK);
    }
}
