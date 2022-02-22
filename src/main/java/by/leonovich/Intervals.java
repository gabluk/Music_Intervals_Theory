package by.leonovich;

import java.util.ArrayList;
import java.util.List;

public class Intervals {
    public static String intervalConstruction(String[] args) {
        //массив чистых(без # и b ) нот : первое значение - символ  ноты, второе -- количество полутонов до соседней ноты слева
        //от ноты, третье  -- количество полутонов до соседней ноты справа
        Object[][] ascNotes = new Object[][]{
                {"C", 1, 2}, {"D", 2, 2}, {"E", 2, 1}, {"F", 1, 2}, {"G", 2, 2}, {"A", 2, 2}, {"B", 2, 1}
        };
        //первый значение - название интервала, второе - количество полутонов, третье - диатоническая степень
        Object[][] intervals = new Object[][]{
                {"m2", 1, 2}, {"M2", 2, 2}, {"m3", 3, 3}, {"M3", 4, 3}, {"P4", 5, 4}, {"P5", 7, 5}, {"m6", 8, 6}, {"M6", 9, 6},
                {"m7", 10, 7}, {"M7", 11, 7}, {"P8", 26, 16}
        };
        //перевернем массив
        Object[][] dscNotes = new Object[ascNotes.length][];
        for (int i = ascNotes.length - 1; i >= 0; i--) {
            dscNotes[ascNotes.length - i - 1] = ascNotes[i];

        }

        String interval = args[0];
        int semitones = 0;
        int degrees = 0;
        int noteLevel = 0;
        String startClearNote = null;
        if (args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Illegal number of elements in input array");
        }
        boolean isAst = args.length == 2 || args[2].equals("asc");
        Object[][] notes = isAst ? ascNotes : dscNotes;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0].equals(interval)) {
                semitones = (int) intervals[i][1];
                degrees = (int) intervals[i][2];
                break;
            }
        }
