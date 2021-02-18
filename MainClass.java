public class MainClass {
    public static void main (String[] args){
        Generics getMyIntegerArrays = new Generics(100, 150);
        Generics getMyStringArrays = new Generics("This", "is");


        // Generics with String, replace two elements
        getMyStringArrays.showElements();
        getMyStringArrays.replaceElementsArray();
        getMyStringArrays.showElements();

        // Generics with Integers, replace two elements
        getMyIntegerArrays.showElements();
        getMyIntegerArrays.replaceElementsArray();
        getMyIntegerArrays.showElements();

        // from Array to ArrayList, String
        getMyStringArrays.fromArrayToArrayList();
        System.out.println("\n" + getMyStringArrays.getMyArrayList());

        // from Array to ArrayList, Integer
        getMyIntegerArrays.fromArrayToArrayList();
        System.out.println(getMyIntegerArrays.getMyArrayList());
    }
}
