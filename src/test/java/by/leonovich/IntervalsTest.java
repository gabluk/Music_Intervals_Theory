package by.leonovich;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static by.leonovich.Intervals.intervalConstruction;
import static by.leonovich.Intervals.intervalIdentification;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntervalsTest {
    @Test
    void intervalConstructionTest() {
        List<String[][]> verificationData = new ArrayList<>();
        verificationData.add(new String[][]{{"M2", "C", "asc"}, {"D"}});
        verificationData.add(new String[][]{{"P5", "B", "asc"}, {"F#"}});
        verificationData.add(new String[][]{{"m2", "Bb", "dsc"}, {"A"}});
        verificationData.add(new String[][]{{"M3", "Cb", "dsc"}, {"Abb"}});
        verificationData.add(new String[][]{{"P4", "G#", "dsc"}, {"D#"}});
        verificationData.add(new String[][]{{"m3", "B", "dsc"}, {"G#"}});
        verificationData.add(new String[][]{{"m2", "Fb", "asc"}, {"Gbb"}});
        verificationData.add(new String[][]{{"M2", "E#", "dsc"}, {"D#"}});
        verificationData.add(new String[][]{{"P4", "E", "dsc"}, {"B"}});
        verificationData.add(new String[][]{{"m2", "D#", "asc"}, {"E"}});
        verificationData.add(new String[][]{{"M7", "G", "asc"}, {"F#"}});
        for (int i = 0; i < verificationData.size(); i++) {
            String[][] arrayData = verificationData.get(i);
            String[] input = arrayData[0];
            String expectedOutput = arrayData[1][0];
            String result = intervalConstruction(input);
            Supplier<String> message = () -> "input data: " + Arrays.toString(input);
            assertEquals(expectedOutput, result, message);

        }
    }

    @Test
    void intervalIdentificationTest() {
        List<String[][]> allVerificationData = new ArrayList<>();
        allVerificationData.add(new String[][]{{"C", "D"}, {"M2"}});
        allVerificationData.add(new String[][]{{"B", "F#", "asc"}, {"P5"}});
        allVerificationData.add(new String[][]{{"Fb", "Gbb"}, {"m2"}});
        allVerificationData.add(new String[][]{{"G", "F#", "asc"}, {"M7"}});
        allVerificationData.add(new String[][]{{"Bb", "A", "dsc"}, {"m2"}});
        allVerificationData.add(new String[][]{{"Cb", "Abb", "dsc"}, {"M3"}});

        allVerificationData.add(new String[][]{{"Cb", "Abb", "dsc"}, {"M3"}});
        allVerificationData.add(new String[][]{{"G#", "D#", "dsc"}, {"P4"}});
        allVerificationData.add(new String[][]{{"E", "B", "dsc"}, {"P4"}});
        allVerificationData.add(new String[][]{{"E#", "D#", "dsc"}, {"M2"}});
        allVerificationData.add(new String[][]{{"B", "G#", "dsc"}, {"m3"}});

        for (int i = 0; i < allVerificationData.size(); i++) {
            String[][] data = allVerificationData.get(i);
            String[] input = data[0];
            String expectedOutput = data[1][0];
            Supplier<String> message = () -> "input data: " + Arrays.toString(input);
            String result = intervalIdentification(input);
            assertEquals(expectedOutput, result, message);
        }

    }
}