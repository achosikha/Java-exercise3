import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit>{
    private List<T> myFruits = new ArrayList<>();

    void fruitAdd (T fruit)
    {
        myFruits.add(fruit);
    }

    float getBoxWeight()
    {
        if (myFruits.size() > 0)
        {
            return myFruits.size() * myFruits.get(0).getBoxWeight();
        }

        return 0;
    }

    boolean compareBoxes(Box <?> theBox)
    {
        return this.getBoxWeight() == theBox.getBoxWeight();
    }

    void fromBoxToBox (Box <T> transfer)
    {
        if (this == transfer)
        {
            if (transfer == null)
            {
                if (transfer.myFruits.isEmpty())
                {
                    return;
                }
            }
        }
        transfer.myFruits.addAll(myFruits);
        myFruits.clear();
    }

    void fruitAdd (T fruit, int quantity)
    {
        for (int index = 0; index < quantity; index++)
        {
            myFruits.add(fruit);
        }
    }
}
