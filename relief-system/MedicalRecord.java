/**
 * @author      Carl Leipoldt <a href="mailto:carl.leipoldt@ucalgary.ca">carl.leipoldt@ucalgary.ca</a>
 * @version     1.3
 * @since       1.0
 */


package edu.ucalgary.oop;
import java.util.regex.*;


public class MedicalRecord {
    private Location location;
    private String treatmentDetails;
    private String dateOfTreatment;

    public MedicalRecord(Location location, String treatmentDetails, String dateOfTreatment){
        this.location = location;
        this. treatmentDetails = treatmentDetails;
        this.dateOfTreatment = dateOfTreatment;
    }

    public Location getLocation(){
        return this.location;
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public String getTreatmentDetails(){
        return this.treatmentDetails;
    }

    public void setTreatmentDetails(String treatmentDetails){
        this.treatmentDetails = treatmentDetails;
    }

    public String getDateOfTreatment(){
        return this.dateOfTreatment;
    }

    public void setDateOfTreatment(String dateOfTreatment)throws IllegalArgumentException {
        if (isValidDateFormat(dateOfTreatment)) {
            this.dateOfTreatment = dateOfTreatment;
        } else {
            throw new IllegalArgumentException("dateOfTreatment is an invalid date: " + dateOfTreatment);
        }
    }

    private boolean isValidDateFormat(String date){
        Pattern datePat = Pattern.compile("^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$");
        Matcher matchPat = datePat.matcher(date);
        return matchPat.matches();
    }

}
