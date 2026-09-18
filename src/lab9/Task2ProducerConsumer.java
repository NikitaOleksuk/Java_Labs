package lab9;

public class Task2ProducerConsumer {

    public static class CircularBuffer<T> {
        private static class Node<E> {
            E value;
            Node<E> next;

            Node(E value) {
                this.value = value;
            }
        }

        private final int capacity;
        private int count = 0;
        private Node<T> head;
        private Node<T> tail;

        public CircularBuffer(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Розмір буфера має бути більше 0");
            }
            this.capacity = capacity;


            Node<T> start = new Node<>(null);
            Node<T> current = start;
            for (int i = 1; i < capacity; i++) {
                Node<T> nextNode = new Node<>(null);
                current.next = nextNode;
                current = nextNode;
            }
            current.next = start;

            this.head = start;
            this.tail = start;
        }

        public synchronized void put(T item) throws InterruptedException {
            while (count == capacity) {
                wait();
            }
            tail.value = item;
            tail = tail.next;
            count++;
            notifyAll();
        }

        public synchronized T take() throws InterruptedException {
            while (count == 0) {
                wait();
            }
            T item = head.value;
            head.value = null;
            head = head.next;
            count--;
            notifyAll();
            return item;
        }
    }

    public static void main(String[] args) {
        CircularBuffer<String> buffer1 = new CircularBuffer<>(15);
        CircularBuffer<String> buffer2 = new CircularBuffer<>(15);


        for (int i = 1; i <= 5; i++) {
            final int producerId = i;
            Thread producer = new Thread(() -> {
                int messageSeq = 1;
                while (true) {
                    try {
                        String msg = "Потік No " + producerId + " згенерував повідомлення " + messageSeq++;
                        buffer1.put(msg);
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
            producer.setDaemon(true);
            producer.start();
        }


        for (int i = 1; i <= 2; i++) {
            final int translatorId = i;
            Thread translator = new Thread(() -> {
                while (true) {
                    try {
                        String incoming = buffer1.take();
                        String translated = "Потік No " + translatorId + " переклав повідомлення [" + incoming + "]";
                        buffer2.put(translated);
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
            translator.setDaemon(true);
            translator.start();
        }


        for (int i = 1; i <= 100; i++) {
            try {
                String result = buffer2.take();
                System.out.printf("[%03d] %s%n", i, result);
            } catch (InterruptedException e) {
                System.err.println("Головний потік було перервано.");
                break;
            }
        }

        System.out.println("Головний потік завершив зчитування 100 повідомлень. Програма завершує роботу.");
    }
}