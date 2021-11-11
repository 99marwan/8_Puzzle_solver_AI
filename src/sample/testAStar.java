package sample;

public class testAStar {

    public static void main(String[] args) {
        AStar test = new AStar();
//		test.SearchTech("432650781", "012345678");
//		System.out.println("-------------------------------------------------------");
        test.SearchTechEuclidean("125340678", "012345678");
        Node startOfPath = test.getStartOfPath();
        while (startOfPath != null) {
            System.out.println(startOfPath.getState());
            startOfPath = startOfPath.getChild();
        }
//		System.out.println("-------------------------------------------------------");
//		System.out.println("-------------------------------------------------------");
//		test.SearchTech("102345678", "012345678");
//		System.out.println("-------------------------------------------------------");
//		test.SearchTechEuclidean("102345678", "012345678");
//		System.out.println("-------------------------------------------------------");
//		System.out.println("-------------------------------------------------------");
//		//UNSOLVABLE EXAMPLE
//		test.SearchTech("812043765", "012345678");
//		System.out.println("-------------------------------------------------------");
//		test.SearchTechEuclidean("812043765", "012345678");
//		System.out.println("-------------------------------------------------------");
//		System.out.println("-------------------------------------------------------");



    }

}