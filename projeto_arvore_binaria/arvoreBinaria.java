public class arvoreBinaria {

    NoArvore root;

    public arvoreBinaria() {
        this.root = null;
    }

    public void InserirNo(int valor) {
        this.root = insertRec(this.root, valor);
    }

    private NoArvore insertRec(NoArvore no, int valor) {
        if (no == null)
            return new NoArvore(valor);

        if (valor < no.valor)
            no.esquerda = insertRec(no.esquerda, valor);
        else if (valor > no.valor)
            no.direita = insertRec(no.direita, valor);

        return no;
    }

    public void EmOrdem() {
        System.out.println("\nValores em ordem crescente:");
        inorderRecAsc(this.root);
        System.out.println("\nValores em ordem decrescente:");
        inorderRecDesc(this.root);
    }

    private void inorderRecAsc(NoArvore no) {
        if (no != null) {
            inorderRecAsc(no.esquerda);
            System.out.print(no.valor + " ");
            inorderRecAsc(no.direita);
        }
    }

    private void inorderRecDesc(NoArvore no) {
        if (no != null) {
            inorderRecDesc(no.direita);
            System.out.print(no.valor + " ");
            inorderRecDesc(no.esquerda);
        }
    }

    public void PreOrdem() {
        System.out.print("Pré-ordem: ");
        preOrdemRec(this.root);
        System.out.println();
    }

    private void preOrdemRec(NoArvore no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            preOrdemRec(no.esquerda);
            preOrdemRec(no.direita);
        }
    }

    public void PostOrdem() {
        System.out.print("Pós-ordem: ");
        postOrderRec(this.root);
        System.out.println();
    }

    private void postOrderRec(NoArvore no) {
        if (no != null) {
            postOrderRec(no.esquerda);
            postOrderRec(no.direita);
            System.out.print(no.valor + " ");
        }
    }

    
    public void delete(int value) {
        this.root = deleteRec(this.root, value);
    }

    private NoArvore deleteRec(NoArvore no, int value) {

        if (no == null) {
            System.out.println("Número não encontrado!");
            return null;
        }

        if (value < no.valor)
            no.esquerda = deleteRec(no.esquerda, value);
        else if (value > no.valor)
            no.direita = deleteRec(no.direita, value);
        else {
            System.out.println("Removendo " + value);

            if (no.esquerda == null)
                return no.direita;
            if (no.direita == null)
                return no.esquerda;

            no.valor = minValue(no.direita);
            no.direita = deleteRec(no.direita, no.valor);
        }

        return no;
    }

    private int minValue(NoArvore no) {
        int minv = no.valor;
        while (no.esquerda != null) {
            minv = no.esquerda.valor;
            no = no.esquerda;
        }
        return minv;
    }
}
