package tygri.pujcovna.model;

public enum Role {
    ROLE_ADMIN("ADMIN"), ROLE_USER("USER"), ROLE_VISITOR("EMPLOYEE");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
