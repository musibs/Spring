package io.codefountain.spring.mvc.court.domain;

public class SportType {

    private int id;
    private String name;

    public SportType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SportType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
