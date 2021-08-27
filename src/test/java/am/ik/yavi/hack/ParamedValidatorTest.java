package am.ik.yavi.hack;

import static org.assertj.core.api.Assertions.assertThat;

import am.ik.yavi.User;
import am.ik.yavi.core.ConstraintViolations;
import org.junit.Test;

public class ParamedValidatorTest {
  public static class ParamedUserValidator extends AbstractValidator<User> {
    @Override
    public void validatorBuilder(MyValidatorBuilder<User> builder) {
      builder.constraintOnParam(user -> {
        if (user.getName().length() <= 3) {
          return new ViolationParam("name", "params.validator.User.name.length_invalid", "invalid name length");
        }

        return null;
      });
    }
  }

  @Test
  public void combiningCharacterByteSizeInValid() throws Exception {
    User user = new User("abc", null, null);
    ParamedUserValidator validator = new ParamedUserValidator();
    validator.postConstruct();
    ConstraintViolations violations = validator.validate(user);
    assertThat(violations.isValid()).isFalse();
    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations.get(0).messageKey()).isEqualTo("params.validator.User.name.length_invalid");
    assertThat(violations.get(0).message()).isEqualTo("invalid name length");
  }
}
