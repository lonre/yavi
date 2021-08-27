package am.ik.yavi.hack;

import am.ik.yavi.core.ConstraintViolations;
import am.ik.yavi.core.Validator;
import java.util.Objects;

public abstract class AbstractValidator<T> {
  protected Validator<T> validator;

  public ConstraintViolations validate(T target) {
    return this.validator.validate(target);
  }

  public void postConstruct() {
    if (Objects.nonNull(this.validator)) {
      return;
    }

    final MyValidatorBuilder<T> builder = new MyValidatorBuilder<>();
    builder.failFast(true);
    validatorBuilder(builder);
    this.validator = builder.build();
  }

  public abstract void validatorBuilder(MyValidatorBuilder<T> builder);
}
