public class Poto<E>{
    private E[] v;
    private int tail;
    private int head;
    private int size;


    public Poto() {
        this.v = (E[]) new Object[5];
        head = 0;
        tail = -1;
        size = 0;
    }

    // SAMUEL GITANO Y GORDO


    private int increment(int x) {
        x++;
        if (x == v.length) {
            x = 0;
        }
        return x;
    }

 
    private void resize() {
        E[] newV = (E[]) new Object[v.length * 2]; // Incrementa el tamaño
        int i = 0;
        int current = head;

        // Copia los elementos existentes en el nuevo arreglo
        while (i < size) {
            newV[i] = v[current];
            current = increment(current);
            i++;
        }

        v = newV; // Asigna el nuevo arreglo
        head = 0;
        tail = size - 1;
    }

   
}
