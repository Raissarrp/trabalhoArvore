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
        //tree.printRootLeftRight();
        tree.delete(3);
        tree.EmOrdem();
        tree.delete(10);
    }
}
