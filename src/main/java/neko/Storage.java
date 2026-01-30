package neko;

import neko.task.Deadline;
import neko.task.Event;
import neko.task.Task;
import neko.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;

public class Storage {
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
        try {
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")
                    + "/src/main/" + filepath));
            String line = br.readLine();

            ArrayList<Task> taskArr = new ArrayList<>();

            while (line != null) {
                String[] split = line.split(" \\| ");
                String type = split[0];
                String description = split[2];

                boolean isDone = split[1].equals("1");

                switch (type) {
                case "T":
                    Task todo = new ToDo(description, isDone);
                    taskArr.add(todo);
                    break;
                case "D":
                    String by = split[3];
                    LocalDate date = DateParser.parseTextIntoDate(by);
                    Task deadline = new Deadline(description, date, isDone);
                    taskArr.add(deadline);
                    break;
                case "E":
                    String from = split[3];
                    String to = split[4];
                    LocalDate dateFrom = DateParser.parseTextIntoDate(from);
                    LocalDate dateTo = DateParser.parseTextIntoDate(to);
                    Task event = new Event(description, dateFrom, dateTo, isDone);
                    taskArr.add(event);
                    break;
                }
                line = br.readLine();
            }
            return taskArr;
        } catch (FileNotFoundException e) {
            throw new NekoException("Meow! I can't find neko.txt in my data folder!");
        } catch (IOException e) {
            throw new NekoException("An I/O error occurred nya!");
        }
    }

    /**
     * Writes the current task list to the data file.
     *
     * @param tasks Task list to be saved.
     * @throws NekoException If an error occurs while writing to the file.
     */
    public void write(TaskList tasks) throws NekoException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")
                + "/src/main/data/neko.txt", false))) {
            for (Task task : tasks.getTaskArr()) {
                writer.write(task.formatIntoData());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new NekoException("I tried to write to the file but the file ran away…");
        }
    }
}
