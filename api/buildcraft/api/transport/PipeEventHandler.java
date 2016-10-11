package buildcraft.api.transport;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface PipeEventHandler {
    PipeEventPriority priority() default PipeEventPriority.NORMAL;

    boolean receiveCancelled() default false;
}
