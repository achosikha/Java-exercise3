import java.util.ArrayList;

public class Generics <T> {
    private T[] myArray;
    private T interimElement;
    private ArrayList<T> myArrayList = new ArrayList<>();

    Generics (T... myArray)
    {
        this.myArray = myArray;
    }

    void showElements()
    {
        for (T t : myArray) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    void replaceElementsArray()
    {
        // The easiest way to replace two initial elements
        // will not work for more complicated situations
        interimElement = myArray[0];
        myArray[0] = myArray[1];
        myArray[1] = interimElement;
    }

    void fromArrayToArrayList()
    {
        for (int index = 0; index < myArray.length; index++)
        {
            myArrayList.add(index, myArray[index]);
        }
    }

    public ArrayList<T> getMyArrayList() {
        return myArrayList;
    }
}
