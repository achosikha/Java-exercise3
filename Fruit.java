public abstract class Fruit {
    private float fruitWeight;

    Fruit(float fruitWeight)
    {
        this.fruitWeight = fruitWeight;
    }

    float getBoxWeight()
    {
        return fruitWeight;
    }
}
