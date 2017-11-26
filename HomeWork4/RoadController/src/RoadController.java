/**
 * Программа эмулирует работа шлагбаума и камеры, которая считает скорость.
 * Задаются некие граничные условия такие как: максимальная масса и высота для разных типов машин, максимальная скорость,
 * тарифы.
 * Сначала создается 10 объектов типа машина(Car), которым случайным образом присваиваются различные поля:
 * номер, высота, вес, метка спецтранспорта (спецтранспорт или обычная машина), наличие прицепа (есть или нет).
 * Далее, с помощью класса Camera считывается скорость машины, сверяется номер со списком номеров в угоне (список генерируется
 * в классе Police, по сути случайные номера которые в массив помещаются и по этому массиву пробегаемся и проверяем
 * есть ли номер текущей машины в этом списке) [если машина значится в угоне - шлагбаум НЕ открывается и
 * выскакивает сообщение чтобы водитель не двигался и что за ним выехала полицейская машина], далее идет проверка является
 * ли машина спецтранспортом. Если является - шлагбаум открывается (выскакивает сообщение приятного пути) без дальнейших проверок.
 * Далее идет условие выхода из цикла и перехода к другой (следующей) машине если за этой машиной уже выехала полицейская машина (тут
 * смысл в том что нет смысла считать штрафы и прочие нюансы, если машина в угоне). Далее идет проверка на высоту машины.
 * Если она больше допустимой - шлагбаум НЕ открывается и выходит соответствующее сообщение. Далее получаем вес машины.
 * Если он превышает допустимый - то взымается налог, который увеличивается если есть прицеп (для грузовых машин). Далее
 * получаем скорость машины. Если она больше максимально дозволенной - шлагбаум НЕ открывается и вызывается полицейская
 * машина (соответствующее сообщение так же показывается). В противном случае вычислияется штраф (по сути тут есть шаг
 * превышения скорости, то есть за каждый speedFineGrade км/ч превышения платится + finePerGrade Руб).
 * Далее определяем тип машины (грузовая или нет). Определяем по высоте. Если высота больше passengerCarMaxHeight - машина
 * грузовая, если меньше - легковая (для каждого типа свой налог). Плюс дополнительный налет взымается если есть прицеп.
 * В конце выводится собщение о машине (номер, если спецтранспорт то эта информация тоже выводится, номер, высота, масса,
 * скорость, сумма штрафа, результат - открывается ли шлагбаум и причина, если не открывается).
 * */

import core.*;
import core.Camera;

public class RoadController
{
    // Double passengerCarMaxWeight
    public static double passengerCarMaxWeight = 3500.0; // kg
    // Integer passengerCarMaxHeight
    public static int passengerCarMaxHeight = 4000; // mm
    // Integer controllerMaxHeight
    public static int controllerMaxHeight = 3500; // mm
    // Integer passengerCarPrice
    public static int passengerCarPrice = 100; // RUB
    // Integer cargoCarPrice
    public static int cargoCarPrice = 250; // RUB
    // Integer vehicleAdditionalPrice
    public static int vehicleAdditionalPrice = 200; // RUB
    // Integer maxOncomingSpeed
    public static int maxOncomingSpeed = 60; // km/h
    // Integer speedFineGrade
    public static int speedFineGrade = 20; // km/h
    // Integer finePerGrade
    public static int finePerGrade = 500; // RUB
    // Integer criminalSpeed
    public static int criminalSpeed = 160; // km/h

    public static void main(String[] args)
    {
        // Integer i
        for(int i = 0; i < 10; i++)
        {
            // Car car
            Car car = Camera.getNextCar();
            System.out.println(car);
            System.out.println("Скорость: " + Camera.getCarSpeed(car) + " км/ч");


            /**
             * Проверка на наличие номера в списке номеров нарушителей
             */
            // Boolean policeCalled
            boolean policeCalled = false;
            // String criminalNumber
            for(String criminalNumber : Police.getCriminalNumbers())
            {
                // String carNumber
                String carNumber = car.getNumber();
                if(carNumber.equals(criminalNumber)) {
                    Police.call("автомобиль нарушителя с номером " + carNumber);
                    blockWay("не двигайтесь с места! За вами уже выехали!");
                    policeCalled = true;
                    break;
                }
            }

            /**
             * Пропускаем автомобили спецтранспорта после проверки что номер НЕ находится в списке преступников
             */
            if(car.isSpecial() && !policeCalled) {
                openWay();
                continue;
            }

            if(Police.wasCalled()) {
                continue;
            }

            /**
             * Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
             */
            // Integer carHeight
            int carHeight = car.getHeight();
            // Integer price
            int price = 0;
            if(carHeight > controllerMaxHeight)
            {
                blockWay("высота вашего ТС превышает высоту пропускного пункта!");
                continue;
            }
            else if(carHeight > passengerCarMaxHeight)
            {
                // Double weight
                double weight = WeightMeter.getWeight(car);
                //Грузовой автомобиль
                if(weight > passengerCarMaxWeight)
                {
                    price = cargoCarPrice;
                    if(car.hasVehicle()) {
                        price += vehicleAdditionalPrice;
                    }
                }
                //Легковой автомобиль
                else {
                    price = passengerCarPrice;
                }
            }
            else {
                price = passengerCarPrice;
            }

            /**
             * Проверка скорости подъезда и выставление штрафа
             */
            // Integer carSpeed
            int carSpeed = Camera.getCarSpeed(car);
            if(carSpeed > criminalSpeed)
            {
                Police.call("cкорость автомобиля - " + carSpeed + " км/ч, номер - " + car.getNumber());
                blockWay("вы значительно превысили скорость. Ожидайте полицию!");
                continue;
            }
            else if(carSpeed > maxOncomingSpeed)
            {
                // Integer overSpeed
                int overSpeed = carSpeed - maxOncomingSpeed;
                // Integer totalFine
                int totalFine = finePerGrade * (1 + overSpeed / speedFineGrade);
                System.out.println("Вы превысили скорость! Штраф: " + totalFine + " руб.");
                price = price + totalFine;
            }

            /**
             * Отображение суммы к оплате
             */
            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }

    }

    /**
     * Открытие шлагбаума
     */
    public static void openWay()
    {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }
    // String reason
    public static void blockWay(String reason)
    {
        System.out.println("Проезд невозможен: " + reason);
    }
}