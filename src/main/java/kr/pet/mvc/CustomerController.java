package kr.pet.mvc;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private List<Customer> customers;
    //진료 기록을 관리하는 컨트롤러
    private MedicalRecordController  recordController;

    public CustomerController(MedicalRecordController recordController){
        this.customers = new ArrayList<>();
        this.recordController=recordController;
    }
    //고객정보를 등록하는 메서드
    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    //고객정보를 삭제하는 메서드(+ 반드시 해당 고객의 진료기록도 함께 삭제)
    //반복문 돌면서 해당 전화번호와 삭제하려는 전화번호를 삭제
    public void removeCustomer(String phoneNumber) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getPhoneNumber().equals(phoneNumber)) {
                customers.remove(i);
                recordController.removeMedicalRecord(phoneNumber); // 해당 고객의 진료 기록 삭제
                break;
            }
        }
    }

    //고객등록 여부를 확인하는 메서드 (고객 검색)
    public Customer findCustomer(String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return null;
    }

    //기존의 전화번호로 등록된 고객이 있는지를 확인하는 메서드 (중복체크)
    public boolean isPhoneNumberExist(String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

}
