package tygri.pujcovna.model;

public enum AuthorityType {
    ROLE_ADMIN {
        @Override
        public String toString() {
            return "Administrator";
        }
    },
    ROLE_EMPLOYEE {
        @Override
        public String toString() {
            return "Zamestnanec";
        }
    },
    ROLE_CUSTOMER {
        @Override
        public String toString() {
            return "Zakaznik";
        }
    }

}
