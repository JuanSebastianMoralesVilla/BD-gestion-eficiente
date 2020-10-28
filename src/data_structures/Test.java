package data_structures;

public class Test {

	public static void main(String[] args) {
		AVLTree<Integer,String> alv = new AVLTree<>();
		alv.insert(100, "Hola Mundo");
		alv.insert(75, "Hola Mundo");
		alv.insert(50, "Hola Mundo");
		alv.insert(25, "Hola Mundo");
		alv.deleteValue(100);
	}

}
