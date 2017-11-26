
public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        String[] fragments = text.split(",");
        String vasyaIncome, mashaIncome;
        for(String incomes : fragments) {
            System.out.println(incomes.matches());
            System.out.println(incomes);
        }
        vasyaIncome = fragments[0].replaceAll("[^0-9]+", "");
        mashaIncome = fragments[2].replaceAll("[^0-9]+", "");
        System.out.println("vasya income: " + vasyaIncome + "\nMasha income: " + mashaIncome);
    }
}