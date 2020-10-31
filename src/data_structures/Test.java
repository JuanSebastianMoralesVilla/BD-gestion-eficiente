package data_structures;

public class Test {

	public static void main(String[] args) {
		AVLTree<Integer,String> alv = new AVLTree<>();
		alv.insert(31, "31");
		alv.insert(72, "72");
		alv.insert(7, "7");
		alv.insert(5, "5");
		alv.insert(24, "24");
		alv.insert(36, "36");
		alv.insert(41, "41");
		System.out.println(alv.preOrder());
		alv.deleteValue(41);
		System.out.println(alv.preOrder());
		alv.deleteValue(72);
		System.out.println(alv.preOrder());
		alv.deleteValue(7);
		System.out.println(alv.preOrder());
		alv.deleteValue(31);
		System.out.println(alv.preOrder());
		
	}

}
