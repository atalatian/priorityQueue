import java.util.Arrays;

interface queue{
    public void delQueue();
    public void insert(int item);
    public void remove();
    public boolean isEmpty();
    public boolean isFull();
}

class Queue implements queue{
    private int[] arr;
    private int front;
    private int rear;
    private int size;

    public Queue(int size){
        this.arr = new int[size];
        this.front = 0;
        this.rear = 0;
        this.size = size;
    }

    @Override
    public void delQueue() {
        arr = null;
    }

    @Override
    public boolean isFull() {
        if (rear == size){
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if (rear == front){
            return true;
        }
        return false;
    }

    @Override
    public void insert(int item) {
        if (isFull()) {
            System.out.println("Queue is full.");
        }else {
            arr[rear] = item;
            rear++;
        }
    }

    @Override
    public void remove() {
        if (isEmpty()){
            System.out.println("Queue is empty.");
        }else {
            System.out.println(arr[front++]);
        }
    }

    public int getSize() {
        return size;
    }

    public int[] getArr() {
        return arr;
    }
}


class Sort{
    public void oneArrayQuickSort(int[] arr, int left, int right){
        int i = left;
        int j = right;
        int pivot = arr[(left + right)/2];
        int temp;
        while (i<=j){
            while (arr[i] < pivot){
                i++;
            }
            while (arr[j] > pivot){
                j--;
            }
            if (i<=j){
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j--;
                i++;
            }
        }
        if (left < j){
            oneArrayQuickSort(arr, left, j);
        }
        if (right > i){
            oneArrayQuickSort(arr, i, right);
        }
    }
}

public class Question2 {
    public static void main(String[] args) {
        Queue queue1 = new Queue(5);
        queue1.insert(83);
        queue1.insert(52);
        queue1.insert(60);
        queue1.insert(21);
        queue1.insert(77);
        Queue queue2 = new Queue(5);
        queue2.insert(70);
        queue2.insert(9);
        queue2.insert(29);
        queue2.insert(86);
        queue2.insert(84);
        Queue queue3 = new Queue(queue1.getSize()+queue2.getSize());
        Sort sort = new Sort();
        sort.oneArrayQuickSort(queue1.getArr(), 0, (queue1.getSize()-1));
        sort.oneArrayQuickSort(queue2.getArr(), 0, (queue2.getSize()-1));
        System.out.println(Arrays.toString(queue1.getArr()));
        System.out.println(Arrays.toString(queue2.getArr()));
        int[] list = new int[queue1.getSize()+queue2.getSize()];
        for (int i=0; i<queue1.getSize(); i++){
            list[i] = queue1.getArr()[i];
        }
        int adder = queue1.getSize();
        for (int i=0; i<queue2.getSize(); i++){
            list[i+adder] = queue2.getArr()[i];
        }
        sort.oneArrayQuickSort(list, 0, list.length-1);
        for (int i=0; i<list.length; i++){
            queue3.insert(list[i]);
        }
        System.out.println(Arrays.toString(queue3.getArr()));
    }
}
