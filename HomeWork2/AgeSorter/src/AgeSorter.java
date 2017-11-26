public class AgeSorter {
    public static void main(String[] args) {
        Integer dimaAge = 35;
        Integer mishaAge = 55;
        Integer vasyaAge = 18;

        Integer oldest = 0;
        Integer youngest = 0;
        Integer middle = 0;

        if(dimaAge > mishaAge){
            if(dimaAge > vasyaAge){
                oldest = dimaAge;
                if(mishaAge > vasyaAge){
                    youngest = vasyaAge;
                    middle = mishaAge;
                } else{
                    youngest = mishaAge;
                    middle = vasyaAge;
                }
            }
        } else if(mishaAge > vasyaAge){
            oldest = mishaAge;
            if(vasyaAge > dimaAge){
                youngest = dimaAge;
                middle = vasyaAge;
            } else {
                youngest = vasyaAge;
                middle = dimaAge;
            }
        }
        System.out.println("Most old: " + oldest);
        System.out.println("Most young: " + youngest);
        System.out.println("Middle: " + middle);
    }

}