//орпеделяем индекс ноты и ее уровень(повышенная или пониженная )
        String startNote = args[1];
        for (int i = 0; i < notes.length; i++) {
            String note = (String) notes[i][0];
            if (startNote.startsWith(note)) {

                startClearNote = (String) notes[i][0];
                if (startNote.length() == 2) {
                    if (startNote.endsWith("#")) {
                        noteLevel = isAst ? -1 : 1;
                    } else noteLevel = isAst ? 1 : -1;
                }
                break;
            }
        }
        //Определяем количество полутонов между чистыми нотами(без # и b)
        int startDegrees = 2;
        boolean started = false;
        int semitonesBetweenClearNotes = 0;
        int indexOfEndNote = 0;
        for (int i = 0; i < notes.length; i++) {
            if (startClearNote.equals(notes[i][0])) {
                started = true;
            }
            if (started) {
                semitonesBetweenClearNotes += (int) notes[i][isAst ? 2 : 1];
            }


            if (started && startDegrees == degrees && i == notes.length - 1) {
                indexOfEndNote = 0;
                break;
            }
            if (started && startDegrees == degrees) {
                indexOfEndNote = i + 1;
                break;
            }
            if (started && (i == notes.length - 1)) {
                startDegrees++;
                i = -1;
            } else if (started) {
                startDegrees++;
            }


        }
        //определяем уровни( # или b) начальной ноты и конечной ноты интервала
        String prefix = "";
        int semitonesBetweenStartAndEnd = 0;
        semitonesBetweenStartAndEnd = semitonesBetweenClearNotes + noteLevel;
        String requiredNote = null;
        if (semitones == semitonesBetweenStartAndEnd) {
            requiredNote = (String) notes[indexOfEndNote][0];

        }
        if (semitones < semitonesBetweenStartAndEnd) {
            int gap = semitonesBetweenStartAndEnd - semitones;
            int counter = 0;
            while (counter < gap) {
                counter++;
                prefix = prefix.concat(isAst ? "b" : "#");
            }
            requiredNote = ((String) notes[indexOfEndNote][0]).concat(prefix);
        }
        if (semitones > semitonesBetweenStartAndEnd) {
            int gap = semitones - semitonesBetweenStartAndEnd;
            int counter = 0;
            while (counter < gap) {
                counter++;
                prefix = prefix.concat(isAst ? "#" : "b");
            }
            requiredNote = ((String) notes[indexOfEndNote][0]).concat(prefix);
        }


        return requiredNote;


    }

    public static String intervalIdentification(String[] args) {

        Object[][] ascNotes = new Object[][]{
                {"C", 1, 2}, {"D", 2, 2}, {"E", 2, 1}, {"F", 1, 2}, {"G", 2, 2}, {"A", 2, 2}, {"B", 2, 1}
        };

        Object[][] intervals = new Object[][]{
                {"m2", 1, 2}, {"M2", 2, 2}, {"m3", 3, 3}, {"M3", 4, 3}, {"P4", 5, 4}, {"P5", 7, 5}, {"m6", 8, 6}, {"M6", 9, 6},
                {"m7", 10, 7}, {"M7", 11, 7}, {"P8", 26, 16}
        };

        Object[][] dscNotes = new Object[ascNotes.length][];
        for (int i = ascNotes.length - 1; i >= 0; i--) {
            dscNotes[ascNotes.length - i - 1] = ascNotes[i];

        }

        //определяем начальную ноту и  конечну из входных данных
        String firstChangedNote = args[0];
        String secondChangedNote = args[1];

        //определяем  ноты без учета "#" и "b"
        String firstClearNote = null;
        boolean isAsc = args.length == 2 || args[2].equals("asc");
        Object[][] notes = isAsc ? ascNotes : dscNotes;
        for (int i = 0; i < notes.length; i++) {
            if (firstChangedNote.startsWith((String) notes[i][0])) {
                firstClearNote = (String) notes[i][0];
                break;
            }
        }

        //определяем название конечной  ноты без учета "#" и "b"
        String secondClearNote = null;
        for (int i = 0; i < notes.length; i++) {
            if (secondChangedNote.startsWith((String) notes[i][0])) {
                secondClearNote = (String) notes[i][0];
                break;
            }
        }

        //определяем количество полутонов между чистыми нотами (без учета # и b)
        Integer degree = 1;
        int semitonesBetweenClearNotes = 0;
        boolean started = false;
        for (int i = 0; i < notes.length; i++) {

            if (started && notes[i][0].equals(secondClearNote)) {
                break;
            }
            if (notes[i][0].equals(firstClearNote)) {
                started = true;
            }
            if (started) {
                semitonesBetweenClearNotes += ((int) notes[i][isAsc ? 2 : 1]);
                degree++;
            }
            if (i == notes.length - 1) {
                i = -1;
            }
        }

        //находим на сколько полутонов повышены(понижены) стартовая и конечная ноты
        int levelOfStartNote = 0;
        if (firstChangedNote.length() > 1) {
            int counter = firstChangedNote.length() - 1;
            if (firstChangedNote.endsWith("#")) {
                levelOfStartNote = isAsc ? -counter : counter;
            } else {
                levelOfStartNote = isAsc ? counter : -counter;
            }
        }
        int levelOfEndNote = 0;
        if (secondChangedNote.length() > 1) {
            int counter = secondChangedNote.length() - 1;
            if (secondChangedNote.endsWith("#")) {
                levelOfEndNote = isAsc ? counter : -counter;
            } else {
                levelOfEndNote = isAsc ? -counter : counter;
            }
        }
        //опеределяем интервал
        Integer semitonesBetweenChangedNotes = semitonesBetweenClearNotes + levelOfEndNote + levelOfStartNote;
        String requiredInterval = null;
        for (int i = 0; i < intervals.length; i++) {
            if (semitonesBetweenChangedNotes.equals(intervals[i][1]) && degree.equals(intervals[i][2])) {
                requiredInterval = (String) intervals[i][0];
                break;
            }
        }
        if (requiredInterval == null) {
            throw new IllegalArgumentException("Cannot identify the interval");
        }
        List<String> list = new ArrayList<>();

        return requiredInterval;

    }
}
