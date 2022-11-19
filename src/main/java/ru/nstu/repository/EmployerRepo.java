package ru.nstu.repository;

import ru.nstu.entity.Employer;
import ru.nstu.util.OwnershipType;

import java.util.List;

public class EmployerRepo extends BaseRepo<Employer, Long> {

    @Override
    protected Class<Employer> getType() {
        return Employer.class;
    }

    final static EmployerRepo instance = new EmployerRepo();

    @Override
    protected String getTableName() {
        return "Employer";
    }

    public static EmployerRepo getInstance() {
        return instance;
    }

    public List<Employer> findAllByName(String name) {
        return this.findByStringField("name", name);
    }

    public List<Employer> findAllByPhone(String phone) {
        return this.findByStringField("phone", phone);
    }

    public List<Employer> findAllByOLF(String ownershipType) {
        int numericGender = OwnershipType.valueOf(ownershipType.toUpperCase()).ordinal();
        String stringOrdinal = Integer.toString(numericGender);

        return this.findByStringField("ownershipType", stringOrdinal);
    }
}
