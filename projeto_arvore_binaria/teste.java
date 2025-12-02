public class teste {
    public static void main(String[] args) {
        arvoreBinaria tree = new arvoreBinaria();

        tree.InserirNo(4);
        tree.InserirNo(2);
        tree.InserirNo(6);
        tree.InserirNo(1);
        tree.InserirNo(8);
        tree.InserirNo(9);
        tree.InserirNo(7);
        tree.InserirNo(3);
        tree.InserirNo(5);

        System.out.println("\nOrdem: Esquerda -> Raiz -> Direita");
        tree.EmOrdem();
        System.out.println("\nPré-ordem: Raiz -> Esquerda -> Direita");
        tree.PreOrdem();
        System.out.println("\nPós-ordem (Post-order) -> Ordem: Esquerda -> Direita -> Raiz");
        tree.PostOrdem();
        tree.delete(3);
        tree.EmOrdem();
        tree.delete(10);

        System.out.println();
        System.out.println("Altura da árvore (nós no maior caminho raiz-folha): " + tree.altura());
        System.out.println("Profundidade do nó 7 (arestas da raiz): " + tree.profundidade(7));

        System.out.println("Caminho da raiz até 7: " + tree.caminhoDaRaizA(7));
        System.out.println("Caminho da raiz até 5: " + tree.caminhoDaRaizA(5));
        System.out.println("Caminho entre 1 e 9: " + tree.caminhoEntreNos(1, 9));
        System.out.println("Ancestral comum mais próximo de 7 e 9: " + tree.ancestralComumMaisProximo(7, 9));
    }
}
