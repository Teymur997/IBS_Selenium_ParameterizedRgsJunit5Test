package ru.ibs.data;

import ru.ibs.base.BaseTests;

import java.util.Arrays;
import java.util.List;

public class ValueGenerators extends BaseTests {
    public static String randomAdressGenerator() {
        StringBuilder str = new StringBuilder();
        List<String> street = Arrays.asList("проезд 10-й Марьиной Рощи, д 25", "ул Забелина, д 1", "Савёловский проезд, д 8 стр 1", "Щёлковский проезд, д 1А",
                "Оболенский пер, д 3", "Зелёный пр-кт, д 2", "ул Косинская, д 12Б");
        int random = (int) ((Math.random()*street.size())-1);
        str.append("г Москва, ").append(street.get(random));
        return str.toString();
    }
    public static String randomPhoneNumberGenerator() {
        String numbers = "0123456789";
        int length = 9;
        StringBuilder phoneNumber = new StringBuilder();
        phoneNumber.append("9");
        for (int i = 0; i < length; i++) {
            int random = (int) ((Math.random()*length)-1);
            phoneNumber.append(numbers.charAt(random));
        }
        return phoneNumber.toString();
    }

    public static String randomFioGenerator() {
        StringBuilder fio = new StringBuilder();
        List<String> lastName = Arrays.asList("Старостин", "Корнеев", "Малинин", "Игнатьев", "Глушков", "Воронин",
                "Александров", "Зайцев", "Кузнецов", "Романов");
        List<String> firstName = Arrays.asList("Матвей", "Сергей", "Захар", "Михаил", "Александр",
                "Иван", "Дмитрий", "Максим", "Адам", "Тимофей");
        List<String> middleName = Arrays.asList("Денисович", "Тимофеевич", "Сергеевич", "Георгиевич", "Кириллович", "Михайлович",
                "Александрович", "Владиславович", "Маратович", "Степанович");
        int random = (int) ((Math.random()* firstName.size())-1);
        fio.append(lastName.get(random)).append(" ").append(firstName.get(random)).append(" ").append(middleName.get(random));
        return fio.toString();
    }

    public static String randomStringGenerator(int x) {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder str = new StringBuilder();
        int length = x;
        for (int i = 0; i < x; i++) {
            int random = (int)(Math.random()*length-1);
            str.append(letters.charAt(random));
        }
        return str.toString();
    }


}
