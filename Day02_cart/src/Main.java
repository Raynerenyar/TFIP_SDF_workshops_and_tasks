public class Main {
    public static void main(String[] args) {

        ShoppingCartDB cart;
        if (args.length > 0) {
            cart = new ShoppingCartDB(args[0]);
            cart.startShell();
        } else {
            cart = new ShoppingCartDB();
            cart.startShell();
        }
    }
}
