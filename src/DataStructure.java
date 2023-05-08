public class DataStructure<E> {
    Node<E> end;
    public DataStructure(){
        end = null;
    }
    public void append(E toAppend){
        Node<E> toAdd = new Node<E>(toAppend);
        toAdd.prev = end;
        end = toAdd;
    }
    public E peek(){
        return end.element;
    }
    public E pop(){
        E toReturn = end.element;
        end = end.prev;
        return toReturn;
    }
}
