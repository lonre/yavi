package am.ik.yavi.hack;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Constraint;
import am.ik.yavi.core.ConstraintPredicate;
import am.ik.yavi.core.CustomConstraint;
import am.ik.yavi.core.NullAs;
import am.ik.yavi.core.ViolationMessage;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class MyValidatorBuilder<T> extends ValidatorBuilder<T> {
  @SuppressWarnings("NullableProblems")
  public static class MyConstraint<T, E> implements Constraint<T, E, MyConstraint<T, E>> {
    private final Deque<ConstraintPredicate<E>> predicates;

    public MyConstraint(Deque<ConstraintPredicate<E>> predicates) {
      this.predicates = predicates;
    }

    @Override
    public MyConstraint<T, E> cast() {
      return this;
    }

    @Override
    public Deque<ConstraintPredicate<E>> predicates() {
      return this.predicates;
    }
  }

  public ValidatorBuilder<T> constraintOnParam(Function<T, ViolationParam> f) {
    Deque<ConstraintPredicate<T>> predicates = new LinkedList<>();
    predicates.add(ConstraintPredicate.withViolatedValue(t -> {
      final ViolationParam result = f.apply(t);
      if (Objects.isNull(result)) {
        return Optional.empty();
      }
      return Optional.of(new ViolatedValueParam(result, t));
    }, ViolationMessage.of("", ""), () -> CustomConstraint.EMPTY_ARRAY, NullAs.INVALID));

    this.constraint(Function.identity(), "", Function.identity(), () -> new MyConstraint<>(predicates));
    return this;
  }
}
