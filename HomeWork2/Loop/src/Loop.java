public class Loop {
    public static void main(String[] args) {
        /***
         * Do while - условие выполняется после первой итерации. То есть в случае с циклом
         * Do while тело цикла будет выполнено как минмиум один раз, в отличие от цикла
         * while do - где тело цикла может быть не выполнено ни разу
         * */
        Integer firstTicketIndex = 200000;
        Integer lastTicketIndex = 210000;
        Integer currentTicketIndex = firstTicketIndex;
        do {
            System.out.println("Номер билета: " + currentTicketIndex);
            currentTicketIndex++;
        } while (currentTicketIndex <= lastTicketIndex);
    }
}
