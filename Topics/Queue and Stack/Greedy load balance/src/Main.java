import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalLoad1 = 0;
        int totalLoad2 = 0;
        Task task;
        Deque<Task> tasks1 = new ArrayDeque<>();
        Deque<Task> tasks2 = new ArrayDeque<>();

        int numberOfTasks = scanner.nextInt();


        for (int i = 0; i < numberOfTasks; i++) {
            int taskNr = scanner.nextInt();
            int taskLoad = scanner.nextInt();
            task = new Task(taskNr, taskLoad);
            if (totalLoad1 <= totalLoad2) {
                tasks1.addLast(task);
                totalLoad1 += taskLoad;
            } else {
                tasks2.addLast(task);
                totalLoad2 += taskLoad;
            }
        }

        for (int i = 0; i < tasks1.size() - 1; i++) {
            task = tasks1.pollFirst();
            System.out.print(task.getTaskNumber() + " ");
        }
        task = tasks1.pollFirst();
        System.out.print(task.getTaskNumber());
        System.out.println();

        for (int i = 0; i < tasks2.size() - 1; i++) {
            task = tasks2.pollFirst();
            System.out.print(task.getTaskNumber() + " ");
        }
        task = tasks2.pollFirst();
        System.out.println(task.getTaskNumber());
    }
}

class Task {
    private int taskNumber;
    private int load;

    public Task(int taskNumber, int load) {
        this.taskNumber = taskNumber;
        this.load = load;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }
}