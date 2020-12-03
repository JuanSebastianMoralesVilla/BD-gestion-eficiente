package Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data_structures.AVLTree;
import data_structures.BinarySearchTree;
import data_structures.Node;
import model.DataBase;

class BSTtreeTest {

	// ------------------------------
	// Associations
	// ------------------------------

	private Node<Integer, String> nodo1;
	private BinarySearchTree<Integer, String> myTree;

	// ------------------------------
	// Setups
	// ------------------------------

	public void setup1() {
		myTree = new BinarySearchTree<Integer, String>();

	}

	public void setup2() {
		myTree = new BinarySearchTree<Integer, String>();
		myTree.insert(4, "04");
		myTree.insert(1, "01");
		myTree.insert(2, "02");

		myTree.insert(7, "07");

		myTree.insert(6, "06");
		myTree.insert(15, "15");
		myTree.insert(14, "14");

	}

	public void setup3() {
		myTree = new BinarySearchTree<Integer, String>();
		myTree.insert(4, "04");
		myTree.insert(2, "02");
		myTree.insert(3, "03");

		myTree.insert(7, "07");

		myTree.insert(15, "15");
		myTree.insert(14, "14");

	}

	// ------------------------------
	// CreateTreeTest
	// ------------------------------

	@Test
	void CreateTreeTest() {
		setup1();
		assertNotNull("El objeto no se creo correctamente", myTree);
	}
	// ------------------------------
	// InsertTest
	// ------------------------------

	@Test
	void insertTest1() {
		setup1();

		myTree.insert(15, "15");
		assertTrue("El nodo no fue insertado correctamente",
				(myTree.getRoot().getKey() == 15) && myTree.getRoot().getElement().equals("15"));
	}

	@Test
	void insertTest2() {
		setup2();
		myTree.insert(7, "07");
		assertTrue("El nodo no se inserto correctamente por la derecha",
				(myTree.getRoot().getRight().getRight().getKey() == 7)
						&& myTree.getRoot().getRight().getRight().getElement().equals("07"));

	}

	@Test
	void inserTest3() {
		setup3();
		myTree.insert(2, "02");
		assertTrue("El nodo no se inserto correctamente por la izquierda",
				(myTree.getRoot().getLeft().getKey() == 2) && myTree.getRoot().getLeft().getElement().equals("02"));

	}

	// ------------------------------
	// Search Test
	// ------------------------------

	@Test
	void searchTest1() {

		setup3();

		String nodeSearch = myTree.searchValue(3);
		assertTrue("El nodo no se encontro en el arbol", (nodeSearch.equals("03")));
	}

	
	@Test
	void searchTest2() {

		setup2();

		assertEquals(null, myTree.searchValue(10), "El nodo no existe ");

	}
	// ------------------------------
	// Delete Test
	// ------------------------------

	@Test
	void deleteTest1() {
		setup2();
		assertTrue(myTree.deleteValue(4), "el nodo de la raiz no fue eliminado");
		assertNull(myTree.searchValue(4));

	}

	@Test
	void deleteTest2() {
		setup2();
		assertTrue(myTree.deleteValue(15), "el nodo no fue eliminado");
		assertNull(myTree.searchValue(15));

	}

	@Test
	void deleteTest3() {
		setup3();
		assertTrue(myTree.deleteValue(3), "el nodo no fue eliminado");
		assertNull(myTree.searchValue(3));

	}

}