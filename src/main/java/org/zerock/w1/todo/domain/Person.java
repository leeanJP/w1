package org.zerock.w1.todo.domain;

public class Person {

    //필수 필드
    private final String firstName;
    private final String lastName;

    //선택 필드
    private final int age;
    private final String address;
    private final String phoneNumber;

    //private 생성자 : 빌더를 통해서만 객체 생성 가능
    private Person(PersonBuilder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
    }

    //빌더 클래스
    public static class PersonBuilder {
        private final String firstName;
        private final String lastName;
        private int age;
        private String address;
        private String phoneNumber;

        public PersonBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public PersonBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder withAddr(String address) {
            this.address = address;
            return this;
        }

        public PersonBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
