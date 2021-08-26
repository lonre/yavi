package am.ik.yavi.hack;

import am.ik.yavi.core.ViolatedValue;

public class ViolatedValueParam extends ViolatedValue {
  private final ViolationParam violationParam;

  public ViolatedValueParam(ViolationParam violationParam, Object value) {
    super(value);
    this.violationParam = violationParam;
  }

  public ViolationParam violationParam() {
    return violationParam;
  }
}
