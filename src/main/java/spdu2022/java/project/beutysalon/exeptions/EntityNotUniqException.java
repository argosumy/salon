package spdu2022.java.project.beutysalon.exeptions;

public class EntityNotUniqException extends RuntimeException {
    public EntityNotUniqException(String message) {
        super(message);
    }
}
