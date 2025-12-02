import java.util.ArrayList;
import java.util.List;

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

    // Verifica se um valor existe na árvore
    private boolean containsRec(NoArvore no, int valor) {
        if (no == null)
            return false;
        if (valor == no.valor)
            return true;
        if (valor < no.valor)
            return containsRec(no.esquerda, valor);
        return containsRec(no.direita, valor);
    }

    // Caminho da raiz até um nó (retorna lista de inteiros ou null se não
    // encontrado)
    public List<Integer> caminhoDaRaizA(int valor) {
        List<Integer> path = new ArrayList<>();
        if (caminhoRec(this.root, valor, path))
            return path;
        return null;
    }

    private boolean caminhoRec(NoArvore no, int valor, List<Integer> path) {
        if (no == null)
            return false;
        path.add(no.valor);
        if (no.valor == valor)
            return true;
        if (valor < no.valor) {
            if (caminhoRec(no.esquerda, valor, path))
                return true;
        } else {
            if (caminhoRec(no.direita, valor, path))
                return true;
        }
        // não encontrado neste ramo, remover este nó do caminho
        path.remove(path.size() - 1);
        return false;
    }

    // Caminho entre dois nós distintos (retorna lista ou null se algum não existir)
    public List<Integer> caminhoEntreNos(int a, int b) {
        List<Integer> pa = caminhoDaRaizA(a);
        List<Integer> pb = caminhoDaRaizA(b);
        if (pa == null || pb == null)
            return null;

        int i = 0;
        int min = Math.min(pa.size(), pb.size());
        while (i < min && pa.get(i).equals(pb.get(i)))
            i++;
        int lcaIndex = i - 1; // índice do LCA em ambas as listas

        List<Integer> path = new ArrayList<>();
        // adicionar de 'a' até o LCA (incluir LCA)
        for (int j = pa.size() - 1; j >= lcaIndex; j--) {
            path.add(pa.get(j));
        }
        // adicionar do filho após LCA até 'b'
        for (int j = lcaIndex + 1; j < pb.size(); j++) {
            path.add(pb.get(j));
        }
        return path;
    }

    // Altura da árvore (número de nós no maior caminho raiz-folha)
    public int altura() {
        return alturaRec(this.root);
    }

    private int alturaRec(NoArvore no) {
        if (no == null)
            return 0;
        int le = alturaRec(no.esquerda);
        int ld = alturaRec(no.direita);
        return 1 + Math.max(le, ld);
    }

    // Profundidade (distância em arestas da raiz até o nó; retorna -1 se não
    // encontrar)
    public int profundidade(int valor) {
        return profundidadeRec(this.root, valor, 0);
    }

    private int profundidadeRec(NoArvore no, int valor, int nivel) {
        if (no == null)
            return -1;
        if (no.valor == valor)
            return nivel;
        if (valor < no.valor)
            return profundidadeRec(no.esquerda, valor, nivel + 1);
        return profundidadeRec(no.direita, valor, nivel + 1);
    }

    // Ancestral comum mais próximo (LCA) usando propriedades de BST
    public Integer ancestralComumMaisProximo(int a, int b) {
        if (!containsRec(this.root, a) || !containsRec(this.root, b))
            return null;
        NoArvore lca = lcaRec(this.root, a, b);
        return lca == null ? null : lca.valor;
    }

    private NoArvore lcaRec(NoArvore no, int a, int b) {
        if (no == null)
            return null;
        if (a < no.valor && b < no.valor)
            return lcaRec(no.esquerda, a, b);
        if (a > no.valor && b > no.valor)
            return lcaRec(no.direita, a, b);
        return no;
    }
}
