
public class Cat {
    private Double originWeight;
    private Double weight;

    private Double minWeight;
    private Double maxWeight;
    private Integer status;
    private static Integer count = 0;

    private final Integer CAT_DEAD = 0;
    private final Integer CAT_EXPLODED = 1;
    private final Integer CAT_SLEEPING = 2;
    private final Integer CAT_PLAYING = 3;

    private String[] textStatus = new String[4];

    public Cat() {
        Cat.count++;
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        System.out.println("Weight: " + weight + ";originWeight: " + originWeight);
        minWeight = 1000.0;
        maxWeight = 9000.0;
        textStatus[CAT_DEAD] = "Dead";
        textStatus[CAT_EXPLODED] = "Exploded";
        textStatus[CAT_SLEEPING] = "Sleeping";
        textStatus[CAT_PLAYING] = "Playing";
        status = CAT_SLEEPING;
    }

    public Cat(Double weight) {
        this();
        this.weight = weight;
        originWeight = weight;
        minWeight = 100.0;
        maxWeight = 500.0;
    }
    public static Integer getCount() {
        return Cat.count;
    }

    public Double getWeight() { return this.weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public void changeWeight(Double amount) { this.weight += amount; }

    public Cat deepCopy() {
        Cat obj = new Cat();
        obj.weight = this.weight;
        obj.originWeight = this.originWeight;
        obj.minWeight = this.minWeight;
        obj.maxWeight = this.maxWeight;
        return obj;
    }

    public void meow() {
        changeWeight(-1.0);
        updateStatus();
        System.out.println("Meow");
    }

    public void feed(Double amount) {
        changeWeight(amount);
        updateStatus();
    }

    public void drink(Double amount) {
        changeWeight(amount);
        updateStatus();;
    }

    public Double getConsumedFood() {
        if(this.weight < this.originWeight) {
            System.out.println("It seems that Cat didnt have any meal for some time");
            return 0.0;
        } else {
            return (weight - originWeight);
        }
    }

    public void visitToilet() {
        if(this.weight <= minWeight) {
            System.out.println("It was last visit to toilet for this cat ever");
        } else {
            changeWeight(-5.0);
            System.out.println("Weight of cat after toilet: " + this.weight);
        }
        updateStatus();
    }

    public void setStatus(Integer status) { this.status = status; }

    public Integer getStatus() { return this.status; }

    public String getTextStatus() { return this.textStatus[getStatus()]; }

    public boolean isAlive() {
        if(getStatus() <= CAT_EXPLODED ) {
            return true;
        } else {
            return false;
        }
    }

    private void updateStatus() {
        if(weight < minWeight) {
            setStatus(CAT_DEAD);
        }
        else if(weight > maxWeight) {
            setStatus(CAT_EXPLODED);
        }
        else if(weight > originWeight) {
            setStatus(CAT_SLEEPING);
        }
        else {
            setStatus(CAT_PLAYING);
        }
    }
}