package hwan.board.board_project.Validate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void validate(Object target, Errors errors) {
        try {
            doValidate((T) target, errors);
        } catch (RuntimeException e) {
            log.error("중복 검증 에러", e);
            throw e;
        }
    }

    protected abstract void doValidate(final T dto, final Errors errors);
    
}
