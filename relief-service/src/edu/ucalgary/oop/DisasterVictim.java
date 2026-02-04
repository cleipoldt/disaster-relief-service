/**
 * @author      Sabrina Li <a href="mailto:sabrina.li1@ucalgary.ca">sabrina.li1@ucalgary.ca</a>
 * @version     1.0
 * @since       1.0
 */

 package edu.ucalgary.oop;

 import java.util.Arrays;
 import java.util.regex.*;

 public class DisasterVictim {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private final int ASSIGNED_SOCIAL_ID;
    private FamilyRelation[] familyConnections;
    private MedicalRecord[] medicalRecords;
    private Supply[] personalBelongings;
    // private ArrayList<FamilyRelation> familyConnections;
    // private ArrayList<MedicalRecord> medicalRecords;
    // private ArrayList<Supply> personalBelongings;
    private final String ENTRY_DATE;
    private String gender;
    private String comments;
    private static int counter;

    
    // constructors --------------------------------------
    public DisasterVictim(String firstName, String ENTRY_DATE) throws IllegalArgumentException {
        this.firstName = firstName;
        
        if (isValidDateFormat(ENTRY_DATE)) {
            this.ENTRY_DATE = ENTRY_DATE;
        } else {
            throw new IllegalArgumentException("ENTRY_DATE is an invalid date: " + ENTRY_DATE);
        }
        
        this.ASSIGNED_SOCIAL_ID = generateSocialID();
    }

    public DisasterVictim(String firstName, String ENTRY_DATE, String dateOfBirth) throws IllegalArgumentException {
        this.firstName = firstName;

        if (isValidDateFormat(ENTRY_DATE)) {
            this.ENTRY_DATE = ENTRY_DATE;
        } else {
            throw new IllegalArgumentException("ENTRY_DATE is an invalid date: " + ENTRY_DATE);
        }

        if (isValidDateFormat(dateOfBirth)) {
            int entryDateInt = convertDateStringToInt(ENTRY_DATE);
            int birthDateInt = convertDateStringToInt(dateOfBirth);
            if (birthDateInt < entryDateInt) {
                this.dateOfBirth = dateOfBirth;
            } else {
                throw new IllegalArgumentException("dateOfBirth is after ENTRY_DATE");
            }
        } else {
            throw new IllegalArgumentException("dateOfBirth is an invalid date: " + dateOfBirth);
        }

        this.ASSIGNED_SOCIAL_ID = generateSocialID();
    }

    // getters ----------------------------------
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public int getAssignedSocialID() {
        return this.ASSIGNED_SOCIAL_ID;
    }

    public FamilyRelation[] getFamilyConnections() {
        return this.familyConnections;
        // return this.familyConnections.toArray(new FamilyRelation[familyConnections.size()]);
    }

    public MedicalRecord[] getMedicalRecords() {
        return this.medicalRecords;
        // return this.medicalRecords.toArray(new MedicalRecord[medicalRecords.size()]);
    }

    public Supply[] getPersonalBelongings() {
        return this.personalBelongings;
        // return this.personalBelongings.toArray(new Supply[personalBelongings.size()]);
    }

    public String getEntryDate() {
        return this.ENTRY_DATE;
    }

    public String getComments() {
        return this.comments;
    }

    public String getGender() {
        return this.gender;
    }

    // setters ------------------------------------------
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) throws IllegalArgumentException {
        if (isValidDateFormat(dateOfBirth)) {
            this.dateOfBirth = dateOfBirth;
        } else {
            throw new IllegalArgumentException("dateOfBirth is an invalid date: " + dateOfBirth);
        }
    }

    public void setFamilyConnections(FamilyRelation[] connections) {
        this.familyConnections = connections; // reference to already made FamilyRelation array
    }

    public void setMedicalRecords(MedicalRecord[] records) {
        this.medicalRecords = records;
    }

    public void setPersonalBelongings(Supply[] belongings) {
        this.personalBelongings = belongings;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // addders and removers ------------------------------------------------
    public void addPersonalBelonging(Supply supply) {
        // FIXME: validation for if already supply of same name, add
        
        if (this.personalBelongings != null) {
            int origLength = this.personalBelongings.length;
            Supply[] newPersonalBelongings = Arrays.copyOf(this.personalBelongings, origLength + 1);
            newPersonalBelongings[origLength] = supply;
            this.personalBelongings = newPersonalBelongings;
        } else {
            int origLength = 0;
            Supply[] newPersonalBelongings = new Supply[1];
            newPersonalBelongings[origLength] = supply;
            this.personalBelongings = newPersonalBelongings;
        }
    }

    public void removePersonalBelonging(Supply unwantedSupply) {
        // FIXME: if not there, do nothing
        // FIXME: validation for if less than what already have, subtract

        // assumes unwanted supply is there, will be fully removed
        int origLength = this.personalBelongings.length;
        int newIndex = 0;
        Supply[] newPersonalBelongings = new Supply[origLength - 1];
        for (int i = 0; i < origLength; i++) {
            if (this.personalBelongings[i] != unwantedSupply) {
                newPersonalBelongings[newIndex] = this.personalBelongings[i];
                newIndex++;
            }
        }
        this.personalBelongings = newPersonalBelongings;
    }

    public void addFamilyConnection(FamilyRelation record) {
        if (this.familyConnections != null) {
            int origLength = this.familyConnections.length;
            FamilyRelation[] newFamilyConnections = Arrays.copyOf(this.familyConnections, origLength + 1);
            newFamilyConnections[origLength] = record;
            this.familyConnections = newFamilyConnections;
        } else {
            int origLength = 0;
            FamilyRelation[] newFamilyConnections = new FamilyRelation[1];
            newFamilyConnections[origLength] = record;
            this.familyConnections = newFamilyConnections;
        }
    }

    public void removeFamilyConnection(FamilyRelation exRelation) {
        int origLength = this.familyConnections.length;
        int newIndex = 0;
        FamilyRelation[] newFamilyConnections = new FamilyRelation[origLength - 1];
        for (int i = 0; i < origLength; i++) {
            if (this.familyConnections[i] != exRelation) {
                newFamilyConnections[newIndex] = this.familyConnections[i];
                newIndex++;
            }
        }
        this.familyConnections = newFamilyConnections;
    }

    public void addMedicalRecord(MedicalRecord record) {
        int origLength = this.medicalRecords.length;
        MedicalRecord[] newMedicalRecords = Arrays.copyOf(this.medicalRecords, origLength + 1);
        newMedicalRecords[origLength] = record;

        this.medicalRecords = newMedicalRecords;
    }

    // static methods -------------------------------------------
    public static int generateSocialID() {
        // starts at 1
        DisasterVictim.counter += 1;
        return DisasterVictim.counter;
    }

    // must throw IllegalArgumentException where called if false
    public static boolean isValidDateFormat(String date) {
        Pattern validDatePattern = Pattern.compile("^\\d{4}[-]{1}\\d{2}[-]{1}\\d{2}");
        Matcher isValid = validDatePattern.matcher(date);
        return isValid.find();
    }

    public static int convertDateStringToInt(String dateStr) {
        String numOnlyDate = dateStr.replace("-", "");
        int dateInt = Integer.parseInt(numOnlyDate);
        return dateInt;
    }

 }