import java.util.NoSuchElementException;

class LinkedList<E> {

    private int size = 0;
    private Node head;
    private Node tail;

    /**
     * Constructor
     */
    LinkedList() {}

    /**
     * Add elem at index
     * @param index - index of linked list to insert elem at
     * @param elem - element to add
     */
    void add(int index, E elem) throws IndexOutOfBoundsException {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        if (index == size - 1) {
            add(elem);
            return;
        }

        Node node = new Node(elem);
        if (index == 0) {
            node.next = head;
            head = node;
        } else {
            Node prev = head;
            for (int i = 1; i < index; i++) {
                prev = prev.next;
            }
            node.next = prev.next;
            prev.next = node;
        }
        size++;
    }

    /**
     * Add elem to end of list
     * @param elem - element to add
     */
    void add(E elem) {
        Node next = new Node(elem);
        if (head == null) {
            head = next;
            tail = next;
        } else {
            tail.next = next;
        }
        tail = next;
        size++;
    }

    /**
     * Remove last element from element
     * @return the element being removed
     * @throws NoSuchElementException
     */
    E pop() throws NoSuchElementException { 
        if (tail == null) { 
            throw new NoSuchElementException("Cannot pop empty list"); 
        }
        Node prev = head;
        while (prev.next != null && prev.next != tail) {
            prev = prev.next;
        }
        Node tmp = tail;
        tail = prev;
        tail.next = null;
        size--;
        return tmp.value;
    }

    /**
     * Remove the value at index
     * @param index
     * @return the value being removed
     */
    E remove(int index) throws IndexOutOfBoundsException { 

        if (size <= index) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }

        Node node = head;
        Node prev = null;

        for (int i = 0; i < index; i++) {
            prev = node;
            node = node.next;
        }

        // if removing first element
        if (prev == null) {
            head = node.next;
        } else {
            prev.next = node.next;
        }

        // If removing last element
        if (node.next == null) {
            tail = prev;
        }

        size--;
        return node.value;

    }

    /**
     * Remove the first instance of elem
     * @param elem - element to remove from the list
     */
    void remove(E elem) {
        Node node = head;
        Node prev = null;
        while (node != null) {
            // TODO: This might cause issues with strings
            if (node.value == elem) {
                // if removing first element
                if (prev == null) {
                    head = node.next;
                } else {
                    prev.next = node.next;
                }

                // If removing last element
                if (node.next == null) {
                    tail = prev;
                }                
                break;
            }

            prev = node;
            node = node.next;
        }
        return;
    }

    /**
     * Empty the list
     */
    void empty() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Get the value at index
     * @param index
     * @return value of the node at index
     * @throws IndexOutOfBoundsException
     */
    E get(int index) throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        Node tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.value; 
    }

    /**
     * Get size of linked list
     * @return number of items in linked list
     */
    int size() { return size; }        

    private class Node {
        public E value;
        public Node next; 

        Node(E value) {
            this.value = value;
        }
    }

}