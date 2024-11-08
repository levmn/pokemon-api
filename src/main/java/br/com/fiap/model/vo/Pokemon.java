package br.com.fiap.model.vo;

public class Pokemon {
    private int id;
    private String name;
    private String types;
    private String abilities;

    public Pokemon() {
    }

    public Pokemon(int id, String name, String types, String abilities) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.abilities = abilities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", types='" + types + '\'' +
                ", abilities='" + abilities + '\'' +
                '}';
    }
}
