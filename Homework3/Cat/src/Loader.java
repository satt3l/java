public class Loader {
    public static void main(String[] args) {
        Cat cat1 = new Cat(1500.0);
        System.out.println("Overall amount of cats: " + Cat.getCount());
        Cat cat2 = new Cat();
        System.out.println("Overall amount of cats: " + Cat.getCount());
        Cat cat3 = new Cat();
        System.out.println("Overall amount of cats: " + Cat.getCount());
        Cat cat4 = new Cat();
        System.out.println("Overall amount of cats: " + Cat.getCount());
        Cat cat5 = new Cat(4500.0);
        System.out.println("Overall amount of cats: " + Cat.getCount());
        Cat cat6 = getCat();
        System.out.println("Overall amount of cats: " + Cat.getCount());

        System.out.println("Cat1 weight before feeding: " + cat1.getWeight());
        cat1.feed(100.0);
        System.out.println("Cat1 weight after feeding: " + cat1.getWeight());
        System.out.println("Cat1 food consumed: " + cat1.getConsumedFood());

        System.out.println("Cat2 weight: " + cat2.getWeight());
        System.out.println("Cat3 weight: " + cat3.getWeight());
        System.out.println("Cat4 weight: " + cat4.getWeight());
        System.out.println("Cat5 weight: " + cat5.getWeight());
        do {
            cat3.feed(100.0);
            System.out.println("cat3 status: " + cat3.getTextStatus());
        } while(!cat3.isAlive() );
        do {
            cat2.meow();
            System.out.println("cat2 status: " + cat2.getTextStatus());
        } while(!cat2.isAlive());

        System.out.println("cat6 weight is: " + cat6.getWeight());
        System.out.println("cat6 status is: " + cat6.getTextStatus());
        System.out.println("Overall amount of cats: " + Cat.getCount());
        cat6.getTextStatus();
        //Cat cat7 = cat6.deepCopy();
        //System.out.println("Cat7 weight is: " + cat7.getWeight() + "\nCat6 weight is: " + cat6.getWeight());
        //cat7.feed(200.0);
        System.out.println("Cat5 weight before toilet:" + cat5.getWeight());
        cat5.visitToilet();
        //System.out.println("Cat7 weight is: " + cat7.getWeight() + "\nCat6 weight is: " + cat6.getWeight());
        System.out.println("Overall amount of cats: " + Cat.getCount());
    }

    public static Cat getCat() {
        Double weight = 100.0 + Math.random() * 100;
        return new Cat(weight);
    }
}