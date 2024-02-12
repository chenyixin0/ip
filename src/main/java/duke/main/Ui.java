package duke.main;

import java.util.Scanner;

/**
 * Represents the user interface that outputs text to users and receives user text
 */



public class Ui {

    private TaskList taskList;
    private Scanner scanner;

    /**
     * constructor for a new UI
     * @param taskList  container for current list of tasks
     */
    public Ui(TaskList taskList) {
        this.taskList = taskList;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints out a welcome message to the user when the program is first launched
     */

    public String printWelcomeMessage() {

        return "Hello! What tasks do you have?";
    }

    /**
     * Prints a confirmation message when a task in the task list has been marked as complete
     *
     * @param index  the index of the task in the task list that has been marked
     */
    public String printOnMark(int index) {
        return "Task marked as done. Good job!\n" + taskList.get(index).toString();
    }

    /**
     * Prints a confirmation message when a task in the task list has been marked as not done
     *
     * @param index  the index of the task in the task list that has been unmarked
     */
    public String printOnUnmark(int index) {
        return "Alright! Task marked as not done\n" + taskList.get(index).toString();
    }

    /**
     * Prints a confirmation message when a new task is added to the task list, then prints the
     * total number of tasks in the task list
     */
    public String printOnAdd() {
        return "Added new task: \n" + taskList.get(taskList.getSize() - 1).toString() + "\n" + printTotal();
    }

    /**
     * Prints a confirmation message when a task at a specified index has been removed from the task list
     *
     * @param index  index of list element to be removed
     */
    public String printOnDelete(int index) {
        return "Removing task from list\n" + taskList.get(index).toString();
    }
    /**
     * Iterates through the task list and prints out each task
     */
    public String printList(TaskList list) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < list.getSize(); i++) {
            out.append(i + 1).append(": ").append(list.get(i).toString()).append("\n");
        }
        return out.toString();
    }

    /**
     * Prints the total number of tasks in the task list
     */
    public String printTotal() {
        return "current number of tasks: " + taskList.getSize();
    }

    /**
     * Prints an exit message when the program is closed
     */

    public String printExitMessage() {
        return "Goodbye!";
    }

    /**
     * Prints the list of found tasks returned after a "find" command has been executed
     * @param list  list of found tasks
     */
    public String printOnFind(TaskList list) {
        return "Here are the matching tasks in your list: \n" + this.printList(list);
    }

    /**
     * Reads user input
     * @return  user input as a String
     */
    public String readLine() {
        return this.scanner.nextLine();
    }

    /**
     * Prints error message when an exception is thrown elsewhere in the program
     * @param exception  exception thrown during program execution
     */
    public String printException(Exception exception) {
        return exception.toString();
    }
}
