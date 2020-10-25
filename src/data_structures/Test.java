package data_structures;

public class Test {

	public static void main(String[] args) {
		AVLTree<Integer,String> alv = new AVLTree<>();
		alv.insert(100, "Hola Mundo");
		alv.insert(150, "Hola Mundo");
		alv.insert(200, "Hola Mundo");
		alv.insert(250, "Hola Mundo");
		alv.deleteValue(100);
	}

}
