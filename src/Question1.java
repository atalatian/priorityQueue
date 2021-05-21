import java.util.Arrays;


interface priorityQueue{
    public void delQueue();
    public void insert(int item, int priority);
    public void remove();
    public boolean isEmpty(int priority);
    public boolean isFull(int priority);
}


class PriorityQueueNormal implements priorityQueue{
    private int[][] arr;
    private int[] front;
    private int[] rear;
    private int row;
    private int column;

    public PriorityQueueNormal(int row, int column){
        this.arr = new int[row][column];
        this.front = new int[6];
        this.rear = new int[6];
        this.row = row;
        this.column = column;
        for (int i=0; i<row; i++){
            front[i] = 0;
            rear[i] = 0;
        }
    }

    @Override
    public void delQueue() {
        for (int i=0; i<row; i++){
            arr[i] = null;
        }
    }

    @Override
    public boolean isFull(int priority) {
        if (rear[priority] == column){
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty(int priority) {
        if (front[priority] == rear[priority]){
            return true;
        }
        return false;
    }

    @Override
    public void insert(int item, int priority) {
        if (isFull(priority)){
            System.out.printf("%d queue is full.\n", priority);
        }else {
            arr[priority][rear[priority]] = item;
            rear[priority]++;
        }
    }

    @Override
    public void remove() {
        boolean empty = true;
        for (int i=0; i<row; i++){
            if (!isEmpty(i)){
                empty = false;
                System.out.println(arr[i][front[i]++]);
                break;
            }
        }
        if (empty){
            System.out.println("All queues are empty.");
        }
    }
}


class PriorityQueueCircular implements priorityQueue{
    private int[][] arr;
    private int[] front;
    private int[] rear;
    private int row;
    private int column;

    public PriorityQueueCircular(int row, int column) {
        this.arr = new int[row][column];
        this.front = new int[6];
        this.rear = new int[6];
        this.row = row;
        this.column = column;
        for (int i=0; i<row; i++){
            front[i] = -1;
            rear[i] = -1;
        }
    }

    @Override
    public void delQueue() {
        for (int i=0; i<row; i++){
            arr[i] = null;
        }
    }

    @Override
    public boolean isFull(int priority) {
        if ((front[priority] == 0 && rear[priority] == column-1) ||
                (rear[priority] == front[priority] - 1 )){
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty(int priority) {
        if (front[priority] == -1){
            return true;
        }
        return false;
    }

    @Override
    public void insert(int item, int priority) {
        if (isFull(priority)){
            System.out.printf("%d queue is full.\n", priority);
        }else if (front[priority] == -1){
            front[priority] = rear[priority] = 0;
            arr[priority][rear[priority]] = item;
        }else if (rear[priority] == column-1 && front[priority] != 0){
            rear[priority] = 0;
            arr[priority][rear[priority]] = item;
        }else {
            rear[priority]++;
            arr[priority][rear[priority]] = item;
        }
    }

    @Override
    public void remove() {
        boolean empty = true;
        for (int i=0; i<row; i++){
            if (!isEmpty(i)){
                empty = false;
                System.out.println(arr[i][front[i]++]);
                break;
            }
        }
        if (empty){
            System.out.println("All queues are empty.");
        }
    }

    public void print(){
        for (int i=0; i<row; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
    }
}



public class Question1 {
    public static void main(String[] args) {
        PriorityQueueNormal priorityQueueNormal =
                new PriorityQueueNormal(6, 3);
        priorityQueueNormal.insert(1, 0);
        priorityQueueNormal.insert(2, 0);
        priorityQueueNormal.insert(3, 1);
        priorityQueueNormal.remove();
        priorityQueueNormal.remove();
        priorityQueueNormal.remove();
        priorityQueueNormal.remove();
        PriorityQueueCircular priorityQueueCircular =
                new PriorityQueueCircular(6, 3);
        priorityQueueCircular.insert(1, 0);
        priorityQueueCircular.insert(2, 0);
        priorityQueueCircular.insert(3, 0);
        priorityQueueCircular.remove();
        priorityQueueCircular.insert(4, 0);
        priorityQueueCircular.insert(4, 2);
        priorityQueueCircular.print();
    }
}
