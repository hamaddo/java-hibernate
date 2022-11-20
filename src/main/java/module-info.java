module ru.nstu.labwork2 {
    requires java.naming;
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;

    opens ru.nstu to javafx.fxml;
    opens ru.nstu.entity to org.hibernate.orm.core, org.hibernate.commons.annotations;

    exports ru.nstu;
    exports ru.nstu.entity;
    exports ru.nstu.repository;
    exports ru.nstu.ui;
    exports ru.nstu.util;

    opens ru.nstu.ui to javafx.fxml;
    opens ru.nstu.util to javafx.fxml;
}