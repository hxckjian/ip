# Neko User Guide 🐾

Neko is your little digital companion that keeps track of your tasks so nothing slips through your paws. Simple, fast, and always reliable.

- [Quick start](#quick-start)
- [Features](#features)
  - [Adding a todo : `todo`](#adding-a-todo--todo-description)
  - [Adding a deadline : `deadline`](#adding-a-deadline--deadline-description-by-yyyy-mm-dd)
  - [Adding an event : `event`](#adding-an-event--event-description-from-yyyy-mm-dd-to-yyyy-mm-dd)
  - [Listing all tasks : `list`](#listing-all-tasks--list)
  - [Marking a task : `mark`](#marking-a-task-mark-index)
  - [Unmarking a task : `unmark`](#unmarking-a-task-unmark-index)
  - [Deleting a task : `delete`](#deleting-a-task-delete-index)
  - [Finding tasks : `find`](#finding-tasks-find-keyword)
  - [Snoozing a deadline or event : `snooze`](#snoozing-a-deadline-or-event-snooze-index-days)
  - [Exiting the program : `bye`](#exiting-the-program-bye)
- [Saving the data](#saving-the-data)
- [Editing the data file](#editing-the-data-file)
- [Command summary](#command-summary)


## Quick start
1. Ensure you have `Java 17` or above installed in your Computer.<br>Mac users: Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

2. Download the latest `.jar` file from [here](https://github.com/hxckjian/ip/releases/tag/A-Release).

3. Copy the file to the folder you want to use as the home folder for your Neko Task Manager.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar neko.jar` command to run the application.<br>A GUI similar to the below should appear in a few seconds.
![Screenshot of the app](Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing `list` and pressing Enter will list all the contacts you saved.
Some example commands you can try:

- `list` : Lists all contacts.

- `todo read book` : Adds a new read book todo task to the list.

- `delete 3` : Deletes the 3rd contact shown in the current list.

- `bye` : Exits the app.

6. Refer to the Features below for details of each command.

---
# Features

## Adding a todo : `todo DESCRIPTION`

Adds a todo task without any date.

Example: `todo read book`

Adds a new todo task to the list.
```
Hehe~ I've added this for you:
[T][ ] read book
Now you have 1 tasks in the list, nya.
```

## Adding a deadline : `deadline DESCRIPTION /by YYYY-MM-DD`

Adds a task with a deadline.

Example: `deadline submit assignment /by 2026-02-20`

Adds a deadline task with the specified due date.
```
Hehe~ I've added this for you:
[D][ ] submit assignment (by: 2026-02-20)
Now you have 2 tasks in the list, nya.
```

## Adding an event : `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`

Adds a task that occurs over a time period.

Example: `event project meeting /from 2026-02-18 /to 2026-02-19`

Adds an event task with the specified start and end dates.
```
Hehe~ I've added this for you:
[E][ ] project meeting (from: 2026-02-18 to: 2026-02-19)
Now you have 3 tasks in the list, nya.
```

## Listing all tasks : `list`

Displays all tasks currently stored.

Example: `list`

Shows all tasks in the list with their index numbers.
```
Here are the tasks in your list nya!
1. [T][ ] read book
2. [D][ ] submit assignment (by: Feb 20 2026)
3. [E][ ] project meeting (from: Feb 18 2026 to: Feb 19 2026)
```

## Marking a task: `mark INDEX`

Marks the specified task as completed.

Example: `mark 1`

Marks task 1 as done.
```
Good job! I marked this as done, nya~
[T][X] read book
```

## Unmarking a task: `unmark INDEX`

Marks the specified task as not completed.

Example: `unmark 1`

Marks task 1 as not done.
```
Hmm~ not done yet? I unmarked it for you.
[T][ ] read book
```

## Deleting a task: `delete INDEX`

Deletes the specified task from the list.

Example: `delete 2`

Removes task 2 from the list.
```
Roger nya! I have deleted this task:
[D][ ] submit assignment (by: Feb 20 2026)
Now you have 2 tasks in the list.
```

## Finding tasks: `find KEYWORD`

Finds tasks containing the specified keyword.

Example: `find assignment`

Displays all tasks whose descriptions contain the keyword.
```
I found them nya! Here are the matching tasks in your list.
2. [E][ ] project meeting (from: Feb 18 2026 to: Feb 19 2026)
```

## Snoozing a deadline or event: `snooze INDEX DAYS`

Postpones a deadline or event task by a number of days.

Example: `snooze 2 3`

Updates the deadline of task 2 by 3 days.
```
Nya! I have snoozed this task:
[E][ ] project meeting (from: Feb 18 2026 to: Feb 22 2026)
```

## Exiting the program `bye`

Exits the application.

Example: `bye`

Closes the application safely.
```
Aww… leaving already? I’ll be here when you’re back, nya~
⠀⠀⠀⠀⢀⡴⣄⠀⠀⠀⠀⢠⣄⠀⠀⠀⠀⠀⠀⠀⣼⣿⡟⠃
⠀⠀⠀⣰⠋⠀⠈⠓⠒⠒⠒⠚⠈⢳⡄⠀⠀⠀⠀⠀⣿⣿
⠀⠀⣼⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣤⣤⣤⣤⣤⣿⣿⣄
⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⠀⠀⠀⠀⠀⠀⠙⣷⡴⠶⣦
⠀⠀⢷⡀⠀⠉⠉⠀⠀⠀⠉⠉⠀⠀⣠⡿⠀⠀⠀⢀⣀⣠⣤⠿⠞⠛⠋
⣠⠾⠋⠙⣶⠤⠤⠤⠤⣤⡤⠤⠤⠞⣠⡴⠶⠚⠋⠉⠁
⠛⠒⠛⠉⠉⠀⠀⠀⣴⠟⢃⡴⠛⠋⠉
⠀⠀⠀⠀⠀⠀⠀⠀⠛⠛⠋
```

---

# Saving the data
Neko Task Manager data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

# Editing the data file
Neko Task Manager data are saved automatically as a JSON file `[JAR file location]/data/neko.txt`. Advanced users are welcome to update data directly by editing that data file.

# Command summary

| Action | Format |
|---|---|
| Add Todo | `todo DESCRIPTION` |
| Add Deadline | `deadline DESCRIPTION /by YYYY-MM-DD` |
| Add Event | `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD` |
| List | `list` |
| Mark | `mark INDEX` |
| Unmark | `unmark INDEX` |
| Delete | `delete INDEX` |
| Find | `find KEYWORD` |
| Snooze | `snooze INDEX DAYS` |
| Exit | `bye` |
