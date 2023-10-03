package AdminManagement;

import Users.Voters.Voter;
import Users.Voters.VoterManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdminVoterApp {

    private List<Voter> voters;
    private static final String VOTERS_DATA_FILE = "votersData.ser";

    public AdminVoterApp() {
        this.voters = new ArrayList<>();
    }

    public void addVoter(String name, String dateOfBirth, String studentNumber, String password) {
        Voter voter = new Voter(name, dateOfBirth, studentNumber, password);
        voter.setOtp(generateOTP());
        VoterManager.getInstance().registerVoter(voter);
    }

    // Generate a 6-digit OTP for the voter
    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // génère un nombre à 6 chiffres
        return String.valueOf(otp);
    }


    public void saveVotersToFile() {
        try (FileOutputStream fileOut = new FileOutputStream(VOTERS_DATA_FILE);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(voters);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // Add other necessary methods like loading voters from file if needed
}
