package com.takaichi00.sample.quarkus.integration.repository;

import static java.util.Collections.emptyEnumeration;

import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@TargetClass(DriverManager.class)
final class Target_DriverManager { // better than -H:-UseServiceLoaderFeature which breaks other things
    @Substitute // com.github.rmannibucau.h2.Sample.getHikariLikeConnection
    public static Enumeration<Driver> getDrivers() {
        return emptyEnumeration();
    }

    @Substitute // disable autoloading and therefore h2 AutoDriver
    public static synchronized void registerDriver(final java.sql.Driver driver) throws SQLException {
        // no-op
    }
}

public class H2GraalCustomizations {
}
