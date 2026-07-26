//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
void main() {
    //TIP 캐럿을 강조 표시된 텍스트에 놓고 <shortcut actionId="ShowIntentionActions"/>을(를) 누르면
    // IntelliJ IDEA이(가) 수정을 제안하는 것을 확인할 수 있습니다.


    class Node<E> {
        private E element;       // The data
        private Node<E> next;    // Reference to the next node

        // Constructor: Creates a node with data and a link to the next one
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Getters and Setters
        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setElement(E newElem) {
            element = newElem;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    class SLinkedList<E> {
        protected Node<E> head; // head node of the list
        protected Node<E> tail; // last node of the list (opt.)
        protected long size;
// # of nodes in the list (opt.)

        /**
         * Default ctor that creates an empty list
         */
        public SLinkedList() {
            head = tail = null;
            size = 0;
        }


    }
}
