package vn.edu.tdtu.javatech.lab3;

import vn.edu.tdtu.javatech.lab3.dao.ManufacturerDAO;
import vn.edu.tdtu.javatech.lab3.dao.PhoneDAO;
import vn.edu.tdtu.javatech.lab3.domain.Manufacturer;
import vn.edu.tdtu.javatech.lab3.domain.Phone;

import java.util.List;

public class PhoneFactory {
    private PhoneDAO phoneDao;
    private ManufacturerDAO manufacturerDAO;

    public PhoneFactory() {
        this.phoneDao = new PhoneDAO();
        this.manufacturerDAO = new ManufacturerDAO();
    }

    private void addPhones()
    {
        Phone phone1 = new Phone(1L, "Iphone", "white", "US", 1000.0, 100);
        Phone phone2 = new Phone(2L, "Galaxy", "black", "Korea", 1000.0, 500);
        Phone phone3 = new Phone(3L, "XiaoMi", "black", "China", 500.0, 1000);
        phoneDao.add(phone1);
        phoneDao.add(phone2);
        phoneDao.add(phone3);
    }

    private void addManufactures()
    {
        Manufacturer manufacturer1 = new Manufacturer(1L, "Apple", "Cupertino", 100000);
        Manufacturer manufacturer2 = new Manufacturer(2L, "Samsung", "Suwon-si", 200000);
        Manufacturer manufacturer3 = new Manufacturer(3L, "XiaoMi", "Beijing", 500000);
        manufacturerDAO.add(manufacturer1);
        manufacturerDAO.add(manufacturer2);
        manufacturerDAO.add(manufacturer3);
    }

    private void updatePhones()
    {
        Phone phone1 = phoneDao.get(1L);
        Phone phone2 = phoneDao.get(2L);
        Phone phone3 = phoneDao.get(3L);
        Manufacturer manufacturer1 = manufacturerDAO.get(1L);
        Manufacturer manufacturer2 = manufacturerDAO.get(2L);
        Manufacturer manufacturer3 = manufacturerDAO.get(3L);
        phone1.setManufacturer(manufacturer1);
        phoneDao.update(phone1);
        phone2.setManufacturer(manufacturer2);
        phoneDao.update(phone2);
        phone3.setManufacturer(manufacturer3);
        phoneDao.update(phone3);
    }

    private void initData() {
        addPhones();
        addManufactures();
        updatePhones();
    }

    private void showHighestPricePhone() {
        List<Phone> phoneList = phoneDao.getHighestPricePhones();
        if (phoneList != null) {
            System.out.println("The highest price phones are: ");
            for (Phone phone : phoneList)
                phone.print();
        } else {
            System.out.println("Can not perform the query.");
        }
    }

    private void sortByCountry() {
        List<Phone> phoneList = phoneDao.sortByCountry();
        if (phoneList != null) {
            System.out.println("The phones are sorted by country ascendingly and then by price descendingly are: ");
            for (Phone phone : phoneList)
                phone.print();
        } else {
            System.out.println("Can not perform the query.");
        }
    }

    private void getPhonesHigherThanPrice(Double price) {
        List<Phone> phoneList = phoneDao.getPhonesHigherThanPrice(price);
        if (phoneList != null) {
            System.out.println("The phones are more expensive than $" + price + " are: ");
            for (Phone phone : phoneList)
                phone.print();
        } else {
            System.out.println("Can not perform the query.");
        }
    }

    private void getPhonesWithColorAndPrice(String color, Double price) {
        List<Phone> phoneList = phoneDao.getPhonesWithColorAndPrice(color, price);
        if (phoneList != null) {
            System.out.println("The phones with color " + color + " and are more expensive than $" + price + " are: ");
            for (Phone phone : phoneList)
                phone.print();
        } else {
            System.out.println("Can not perform the query.");
        }
    }

    private void checkManufactureEmployees(Long number) {
        if (manufacturerDAO.allHaveMoreEmployees(number)) {
            System.out.println("All manufactures have more than " + number + " employees");
        } else {
            System.out.println("Some manufacture has less than or equal to " + number + " employees");
        }
    }

    private void findTotalEmployees() {
        System.out.println("The total number of employees is: " + manufacturerDAO.getTotalEmployees());
    }

    private void findManufacturesByLocation(String location) {
        List<Manufacturer> manufacturerList = manufacturerDAO.findManufacturesByLocation(location);
        if (manufacturerList != null) {
            System.out.println("All the manufactures from " + location + " are: ");
            for (Manufacturer manufacturer : manufacturerList)
                manufacturer.print();
        } else {
            System.out.println("Can not perform the query.");
        }
    }

    public static void main(String[] args) {
        PhoneFactory phoneFactory = new PhoneFactory();
        phoneFactory.initData();
        phoneFactory.showHighestPricePhone();
        phoneFactory.sortByCountry();
        phoneFactory.getPhonesHigherThanPrice(500.0);
        phoneFactory.getPhonesWithColorAndPrice("white", 100.0);
        phoneFactory.checkManufactureEmployees(10000L);
        phoneFactory.findTotalEmployees();
        phoneFactory.findManufacturesByLocation("Cupertino");
    }
}
