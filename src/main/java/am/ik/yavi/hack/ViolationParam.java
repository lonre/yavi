package am.ik.yavi.hack;

import am.ik.yavi.core.CustomConstraint;
import java.util.function.Supplier;

public class ViolationParam {
  private final String name;
  private final Supplier<Object[]> args;
  private final String defaultMessageFormat;
  private final String messageKey;

  public ViolationParam(String name, String messageKey) {
    this(name, messageKey, "", () -> CustomConstraint.EMPTY_ARRAY);
  }

  public ViolationParam(String name, String messageKey, String defaultMessageFormat) {
    this(name, messageKey, defaultMessageFormat, () -> CustomConstraint.EMPTY_ARRAY);
  }

  public ViolationParam(String name, String messageKey, String defaultMessageFormat, Supplier<Object[]> args) {
    this.name = name;
    this.args = args;
    this.defaultMessageFormat = defaultMessageFormat;
    this.messageKey = messageKey;
  }

  public String name() {
    return name;
  }

  public Supplier<Object[]> args() {
    return args;
  }

  public String defaultMessageFormat() {
    return defaultMessageFormat;
  }

  public String messageKey() {
    return messageKey;
  }
}
