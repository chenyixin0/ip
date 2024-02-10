package duke.main;
import duke.exception.*;
import duke.task.*;


/**
 * Represents a parser that interprets user inputs and carries out the interpreted commands
 */
public class Parser {
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for a new Parser object
     * @param taskList  container for new Tasks created by add commands
     * @param ui        interface for printing messages to user
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public boolean isExit(String input) {
        return input.equals("bye");
    }

    /**
     * Method for taking in user input and executing specified commands based on a pre-defined list
     *
     * @param input                   Takes in user input as a String
     * @throws UnknownInputException  Throws exception if input is in list of recognised commands
     */
    public void parse(String input) throws UnknownInputException {
        int taskEnd = input.indexOf(" ");
        String commandType = taskEnd > 0 ? input.substring(0, taskEnd) : "list";
        try {
            TaskType type = TaskType.valueOf(commandType);
            String details = input.substring(taskEnd + 1);

            switch (type) {
            case todo:
                this.taskList.add(new ToDo(details));
                this.ui.printOnAdd();
                break;
            case deadline:
                String[] d = details.split("/by ");
                try {
                    this.taskList.add(new Deadline(d[0], d[1]));
                } catch (DateFormatException dFE) {
                    this.ui.printException(dFE);
                }
                this.ui.printOnAdd();
                break;
            case event:
                String[] v1 = details.split("/from ");
                String[] v2 = v1[1].split("/to ");
                try {
                    this.taskList.add(new Event(v1[0], v2[0], v2[1]));
                } catch (ArrayIndexOutOfBoundsException | DateFormatException formatException) {
                    this.ui.printException(formatException);
                }
                this.ui.printOnAdd();
                break;
            case delete:
                int deleteIndex = Integer.parseInt(details) - 1;
                this.ui.printOnDelete(deleteIndex);
                this.taskList.remove(deleteIndex);
                this.ui.printTotal();
                try {
                    this.ui.printOnDelete(deleteIndex);
                    this.taskList.remove(deleteIndex);
                    this.ui.printTotal();
                } catch (ArrayIndexOutOfBoundsException arrIndexEx) {
                    throw new InvalidIndexException();
                }
                break;
            case mark:
                int markIndex = Integer.parseInt(details) - 1;
                try {
                    this.taskList.mark(markIndex);
                    this.ui.printOnMark(markIndex);
                } catch (ArrayIndexOutOfBoundsException arrEx) {
                    throw new InvalidIndexException();
                }
                break;
            case unmark:
                int unmarkIndex = Integer.parseInt(details) - 1;
                try {
                    this.taskList.unmark(unmarkIndex);
                    this.ui.printOnUnmark(unmarkIndex);
                } catch (ArrayIndexOutOfBoundsException arrException) {
                    throw new InvalidIndexException();
                }
                break;
            case find:
                this.ui.printOnFind(taskList.find(details));
                break;
            case list:
                this.ui.printList(taskList);
                break;
            default:
                throw new UnknownInputException();
            }
        } catch (IllegalArgumentException | InvalidIndexException e) {
            throw new UnknownInputException();
        }
    }
}


