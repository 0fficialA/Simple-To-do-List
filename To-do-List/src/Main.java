import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static LinkedList<String> toDoList = new LinkedList<>();
    static Scanner scanner = new Scanner(System.in);
    public static void reviewToDoList(LinkedList<String> toDoList){
        System.out.println("Your To-do List:");
        for (String task : toDoList) {
            System.out.println(task);
        }
    }

    enum ModifyType{
        DELETE, CHECK, UNDO, ADD
    }

    public static int matchTaskInList(String answer, LinkedList<String> toDoList){
        for (int i=0;i<toDoList.size();i++){
            if (answer.equals(toDoList.get(i))){
                return i;
            }
        }
        return -1;
    }
    public static void modifyTaskStatus(String answer, int index, ModifyType modifyType){
        if (index != -1){
            if (modifyType == ModifyType.CHECK){
                toDoList.set(index, "X " + toDoList.get(index));
                System.out.print(answer + " has been checked from the list\n");
            }
            else if (modifyType == ModifyType.UNDO){
                toDoList.set(index, answer);
                System.out.print(answer + " has been deleted from the list\n");
            }
            toDoList.remove(answer);
            System.out.print(answer + " has been deleted from the list\n");
        }
        else {
            System.out.print(answer + " task does not exist in list\n");
        }
    }


    public static void main(String[] args) {
        toDoList.add("Wake up");
        toDoList.add("Brush teeth");
        toDoList.add("Take a shower");
        reviewToDoList(toDoList);
        while (true) {
            System.out.println("Would you like to add, delete, check or undo from your list or review it?");
            String choice = scanner.nextLine();
            String answer;
            ModifyType Modify;
            switch (choice){
                case "add":
                    Modify = ModifyType.ADD;
                    System.out.println("Name what you would like to add");
                    answer = scanner.nextLine();
                    toDoList.add(answer);
                    System.out.println(answer + " has been added");
                    break;
                case "delete":
                    Modify = ModifyType.DELETE;
                    System.out.println("Name what you would like to be delete from the list");
                    answer = scanner.nextLine();
                    modifyTaskStatus(answer, matchTaskInList(answer,toDoList), Modify);
                break;
                case "check":
                    Modify = ModifyType.CHECK;
                    System.out.println("What would like to check off the list?");
                    answer = scanner.nextLine();
                    modifyTaskStatus(answer, matchTaskInList(answer,toDoList), Modify);
                break;
                case "undo":
                    Modify = ModifyType.UNDO;
                    System.out.println("What would like to undo off of the check the list?");
                    answer = scanner.nextLine();
                    modifyTaskStatus(answer, matchTaskInList("X " + answer,toDoList), Modify);
                break;
                case "review":
                    reviewToDoList(toDoList);
                    break;
                default:
                    System.out.println("Incorrect input, only add, delete, check, undo or review can be used");
            }
        }
    }
}