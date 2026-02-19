package neko;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import neko.task.Deadline;
import neko.task.Event;
import neko.task.Task;
import neko.task.ToDo;

/**
 * Handles loading and saving of task data to persistent storage.
 */
public class Storage {
    private static final String BASE_DIRECTORY = "data/";
    private static final String DATA_DELIMITER = " \\| ";
    private String filepath;

    /**
     * Creates a storage instance using the specified file path.
     *
     * @param filepath Relative path to the data file.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the data file.
     *
     * @return An Array list of tasks loaded from storage.
     * @throws NekoException If the file cannot be found or read.
     */
    public ArrayList<Task> load() throws NekoException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(resolvePath());

        if (!file.exists()) {
            return tasks; // first run: no data file yet
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {
                try {
                    tasks.add(parseLine(line));
                } catch (Exception e) {
                    // Skip corrupted line and continue loading
                    System.err.println("Nya! Skipping corrupted line: " + line);
                }
            }


        } catch (IOException e) {
            throw new NekoException("An I/O error occurred nya!");
        }

        return tasks;
    }

    private String resolvePath() {
        return System.getProperty("user.dir") + "/" + BASE_DIRECTORY + filepath;
    }

    private Task parseLine(String line) throws NekoException {
        String[] parts = line.split(DATA_DELIMITER);

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return new ToDo(description, isDone);

        case "D":
            LocalDate by = DateParser.parseTextIntoDate(parts[3]);
            return new Deadline(description, by, isDone);

        case "E":
            LocalDate from = DateParser.parseTextIntoDate(parts[3]);
            LocalDate to = DateParser.parseTextIntoDate(parts[4]);
            return new Event(description, from, to, isDone);

        default:
            throw new NekoException("Unknown task type found in file: " + type);
        }
    }

    private void ensureDirectoryExists() throws NekoException {
        File file = new File(resolvePath());
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            boolean created = parentDir.mkdirs();
            if (!created) {
                throw new NekoException("Unable to create data directory.");
            }
        }
    }

    /**
     * Writes the current task list to the data file.
     *
     * @param tasks Task list to be saved.
     * @throws NekoException If an error occurs while writing to the file.
     */
    public void write(TaskList tasks) throws NekoException {
        ensureDirectoryExists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resolvePath(), false))) {
            for (Task task : tasks.getTaskArr()) {
                writer.write(task.formatIntoData());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new NekoException("I tried to write to the file but the file ran away…");
        }
    }
}
