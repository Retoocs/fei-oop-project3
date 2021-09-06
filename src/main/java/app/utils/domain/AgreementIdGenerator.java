package app.utils.domain;

import java.util.concurrent.atomic.AtomicLong;

public class AgreementIdGenerator {
    private static final AtomicLong generator = new AtomicLong(0);

    public static long newId(){
        return generator.getAndIncrement();
    }
}
