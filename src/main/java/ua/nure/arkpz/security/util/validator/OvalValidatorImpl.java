package ua.nure.arkpz.security.util.validator;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.context.FieldContext;
import net.sf.oval.integration.spring.SpringCheckInitializationListener;
import ua.nure.arkpz.exception.ValidateException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OvalValidatorImpl implements OvalValidator {

    private Validator validator;

    public OvalValidatorImpl() {
        AnnotationsConfigurer configurer = new AnnotationsConfigurer();
        configurer.addCheckInitializationListener(SpringCheckInitializationListener.INSTANCE);
        validator = new Validator(configurer);
    }

    @Override
    public void validate(Object o) throws ValidateException {
        List<ConstraintViolation> violations = validator.validate(o);
        if (!violations.isEmpty()) {
            Map<String, String> errorsMap = new HashMap<>();
            for (ConstraintViolation violation : violations) {
                if (violation.getContext() instanceof FieldContext) {
                    errorsMap.put(((FieldContext) violation.getContext()).getField().getName(), violation.getMessage());
                }
            }
            throw new ValidateException(errorsMap);
        }
    }
}
